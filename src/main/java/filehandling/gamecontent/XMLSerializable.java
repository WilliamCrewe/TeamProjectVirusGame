package main.java.filehandling.gamecontent;

/**
 * Interface representing the functions required for any objects that can be converted to an XML format for storage
 * @author Daniel
 *
 */
public interface XMLSerializable {

	/**
	 * @return The objects XML format in a byte array
	 */
	public byte[] serialize();
	
	/**
	 * @return The objects XML format as a string
	 */
	public String toXMLString();
}
