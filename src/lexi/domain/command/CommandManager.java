package lexi.domain.command;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    private List<Command> commands;

    private int current;

    public CommandManager() {
        commands = new ArrayList<>();
        current = -1;
    }

    public boolean run(Command command) {
        boolean val = command.run() && command.supportUndo();
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

    public void undo() {
        if(couldUndo()) {
            commands.get(current).undo();
            current--;
        }
    }

    public void redo() {
        if(couldRedo()) {
            current++;
            commands.get(current).run();
        }
    }

    public boolean couldUndo() {
        return current > -1;
    }

    public boolean couldRedo() {
        return current < (commands.size() - 1);
    }

    public int size() {
        return commands.size();
    }

    public int getCurrent() {
        return current;
    }
}
