package lexi.domain.command;

import org.junit.Test;

import lexi.domain.glyph.Char;
import lexi.domain.glyph.Document;
import lexi.domain.glyph.Glyph;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2019-7-25.
 */
public class InsertCommandTest {

    @Test
    public void testInsertCommand() {
        Document document = new Document();
        Glyph glyph = new Char('F', null);
        Command command = new InsertCommand(document, glyph, document.size());
        command.run();
        assertEquals(1, document.size());
        assertTrue(command.supportUndo());
        command.undo();
        assertEquals(0, document.size());
    }
}