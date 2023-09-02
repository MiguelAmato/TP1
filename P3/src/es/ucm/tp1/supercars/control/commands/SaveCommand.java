package es.ucm.tp1.supercars.control.commands;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import es.ucm.tp1.supercars.control.exceptions.CommandExecuteException;
import es.ucm.tp1.supercars.control.exceptions.CommandParseException;
import es.ucm.tp1.supercars.control.exceptions.InputOutputRecordException;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.view.GameSerializer;

public class SaveCommand extends Command{
	
	private String filename;

	private static final String NAME = "save";
	private static final String DETAILS = "sa[v]e <filename>";
	private static final String SHORTCUT = "v";
	private static final String HELP = "Save the state of the game to a file.";
	
	public SaveCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
 	}

  	public boolean execute(Game game) throws CommandExecuteException {
  		filename += ".txt"; 
  		try {
  			File file = new File(filename);
  			FileWriter writer = new FileWriter(file);
  			BufferedWriter buffer = new BufferedWriter(writer);
  			buffer.write("Super cars 3.0\n\n");
  			buffer.write(GameSerializer.serializer(game));
  			buffer.close();
  			System.out.println("Game successfully saved to file " + filename);
  		}
  		catch(IOException ioe) {
  			throw new InputOutputRecordException("[ERROR]: Save action failed");
  		}
 		return false;
	}
	
	protected Command parse(String[] commandWords) throws CommandParseException{
		
		if (matchCommandName(commandWords[0])) {
			commandWords[0] = SHORTCUT;
			if(commandWords.length == 1) {
				throw new CommandParseException("[ERROR]: Must indicate a file");
			}
			else if(commandWords.length == 2) {	
				try {
					 filename = commandWords[1];
				}
				catch(NumberFormatException nfe) {
					return null;
				}
				commandWords = new String[] {SHORTCUT};
			}	
		}	
		return super.parse(commandWords);
	}
}
