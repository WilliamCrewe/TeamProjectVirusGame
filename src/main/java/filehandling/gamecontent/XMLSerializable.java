package main.java.filehandling.gamecontent;

/**
 * Interface representing the functions required for any objects that can be converted to an XML format for storage
 * @author Daniel
 *
 */
public interface XMLSerializable {

	public byte[] serialize();
}
