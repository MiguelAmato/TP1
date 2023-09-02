package es.ucm.tp1.supercars.control.exceptions;

@SuppressWarnings("serial")
public class GameException extends Exception {
	public GameException() {super();}
	public GameException(String message) {super(message);}
	public GameException(String message, Throwable cause) {super(message, cause);}
	public GameException(Throwable cause) {super(cause);}
}
