package es.ucm.tp1.supercars.control.exceptions;

@SuppressWarnings("serial")
public class CommandExecuteException extends GameException {
	public CommandExecuteException() {super();}
	public CommandExecuteException(String message) {super(message);}
	public CommandExecuteException(String message, Throwable cause) {super(message, cause);}
	public CommandExecuteException(Throwable cause) {super(cause);}
}
