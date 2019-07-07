package lexi.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.List;
import lexi.command.*;
import lexi.model.Arrow;
import lexi.model.Char;
import lexi.model.Composition;
import lexi.model.Glyph;
import lexi.model.Picture;
import lexi.model.Row;
import lexi.util.*;
import lexi.viewmodel.ConcreteDocument;
import lexi.viewmodel.Document;
import lexi.viewmodel.ScrollableDocument;
import lexi.viewmodel.SelectionRange;
import lexi.viewmodel.UiGlyph;
import lexi.visitor.Visitor;
import lexi.visitor.SpellingCheckingVisitor;

public class EditorControllerImpl implements EditorController, SplleingErrorHandler {
	
	private Composition document;
	private Document logicalDocument;
	private int index;
	private Boolean spellCheckEnabled;
	private SelectionRange selectionRange;
	private List<UiGlyph[]> misspelledGlyphs;
	private Graphics graphics;
	
	public EditorControllerImpl(Composition document){
		this.index = 0;		
		this.document = document;
		this.logicalDocument = new ConcreteDocument();
		this.spellCheckEnabled = false;
		SpellChecker.getInstance().loadDictionary("./dictionary/american-english");
	}	

	@Override
	public void onKeyPressed(KeyPressedEventArgs param) {
	    //TODO-yepan 期望能够只处理编辑器的功能建
		Glyph glyph;
		Command cmd;
		if (param.getKeyEvent().getKeyCode() == KeyEvent.VK_ESCAPE){
			this.selectionRange = null;
		}
		else if (param.getKeyEvent().getKeyCode() == KeyEvent.VK_DELETE
				|| param.getKeyEvent().getKeyCode() == KeyEvent.VK_BACK_SPACE){
			if (this.selectionRange != null){								
				int startFrom = this.getStartFrom();
				int endAt = this.getEndAt();
				if(endAt == 0 && startFrom > endAt) {
					cmd = new DeleteCommand(document, startFrom - 1, startFrom);
				} else {
					cmd = new DeleteCommand(document, startFrom, endAt);
				}
				CommandManager.getInstance().execute(cmd);
				this.selectionRange = null;
			} else {
				cmd = new DeleteTheLastCommand(document);
				CommandManager.getInstance().execute(cmd);
			}
		}
		else if (param.getKeyEvent().isControlDown() && param.getKeyEvent().getKeyChar() == '+'  && param.getKeyEvent().getKeyCode() == 107) {
			int startFrom = this.getStartFrom();
			int endAt = this.getEndAt();
			cmd = new IncreaseFontSizeCommand(param.getGraphics(), this.document, startFrom, endAt);
			CommandManager.getInstance().execute(cmd);
			
		}
		else if (param.getKeyEvent().isControlDown() && param.getKeyEvent().getKeyChar() == '-'  && param.getKeyEvent().getKeyCode() == 109) {
			int startFrom = this.getStartFrom();
			int endAt = this.getEndAt();
			cmd = new DecreaseFontSizeCommand(param.getGraphics(), this.document, startFrom, endAt);
			CommandManager.getInstance().execute(cmd);
		}		
		else if (param.getKeyEvent().isControlDown() && param.getKeyEvent().getKeyChar() != 'a'  && param.getKeyEvent().getKeyCode() == 65){
			glyph = new Arrow(param.getFont());
			this.document.insert(glyph, this.document.getChildren().size());
		}
		else if (param.getKeyEvent().isControlDown() && param.getKeyEvent().getKeyChar() != 'b'  && param.getKeyEvent().getKeyCode() == 66){
			int startFrom = this.getStartFrom();
			int endAt = this.getEndAt();
			cmd = new ToggleBoldCommand(param.getGraphics(), this.document, startFrom, endAt);
			CommandManager.getInstance().execute(cmd);
		}
		else if (param.getKeyEvent().isControlDown() && param.getKeyEvent().getKeyChar() != 'i'  && param.getKeyEvent().getKeyCode() == 73){
			int startFrom = this.getStartFrom();
			int endAt = this.getEndAt();
			cmd = new ToggleItalicCommand(param.getGraphics(), this.document, startFrom, endAt);
			CommandManager.getInstance().execute(cmd);
		}
		else if (param.getKeyEvent().isControlDown() && param.getKeyEvent().getKeyChar() != 'z'  && param.getKeyEvent().getKeyCode() == 90){
			CommandManager.getInstance().undo();
		}
		else if (param.getKeyEvent().isControlDown() && param.getKeyEvent().getKeyChar() != 'y'  && param.getKeyEvent().getKeyCode() == 89){
			CommandManager.getInstance().redo();
		}
		else if (param.getKeyEvent().getKeyCode() == KeyEvent.VK_PAGE_UP){			
			if (this.logicalDocument.needScrolling(param)){			
				if (index > 0){
					index -= 1;
				}
			}
		}
		else if (param.getKeyEvent().getKeyCode() == KeyEvent.VK_PAGE_DOWN){			
			if (this.logicalDocument.needScrolling(param)){
				if (index < (this.logicalDocument.getRows().size() - 1)){
					index += 1;					
				}
			}			
		}
	}

