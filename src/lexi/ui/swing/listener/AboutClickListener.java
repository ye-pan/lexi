package lexi.ui.swing.listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutClickListener implements ActionListener  {

    private final Component window;

    private final String content;

    private final String title;

    public AboutClickListener(String title, String content, Component window) {
        this.content = content;
        this.title = title;
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(window, content, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
