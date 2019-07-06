package lexi.ui;

import java.awt.EventQueue;

import lexi.model.Composition;
import lexi.controller.EditorControllerImpl;

public class Application {

	public static void main(String[] args) {
		EventQueue.invokeLater(()->{
			try {
				Composition document = new Composition();
				EditorControllerImpl controller = new EditorControllerImpl(document);
				new lexi.ui.swing.MainFrame(document, controller);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
