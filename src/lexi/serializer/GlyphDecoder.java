package lexi.serializer;

public interface GlyphDecoder<T, V> {
    T decode(V elem);
}
