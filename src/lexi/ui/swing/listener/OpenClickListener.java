package lexi.ui.swing.listener;

import lexi.controller.EditorController;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenClickListener implements ActionListener {

    private Component component;
    private EditorController controller;

    public OpenClickListener(Component component, EditorController controller) {
        this.component = component;
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("text file", "xml", "txt");
        fileChooser.setFileFilter(filter);
        if (fileChooser.showOpenDialog(component) == JFileChooser.APPROVE_OPTION) {
            this.controller.onLoadMenuItemClick(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }
}
