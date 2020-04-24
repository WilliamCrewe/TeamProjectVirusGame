package test.java.filehandling.gamecontent.realisations.components;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import main.java.filehandling.gamecontent.realisations.components.CompletedEvent;
import main.java.filehandling.xml.XMLUtils;
import main.java.filehandling.xml.exception.XMLParseException;

public class CompletedEventTest {

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
		NodeList completedEventNodeList = document.getElementsByTagName("CompletedEvent");
		
		// Call the method in test (Constructor so instantiate the object)
		CompletedEvent completedEvent = new CompletedEvent(completedEventNodeList.item(0));
		
		// Assert the object has the expected values
		assertEquals("TestEventID", completedEvent.getEventID());
		assertEquals("TestEventOptionID", completedEvent.getEventOptionID());
	}
	
	/**
	 * negative test case for when the constructor is called but an unexected tag (Not XSD valid) was found
	 * @throws XMLParseException
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorUnexpectedTag() throws XMLParseException {
		String testXML = "<Content>" + 
				"	<Save>" + 
				"		<SaveName>TestSaveName</SaveName>" + 
				"		<Seed>TestSeed</Seed>" + 
				"		<DayNumber>42</DayNumber>" + 
				"		<CompletedEvents>" + 
				"			<CompletedEvent>" +
				"				<EventID>TestEventID</EventID>" +
				"				<EventOptionID>TestEventOptionID</EventOptionID>" +
				"               <UNEXPECTED_TAG>text</UNEXPECTED_TAG>" +
				"			</CompletedEvent>" +
				"		</CompletedEvents>"	+	
				"	</Save>" + 
				"</Content>";
		
		// Convert the testXML to a byte array for the method in test
		byte[] xmlBytes = testXML.getBytes();
		
		// Call the method in test
		Document document = XMLUtils.convertByteArrayToDocument(xmlBytes);
		
		// Get the CompletedEvent nodes
		NodeList completedEventNodeList = document.getElementsByTagName("CompletedEvent");
		
		// Call the method in test (Constructor so instantiate the object)
		new CompletedEvent(completedEventNodeList.item(0));
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
		NodeList completedEventNodeList = document.getElementsByTagName("CompletedEvent");
		
		// Create the object in test
		CompletedEvent completedEvent = new CompletedEvent(completedEventNodeList.item(0));
		
		// Call the method in test
		byte[] serializedObject = completedEvent.serialize();
		
		// Check the the bytes represent the string expected
		assertEquals("<CompletedEvent><EventID>TestEventID</EventID><EventOptionID>TestEventOptionID</EventOptionID></CompletedEvent>", new String(serializedObject));
	}
}
