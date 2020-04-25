package test.java.filehandling.gamecontent.realisations;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.nio.file.Files;

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
	public void testConstructor() throws Exception {
		String fullFilePath = System.getProperty("user.dir") + "\\Resources\\TestFiles\\Characters\\CHARACTER_TestCharacterName.xml";
		
		// Create the XML document
		Document document = XMLUtils.convertByteArrayToDocument(Files.readAllBytes(new File(fullFilePath).toPath()));	
		
		// Call the method in test (Constructor so instantiate the object)
		CharacterGameContent characterGameContent = new CharacterGameContent(document);
		
		// Assert the values on the object are as expected
		assertEquals(ContentType.CHARACTER, characterGameContent.getContentType());
		assertEquals("TestCharacterID", characterGameContent.getCharacterID());
		assertEquals("TestCharacterName", characterGameContent.getCharacterName());
		assertEquals("CharacterGameContent object with values: characterName=TestCharacterName", characterGameContent.toString());
	}
	
	/**
	 * Negative test case for when the constructor is called but the XML is invalid for this object type
	 * @throws XMLParseException
	 */
	@Test (expected = XMLParseException.class)
	public void testConstructorIncorrectXML() throws Exception {
		// This XML is for the save event type
		String fullFilePath = System.getProperty("user.dir") + "\\Resources\\TestFiles\\Saves\\SAVE_TestEventName.xml";
		
		// Create the XML document
		Document document = XMLUtils.convertByteArrayToDocument(Files.readAllBytes(new File(fullFilePath).toPath()));	
				
		// Call the method in test (Constructor so instantiate the object)
		new CharacterGameContent(document);
	}
}
