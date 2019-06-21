package ui;

import java.awt.EventQueue;

import model.Composition;
import controller.EditorController;

public class Application {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {			
					Composition document = new Composition();
					//IEditorController controller = new EditorController(document);
					EditorController controller = new EditorController(document);
					new ui.swing.MainFrame(document, controller);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
