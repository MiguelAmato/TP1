package es.ucm.tp1.supercars.control.exceptions;

@SuppressWarnings("serial")
public class ResetLevelException extends CommandParseException {
	public ResetLevelException() {  super(); }
	public ResetLevelException(String message) { super(message); }
	public ResetLevelException(String message, Throwable cause) { super(message, cause); }
	public ResetLevelException(Throwable cause) { super(cause); }
}
