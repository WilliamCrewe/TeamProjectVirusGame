package main.java.filehandling.xml.exception;

/**
 * Exception to be thrown when xml fails validation
 * @author Daniel
 *
 */
public class XMLValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5687973193526409176L;

	public XMLValidationException(String message, Exception e) {
		super(message, e);
	}
}
