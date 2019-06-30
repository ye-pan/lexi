package serializer.xml;

import model.Char;
import model.Composition;
import model.Glyph;
import model.Picture;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import serializer.AbstractFileSerializer;
import util.StringUtils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.InputStream;
import java.io.OutputStream;

public class DocumentSerializer extends AbstractFileSerializer {
    public DocumentSerializer(String filePath) {
        super(filePath);
    }

    @Override
    protected void encode(Composition document, OutputStream out) throws Exception {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();


        Element rootElement = doc.createElement(ElementNames.DOCUMENT_ROOT_NOTE_NAME);
        doc.appendChild(rootElement);

        Element glyphsElement = doc.createElement(ElementNames.GLYPHS_NODE_NAME);
        rootElement.appendChild(glyphsElement);

        CharSerializer charSerializer = new CharSerializer(doc);
        PictureSerializer pictureSerializer = new PictureSerializer(doc);

        for (Glyph glyph : document.getChildren()) {
            Element element = null;
            if(glyph instanceof  Char) {
                element = charSerializer.encode((Char)glyph);
            } else if(glyph instanceof Picture) {
                element = pictureSerializer.encode((Picture)glyph);
            }
            if(element != null) {
                glyphsElement.appendChild(element);
            }
        }

        TransformerFactory transformerFactory = TransformerFactory
                .newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(
                "{http://xml.apache.org/xslt}indent-amount", "2");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(out);
        transformer.transform(source, result);
    }

    @Override
    protected void decode(Composition document, InputStream in) throws Exception {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder dBuilder = docFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(in);

        Element docEle = doc.getDocumentElement();

        NodeList nl = docEle.getElementsByTagName(ElementNames.GLYPHS_NODE_NAME);

        Element glyphs = (Element) nl.item(0);

        nl = glyphs.getChildNodes();

        document.getChildren().clear();

        CharSerializer charSerializer = new CharSerializer(doc);
        PictureSerializer pictureSerializer = new PictureSerializer(doc);

        for (int i = 0; i < nl.getLength(); i++) {
            Node nNode = nl.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                if (StringUtils.equals(eElement.getNodeName(), ElementNames.CHAR_NODE_NAME)) {
                    Char ch = charSerializer.decode(eElement);
                    document.getChildren().add(ch);
                } else if (StringUtils.equals(eElement.getNodeName(), ElementNames.PICTURE_NODE_NAME)) {
                    Picture pic = pictureSerializer.decode(eElement);
                    document.getChildren().add(pic);
                }
            }
        }
        document.modelChanged();
    }
}
