package test.java.filehandling.gamecontent.realisations.components;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import main.java.filehandling.gamecontent.realisations.components.CompletedEvent;
import main.java.filehandling.gamecontent.realisations.components.CompletedEvents;
import main.java.filehandling.xml.XMLUtils;
import main.java.filehandling.xml.exception.XMLParseException;

public class CompletedEventsTest {


	/**
	 * Positive test case for when the constructor is called
	 * @throws XMLParseException
	 */
	@Test
	public void testConstructor() throws XMLParseException {
		String testXML = "<Content>" + 
				"	<Save>" + 
				"		<SaveName>TestSaveName</SaveName>" + 
				"		<Seed>TestSeed</Seed>" + 
				"		<DayNumber>42</DayNumber>" + 
				"		<CompletedEvents>" + 
				"			<CompletedEvent>" +
				"				<EventID>TestEventID</EventID>" +
				"				<EventOptionID>TestEventOptionID</EventOptionID>" +
				"			</CompletedEvent>" +
				"		</CompletedEvents>"	+	
				"	</Save>" + 
				"</Content>";
		
		// Convert the testXML to a byte array for the method in test
		byte[] xmlBytes = testXML.getBytes();
		
		// Call the method in test
		Document document = XMLUtils.convertByteArrayToDocument(xmlBytes);
		
		// Get the CompletedEvent nodes
		NodeList completedEventNodeList = document.getElementsByTagName("CompletedEvents");
		
		// Call the method in test (Constructor so instantiate the object)
		CompletedEvents completedEvents = new CompletedEvents(completedEventNodeList.item(0));
		
		// Assert the object has the expected values
		List<CompletedEvent> completedEventList = completedEvents.getCompletedEvents();
		assertEquals(1, completedEventList.size());
		assertEquals("TestEventID", completedEventList.get(0).getEventID());
		assertEquals("TestEventOptionID", completedEventList.get(0).getEventOptionID());
	}
	
	/**
	 * Positive test case for when the serialize method is called
	 * @throws XMLParseException
	 */
	@Test
	public void testSerialize() throws XMLParseException {
		String testXML = "<Content>" + 
				"	<Save>" + 
				"		<SaveName>TestSaveName</SaveName>" + 
				"		<Seed>TestSeed</Seed>" + 
				"		<DayNumber>42</DayNumber>" + 
				"		<CompletedEvents>" + 
				"			<CompletedEvent>" +
				"				<EventID>TestEventID</EventID>" +
				"				<EventOptionID>TestEventOptionID</EventOptionID>" +
				"			</CompletedEvent>" +
				"		</CompletedEvents>"	+	
				"	</Save>" + 
				"</Content>";
		
		// Convert the testXML to a byte array for the method in test
		byte[] xmlBytes = testXML.getBytes();
		
		// Call the method in test
		Document document = XMLUtils.convertByteArrayToDocument(xmlBytes);
		
		// Get the CompletedEvent nodes
		NodeList completedEventNodeList = document.getElementsByTagName("CompletedEvents");
		
		// Create the object in test
		CompletedEvents completedEvents = new CompletedEvents(completedEventNodeList.item(0));
		
		// Call the method in test
		byte[] serializedObject = completedEvents.serialize();
		
		// Check the the bytes represent the string expected
		assertEquals("<CompletedEvents><CompletedEvent><EventID>TestEventID</EventID><EventOptionID>TestEventOptionID</EventOptionID></CompletedEvent></CompletedEvents>", new String(serializedObject));
	}
}
