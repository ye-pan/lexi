package lexi.ui.swing.listener;

import lexi.controller.EditorController;
import lexi.model.Row;
import lexi.ui.swing.MainFrame;
import lexi.viewmodel.SelectionRange;
import lexi.viewmodel.UiGlyph;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class EditorMouseListener implements MouseListener {
    private MainFrame frame;
    private EditorController controller;
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public EditorMouseListener(MainFrame frame, EditorController controller) {
        this.frame = frame;
        this.controller = controller;
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();
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
            frame.repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

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
}
