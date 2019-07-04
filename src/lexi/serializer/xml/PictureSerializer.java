package lexi.serializer.xml;

import lexi.model.Picture;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import lexi.serializer.GlyphCoder;


public class PictureSerializer implements GlyphCoder<Element, Picture> {
    private Document document;
    public PictureSerializer(Document document) {
        this.document = document;
    }
    @Override
    public Picture decode(Element element) {
        String filePath = element.getAttribute(ElementNames.FILE_PATH_ATTRIBUTE_NAME);
        return new Picture(filePath);
    }

    @Override
    public Element encode(Picture element) {
        Element picElement = document.createElement(ElementNames.PICTURE_NODE_NAME);
        Attr path = document.createAttribute(ElementNames.FILE_PATH_ATTRIBUTE_NAME);
        path.setValue(element.getFullFilePath());
        picElement.setAttributeNode(path);
        return picElement;
    }
}
