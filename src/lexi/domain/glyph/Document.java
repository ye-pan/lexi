package lexi.domain.glyph;

import com.google.common.base.Preconditions;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Document implements Glyph {
    private List<Glyph> glyphs;

    public Document() {
        glyphs = new ArrayList<>();
    }

    @Override
    public void draw(Graphics graphics, Point point) {

    }

    @Override
    public void select(Graphics graphics, SelectDraw range) {

    }

    @Override
    public void incrementSize() {
        glyphs.forEach(Glyph::incrementSize);
    }

    @Override
    public void decrementSize() {
        glyphs.forEach(Glyph::decrementSize);
    }

    public void incrementSize(int start, int end) {
        rangeCheck(start, end);
        for(int i = start; i <= end; i++) {
            glyphs.get(i).incrementSize();
        }
    }

    public void decrementSize(int start, int end) {
        rangeCheck(start, end);
        for(int i = start; i <= end; i++) {
            glyphs.get(i).decrementSize();;
        }
    }

    public int size() {
        return glyphs.size();
    }

    public void insert(int index, Glyph glyph) {
        glyphs.add(index, glyph);
    }

    public void remove(int index) {
        glyphs.remove(index);
    }

    private void rangeCheck(int start, int end) {
        Preconditions.checkArgument(start >= 0);
        Preconditions.checkArgument(end >= 0);
        Preconditions.checkArgument((end > start));
    }
}
