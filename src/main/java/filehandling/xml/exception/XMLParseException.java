package main.java.filehandling.xml.exception;

/**
 * Exception to be thrown when an error occurs parsing an input to XML format (Including into a Document)
 * @author Daniel
 *
 */
public class XMLParseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6282172429532102133L;

	public XMLParseException(String message, Exception cause) {
		super(message, cause);
	}
	
	public XMLParseException(String message) {
		super(message);
	}
}
