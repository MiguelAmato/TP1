package es.ucm.tp1.supercars.control.exceptions;

@SuppressWarnings("serial")
public class NumberFormatException extends CommandParseException {
	public NumberFormatException() {super();}
	public NumberFormatException(String message) {super(message);}
	public NumberFormatException(String message, Throwable cause) {super(message, cause);}
	public NumberFormatException(Throwable cause) {super(cause);}
}
