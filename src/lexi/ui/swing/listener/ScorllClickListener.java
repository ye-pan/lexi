package lexi.ui.swing.listener;

import lexi.controller.EditorController;
import lexi.util.MenuPressedEventArgs;
import lexi.util.StringUtils;
import lexi.util.i18n.MessageResource;
import lexi.util.i18n.ResourceBundleMessageResource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScorllClickListener implements ActionListener {
    private Component component;
    private EditorController controller;
    private MessageResource message;
    public ScorllClickListener(Component component, EditorController controller) {
        this.component = component;
        this.controller = controller;
        this.message = ResourceBundleMessageResource.getInstance();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem self = (JMenuItem)e.getSource();
        String scrollOffText = message.get("menu.file.items.scroll.off");
        String scrollOnText = message.get("menu.file.items.scroll.on");
        boolean isToScrollOn = StringUtils.equals(self.getText(), scrollOffText);
        this.controller.onMenuItemPressed(new MenuPressedEventArgs() {
            @Override
            public boolean isScrollOn() {
                return isToScrollOn;
            }

            @Override
            public boolean isScrollOff() {
                return !isToScrollOn;
            }
        });
        if (isToScrollOn){
            self.setText(scrollOnText);
        } else{
            self.setText(scrollOffText);
        }
        component.repaint();
    }
}
