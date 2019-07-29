package com.yp.lexi.command;

import com.yp.lexi.glyph.Document;
import com.yp.lexi.glyph.Glyph;

public class DecrementSizeCommand implements Command {

    private Document document;

    public DecrementSizeCommand(Document document) {
        this.document = document;
    }

    @Override
    public boolean exec() {
        document.getGlyphs().forEach(Glyph::decrementSize);
        return true;
    }

    @Override
    public void undo() {
        document.getGlyphs().forEach(Glyph::incrementSize);
    }

    @Override
    public boolean supportUndo() {
        return true;
    }
}
