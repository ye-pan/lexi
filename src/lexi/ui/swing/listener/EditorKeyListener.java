package lexi.ui.swing.listener;

import lexi.controller.EditorController;
import lexi.ui.PositionUtil;
import lexi.util.KeyPressedEventArgs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EditorKeyListener implements KeyListener {

    private static final Logger log = LoggerFactory.getLogger(EditorKeyListener.class);

    private JFrame frame;

    private EditorController controller;

    public EditorKeyListener(JFrame frame, EditorController controller) {
        this.frame = frame;
        this.controller = controller;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        log.debug("{}", e);
        controller.onKeyTyped(e, frame.getFont());
        frame.repaint(1);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        log.debug("{}", e);
        int top = PositionUtil.getMainFrameTop(frame);
        int left = PositionUtil.getMainFrameLeft(frame);
        KeyPressedEventArgs param = new KeyPressedEventArgs(frame.getGraphics(), top, left, frame.getContentPane().getWidth(),
                frame.getContentPane().getHeight(), e, frame.getFont());
        this.controller.onKeyPressed(param);
        frame.repaint(1);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
