package com.yp.lexi.command;

import com.yp.lexi.glyph.Document;
import com.yp.lexi.glyph.Glyph;

import java.util.List;

public class DeleteCommand implements Command {
    private final Document document;
    private final int start;
    private final int end;
    private List<Glyph> buffer;
    public DeleteCommand(Document document) {
        this.document = document;
        start = document.size() - 1;
        end = start;

    }

    @Override
    public boolean exec() {
        if(start == end) {
            Glyph single = document.remove(end);
            buffer = List.of(single);
        } else {
            throw new UnsupportedOperationException();
        }
        return true;
    }

    @Override
    public void undo() {
        if(start == end) {
            document.insert(end, buffer);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public boolean supportUndo() {
        return true;
    }
}
