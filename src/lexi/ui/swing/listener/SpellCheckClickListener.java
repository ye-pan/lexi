package lexi.ui.swing.listener;

import lexi.controller.EditorControllerImpl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpellCheckClickListener implements ActionListener {

    private Component component;

    private EditorControllerImpl controller;

    private boolean isSpellCheck;

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
        JMenuItem clickItem = (JMenuItem) e.getSource();
        isSpellCheck = !isSpellCheck;
        if (isSpellCheck) {
            clickItem.setText(spellCheckOffText);
            controller.disableSpellCheck();
        } else {
            clickItem.setText(spellCheckOnText);
            controller.enableSpellCheck();
        }
        component.repaint();
    }
}
