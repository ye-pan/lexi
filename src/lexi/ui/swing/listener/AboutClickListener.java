package lexi.ui.swing.listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutClickListener implements ActionListener  {

    private Component window;

    public AboutClickListener(Component window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(window, "Lext editor implementation\nDeveloper: Amit Dutta" +
                "\nEmail: adutta@cis.uab.edu\nWeb: http://www.amitdutta.net", "Lexi", JOptionPane.INFORMATION_MESSAGE);
    }
}
