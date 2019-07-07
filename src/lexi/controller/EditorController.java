package lexi.controller;

import java.util.List;

import lexi.model.Row;
import lexi.util.InsertImageEventArgs;
import lexi.util.KeyPressedEventArgs;
import lexi.util.ViewEventArgs;
import lexi.viewmodel.Document;

public interface EditorController {
	void onKeyPressed(KeyPressedEventArgs param);
	void onImageInserted(InsertImageEventArgs param);
	void handleDrawing(List<Row> rows, ViewEventArgs param);
	void handleResize();
	Document getLogicalDocument();
}