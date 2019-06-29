package command;

import model.Composition;

public class LoadTxtCommand implements ICommand {

    private String filePath;

    private Composition document;

    public LoadTxtCommand(Composition document, String filePath) {
        this.document = document;
        this.filePath = filePath;
    }
    @Override
    public boolean execute() {
        try {
            document.loadFromTxt(filePath);
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void unExecute() {

    }

    @Override
    public boolean canUndo() {
        return false;
    }
}
