package test.java.filehandling.gamecontent.realisations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import org.junit.Test;
import org.w3c.dom.Document;

import main.java.filehandling.gamecontent.ContentType;
import main.java.filehandling.gamecontent.realisations.EventGameContent;
import main.java.filehandling.gamecontent.realisations.components.EventOption;
import main.java.filehandling.gamecontent.realisations.components.EventOptions;
import main.java.filehandling.xml.XMLUtils;
import main.java.filehandling.xml.exception.XMLParseException;
import main.java.random.Rarity;

public class EventGameContentTest {

	/**
	 * Positive test case for when the constructor is called and the XML is valid
	 * and contains all expected values
	 * 
	 * @throws XMLParseException
	 */
	@Test
	public void testConstructor() throws Exception {
		String fullFilePath = System.getProperty("user.dir")
				+ "\\Resources\\TestFiles\\Events\\EVENT_TestEventName.xml";

		// Create the XML document
		Document document = XMLUtils.convertByteArrayToDocument(Files.readAllBytes(new File(fullFilePath).toPath()));

		// Call the method in test (Constructor so instantiate the object)
		EventGameContent eventGameContent = new EventGameContent(document);

		// Assert the values on the object are as expected
		assertEquals(ContentType.EVENT, eventGameContent.getContentType());
		assertEquals("TestEventID", eventGameContent.getEventID());
		assertEquals("Test Event Name", eventGameContent.getEventName());
		assertEquals("Hospital", eventGameContent.getEventLocationID());
		assertEquals("TestEventID", eventGameContent.getEventID());
		assertTrue(eventGameContent.isPassiveEvent());
		assertFalse(eventGameContent.isSingleOccurence());

		EventOptions eventOptions = eventGameContent.getEventOptions();
		assertNotNull(eventOptions);
		List<EventOption> eventOptionsValues = eventOptions.getEventOptionsValues();
		assertEquals(2, eventOptionsValues.size());

		assertEquals("TestEventID2", eventGameContent.getFollowingEventID());
		assertEquals(Rarity.COMMON, eventGameContent.getRarity());

	}

	/**
	 * Negative test case for when the constructor is called but the XML is invalid
	 * for this object type
	 * 
	 * @throws XMLParseException
	 */
	@Test(expected = XMLParseException.class)
	public void testConstructorIncorrectXML() throws Exception {
		// This XML is for the save event type
		String fullFilePath = System.getProperty("user.dir") + "\\Resources\\TestFiles\\Saves\\SAVE_TestEventName.xml";

		// Create the XML document
		Document document = XMLUtils.convertByteArrayToDocument(Files.readAllBytes(new File(fullFilePath).toPath()));

		// Call the method in test (Constructor so instantiate the object)
		new EventGameContent(document);
	}
}
