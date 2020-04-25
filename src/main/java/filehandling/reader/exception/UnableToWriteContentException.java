package main.java.filehandling.reader.exception;

/**
 * Exception to be thrown when there was a problem writing content to a file
 * @author Daniel
 *
 */
public class UnableToWriteContentException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6333201204741378116L;

	public UnableToWriteContentException(String message, Exception e) {
		super(message, e);
	}
	
	public UnableToWriteContentException(String message) {
		super(message);
	}
}
