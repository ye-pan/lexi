package lexi.domain;

import org.junit.Test;

import java.awt.Font;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2019-7-25.
 */
public class EditorTest {

    @Test
    public void testInsertChar() {
        Editor editor = new Editor();
        Font font = new Font(Font.SERIF, Font.BOLD, 16);
        editor.insertChar('F', font);
        assertEquals(1, editor.getDocument().size());
    }
}