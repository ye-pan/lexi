package ui;

import controller.EditorController;
import model.Composition;
import ui.swing.MainFrame;

import javax.swing.*;

public interface GUIFactory {

    JMenuBar createJmenuBar();

    JMenu createJMenu(String title);

    JMenuItem createJMenuItem(String title);

    JMenu createMainMenu(MainFrame frame, EditorController controller, Composition document);

    JMenu createHelpMenu(MainFrame frame);
}
