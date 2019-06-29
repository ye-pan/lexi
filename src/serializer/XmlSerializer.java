package serializer;

import model.Char;
import model.Composition;
import model.Glyph;
import model.Picture;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import util.Constants;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;

public class XmlSerializer extends AbstractFileSerializer {
    public final static  String DOCUMENT_ROOT_NOTE_NAME = "document";
    public final static String GLYPHS_NODE_NAME = "glyphs";

    public XmlSerializer(String filePath) {
        super(filePath);
    }

    @Override
    protected void encode(Composition document, File file) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();


            Element rootElement = doc.createElement(DOCUMENT_ROOT_NOTE_NAME);
            doc.appendChild(rootElement);

            Element glyphsElement = doc.createElement(GLYPHS_NODE_NAME);
            rootElement.appendChild(glyphsElement);

            for (Glyph glyph : document.getChildren()) {
                Element glyphElement = glyph.toXmlElement(doc);
                glyphsElement.appendChild(glyphElement);
            }

            TransformerFactory transformerFactory = TransformerFactory
                    .newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(
                    "{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);
        } catch(Exception e) {
            throw new RuntimeException("save to file exception!", e);
        }
    }

    @Override
    protected void decode(Composition document, File file) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder dBuilder = docFactory.newDocumentBuilder();
            FileInputStream stream = new FileInputStream(file);
            Document doc = dBuilder.parse(stream);

            Element docEle = doc.getDocumentElement();

            NodeList nl = docEle.getElementsByTagName(GLYPHS_NODE_NAME);

            Element glyphs = (Element) nl.item(0);

            nl = glyphs.getChildNodes();

            document.getChildren().clear();

            for (int i = 0; i < nl.getLength(); i++) {

                Node nNode = nl.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    if (eElement.getNodeName() == Constants.CHAR_NODE_NAME) {
                        NodeList nlList = eElement.getElementsByTagName(Constants.CONTENT_STRING).item(0).getChildNodes();
                        Node nValue = (Node) nlList.item(0);
                        char content = nValue.getNodeValue().charAt(0);
                        Node fontNode = eElement.getElementsByTagName(Constants.FONT_NODE_NAME).item(0);
                        Element fontElemtn = (Element) fontNode;
                        String fontName = fontElemtn.getAttribute(Constants.FONT_NAME_ATTRIBUTE_NAME);
                        int style = Integer.parseInt(fontElemtn.getAttribute(Constants.FONT_STYLE_ATTRIBUTE_NAME));
                        int size = Integer.parseInt(fontElemtn.getAttribute(Constants.FONT_SIZE_ATTRIBUTE_NAME));
                        Font font = new Font(fontName, style, size);
                        Char ch = new Char(content, font);
                        document.getChildren().add(ch);
                    } else if (eElement.getNodeName() == Constants.PICTURE_NODE_NAME) {
                        Picture pic = new Picture(eElement.getAttribute(Constants.FILE_PATH_ATTRIBUTE_NAME));
                        document.getChildren().add(pic);
                    }
                }
            }

            stream.close();
            document.modelChanged();
        } catch(Exception e) {
            throw new RuntimeException("load from file exception!", e);
        }
    }
}
