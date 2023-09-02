package es.ucm.tp1.supercars.control.commands;
import es.ucm.tp1.supercars.control.exceptions.CommandExecuteException;
import es.ucm.tp1.supercars.control.exceptions.CommandParseException;
import es.ucm.tp1.supercars.logic.Game;

public abstract class Command {

	protected static final String INCORRECT_NUMBER_OF_ARGS_MSG = "Incorrect number of arguments";
	private static final String UNKNOWN_COMMAND_MSG = "Unknown command";
	private final String name;  
	private final String shortcut;
	private final String details;
	private final String help;
	
	private static final Command[] AVAILABLE_COMMANDS = {
		new HelpCommand(),
		new InfoCommand(),
		new UpdateCommand(),
		new MoveUpCommand(),
		new MoveDownCommand(),
		new ExitCommand(),
		new ResetCommand(),
		new TestCommand(),
		new ShootCommand(),
		new GrenadeCommand(),
		new WaveCommand(),
		new SerializeCommand(),
		new SaveCommand(),
		new DumpCommand(),
		new ShowRecordCommand(),
		new ClearCommand(),
		new CheatCommand()
 	};
		
	public Command(String name, String shortcut, String details, String help) {
		this.name = name;
		this.shortcut = shortcut;
		this.details = details;
		this.help = help;
	}
	
	public static Command getCommand(String[] commandWords) throws CommandParseException { 
		Command command = null;
		int i = 0;
		while(command == null && i < AVAILABLE_COMMANDS.length) {
			command = AVAILABLE_COMMANDS[i].parse(commandWords);
			i++;
		}
		if (command == null) {
			throw new CommandParseException(String.format("[ERROR]: %s", UNKNOWN_COMMAND_MSG + "\n")); 
		}
		return command;
	}
	
	protected Command parse(String[] words) throws CommandParseException {
		if (matchCommandName(words[0])) {
			if (words.length != 1) {
				throw new CommandParseException(String.format("[ERROR]: Command %s: %s", name, INCORRECT_NUMBER_OF_ARGS_MSG + "\n"));
			} 
			else {
				return this;
			}
		}
		return null;
	}

	
	protected boolean matchCommandName(String name) { 
		return this.shortcut.equalsIgnoreCase(name) || this.name.equalsIgnoreCase(name); 
	}
	
	public abstract boolean execute(Game game) throws CommandExecuteException;
	
	protected Command[] getAvailableCommands() {
		return AVAILABLE_COMMANDS;
	}

	protected String getDetails() {
		return details;
	}
	
	protected String getHelp() {
		return help;
	}
}