	public void onKeyTyped(KeyPressedEventArgs param) {
	    //TODO-yepan 期望能够只处理编辑器的内容输入
		if (param.getKeyEvent().getKeyChar() == KeyEvent.VK_DELETE
				|| param.getKeyEvent().getKeyChar() == KeyEvent.VK_BACK_SPACE
				|| (param.getKeyEvent().isControlDown() && param.getKeyEvent().getKeyChar() == '+')
				|| (param.getKeyEvent().isControlDown() && param.getKeyEvent().getKeyChar() == '-')
				|| (param.getKeyEvent().isControlDown() && param.getKeyEvent().getKeyChar() != 'a')
				|| (param.getKeyEvent().isControlDown() && param.getKeyEvent().getKeyChar() != 'b')
				|| (param.getKeyEvent().isControlDown() && param.getKeyEvent().getKeyChar() != 'i')
				|| (param.getKeyEvent().isControlDown() && param.getKeyEvent().getKeyChar() != 'z')
				|| (param.getKeyEvent().isControlDown() && param.getKeyEvent().getKeyChar() != 'y')
				|| (param.getKeyEvent().getKeyChar() == KeyEvent.VK_PAGE_UP)
				|| (param.getKeyEvent().getKeyChar() == KeyEvent.VK_PAGE_DOWN)
				|| (param.getKeyEvent().getKeyChar() == KeyEvent.VK_ESCAPE)){
		//nothings
		} else {
			Glyph glyph = new Char(param.getKeyEvent().getKeyChar(), param.getFont());
			this.insertGlyph(glyph);
			this.selectionRange = null;
		}
	}

	@Override
	public void onImageInserted(InsertImageEventArgs param) {
		Glyph glyph = new Picture(param.getFilePath());
		this.insertGlyph(glyph);
		this.selectionRange = null;
	}
	
	/**
	 * 禁用编辑器检查
	 */
	public void disableSpellCheck() {
		this.spellCheckEnabled = false;
		this.misspelledGlyphs = null;
	}

	/**
	 * 启用编辑器检查
	 */
	public void enableSpellCheck() {
		this.spellCheckEnabled = true;
	}

	/**
	 * 关闭编辑器滚动条
	 */
	public void scrollOff() {
		List<Row> rows = this.logicalDocument.getRows();
		this.logicalDocument = new ConcreteDocument();
		this.logicalDocument.setRows(rows);
		this.index = 0;
	}

	/**
	 * 开启编辑器滚动条
	 */
	public void scrollOn() {
		List<Row> rows = this.logicalDocument.getRows();
		this.logicalDocument = new ScrollableDocument(this.logicalDocument);
		// this.logicalDocument = new BorderedDocument(new ScrollableDocument(this.logicalDocument));
		this.logicalDocument.setRows(rows);
	}

	@Override
	public void handleDrawing(List<Row> rows, ViewEventArgs args){
		this.logicalDocument.draw(rows, args, this.index);
		this.updateLogicalLocations(args);
		if (this.selectionRange != null){
			this.selectGlyphs(args);
		}
		
		if (this.spellCheckEnabled){
			Visitor visitor = new SpellingCheckingVisitor(this);
			for(Row row : rows){
				row.accept(visitor);
			}
		}
	}
	
	@Override
	public void handleSpellingError(String word, UiGlyph[] glyphs) {
		for (UiGlyph uiGlyph : glyphs){
			uiGlyph.getGlyph().select(this.graphics, Color.RED, Color.WHITE, uiGlyph.getPosition().x, uiGlyph.getPosition().y);
		}
	}
	
