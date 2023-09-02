package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.control.Buyable;
import es.ucm.tp1.supercars.control.exceptions.CommandExecuteException;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.actions.Wave;

public class WaveCommand extends Command implements Buyable {

	private final int COST = 5;
	
	private static final String NAME = "wave";
	private static final String DETAILS = "[w]ave";
	private static final String SHORTCUT = "w";
	private static final String HELP = "do wave";
	
	
	public WaveCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
 	}
	
 	public int cost() {
		return COST;
	}
 
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			if(buy(game)) {
				game.execute(new Wave());
				game.updateGame();
				return true;
			}	
		}
		catch(CommandExecuteException cee) {
			System.out.println(cee.getMessage());
		}
		return false;
	}
}
