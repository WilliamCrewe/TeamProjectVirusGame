package main.java.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import main.java.logging.SystemLogger;

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
	
	/**
	 * Initialise the properties from the properties file passed in (Used as a development override)
	 * @param propertyFilePath
	 */
	public static void initialiseProperties(String propertyFilePath) {
		SystemLogger.fine("Initialising the property file %s", propertyFilePath);
        try (InputStream input = new FileInputStream(propertyFilePath)) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);
            
            xsdLocation = prop.getProperty(Property.XSD_LOCATION.getPropertyString());
            baseDirectory = prop.getProperty(Property.BASE_DIRECTORY.getPropertyString());
            

    		SystemLogger.config("Initialised the property file %s successfully", propertyFilePath);
        } catch (IOException e) {
        	SystemLogger.severe("There was an error when loading in the override property file %s, reason was %s", propertyFilePath, e.getMessage());
        }
	}
	
	public static void setXSDLocation(String newXSDLocation) {
		xsdLocation = newXSDLocation;
	}
	
	public static String getXSDLocation() {
		if (xsdLocation == null) {
			xsdLocation = getBaseDirectory() + File.separator + "XSD"  + File.separator + "VirusGameXSD.xsd";
		}
		
		return xsdLocation;
	}
	

	public static void setBaseDirectory(String newBaseDirectory) {
		baseDirectory = newBaseDirectory;
	}
	
	public static String getBaseDirectory() {
		if (baseDirectory == null) {
			baseDirectory = System.getProperty("user.dir");
		}
		
		return baseDirectory;
	}
	
	/**
	 * Enum representing the different properties that can be configured
	 * @author Daniel
	 *
	 */
	private enum Property {
		XSD_LOCATION("xsd.location"),
		BASE_DIRECTORY("base.directory");
		
		private String propertyString;
		
		private Property(String propertyString) {
			this.propertyString = propertyString;
		}
		
		public String getPropertyString() {
			return propertyString;
		}
		
	}
}
