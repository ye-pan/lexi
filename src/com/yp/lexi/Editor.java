package com.yp.lexi;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

import com.yp.lexi.command.*;
import com.yp.lexi.glyph.Char;
import com.yp.lexi.glyph.Document;
import com.yp.lexi.glyph.Glyph;

public class Editor {

    private Document document;

    private CommandManager manager;

    public Editor() {
        document = new Document();
    }

    public void insertChar(Character character, Font font) {
        Glyph glyph = new Char(character, font);
        this.insertGlyph(glyph);
    }

    private void insertGlyph(Glyph glyph) {
        int physicalIndex = document.size();
        Command command = new InsertCommand(document, glyph, physicalIndex);
        getManager().exec(command);
    }

    public Document getDocument() {
        return document;
    }

    public void send(Command command) {
        getManager().exec(command);
    }

    public void draw(Graphics graphics, Point start) {
        for (Glyph glyph : document.getGlyphs()) {
            glyph.draw(graphics, start);
            start.x += glyph.getWeight();
        }
    }

    public CommandManager getManager() {
        if(manager == null) {
            manager = new DefaultCommandManager();
        }
        return manager;
    }

    public void function(KeyEvent keyEvent) {
        if(KeyEvents.isIncrementSize(keyEvent)) {
            getManager().exec(new IncrementSizeCommand(document));
        } else if(KeyEvents.isDecrementSize(keyEvent)) {
            getManager().exec(new DecrementSizeCommand(document));
        } else if(KeyEvents.isDelete(keyEvent)) {
            getManager().exec(new DeleteCommand(document));
        }
    }
}
