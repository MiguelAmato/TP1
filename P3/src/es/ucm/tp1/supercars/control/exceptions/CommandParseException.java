package es.ucm.tp1.supercars.control.exceptions;

@SuppressWarnings("serial")
public class CommandParseException extends GameException {
	public CommandParseException() {super();}
	public CommandParseException(String message) {super(message);}
	public CommandParseException(String message, Throwable cause) {super(message, cause);}
	public CommandParseException(Throwable cause) {super(cause);}
}
