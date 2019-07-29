package com.yp.lexi.command;

import java.util.ArrayList;
import java.util.List;

public class DefaultCommandManager implements CommandManager {

    private List<Command> commands;

    private int current;

    public DefaultCommandManager() {
        commands = new ArrayList<>();
        current = -1;
    }

    @Override
    public boolean exec(Command command) {
        boolean val = command.exec() && command.supportUndo();
        if(val) {
            int size = commands.size();
            for(int i = size - 1; i >= current + 1; i--) {
                commands.remove(i);
            }
            commands.add(command);
            current++;
        }
        return val;
    }

    @Override
    public void undo() {
        if(couldUndo()) {
            commands.get(current).undo();
            current--;
        }
    }

    @Override
    public void redo() {
        if(couldRedo()) {
            current++;
            commands.get(current).exec();
        }
    }

    protected boolean couldUndo() {
        return current > -1;
    }

    protected boolean couldRedo() {
        return current < (commands.size() - 1);
    }

    public int size() {
        return commands.size();
    }

    public int getCurrent() {
        return current;
    }
}
