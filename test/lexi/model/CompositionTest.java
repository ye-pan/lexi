package lexi.model;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class CompositionTest {

    @Test
    public void testDocumentObject() {
        Composition document = new Composition();
        document.insert(new Char('F', Font.getFont("宋体")), document.getChildren().size());
    }

}