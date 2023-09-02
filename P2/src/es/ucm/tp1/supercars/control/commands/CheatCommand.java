package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.logic.Game;

public class CheatCommand extends Command {

	private static final String NAME = "generate objects";
	private static final String DETAILS = "Cheat [1..5]";
	private static final String SHORTCUT = "0";
	private static final String HELP = "Removes all elements of last visible column, and adds an Advanced Object";
	
	private int option;
	
	public CheatCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
 	}

	public boolean execute(Game game) {
		game.clearLastLane();
		game.cheat(option);
		return true;
	}
	
	protected Command parse(String[] commandWords) {
		 try {
			option = Integer.parseInt(commandWords[0]); 
			if(option > 0 && option < 6) {
				commandWords[0] = SHORTCUT;
			}
			return super.parse(commandWords);}
		 catch(NumberFormatException nfe) {
			 return null;
		 }
	}
}


