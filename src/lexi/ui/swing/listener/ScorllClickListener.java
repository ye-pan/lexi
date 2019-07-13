package lexi.ui.swing.listener;

import lexi.controller.EditorController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScorllClickListener implements ActionListener {
    private final Component component;
    private final EditorController controller;
    private final String scrollOffText;
    private final String scrollOnText;
    private boolean isScrollOn;
    public ScorllClickListener(String scrollOnText, String scrollOffText, boolean isScrollOn, Component component, EditorController controller) {
        this.scrollOffText = scrollOffText;
        this.scrollOnText = scrollOnText;
        this.component = component;
        this.controller = controller;
        this.isScrollOn = isScrollOn;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem clickItem = (JMenuItem)e.getSource();
        isScrollOn = !isScrollOn;
        if (isScrollOn){
            clickItem.setText(scrollOnText);
            controller.scrollOff();
        } else{
            clickItem.setText(scrollOffText);
            controller.scrollOn();
        }
        component.repaint();
    }
}
