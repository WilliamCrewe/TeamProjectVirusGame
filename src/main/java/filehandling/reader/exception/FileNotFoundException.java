package main.java.filehandling.reader.exception;

/**
 * Exception to be thrown when the file required by the system could not be found
 * 
 * @author Daniel
 *
 */
public class FileNotFoundException extends FileReadException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3844382723897526987L;

	public FileNotFoundException(String message, Exception e) {
		super(message, e);
	}
	
	public FileNotFoundException(String message) {
		super(message);
	}
}
