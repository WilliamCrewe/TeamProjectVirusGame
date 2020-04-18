package main.java.filehandling.reader.exception;

/**
 * Exception to be thrown when a file fails to be read
 * @author Daniel
 *
 */
public class FileReadException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2277174097389734941L;

	public FileReadException(String message, Exception e) {
		super(message, e);
	}
	
	public FileReadException(String message) {
		super(message);
	}
}
