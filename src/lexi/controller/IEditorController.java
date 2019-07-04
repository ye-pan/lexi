package lexi.controller;

import java.util.List;

import lexi.viewmodel.Document;

public interface IEditorController {	
	void onKeyPressed(KeyPressedEventArgs param);	
	void onImageInserted(InsertImageEventArgs param);
	void onMenuItemPressed(MenuPressedEventArgs param);
	void handleDrawing(List<Row> rows, ViewEventArgs param);
	void handleResize();
	Document getLogicalDocument();
}