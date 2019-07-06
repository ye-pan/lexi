package lexi.ui.swing.listener;

import lexi.controller.EditorControllerImpl;
import lexi.ui.Position;
import lexi.ui.swing.MainFrame;
import lexi.util.KeyPressedEventArgs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EditorKeyListener implements KeyListener {

    private static final Logger log = LoggerFactory.getLogger(EditorKeyListener.class);

    private MainFrame frame;

    private EditorControllerImpl controller;

    public EditorKeyListener(MainFrame frame, EditorControllerImpl controller) {
        this.frame = frame;
        this.controller = controller;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        log.debug("{}", e);
        int top = Position.getMainFrameTop(frame);
        int left = Position.getMainFrameLeft(frame);
        KeyPressedEventArgs param = new KeyPressedEventArgs(frame.getGraphics(), top, left, frame.getContentPane().getWidth(),
                frame.getContentPane().getHeight(), e, frame.getFont());
        this.controller.onKeyTyped(param);
        frame.repaint(1);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        log.debug("{}", e);
        int top = Position.getMainFrameTop(frame);
        int left = Position.getMainFrameLeft(frame);
        KeyPressedEventArgs param = new KeyPressedEventArgs(frame.getGraphics(), top, left, frame.getContentPane().getWidth(),
                frame.getContentPane().getHeight(), e, frame.getFont());
        this.controller.onKeyPressed(param);
        frame.repaint(1);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
