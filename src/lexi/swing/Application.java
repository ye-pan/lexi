package lexi.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;
import lexi.domain.Editor;
import lexi.ui.swing.listener.EditorKeyListener;

/**
 * Created by Administrator on 2019-7-25.
 */
public class Application {

    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            Editor editor = new Editor();
            ApplicationFrame frame = new ApplicationFrame(editor);
            frame.setTitle("test");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            int width = 400;
            int height = 400;
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            float x = ((float)dimension.getWidth() - width) / 2;
            float y = ((float)dimension.getHeight() - height) / 2;
            frame.setBounds((int)x, (int)y, width, height);
            frame.setLayout(new BorderLayout());
            frame.addKeyListener(frame);
            frame.setVisible(true);
        });

    }
}
