package test.java.filehandling.gamecontent.realisations;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.nio.file.Files;

import org.junit.Test;
import org.w3c.dom.Document;

import main.java.filehandling.gamecontent.ContentType;
import main.java.filehandling.gamecontent.realisations.LocationGameContent;
import main.java.filehandling.gamecontent.realisations.components.ConnectedLocations;
import main.java.filehandling.xml.XMLUtils;
import main.java.filehandling.xml.exception.XMLParseException;

public class LocationGameContentTest {

	/**
	 * Positive test case for when the constructor is called and the XML is valid and contains all expected values
	 * @throws XMLParseException
	 */
	@Test
	public void testConstructor() throws Exception {
		String fullFilePath = System.getProperty("user.dir")
				+ "\\Resources\\TestFiles\\Locations\\LOCATION_TestLocationName.xml";

		// Create the XML document
		Document document = XMLUtils.convertByteArrayToDocument(Files.readAllBytes(new File(fullFilePath).toPath()));

		// Call the method in test (Constructor so instantiate the object)
		LocationGameContent locationGameContent = new LocationGameContent(document);
		
		// Assert the values on the object are as expected
		assertEquals(ContentType.LOCATION, locationGameContent.getContentType());
		assertEquals("TestLocationName", locationGameContent.getLocationName());
		assertEquals("LocationGameContent object with values: locationName=TestLocationName", locationGameContent.toString());
		assertEquals("TestPassiveEventID", locationGameContent.getLocationPassiveEventID());
		
		ConnectedLocations connectedLocations = locationGameContent.getConnectedLocations();
		assertEquals(2, connectedLocations.getConnectedLocationIDs().size());
		assertEquals("TestConnectedLocationID1", connectedLocations.getConnectedLocationIDs().get(0));
		assertEquals("TestConnectedLocationID2", connectedLocations.getConnectedLocationIDs().get(1));
		
	}
	
	/**
	 * Negative test case for when the constructor is called but the XML is invalid for this object type
	 * @throws XMLParseException
	 */
	@Test (expected = XMLParseException.class)
	public void testConstructorIncorrectXML() throws Exception {
		String fullFilePath = System.getProperty("user.dir")
				+ "\\Resources\\TestFiles\\Events\\EVENT_TestEventName.xml";

		// Create the XML document
		Document document = XMLUtils.convertByteArrayToDocument(Files.readAllBytes(new File(fullFilePath).toPath()));

		// Call the method in test (Constructor so instantiate the object)
		new LocationGameContent(document);
	}
}
