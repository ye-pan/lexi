package com.yp.lexi.glyph;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class Char implements Glyph {
    private Character character;

    private Font font;

    private FontMetrics fontMetrics;

    public Char(Character character, Font font) {
        this.character = character;
        this.font = font;
    }
    @Override
    public void draw(Graphics graphics, Point point) {
        fontMetrics = graphics.getFontMetrics(font);
        graphics.setFont(font);
        graphics.drawString(character.toString(), point.x, point.y);
    }

    @Override
    public void select(Graphics graphics, SelectDraw range) {
        Color previous = graphics.getColor();
        graphics.setColor(range.getHightLightColor());
        Rectangle2D rectangle = graphics.getFontMetrics().getStringBounds(character.toString(), graphics);
        int y = range.getPoint().y - (int)rectangle.getHeight();
        graphics.drawRect(range.getPoint().x, y, (int)rectangle.getWidth(), (int)rectangle.getHeight());
        graphics.fillRect(range.getPoint().x, y, (int)rectangle.getWidth(), (int)rectangle.getHeight());
        graphics.setColor(range.getFontColor());
        draw(graphics, range.getPoint());
        graphics.setColor(previous);
    }

    @Override
    public void incrementSize() {
        int size = font.getSize();
        font = font.deriveFont(((float)(size + 1)));
    }

    @Override
    public void decrementSize() {
        int size = font.getSize();
        font = font.deriveFont(((float)(size - 1)));
    }

    public int fontSize() {
        return font.getSize();
    }
    
    public Character getCharacter() {
        return character;
    }

    @Override
    public String toString() {
        return character.toString();
    }

    @Override
    public int getWeight() {
        int weight = 0;
        if(fontMetrics != null) {
            weight = fontMetrics.charWidth(character);
        }
        return weight;
    }

    @Override
    public int getHeight() {
        int height = 0;
        if(fontMetrics != null) {
            height = fontMetrics.getHeight();
        }
        return height;
    }
}
