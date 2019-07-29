package com.yp.lexi.glyph;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class DocumentTest {
    @Test
    public void testAddAndRemove() {
        Document document = new Document();
        Font font = new Font(Font.SERIF, Font.BOLD, 14);
        document.insert(document.size(), new Char('F', font));
        assertEquals(1, document.size());

        document.remove(document.size() - 1);
        assertEquals(0, document.size());
    }
    @Test
    public void testAdjustSize() {
        Document document = new Document();
        Font font = new Font(Font.SERIF, Font.BOLD, 14);
        document.insert(document.size(), new Char('F', font));
        document.incrementSize();
        assertEquals(15, ((Char)(document.getGlyphs().get(0))).fontSize());

        document.decrementSize();
        assertEquals(14, ((Char)(document.getGlyphs().get(0))).fontSize());

        document.incrementSize(0, 0);
        assertEquals(15, ((Char)(document.getGlyphs().get(0))).fontSize());

        document.decrementSize(0, 0);
        assertEquals(14, ((Char)(document.getGlyphs().get(0))).fontSize());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testIllegalRange() {
        Document document = new Document();
        document.incrementSize(-1, 0);
    }
}