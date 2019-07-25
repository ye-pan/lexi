package lexi.domain.command;

import lexi.domain.glyph.Document;
import lexi.domain.glyph.Glyph;

public class InsertCommand implements Command {
    private Document document;

    private int physicalIndex;

    private Glyph glyph;

    public InsertCommand(Document document, Glyph glyph, int index) {
        this.document = document;
        this.glyph = glyph;
        this.physicalIndex = index;
    }

    @Override
    public boolean run() {
        document.insert(physicalIndex, glyph);
        return true;
    }

    @Override
    public void undo() {
        document.remove(physicalIndex);
    }

    @Override
    public boolean supportUndo() {
        return true;
    }
}
