package lexi.ui.swing.listener;

import lexi.model.Composition;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class EditorWindowListener implements WindowListener {
    private Composition document;

    public EditorWindowListener(Composition document) {
        this.document = document;
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        document.destory();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
