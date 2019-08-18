package com.yp.lexi;

import java.awt.event.KeyEvent;

public final class KeyEvents {

    public static boolean isIncrementSize(KeyEvent event) {
        return (event.isControlDown() && event.getKeyChar() == '+' && event.getKeyCode() == 107);
    }

    public static boolean isDecrementSize(KeyEvent event) {
        return (event.isControlDown() && event.getKeyChar() == '-' && event.getKeyCode() == 109);
    }

    public static boolean isDelete(KeyEvent event) {
        return (event.getKeyCode() == KeyEvent.VK_DELETE || event.getKeyCode() == KeyEvent.VK_BACK_SPACE);
    }
}
