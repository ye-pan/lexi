package com.yp.lexi.command;

import com.yp.lexi.glyph.Document;
import com.yp.lexi.glyph.Glyph;

public class IncrementSizeCommand implements Command {

    private Document document;

    public IncrementSizeCommand(Document document) {
        this.document = document;
    }

    @Override
    public boolean exec() {
        document.getGlyphs().forEach(Glyph::incrementSize);
        return true;
    }

    @Override
    public void undo() {
        document.getGlyphs().forEach(Glyph::decrementSize);
    }

    @Override
    public boolean supportUndo() {
        return true;
    }
}
