package ui;

import java.awt.EventQueue;

import model.Composition;
import controller.EditorController;

public class Application {

	public static void main(String[] args) {
		EventQueue.invokeLater(()->{
			try {
				Composition document = new Composition();
				EditorController controller = new EditorController(document);
				new ui.swing.MainFrame(document, controller);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
