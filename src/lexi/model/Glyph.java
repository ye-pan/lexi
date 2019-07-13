package lexi.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import lexi.visitor.Visitor;

public interface Glyph {

	void draw(Graphics graphics, int x, int y);

	void select(Graphics graphics, Color hightlightColor,
			Color fontColor, int x, int y);

	int getWidth();

	int getHeight();

	default Font getFont() {
		return null;
	}
	
	default void setFont(Font font) {

	}

	void accept(Visitor visitor);
}
