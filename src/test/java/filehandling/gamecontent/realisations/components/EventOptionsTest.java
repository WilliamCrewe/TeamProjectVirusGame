package test.java.filehandling.gamecontent.realisations.components;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import main.java.filehandling.gamecontent.realisations.components.EventOption;
import main.java.filehandling.gamecontent.realisations.components.EventOptions;
import main.java.filehandling.xml.XMLUtils;
import main.java.filehandling.xml.exception.XMLParseException;

public class EventOptionsTest {

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

		// Get the CompletedEvent nodes
		NodeList completedEventNodeList = document.getElementsByTagName("EventOptions");
	
		// Call the method in test (Constructor so instantiate the object)
		EventOptions eventOptions = new EventOptions(completedEventNodeList.item(0));

		// Assert the values on the object are as expected
		assertNotNull(eventOptions);
		List<EventOption> eventOptionsValues = eventOptions.getEventOptionsValues();
		assertEquals(2, eventOptionsValues.size());
	}
}
