package lexi.ui;

import java.awt.EventQueue;

import lexi.model.Composition;
import lexi.controller.EditorController;

public class Application {

	public static void main(String[] args) {
		EventQueue.invokeLater(()->{
			try {
				Composition document = new Composition();
				EditorController controller = new EditorController(document);
				new lexi.ui.swing.MainFrame(document, controller);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
