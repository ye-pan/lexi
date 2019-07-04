package ui.listener;

import model.Composition;
import ui.swing.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitClickListener implements ActionListener {
    private MainFrame mainFrame;
    private Composition document;

    public ExitClickListener(MainFrame frame, Composition document) {
        this.mainFrame = frame;
        this.document = document;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        document.removeObserver(mainFrame);
        mainFrame.dispose();
    }
}
