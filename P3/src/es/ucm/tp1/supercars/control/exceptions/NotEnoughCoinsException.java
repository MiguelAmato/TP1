package es.ucm.tp1.supercars.control.exceptions;

@SuppressWarnings("serial")
public class NotEnoughCoinsException extends CommandExecuteException {
	public NotEnoughCoinsException() {super();}
	public NotEnoughCoinsException(String message) {super(message);}
	public NotEnoughCoinsException(String message, Throwable cause) {super(message, cause);}
	public NotEnoughCoinsException(Throwable cause) {super(cause);}
}
