package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.control.Level;
import es.ucm.tp1.supercars.control.exceptions.CommandParseException;
import es.ucm.tp1.supercars.logic.Game;

public class ResetCommand extends Command {
	
	private final String LEVEL_EXCEPTION = "Command r: Level must be one of: TEST, EASY, HARD, ADVANCED";
	private final String RESET_EXCEPTION = "the seed is not a proper long number";
	
	private static final String NAME = "r";
	private static final String DETAILS = "[r]eset [<level> <seed>]";
	private static final String SHORTCUT = "r";
	private static final String HELP = "reset game";
	
	private String level;
	private long seed;
	private boolean bigReset; 
	
	public ResetCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
 
	public boolean execute(Game game) {
		if(bigReset) {
			resetInfo();
		}
		game.reset(seed, level, bigReset);
		return true;
	}
	
	protected Command parse(String[] commandWords) throws CommandParseException {   
		if(matchCommandName(commandWords[0])) {
			commandWords[0] = SHORTCUT;
			if (commandWords.length == 3) { // (reset, level y seed)
				if (Level.valueOfIgnoreCase(commandWords[1]) == null) { 
					throw new CommandParseException("[ERROR]: " + LEVEL_EXCEPTION + "\n");
				}
				level = commandWords[1];
				try {
					seed = Long.parseLong(commandWords[2]);
				} 
				catch (NumberFormatException nfe) {
					throw new CommandParseException(String.format("[ERROR]: Command %s: %s", SHORTCUT, RESET_EXCEPTION + "\n"));
				}
				bigReset = true;
				commandWords = new String[] {SHORTCUT};
 			} 
			else {
				bigReset = false;
			}
		}
		return super.parse(commandWords);
	}
	
	private void resetInfo() { 
		System.out.println("Level: " + level.toUpperCase());
		System.out.println("Random generator initialized with seed: " + seed);
	}
}
