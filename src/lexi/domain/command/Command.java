package lexi.domain.command;

public interface Command {

    boolean run();

    void undo();

    boolean supportUndo();
}
