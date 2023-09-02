package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.control.Buyable;
import es.ucm.tp1.supercars.control.exceptions.CommandExecuteException;
import es.ucm.tp1.supercars.control.exceptions.CommandParseException;
import es.ucm.tp1.supercars.control.exceptions.InvalidPositionException;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.gameobjects.Grenade;

public class GrenadeCommand extends Command implements Buyable {
	
	private static final String INVALID_POSITION = "Invalid position.";
	private static final String FAILED_MSG = "Failed to add grenade";
	
	private final int COST = 3;
	private static final String NAME = "grenade";
	private static final String DETAILS = "[g]renade <x> <y>";
	private static final String SHORTCUT = "g";
	private static final String HELP = "add a grenade in position x, y";
	
	private int x; 
	private int y;

	public GrenadeCommand( ) {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	public int cost() {
 		return COST;
	}

	public boolean execute(Game game) throws CommandExecuteException {
		try {
			if(isValidPosition(game, x, y)) {
				if(buy(game)) {
					game.forceAddObject(new Grenade(game, x + game.getCarPosX(), y));
					game.updateGame();
					return true;
				}
			}	
		}
		catch (CommandExecuteException cee) {
			System.out.println(cee.getMessage());
			System.out.println("[ERROR]: " + FAILED_MSG + "\n");
		}
		return false;
	}
	
	protected Command parse(String[] commandWords) throws CommandParseException {
		if (matchCommandName(commandWords[0])) {
			commandWords[0] = SHORTCUT;
			if(commandWords.length == 1 || commandWords.length == 2) {
				throw new CommandParseException("[ERROR]: Incorrect number of arguments for grenade command: [g]renade <x> <y>\n");
			}
			else if(commandWords.length == 3) {	
				try {
					x = Integer.parseInt(commandWords[1]);  
					y = Integer.parseInt(commandWords[2]);
				}
				catch(NumberFormatException nfe) {
					return null;
				}
				commandWords = new String[] {SHORTCUT};
			}	
		}
		return super.parse(commandWords);
	}
	
	private boolean isValidPosition(Game game, int x, int y) throws InvalidPositionException {
		if (!(game.isValidVisiblePosition(x, y) && game.getObjectInPosition(x + game.getCarPosX(), y) == null)) {
			throw new InvalidPositionException(INVALID_POSITION);
		}
		return true;
	}
}
