package lexi.util;

import lexi.viewmodel.UiGlyph;

public interface SplleingErrorHandler {
	void handleSpellingError(String word, UiGlyph[] glyphs);
}
