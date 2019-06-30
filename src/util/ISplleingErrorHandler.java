package util;

import viewmodel.UiGlyph;

public interface ISplleingErrorHandler {
	void handleSpellingError(String word, UiGlyph[] glyphs);
}
