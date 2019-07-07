package lexi.ui.swing;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.List;
import javax.swing.JFrame;
import lexi.model.Composition;
import lexi.model.ICompositor;
import lexi.model.Row;
import lexi.model.SimpleCompositor;
import lexi.ui.PositionUtil;
import lexi.util.*;
import lexi.controller.EditorControllerImpl;

public class MainFrame extends JFrame implements ComponentListener {

	private EditorControllerImpl controller;
	private Composition document;
	private ICompositor compositor;

	public MainFrame(Composition document, EditorControllerImpl controller){
		super();		
		this.document = document;
		this.controller = controller;
		this.compositor = new SimpleCompositor();
	}

	@Override
	public void componentResized(ComponentEvent e) {	
		this.controller.handleResize();
		this.repaint(1);
	}
	
	@Override
	public void componentMoved(ComponentEvent e) {	
		this.repaint(1);
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}
	
	@Override
	public void update(Graphics g){
		this.paint(g);
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		int top = PositionUtil.getMainFrameTop(this);
		int left = PositionUtil.getMainFrameLeft(this);
		ViewEventArgs param = new ViewEventArgs(getGraphics(), top, left, this.getWidth(),
				this.getHeight());
		List<Row> rows = this.compositor.compose(this.document.getChildren(), param);
		this.controller.handleDrawing(rows, param);
	}
}
