package lexi.command;

import java.util.*;

public class CommandManager {

	private List<ICommand> commands = new ArrayList<>();
	private int current = -1;
	
	private CommandManager(){}
	
	public static CommandManager getInstance(){
		return CommandManagerHolder.INSTANCE;
	}
	
	public Boolean execute(ICommand cmd){
		Boolean val = cmd.execute() && cmd.canUndo();
		if (val){
			int size = this.commands.size();
			for (int i = size - 1; i >= current + 1; i--){
				this.commands.remove(i);
			}
			
			this.commands.add(cmd);
			this.current++;
		}
		
		return val;
	}
	
	public void undo(){
		if (this.canUndo()){
			this.commands.get(current).unExecute();
			current--;
		}
	}
	
	public void redo(){
		if (this.canRedo()){
			current++;
			this.commands.get(current).execute();
		}
	}
	
	public Boolean canUndo(){
		return this.current > -1;
	}
	
	public Boolean canRedo(){
		return this.current < (this.commands.size() - 1);
	}

	private static class CommandManagerHolder {
		static final CommandManager INSTANCE = new CommandManager();
	}
}
