package com.yp.lexi.glyph;

import java.awt.Graphics;
import java.awt.Point;

public interface Glyph {

    void draw(Graphics graphics, Point point);

    void select(Graphics graphics, SelectDraw range);

    void incrementSize();

    void decrementSize();

    int getWeight();

    int getHeight();
}
