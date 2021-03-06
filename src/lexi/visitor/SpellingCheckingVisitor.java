package lexi.visitor;

import java.util.ArrayList;
import java.util.List;

import lexi.model.Char;
import lexi.model.Glyph;
import lexi.model.Picture;
import lexi.model.Row;
import lexi.util.SplleingErrorHandler;
import lexi.util.SpellChecker;
import lexi.viewmodel.UiGlyph;

public class SpellingCheckingVisitor implements Visitor {

	private StringBuffer currentWord;
	private List<Glyph> currentGlyphs;
	private List<UiGlyph> uiGlyphs;
	private SplleingErrorHandler spllingErrorHandler;

	public SpellingCheckingVisitor() {
		this.currentWord = new StringBuffer();
		this.currentGlyphs = new ArrayList<Glyph>();
		this.uiGlyphs = new ArrayList<UiGlyph>();
	}

	public SpellingCheckingVisitor(SplleingErrorHandler spllingErrorHandler) {
		this();
		this.spllingErrorHandler = spllingErrorHandler;
	}

	@Override
	public void visitChar(Char character) {
		if (Character.isAlphabetic(character.getCharacterCode())
				|| Character.isDigit(character.getCharacterCode())) {
			this.currentWord.append(character.getChar());
			this.currentGlyphs.add(character);
		} else {
			this.uiGlyphs.remove(this.uiGlyphs.size() - 1);
			this.spellCheck();
			this.uiGlyphs.clear();
		}
	}

	private void spellCheck() {
		String word = this.currentWord.toString();
		if (!word.equals("")
				&& SpellChecker.getInstance().isMisspelled(word)) {			
			if (this.spllingErrorHandler != null) {
				this.spllingErrorHandler
						.handleSpellingError(this.currentWord.toString(),
								this.uiGlyphs
										.toArray(new UiGlyph[this.uiGlyphs
												.size()]));
			}
		}

		this.currentWord = new StringBuffer();
		this.currentGlyphs.clear();
	}

	@Override
	public void visitPicture(Picture picture) {
	}

	@Override
	public void visitRow(Row row) {
		List<UiGlyph> glyphs = row.getUiGlyphs();
		for (UiGlyph uiGlyph : glyphs) {
			this.uiGlyphs.add(uiGlyph);
			uiGlyph.getGlyph().accept(this);
		}

		/* This checking is required for last word. */
		if (this.currentWord.length() > 0) {
			this.spellCheck();
		}
	}
}
