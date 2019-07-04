package lexi.ui.listener;

import lexi.controller.EditorController;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveClickListener implements ActionListener  {
    private Component component;
    private EditorController controller;

    public SaveClickListener(Component component, EditorController controller) {
        this.component = component;
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Xml files", "xml");
        fileChooser.setFileFilter(filter);
        if (fileChooser.showSaveDialog(component) == JFileChooser.APPROVE_OPTION) {
            this.controller.onSaveMenuItemClick(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }
}
