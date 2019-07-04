package lexi.ui.listener;

import lexi.controller.EditorController;
import lexi.util.InsertImageEventArgs;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertImageClickListener implements ActionListener {
    private static final int TOP_MARGIN = 20;
    private static final int LEFT_MARGIN = 5;
    private JFrame jFrame;
    private EditorController controller;
    private JFileChooser jFileChooser;

    public InsertImageClickListener(JFrame jFrame, EditorController controller) {
        this.jFrame = jFrame;
        this.controller = controller;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(getJFileChooser().showOpenDialog(jFrame) == JFileChooser.APPROVE_OPTION){
            try {
                String fullFilePath = this.getJFileChooser().getSelectedFile().getAbsolutePath();
                InsertImageEventArgs args = new InsertImageEventArgs(jFrame.getGraphics(), getTop(), getLeft(), jFrame.getContentPane().getWidth(),
                        jFrame.getContentPane().getHeight(), fullFilePath);
                this.controller.onImageInserted(args);

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    private JFileChooser getJFileChooser(){
        if (this.jFileChooser == null){
            this.jFileChooser = new JFileChooser();
        }

        return this.jFileChooser;
    }

    private int getLeft(){
        return jFrame.getInsets().left + LEFT_MARGIN;
    }

    private int getTop(){
        return jFrame.getInsets().top + jFrame.getJMenuBar().getHeight() + TOP_MARGIN;
    }
}
