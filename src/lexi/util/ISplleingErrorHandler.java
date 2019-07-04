package lexi.util;

import lexi.viewmodel.UiGlyph;

public interface ISplleingErrorHandler {
	void handleSpellingError(String word, UiGlyph[] glyphs);
}
