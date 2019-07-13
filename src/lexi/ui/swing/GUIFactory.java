package lexi.ui.swing;

import lexi.controller.EditorController;
import lexi.model.Composition;
import javax.swing.*;

/**
 * 界面显示元素工厂类
 */
public interface GUIFactory {

    /**
     * 创建菜单栏
     * @return
     */
    JMenuBar createJmenuBar();

    /**
     * 创建菜单
     * @param title
     * @return
     */
    JMenu createJMenu(String title);

    /**
     * 创建菜单项
     * @param title
     * @return
     */
    JMenuItem createJMenuItem(String title);

    /**
     * 创建窗口主菜单
     * @param frame
     * @param controller
     * @param document
     * @return
     */
    JMenu createMainMenu(MainFrame frame, EditorController controller, Composition document);

    /**
     * 创建窗口帮助菜单
     * @param frame
     * @return
     */
    JMenu createHelpMenu(MainFrame frame);

    /**
     * 创建主窗口
     * @param document
     * @param controller
     * @return
     */
    JFrame createMainFrame(Composition document, EditorController controller);
}
