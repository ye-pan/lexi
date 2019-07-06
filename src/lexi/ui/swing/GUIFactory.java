package lexi.ui.swing;

import lexi.controller.EditorControllerImpl;
import lexi.model.Composition;
import javax.swing.*;

public interface GUIFactory {

    JMenuBar createJmenuBar();

    JMenu createJMenu(String title);

    JMenuItem createJMenuItem(String title);

    JMenu createMainMenu(MainFrame frame, EditorControllerImpl controller, Composition document);

    JMenu createHelpMenu(MainFrame frame);
}
