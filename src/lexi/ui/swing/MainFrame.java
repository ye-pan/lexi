package lexi.ui.swing;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import lexi.model.Composition;
import lexi.model.ICompositor;
import lexi.model.Row;
import lexi.model.SimpleCompositor;
import lexi.ui.DefaultGUIFactory;
import lexi.ui.GUIFactory;
import lexi.util.*;
import lexi.viewmodel.SelectionRange;
import lexi.viewmodel.UiGlyph;
import lexi.controller.EditorController;

public class MainFrame extends JFrame implements lexi.ui.IMainFrame, KeyListener, ComponentListener, IObserver, WindowListener, MouseListener{
		
	private static final int TOP_MARGIN = 20;
	private static final int LEFT_MARGIN = 5;
	private Graphics graphics;
	private EditorController controller;
	private Composition document;
	private ICompositor compositor;
	private int x1, y1, x2, y2;

	private GUIFactory guiFactory;
	
	public MainFrame(Composition document, EditorController controller){		
		super();		
		
		this.document = document;
		this.controller = controller;
		this.document.registerObserver(this);
		this.compositor = new SimpleCompositor();
		this.guiFactory = DefaultGUIFactory.getInstance();
		
		this.setTitle("Lexi");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//窗口定位到屏幕正中央
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 400;
		int height = 400;
		int x = (int)(dimension.getWidth() - width) / 2;
		int y = (int)(dimension.getHeight() - height) / 2;

		this.setBounds(x, y, width, height);
		this.setLayout(new BorderLayout());
		
		JMenuBar menuBar = guiFactory.createJmenuBar();
		this.setJMenuBar(menuBar);
		
		JMenu mnFile = guiFactory.createMainMenu(this, controller, document);
		menuBar.add(mnFile);

		JMenu mnHelp = guiFactory.createHelpMenu(this);
		menuBar.add(mnHelp);
		
		this.addKeyListener(this);
		this.addComponentListener(this);
		this.addWindowListener(this);
		this.addMouseListener(this);
		
		this.setVisible(true);		
		
		this.x1 = this.y1 = -10;
		this.x2 = this.y2 = -20;
		
		this.graphics = this.getGraphics();
		this.controller.setGraphics(graphics);
	}

	@Override
	public void componentResized(ComponentEvent e) {	
		this.controller.handleResize();
		this.repaint(1);
	}
	
	@Override
	public void componentMoved(ComponentEvent e) {	
		/*this.lexi.controller.handleResize(); */
		this.repaint(1);
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}	
	
	@Override
	public void keyPressed(KeyEvent e) {		
			KeyPressedEventArgs param = new KeyPressedEventArgs(this.graphics, this.getTop(), this.getLeft(), this.getContentPane().getWidth(),
					this.getContentPane().getHeight(), e, this.getFont());
			this.controller.onKeyPressed(param);
			this.repaint(1);			
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
	
	@Override
	public void updateObserver(ModelChangedEventArgs args) {
		this.repaint(1);
	}	
	
	@Override
	public void update(Graphics g){
		this.paint(g);
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		ViewEventArgs param = new ViewEventArgs(this.graphics, this.getTop(), this.getLeft(), this.getWidth(),
				this.getHeight());
		List<Row> rows = this.compositor.compose(this.document.getChildren(), param);
		this.controller.handleDrawing(rows, param);
	}
	

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		this.document.removeObserver(this);		
	}

	@Override
	public void windowClosed(WindowEvent e) {		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
	}
	
	private int getLeft(){
		return this.getInsets().left + LEFT_MARGIN;
	}

	private int getTop(){
		return this.getInsets().top + this.getJMenuBar().getHeight() + TOP_MARGIN;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		x1 = e.getX();
		y1 = e.getY();			
	}
	
	/* Return 1 if p1 is greater than p2, 0 if p1 and p2 are equal
	 * and -1 if p1 is smaller than p2 */
	public static int isGreater(Point p1, Point p2){
		int i = 0;
		if (p1.x < p2.x){
			i = -1;
		}
		else if (p1.x > p2.x){
			i = 1;
		}
		else if (p1.y < p2.y){
			i = -1;
		}
		else if (p1.y > p2.y){
			i = 1;
		}	
		
		return i;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {		
		x2 = e.getX();
		y2 = e.getY();		
		Point p1 = new Point(x1, y1);
		Point p2 = new Point(x2, y2);		
		if (isGreater(p1, p2) == 1){
			// p1 is bigger
			Point temp = p1;
			p1 = p2;
			p2 = temp;
		}		
		
		// p2 needs to be greater than or equal p1		
		x1 = p1.x;
		y1 = p1.y;
		x2 = p2.x;
		y2 = p2.y;
		
		int i, j;
		i = j = 0;
		List<Row> rows = this.controller.getLogicalDocument().getRows();
		for (i = 0 + this.controller.getIndex(); i < rows.size(); i++){
			Row row = rows.get(i);
			if (row.getTop() >= y1){
				for (j = 0; j < row.getUiGlyphs().size(); j++){
					UiGlyph glyph = row.getUiGlyphs().get(j);
					// System.out.println("x: " + x1 + " pointx: " + glyph.getPosition().x);
					if (glyph.getPosition().x >= x1){
						break;
					}
				}
				
				break;
			}
		}		
		
		SelectionRange range = new SelectionRange();
		j = j == 0 ? j : j - 1;
		range.setStartRow(i);
		range.setStartCol(j);
		//System.out.println("Start - Row:" + i + " Col: " + j);
		
		for (i = 0 + this.controller.getIndex(); i < rows.size(); i++){
			Row row = rows.get(i);
			if (row.getTop() >= y2){
				for (j = 0; j < row.getUiGlyphs().size(); j++){
					UiGlyph glyph = row.getUiGlyphs().get(j);
					// System.out.println("x: " + x1 + " pointx: " + glyph.getPosition().x);
					if (glyph.getPosition().x >= x2){
						break;
					}
				}
				
				break;
			}
		}
		
		j = j == 0 ? j : j - 1;
		if (i >= rows.size() && rows.size() > 0){
			// clicked outside of the text. Select all the text
			i = rows.size() - 1;
			j = rows.get(i).getUiGlyphs().size() - 1;
		}
		
		range.setEndRow(i);		
		range.setEndCol(j);
		
		// check if the range is valid. Only the start check will suffice
		if (range.getStartRow() > range.getEndRow()){
			int startx = range.getEndRow();
			int starty = range.getEndCol();
			range.setEndRow(range.getStartRow());
			range.setEndCol(range.getStartCol());
			range.setStartRow(startx);
			range.setStartCol(starty);
		}
		
		if (range.getEndRow() >= rows.size()){
			range.setEndRow(range.getEndRow() - 1);
		}
		
		if (range.getStartRow() < rows.size()){
			this.controller.setSelectionRange(range);
			this.repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub		
	}	
}
