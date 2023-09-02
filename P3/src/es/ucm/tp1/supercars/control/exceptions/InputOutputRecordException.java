package es.ucm.tp1.supercars.control.exceptions;

@SuppressWarnings("serial")
public class InputOutputRecordException extends CommandExecuteException {
	public InputOutputRecordException() {super();}
	public InputOutputRecordException(String message) {super(message);}
	public InputOutputRecordException(String message, Throwable cause) {super(message, cause);}
	public InputOutputRecordException(Throwable cause) {super(cause);}
}
