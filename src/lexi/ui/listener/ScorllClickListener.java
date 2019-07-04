package lexi.ui.listener;

import lexi.controller.EditorController;
import lexi.util.Buttons;
import lexi.util.MenuPressedEventArgs;
import lexi.util.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScorllClickListener implements ActionListener {
    private Component component;
    private EditorController controller;
    public ScorllClickListener(Component component, EditorController controller) {
        this.component = component;
        this.controller = controller;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem self = (JMenuItem)e.getSource();
        this.controller.onMenuItemPressed(new MenuPressedEventArgs(self));
        if (StringUtils.equals(self.getText(), Buttons.SCROLL_OFF_TEXT)){
            self.setText(Buttons.SCROLL_ON_TEXT);
        }
        else{
            self.setText(Buttons.SCROLL_OFF_TEXT);
        }

        component.repaint();
    }
}
