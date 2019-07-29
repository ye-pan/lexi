package com.yp.lexi.command;

public interface Command {

    boolean exec();

    void undo();

    boolean supportUndo();
}
