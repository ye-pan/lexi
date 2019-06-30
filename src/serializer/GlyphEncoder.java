package serializer;

import model.Glyph;

public interface GlyphEncoder<T, V extends Glyph> {
    T encode(V element);
}
