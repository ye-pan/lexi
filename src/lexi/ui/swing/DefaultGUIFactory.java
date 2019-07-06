package lexi.ui.swing;

import lexi.controller.EditorControllerImpl;
import lexi.model.Composition;
import lexi.ui.swing.listener.*;
import lexi.util.i18n.MessageResource;
import lexi.util.i18n.ResourceBundleMessageResource;

import javax.swing.*;

public class DefaultGUIFactory implements GUIFactory {

    private MessageResource message;

    public DefaultGUIFactory() {
        this.message = ResourceBundleMessageResource.getInstance();
    }

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
    public JMenu createMainMenu(MainFrame frame, EditorControllerImpl controller, Composition document) {
        JMenu menu = createJMenu(message.get("menu.file"));
        JMenuItem saveMenuItem = createJMenuItem(message.get("menu.file.items.save"));
        saveMenuItem.addActionListener(new SaveClickListener(frame, controller));
        menu.add(saveMenuItem);

        JMenuItem openMenuItem = createJMenuItem(message.get("menu.file.items.open"));
        openMenuItem.addActionListener(new OpenClickListener(frame, controller));
        menu.add(openMenuItem);

        JMenuItem imageMenuItem = createJMenuItem(message.get("menu.file.items.insertImage"));
        imageMenuItem.addActionListener(new InsertImageClickListener(frame, controller));
        menu.add(imageMenuItem);

        JMenuItem scrollMenuItem = createJMenuItem(message.get("menu.file.items.scroll.on"));
        scrollMenuItem.addActionListener(new ScorllClickListener(frame, controller));
        menu.add(scrollMenuItem);

        JMenuItem spellCheckMenuItem = createJMenuItem(message.get("menu.file.items.spellCheck.on"));
        spellCheckMenuItem.addActionListener(new SpellCheckClickListener(frame, controller));
        menu.add(spellCheckMenuItem);

        JMenuItem exitMenuItem = createJMenuItem(message.get("menu.file.items.exit"));
        exitMenuItem.addActionListener(new ExitClickListener(frame, document));
        menu.add(exitMenuItem);

        return menu;
    }

    @Override
    public JMenu createHelpMenu(MainFrame frame) {
        JMenu menu = createJMenu(message.get("menu.help"));

        JMenuItem aboutMenuItem = createJMenuItem(message.get("menu.help.items.about"));
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
