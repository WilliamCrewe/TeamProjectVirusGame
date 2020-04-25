package main.java.filehandling.gamecontent;

/**
 * Interface for any class that can be written to a XML file
 * @author Daniel
 *
 */
public interface XMLFileWritable extends XMLSerializable {

	/**
	 * @return the fileName the contents should be associated to
	 */
	public String getFileName();
	
	/**
	 * @return the directory name the file should be stored in
	 */
	public String getDirectoryName();
}
