package com.yp.lexi.command;

public interface CommandManager {

    boolean exec(Command command);

    void undo();

    void redo();
}
