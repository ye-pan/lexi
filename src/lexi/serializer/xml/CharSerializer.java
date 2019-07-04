package lexi.serializer.xml;

import lexi.model.Char;
import org.w3c.dom.*;
import lexi.serializer.GlyphDecoder;
import lexi.serializer.GlyphEncoder;

import java.awt.*;

public class CharSerializer implements GlyphDecoder<Char, Element>, GlyphEncoder<Element, Char> {
    private Document document;
    public CharSerializer(Document document) {
        this.document = document;
    }
    @Override
    public Char decode(Element element) {
        NodeList nlList = element.getElementsByTagName(ElementNames.CONTENT_STRING).item(0).getChildNodes();
        Node nValue = nlList.item(0);
        char content = nValue.getNodeValue().charAt(0);
        Node fontNode = element.getElementsByTagName(ElementNames.FONT_NODE_NAME).item(0);
        Element fontElemtn = (Element) fontNode;
        String fontName = fontElemtn.getAttribute(ElementNames.FONT_NAME_ATTRIBUTE_NAME);
        int style = Integer.parseInt(fontElemtn.getAttribute(ElementNames.FONT_STYLE_ATTRIBUTE_NAME));
        int size = Integer.parseInt(fontElemtn.getAttribute(ElementNames.FONT_SIZE_ATTRIBUTE_NAME));
        Font font = new Font(fontName, style, size);
        return new Char(content, font);
    }

    @Override
    public Element encode(Char ch) {
        Element charElement = document.createElement(ElementNames.CHAR_NODE_NAME);
        Element contentElement = document
                .createElement(ElementNames.CONTENT_STRING);
        contentElement.appendChild(document.createTextNode(Character
                .toString(ch.getChar())));
        charElement.appendChild(contentElement);

        Element fontNameElement = document
                .createElement(ElementNames.FONT_NODE_NAME);


        Attr name = document.createAttribute(ElementNames.FONT_NAME_ATTRIBUTE_NAME);
        name.setValue(ch.getFont().getName());
        fontNameElement.setAttributeNode(name);

        Attr style = document.createAttribute(ElementNames.FONT_STYLE_ATTRIBUTE_NAME);
        style.setValue(Integer.toString(ch.getFont().getStyle()));
        fontNameElement.setAttributeNode(style);

        Attr size = document.createAttribute(ElementNames.FONT_SIZE_ATTRIBUTE_NAME);
        size.setValue(Integer.toString(ch.getFont().getSize()));
        fontNameElement.setAttributeNode(size);

        charElement.appendChild(fontNameElement);
        return charElement;
    }
}
