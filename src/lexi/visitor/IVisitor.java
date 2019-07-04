package lexi.visitor;

import lexi.model.Char;
import lexi.model.Picture;
import lexi.model.Row;

public interface IVisitor {

	void visitChar(Char ch);

	void visitPicture(Picture picture);

	void visitRow(Row row);
}
