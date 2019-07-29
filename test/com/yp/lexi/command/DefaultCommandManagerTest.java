package com.yp.lexi.command;

import com.yp.lexi.glyph.Char;
import com.yp.lexi.glyph.Document;
import com.yp.lexi.glyph.Glyph;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class DefaultCommandManagerTest {

    private Document document;

    @Test
    public void testManagerRun() {
        Document document = new Document();
        Command command = new InsertCommand(document, new Char('F', null), document.size());

        DefaultCommandManager manager = new DefaultCommandManager();
        assertFalse(manager.couldRedo());
        assertFalse(manager.couldUndo());

        manager.exec(command);
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
        manager.exec(command);
        assertEquals(2, manager.size());
        assertEquals(1, manager.getCurrent());

        manager.undo();
        assertEquals(0, manager.getCurrent());

        command = new InsertCommand(document, new Char('A', null), document.size());
        manager.exec(command);
        assertEquals(2, manager.size());
    }

    @Test
    public void testInsertCommand() {
        Document document = new Document();
        Glyph glyph = new Char('F', null);
        Command command = new InsertCommand(document, glyph, document.size());
        command.exec();
        assertEquals(1, document.size());
        assertTrue(command.supportUndo());
        command.undo();
        assertEquals(0, document.size());
    }

    @Before
    public void before() {
        document = new Document();
        Font font = new Font(Font.SERIF, Font.BOLD, 14);
        Glyph glyph = new Char('F', font);
        Command command = new InsertCommand(document, glyph, document.size());
        command.exec();
    }

    @Test
    public void testIncrementSizeCommand() {

        Char ch = (Char)document.getGlyphs().get(0);
        int size = ch.fontSize();

        Command command = new IncrementSizeCommand(document);
        assertTrue(command.supportUndo());
        command.exec();
        assertEquals(size + 1, ch.fontSize());
        command.undo();
        assertEquals(size, ch.fontSize());
    }

    @Test
    public void testDecrementSizeCommand() {
        Char ch = (Char)document.getGlyphs().get(0);
        int size = ch.fontSize();

        Command command = new DecrementSizeCommand(document);
        assertTrue(command.supportUndo());
        command.exec();
        assertEquals(size - 1, ch.fontSize());
        command.undo();
        assertEquals(size, ch.fontSize());
    }
}