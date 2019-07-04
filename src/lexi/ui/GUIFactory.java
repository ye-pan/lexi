package lexi.ui;

import lexi.controller.EditorController;
import lexi.model.Composition;
import lexi.ui.swing.MainFrame;

import javax.swing.*;

public interface GUIFactory {

    JMenuBar createJmenuBar();

    JMenu createJMenu(String title);

    JMenuItem createJMenuItem(String title);

    JMenu createMainMenu(MainFrame frame, EditorController controller, Composition document);

    JMenu createHelpMenu(MainFrame frame);
}
