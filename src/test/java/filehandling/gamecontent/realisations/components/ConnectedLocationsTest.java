package test.java.filehandling.gamecontent.realisations.components;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.nio.file.Files;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import main.java.filehandling.gamecontent.realisations.components.ConnectedLocations;
import main.java.filehandling.xml.XMLUtils;
import main.java.filehandling.xml.exception.XMLParseException;

public class ConnectedLocationsTest {

	/**
	 * Positive test case for when the constructor is called
	 * @throws XMLParseException
	 */
	@Test
	public void testConstructor() throws Exception {
		String fullFilePath = System.getProperty("user.dir")
				+ "\\Resources\\TestFiles\\Locations\\LOCATION_TestLocationName.xml";

		// Create the XML document
		Document document = XMLUtils.convertByteArrayToDocument(Files.readAllBytes(new File(fullFilePath).toPath()));
	
		// Get the CompletedEvent nodes
		NodeList completedEventNodeList = document.getElementsByTagName("ConnectedLocations");
		
		// Call the method in test (Constructor so instantiate the object)
		ConnectedLocations connectedLocations = new ConnectedLocations(completedEventNodeList.item(0));
		
		// Assert the object has the expected values
		assertEquals(2, connectedLocations.getConnectedLocationIDs().size());
		assertEquals("TestConnectedLocationID1", connectedLocations.getConnectedLocationIDs().get(0));
		assertEquals("TestConnectedLocationID2", connectedLocations.getConnectedLocationIDs().get(1));
	}
	
}
