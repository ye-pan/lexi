package lexi.domain.command;

import org.junit.Before;
import org.junit.Test;

import lexi.domain.glyph.Char;
import lexi.domain.glyph.Document;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2019-7-25.
 */
public class CommandManagerTest {
    private Document document;

    @Before
    public void before() {
        document = new Document();
    }

    @Test
    public void testManagerRun() {
        Command command = new InsertCommand(document, new Char('F', null), document.size());

        CommandManager manager = new CommandManager();
        assertFalse(manager.couldRedo());
        assertFalse(manager.couldUndo());

        manager.run(command);
        assertEquals(1, document.size());
        assertTrue(manager.couldUndo());
        assertEquals(1, manager.size());
        assertEquals(0, manager.getCurrent());

        manager.undo();
        assertTrue(manager.couldRedo());
        assertEquals(0, document.size());
        assertEquals(1, manager.size());
        assertEquals(-1, manager.getCurrent());

        manager.redo();
        assertTrue(manager.couldUndo());
        assertEquals(1, document.size());
        assertEquals(0, manager.getCurrent());

        command = new InsertCommand(document, new Char('T', null), document.size());
        manager.run(command);
        assertEquals(2, manager.size());
        assertEquals(1, manager.getCurrent());

        manager.undo();
        assertEquals(0, manager.getCurrent());

        command = new InsertCommand(document, new Char('A', null), document.size());
        manager.run(command);
        assertEquals(2, manager.size());
    }

}