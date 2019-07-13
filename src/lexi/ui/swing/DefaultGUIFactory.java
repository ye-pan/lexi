package lexi.ui.swing;

import lexi.controller.EditorController;
import lexi.model.Composition;
import lexi.ui.Position;
import lexi.ui.swing.listener.*;
import lexi.util.i18n.MessageResource;
import lexi.util.i18n.ResourceBundleMessageResource;

import javax.swing.*;
import java.awt.*;

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
    public JMenu createMainMenu(MainFrame frame, EditorController controller, Composition document) {
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

        String scrollOnText = message.get("menu.file.items.scroll.on");
        String scrollOffText = message.get("menu.file.items.scroll.off");
        JMenuItem scrollMenuItem = createJMenuItem(scrollOnText);
        scrollMenuItem.addActionListener(new ScorllClickListener(scrollOnText, scrollOffText, false, frame, controller));
        menu.add(scrollMenuItem);

        String spellCheckOnText = message.get("menu.file.items.spellCheck.on");
        String spellCheckOffText = message.get("menu.file.items.spellCheck.off");
        JMenuItem spellCheckMenuItem = createJMenuItem(spellCheckOnText);
        spellCheckMenuItem.addActionListener(new SpellCheckClickListener(spellCheckOnText, spellCheckOffText, false, frame, controller));
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
        String title = message.get("software.title");
        String content = message.get("menu.help.items.about.content");
        aboutMenuItem.addActionListener(new AboutClickListener(title, content, frame));
        menu.add(aboutMenuItem);
        return menu;
    }

    @Override
    public JFrame createMainFrame(Composition document, EditorController controller) {
        MainFrame frame = new MainFrame(document, controller);
        String title = message.get("software.title");
        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Position position = defaultPosition();
        frame.setBounds((int)position.getX(), (int)position.getY(), (int)position.getWidth(), (int)position.getHeight());
        frame.setLayout(new BorderLayout());

        JMenuBar menuBar = createJmenuBar();
        frame.setJMenuBar(menuBar);

        JMenu menu = createMainMenu(frame, controller, document);
        menuBar.add(menu);

        JMenu helpMenu = createHelpMenu(frame);
        menuBar.add(helpMenu);

        frame.addKeyListener(new EditorKeyListener(frame, controller));
        frame.addComponentListener(frame);
        frame.addWindowListener(new EditorWindowListener(document));
        frame.addMouseListener(new EditorMouseListener(frame, controller));

        Graphics graphics = frame.getGraphics();
        controller.setGraphics(graphics);

        document.removeObserver(new MainFrameObserver(frame));
        return frame;
    }

    /**
     * 获取默认窗体位置及大小
     * @return
     */
    Position defaultPosition() {
        int width = 400;
        int height = 400;
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        float x = ((float)dimension.getWidth() - width) / 2;
        float y = ((float)dimension.getHeight() - height) / 2;
        return new Position(x, y, width, height);
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
