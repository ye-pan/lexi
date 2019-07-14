package lexi.command;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import lexi.model.Composition;

public class DecreaseFontSizeCommand implements Command {
	private Composition document;
	private int startFrom;
	private int endAt;	
	private List<Font> previousFonts;

	public DecreaseFontSizeCommand(Composition document) {
		this.document = document;
		this.startFrom = 0;
		this.endAt = document.getChildren().size() - 1;
	}
	public DecreaseFontSizeCommand(Composition document, int startFrom, int endAt) {
		this.document = document;
		this.startFrom = startFrom;
		this.endAt = endAt;
		this.loadPreviousFonts();
	}

	@Override
	public boolean execute() {
		List<Font> fonts = new ArrayList<Font>();
		for (int i = this.startFrom; i <= this.endAt; i++) {
			Font previousFont = this.document.getChildren().get(i)
					.getFont();
			Font newFont = new Font(previousFont.getName(),
					previousFont.getStyle(), previousFont.getSize() - 1);
			fonts.add(newFont);
		}
		this.document.updateFont(fonts, startFrom, endAt);
		return true;
	}

	@Override
	public void unExecute() {
		this.document
		.updateFont(this.previousFonts, this.startFrom, this.endAt);
	}

	@Override
	public boolean canUndo() {
		return true;
	}
	
	private void loadPreviousFonts() {
		this.previousFonts = new ArrayList<Font>();
		for (int i = this.startFrom; i <= this.endAt; i++) {
			this.previousFonts
					.add(this.document.getChildren().get(i).getFont());
		}
	}	
}
