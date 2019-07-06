package lexi.ui.swing.listener;

import lexi.controller.EditorControllerImpl;
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

    private EditorControllerImpl controller;

    private volatile boolean isSpellCheck;

    private final String spellCheckOnText;
    private final String spellCheckOffText;


    public SpellCheckClickListener(String spellCheckOnText, String spellCheckOffText, boolean isSpellCheck, Component component, EditorControllerImpl controller) {
        this.spellCheckOnText = spellCheckOnText;
        this.spellCheckOffText = spellCheckOffText;
        this.component = component;
        this.controller = controller;
        this.isSpellCheck = isSpellCheck;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JMenuItem self = (JMenuItem) e.getSource();
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
