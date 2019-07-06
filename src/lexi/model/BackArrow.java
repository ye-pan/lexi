package lexi.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import lexi.visitor.Visitor;

public class BackArrow implements Glyph {

	private String str;
	private Font font;
	private FontMetrics fontmetrics;

	public BackArrow(Font font) {
		this.font = font;
		this.str = " <-- ";
	}

	public BackArrow(String str, Font font) {
		this.str = str;
		this.font = font;
	}

	@Override
	public void draw(Graphics graphics, int x, int y) {
		if (this.fontmetrics == null) {
			this.fontmetrics = graphics.getFontMetrics(this.font);
		}

		// TODO set the font before drawing
		graphics.drawString(this.str, x, y);
	}

	@Override
	public void select(Graphics graphics, Color hightlightColor,
			Color fontColor, int x, int y) {
	}

	@Override
	public int getWidth() {
		int width = 0;
		if (this.fontmetrics != null) {
			width = this.fontmetrics.stringWidth(this.str);
		}

		return width;
	}

	@Override
	public int getHeight() {
		int height = 0;
		if (this.fontmetrics != null) {
			height = this.fontmetrics.getHeight();
		}

		return height;
	}

	@Override
	public String toString() {
		return "Back Arrow Glyph: [" + this.str + "]";
	}

	@Override
	public void accept(Visitor visitor) {
	}

	@Override
	public void setFont(Font font) {
		this.font = font;
	}

	@Override
	public Font getFont() {
		return this.font;
	}
}
