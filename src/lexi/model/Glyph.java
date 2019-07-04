package lexi.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import lexi.visitor.IVisitor;

public interface Glyph {

	void draw(Graphics graphics, int x, int y);

	void select(Graphics graphics, Color hightlightColor,
			Color fontColor, int x, int y);

	int getWidth();

	int getHeight();

	Font getFont();
	
	void setFont(Font font);

	void accept(IVisitor visitor);
}
