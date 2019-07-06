package lexi.ui;

import java.awt.EventQueue;

import lexi.model.Composition;
import lexi.controller.EditorControllerImpl;
import lexi.ui.swing.DefaultGUIFactory;
import lexi.ui.swing.GUIFactory;

import javax.swing.*;

public class Application {

	public static void main(String[] args) {
		EventQueue.invokeLater(()->{
			try {
				Composition document = new Composition();
				EditorControllerImpl controller = new EditorControllerImpl(document);
				GUIFactory factory = DefaultGUIFactory.getInstance();
				JFrame frame = factory.createMainFrame(document, controller);
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
