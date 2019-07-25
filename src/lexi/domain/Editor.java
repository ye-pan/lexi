package lexi.domain;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import lexi.domain.command.Command;
import lexi.domain.command.CommandManager;
import lexi.domain.command.InsertCommand;
import lexi.domain.glyph.Char;
import lexi.domain.glyph.Document;
import lexi.domain.glyph.Glyph;

public class Editor {

    private Document document;

    private CommandManager manager;

    public Editor() {
        document = new Document();
        manager = new CommandManager();
    }

    public void insertChar(Character character, Font font) {
        Glyph glyph = new Char(character, font);
        this.insertGlyph(glyph);
    }

    private void insertGlyph(Glyph glyph) {
        int physicalIndex = document.size();
        Command command = new InsertCommand(document, glyph, physicalIndex);
        manager.run(command);
    }

    public Document getDocument() {
        return document;
    }

    public void send(Command command) {
        manager.run(command);
    }

    public void draw(Graphics graphics, Point start) {
        for (Glyph glyph : document.getGlyphs()) {
            glyph.draw(graphics, start);
            start.x += glyph.getWeight();
        }
    }
}
