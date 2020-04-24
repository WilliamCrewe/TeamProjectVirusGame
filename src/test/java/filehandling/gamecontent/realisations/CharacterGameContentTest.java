package test.java.filehandling.gamecontent.realisations;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.w3c.dom.Document;

import main.java.filehandling.gamecontent.ContentType;
import main.java.filehandling.gamecontent.realisations.CharacterGameContent;
import main.java.filehandling.xml.XMLUtils;
import main.java.filehandling.xml.exception.XMLParseException;

public class CharacterGameContentTest {


	/**
	 * Positive test case for when the constructor is called and the XML is valid and contains all expected values
	 * @throws XMLParseException
	 */
	@Test
	public void testConstructor() throws XMLParseException {
		String testXML = "<Content>" + 
				"	<Character>" + 
				"		<CharacterName>TestCharacterName</CharacterName>" + 
				"	</Character>" + 
				"</Content>";
		
		// Convert the testXML to a byte array for the method in test
		byte[] xmlBytes = testXML.getBytes();
		
		// Call the method in test
		Document document = XMLUtils.convertByteArrayToDocument(xmlBytes);
		
		// Call the method in test (Constructor so instantiate the object)
		CharacterGameContent characterGameContent = new CharacterGameContent(document);
		
		// Assert the values on the object are as expected
		assertEquals(ContentType.CHARACTER, characterGameContent.getContentType());
		assertEquals("TestCharacterName", characterGameContent.getCharacterName());
		assertEquals("CharacterGameContent object with values: characterName=TestCharacterName", characterGameContent.toString());
	}
	
	/**
	 * Negative test case for when the constructor is called but the XML is invalid for this object type
	 * @throws XMLParseException
	 */
	@Test (expected = XMLParseException.class)
	public void testConstructorIncorrectXML() throws XMLParseException {
		// This XML is for the event type not the Save
		String testXML = "" + 
				"<Content>" + 
				"	<Event>" + 
				"		<EventName>TestEventName</EventName>" + 
				"		<EventLocation>School</EventLocation>" + 
				"		<EventOccurenceProbability>0.2</EventOccurenceProbability>" + 
				"		<EventOptions>" + 
				"			<EventOption>" + 
				"				<EventOptionDescription>TestOptionOne</EventOptionDescription>" + 
				"				<EventOptionImmunityModification>-10</EventOptionImmunityModification>" + 
				"				<EventOptionContagionLevelModifier>30</EventOptionContagionLevelModifier>" + 
				"				<EventOptionItems>" + 
				"					<ItemName>TestItemOne</ItemName>" + 
				"					<ItemName>TestItemTwo</ItemName>" + 
				"				</EventOptionItems>" + 
				"			</EventOption>" + 
				"		</EventOptions>" + 
				"	</Event>" + 
				"</Content>";
		
		// Convert the testXML to a byte array for the method in test
		byte[] xmlBytes = testXML.getBytes();
		
		// Call the method in test
		Document document = XMLUtils.convertByteArrayToDocument(xmlBytes);
		
		// Call the method in test (Constructor so instantiate the object)
		new CharacterGameContent(document);
	}
}
