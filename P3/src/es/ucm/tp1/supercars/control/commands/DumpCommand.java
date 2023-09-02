package es.ucm.tp1.supercars.control.commands;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import es.ucm.tp1.supercars.control.exceptions.CommandExecuteException;
import es.ucm.tp1.supercars.control.exceptions.CommandParseException;
import es.ucm.tp1.supercars.control.exceptions.InputOutputRecordException;
import es.ucm.tp1.supercars.logic.Game;

public class DumpCommand extends Command{

	private String filename;
	
	private static final String NAME = "dump";
	private static final String DETAILS = "[d]ump <filename>";
	private static final String SHORTCUT = "d";
	private static final String HELP = "Shows the content of a saved file";
	
	public DumpCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);	
	}
	
	public boolean execute(Game game) throws CommandExecuteException {
		filename += ".txt"; 
  		try {
  			File file = new File(filename);
  			Scanner scanner = new Scanner(file);
  			while (scanner.hasNextLine()) {
  				System.out.println(scanner.nextLine());
  			}
  			scanner.close();
  		}
  		catch(IOException ioe) {
  			throw new InputOutputRecordException("An error ocurred on reading a file\n");
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
