package lexi.ui.swing;

import lexi.util.ModelChangedEventArgs;
import lexi.util.Observer;

import javax.swing.*;

public class MainFrameObserver implements Observer {
    private JFrame frame;
    public MainFrameObserver(JFrame frame) {
        this.frame = frame;
    }
    @Override
    public void updateObserver(ModelChangedEventArgs args) {
        frame.repaint(1);
    }
}
