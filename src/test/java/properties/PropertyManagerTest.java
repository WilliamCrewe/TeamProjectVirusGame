package test.java.properties;

import org.junit.Test;

import main.java.properties.PropertyManager;

public class PropertyManagerTest {

	/**
	 * 
	 */
	@Test
	public void testInitialiseProperties() {
		String fullFilePath = System.getProperty("user.dir") + "\\Resources\\TestFiles\\propertyOverride.properties";
		
		// call the method in test
		PropertyManager.initialiseProperties(fullFilePath);
	}
	
	@Test
	public void testGetXSDLocation() {
		PropertyManager.getXSDLocation();
	}
	
	@Test
	public void testGetBaseDirectory() {
		PropertyManager.getBaseDirectory();
	}
}
