package lexi.ui;

import lexi.controller.EditorController;
import lexi.model.Composition;
import lexi.ui.listener.*;
import lexi.ui.swing.MainFrame;
import lexi.util.Buttons;

import javax.swing.*;

public class DefaultGUIFactory implements GUIFactory {
    @Override
    public JMenuBar createJmenuBar() {
        return new JMenuBar();
    }

    @Override
    public JMenu createJMenu(String title) {
        return new JMenu(title);
    }

    @Override
    public JMenuItem createJMenuItem(String title) {
        return new JMenuItem(title);
    }

    public static GUIFactory getInstance() {
        return DefaultGUIFactoryHolder.INSTANCE;
    }

    @Override
    public JMenu createMainMenu(MainFrame frame, EditorController controller, Composition document) {
        JMenu menu = createJMenu("File");
        JMenuItem saveMenuItem = createJMenuItem(Buttons.SAVE_TEXT);
        saveMenuItem.addActionListener(new SaveClickListener(frame, controller));
        menu.add(saveMenuItem);

        JMenuItem openMenuItem = createJMenuItem(Buttons.OPEN_TEXT);
        openMenuItem.addActionListener(new OpenClickListener(frame, controller));
        menu.add(openMenuItem);

        JMenuItem imageMenuItem = createJMenuItem("Insert Image");
        imageMenuItem.addActionListener(new InsertImageClickListener(frame, controller));
        menu.add(imageMenuItem);

        JMenuItem scrollMenuItem = createJMenuItem(Buttons.SCROLL_ON_TEXT);
        scrollMenuItem.addActionListener(new ScorllClickListener(frame, controller));
        menu.add(scrollMenuItem);

        JMenuItem spellCheckMenuItem = createJMenuItem(Buttons.SPELL_CHECK_ON_TEXT);
        spellCheckMenuItem.addActionListener(new SpellCheckClickListener(frame, controller));
        menu.add(spellCheckMenuItem);

        JMenuItem exitMenuItem = createJMenuItem("Exit");
        exitMenuItem.addActionListener(new ExitClickListener(frame, document));
        menu.add(exitMenuItem);

        return menu;
    }

    @Override
    public JMenu createHelpMenu(MainFrame frame) {
        JMenu menu = createJMenu("Help");

        JMenuItem aboutMenuItem = createJMenuItem("About");
        aboutMenuItem.addActionListener(new AboutClickListener(frame));
        menu.add(aboutMenuItem);
        return menu;
    }

    private static class DefaultGUIFactoryHolder {
        static {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");//Nimbus风格，jdk6 update10版本以后的才会出现
                //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//当前系统风格
                //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");//Motif风格，是蓝黑
                //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());//跨平台的Java风格
                //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");//windows风格
                //UIManager.setLookAndFeel("javax.swing.plaf.windows.WindowsLookAndFeel");//windows风格
                //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");//java风格
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        static final DefaultGUIFactory INSTANCE = new DefaultGUIFactory();
    }
}
