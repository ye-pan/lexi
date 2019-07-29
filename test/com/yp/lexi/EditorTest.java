package com.yp.lexi;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class EditorTest {
    @Test
    public void testInsertChar() {
        Editor editor = new Editor();
        Font font = new Font(Font.SERIF, Font.BOLD, 16);
        editor.insertChar('F', font);
        assertEquals(1, editor.getDocument().size());
    }
}