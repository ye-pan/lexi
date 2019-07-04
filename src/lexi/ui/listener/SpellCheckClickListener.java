package lexi.ui.listener;

import lexi.controller.EditorController;
import lexi.util.MenuPressedEventArgs;
import lexi.util.SpellChecker;
import lexi.util.i18n.MessageResource;
import lexi.util.i18n.ResourceBundleMessageResource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpellCheckClickListener implements ActionListener {

    static {
        SpellChecker.getInstance().loadDictionary("./dictionary/american-english");
    }

    private Component component;

    private EditorController controller;

    private MessageResource message;

    private volatile boolean isSpellCheck;

    public SpellCheckClickListener(Component component, EditorController controller) {
        this.component = component;
        this.controller = controller;
        this.message = ResourceBundleMessageResource.getInstance();
        this.isSpellCheck = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JMenuItem self = (JMenuItem) e.getSource();
        String spellCheckOnText = message.get("menu.file.items.spellCheck.on");
        String spellCheckOffText = message.get("menu.file.items.spellCheck.off");
        isSpellCheck = !isSpellCheck;
        this.controller.onMenuItemPressed(new MenuPressedEventArgs() {
            @Override
            public boolean isSpellCheckOff() {
                return !isSpellCheck;
            }

            @Override
            public boolean isSpellCheckOn() {
                return isSpellCheck;
            }
        });
        if (isSpellCheck) {
            self.setText(spellCheckOffText);
        } else {
            self.setText(spellCheckOnText);
        }

        component.repaint();
    }
}
