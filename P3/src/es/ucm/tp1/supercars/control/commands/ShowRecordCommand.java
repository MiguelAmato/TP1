package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.control.exceptions.CommandExecuteException;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.Record;

public class ShowRecordCommand extends Command {
	
	private static final String NAME = "record";
	private static final String DETAILS = "rec[o]rd";
	private static final String SHORTCUT = "o";
	private static final String HELP = "show level record";

	public ShowRecordCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	public boolean execute(Game game) throws CommandExecuteException {
		try {
			Record.showRecord(game.getLevel().toString());
		}
		catch(CommandExecuteException cee) {
			cee.getMessage();
		}
		return false;
	}
}
