package command;

import model.Composition;
import model.Glyph;

public class DeleteTheLastCommand implements ICommand{
    private Composition document;
    private Glyph theLast;
    private int theLastIndex;
    public DeleteTheLastCommand(Composition document) {
        this.document = document;
    }
    @Override
    public boolean execute() {
        try {
            int size = this.document.getChildren().size();
            theLastIndex = size - 1;
            if(theLastIndex < 0) {
                return false;
            }
            theLast = document.getChildren().get(theLastIndex);
            document.remove(theLastIndex);
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void unExecute() {
        document.insert(theLast, theLastIndex);
    }

    @Override
    public boolean canUndo() {
        return true;
    }
}
