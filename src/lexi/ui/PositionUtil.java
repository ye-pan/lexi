package lexi.ui;

import javax.swing.*;

public class PositionUtil {
    private static final int TOP_MARGIN = 20;
    private static final int LEFT_MARGIN = 5;

    private PositionUtil() {
        throw new UnsupportedOperationException();
    }


    public static int getMainFrameLeft(JFrame frame) {
        return frame.getInsets().left + LEFT_MARGIN;
    }

    public static int getMainFrameTop(JFrame frame) {
        return frame.getInsets().top + frame.getJMenuBar().getHeight() + TOP_MARGIN;
    }
}