	public void onSaveMenuItemClick(String filePath){
		Command cmd = new SaveCommand(this.document, filePath);
		CommandManager.getInstance().execute(cmd);
	}
	
	public void onLoadMenuItemClick(String filePath){
		Command cmd;
		if(StringUtils.endsWith(filePath, ".xml")) {
			cmd = new LoadCommand(this.document, filePath);
		} else {
			cmd = new LoadTxtCommand(this.document, filePath);
		}
		CommandManager.getInstance().execute(cmd);
	}
	
	public int getIndex(){
		return this.index;
	}
	
	public int getStartFrom(){
		return this.logicalDocument.getRows().get(this.selectionRange.getStartRow()).getUiGlyphs()
				.get(this.selectionRange.getStartCol()).getPhysicalIndex();
	}
	
	public int getEndAt(){
		return this.logicalDocument.getRows().get(this.selectionRange.getEndRow()).getUiGlyphs()
		.get(this.selectionRange.getEndCol()).getPhysicalIndex();
	}
	
	public void setSelectionRange(SelectionRange selectionRange){
		if (selectionRange != null){
			this.selectionRange = selectionRange;
		}
	}
	
	public void selectGlyphs(ViewEventArgs args){
		int start, end;		
		for (int i = this.selectionRange.getStartRow(); i <= this.selectionRange.getEndRow(); i++){
			Row row = this.logicalDocument.getRows().get(i);
			start = 0;
			end = row.getUiGlyphs().size() - 1;
			if (i == this.selectionRange.getStartRow()){
				start = this.selectionRange.getStartCol();
			}
			
			if (i == this.selectionRange.getEndRow()){
				end = this.selectionRange.getEndCol();
			}
			
			row.select(args.getGraphics(), Color.BLACK, Color.WHITE, row.getTop(), row.getLeft(), start, end);
		}
	}
	
	/*update locations of Uiglyphs. required for selection to work in scrolling environment*/
	public void updateLogicalLocations(ViewEventArgs args){
		int i, j;
		Point dummyPoint = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);
		for (i = 0; i < this.index; i++){
			Row currentRow = this.logicalDocument.getRows().get(i);
			currentRow.setTop(Integer.MIN_VALUE);
			currentRow.setLeft(Integer.MIN_VALUE);
			for (UiGlyph uiGlyph : currentRow.getUiGlyphs()){
				uiGlyph.setPosition(dummyPoint);
			}
		}
				
		/* calculate points exactly like the compositor */
		int currentTop = args.getTop();
		int currentLeft = args.getLeft();
		for (j = i; j < this.logicalDocument.getRows().size(); j++){
			Row currentRow = this.logicalDocument.getRows().get(j);
			currentRow.setTop(currentTop);
			currentRow.setLeft(currentLeft);
			for (UiGlyph uiGlyph : currentRow.getUiGlyphs()){
				Point position = new Point(currentLeft, currentTop);
				uiGlyph.setPosition(position);
				currentLeft += uiGlyph.getGlyph().getWidth() + 2;
			}
			
			currentTop += currentRow.getHeight();
			currentLeft = args.getLeft();
		}
	}
	
	@Override
	public Document getLogicalDocument(){		
		return this.logicalDocument;
	}
	
	@Override
	public void handleResize(){
		this.selectionRange = null;
		this.index = 0;
	}
	
	private void insertGlyph(Glyph glyph){
		Command cmd = null;
		int physicalIndex = Integer.MIN_VALUE;
		if (this.selectionRange == null){
			/* Add at the end of document*/
			physicalIndex = this.document.getChildren().size();
		}
		else if (this.selectionRange != null && this.selectionRange.isSingleGlyphSelection()){
			physicalIndex = this.logicalDocument.getRows().get(this.selectionRange.getStartRow()).getUiGlyphs()
					.get(this.selectionRange.getStartCol()).getPhysicalIndex() + 1 ;			
		}
		
		if (physicalIndex != Integer.MIN_VALUE){			
			cmd = new InsertCommand(this.document, glyph, physicalIndex);				
			CommandManager.getInstance().execute(cmd);
			this.selectionRange = null;
		}
	}
	
	public void setGraphics(Graphics graphics){
		this.graphics = graphics;
	}
}