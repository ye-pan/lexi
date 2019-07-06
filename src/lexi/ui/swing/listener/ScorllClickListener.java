package lexi.ui.swing.listener;

import lexi.controller.EditorController;
import lexi.util.MenuPressedEventArgs;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScorllClickListener implements ActionListener {
    private final Component component;
    private final EditorController controller;
    private final String scrollOffText;
    private final String scrollOnText;
    private volatile boolean isScrollOn;
    public ScorllClickListener(String scrollOnText, String scrollOffText, boolean isScrollOn, Component component, EditorController controller) {
        this.scrollOffText = scrollOffText;
        this.scrollOnText = scrollOnText;
        this.component = component;
        this.controller = controller;
        this.isScrollOn = isScrollOn;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem self = (JMenuItem)e.getSource();
        isScrollOn = !isScrollOn;
        this.controller.onMenuItemPressed(new MenuPressedEventArgs() {
            @Override
            public boolean isScrollOn() {
                return isScrollOn;
            }

            @Override
            public boolean isScrollOff() {
                return !isScrollOn;
            }
        });
        if (isScrollOn){
            self.setText(scrollOnText);
        } else{
            self.setText(scrollOffText);
        }
        component.repaint();
    }
}
