package lexi.serializer;

import lexi.model.Glyph;

public interface GlyphCoder<T, V extends Glyph> {
    T encode(V element);
    V decode(T element);
}
