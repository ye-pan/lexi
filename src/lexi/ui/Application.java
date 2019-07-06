package lexi.ui;

import java.awt.EventQueue;

import lexi.model.Composition;
import lexi.controller.EditorControllerImpl;
import lexi.ui.swing.DefaultGUIFactory;
import lexi.ui.swing.GUIFactory;

public class Application {

	public static void main(String[] args) {
		EventQueue.invokeLater(()->{
			try {
				Composition document = new Composition();
				EditorControllerImpl controller = new EditorControllerImpl(document);
				GUIFactory guiFactory = DefaultGUIFactory.getInstance();
				new lexi.ui.swing.MainFrame(document, controller, guiFactory);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
