package main.java.properties;

/**
 * Static class to handle the properties of the system
 * @author Daniel
 *
 */
public class PropertyManager {

	private static String xsdLocation;
	private static String baseDirectory;
	
	private PropertyManager() {
		// private constructor to prevent instantiation
	}
	
	public static void setXSDLocation(String newXSDLocation) {
		xsdLocation = newXSDLocation;
	}
	
	public static String getXSDLocation() {
		return xsdLocation;
	}
	

	public static void setBaseDirectory(String newBaseDirectory) {
		baseDirectory = newBaseDirectory;
	}
	
	public static String getBaseDirectory() {
		return baseDirectory;
	}
}
