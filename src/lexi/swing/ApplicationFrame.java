package lexi.swing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import lexi.domain.Editor;

/**
 * Created by Administrator on 2019-7-25.
 */
public class ApplicationFrame extends JFrame implements KeyListener {

    private static final Logger log = LoggerFactory.getLogger(ApplicationFrame.class);

    private Editor editor;

    public ApplicationFrame(Editor editor) {
        this.editor = editor;
    }
    @Override
    public void keyTyped(KeyEvent keyEvent) {
        editor.insertChar(keyEvent.getKeyChar(), getFont());
        repaint(1);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Point start = new Point();
        start.x = getInsets().left;
        start.y = getInsets().top;
        editor.draw(getGraphics(), start);
    }
}
