package lexi.command;

public interface Command {

	boolean execute();

	void unExecute();

	boolean canUndo();

	String toString();
}
