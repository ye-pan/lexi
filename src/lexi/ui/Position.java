package lexi.ui;

import javax.swing.*;

public class Position {
    private static final int TOP_MARGIN = 20;
    private static final int LEFT_MARGIN = 5;

    private final float x;
    private final float y;
    private final float  width;
    private final float height;
    public Position(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public static int getMainFrameLeft(JFrame frame) {
        return frame.getInsets().left + LEFT_MARGIN;
    }

    public static int getMainFrameTop(JFrame frame) {
        return frame.getInsets().top + frame.getJMenuBar().getHeight() + TOP_MARGIN;
    }
}
