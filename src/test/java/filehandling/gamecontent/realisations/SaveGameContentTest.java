package test.java.filehandling.gamecontent.realisations;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.nio.file.Files;

import org.junit.Test;
import org.w3c.dom.Document;

import main.java.filehandling.gamecontent.ContentType;
import main.java.filehandling.gamecontent.realisations.SaveGameContent;
import main.java.filehandling.xml.XMLUtils;
import main.java.filehandling.xml.exception.XMLParseException;

public class SaveGameContentTest {

	/**
	 * Positive test case for when the constructor is called and the XML is valid
	 * and contains all expected values
	 * 
	 * @throws XMLParseException
	 */
	@Test
	public void testConstructorNoDocument() throws Exception {
		// Call the method in test (Constructor so instantiate the object)
		SaveGameContent saveGameContent = new SaveGameContent("NewSaveName", "AABBCC");

		// Assert the values on the object are as expected
		assertEquals(ContentType.SAVE, saveGameContent.getContentType());
		assertEquals("NewSaveName", saveGameContent.getSaveName());
		assertEquals("AABBCC", saveGameContent.getSeed());
		assertEquals(0, saveGameContent.getDayNumber());
		assertEquals("SaveGameContent object with values: saveName=NewSaveName, seed=AABBCC, dayNumber=0",
				saveGameContent.toString());
	}
	
	/**
	 * Positive test case for when the constructor is called and the XML is valid
	 * and contains all expected values
	 * 
	 * @throws XMLParseException
	 */
	@Test
	public void testConstructor() throws Exception {
		// This XML is for the save event type
		String fullFilePath = System.getProperty("user.dir") + "\\Resources\\TestFiles\\Saves\\SAVE_TestEventName.xml";

		// Create the XML document
		Document document = XMLUtils.convertByteArrayToDocument(Files.readAllBytes(new File(fullFilePath).toPath()));

		// Call the method in test (Constructor so instantiate the object)
		SaveGameContent saveGameContent = new SaveGameContent(document);

		// Assert the values on the object are as expected
		assertEquals(ContentType.SAVE, saveGameContent.getContentType());
		assertEquals("TestSaveName", saveGameContent.getSaveName());
		assertEquals("AABBCC12", saveGameContent.getSeed());
		assertEquals(42, saveGameContent.getDayNumber());
		assertEquals("SaveGameContent object with values: saveName=TestSaveName, seed=AABBCC12, dayNumber=42",
				saveGameContent.toString());
	}

	/**
	 * Negative test case for when the constructor is called but the XML is invalid
	 * for this object type
	 * 
	 * @throws XMLParseException
	 */
	@Test(expected = XMLParseException.class)
	public void testConstructorIncorrectXML() throws Exception {
		String fullFilePath = System.getProperty("user.dir")
				+ "\\Resources\\TestFiles\\Events\\EVENT_TestEventName.xml";

		// Create the XML document
		Document document = XMLUtils.convertByteArrayToDocument(Files.readAllBytes(new File(fullFilePath).toPath()));

		// Call the method in test (Constructor so instantiate the object)
		new SaveGameContent(document);
	}

	/**
	 * Positive test case for when the toXMLString method is called
	 * @throws Exception
	 */
	@Test
	public void testToXMLString() throws Exception {
		// This XML is for the save event type
		String fullFilePath = System.getProperty("user.dir") + "\\Resources\\TestFiles\\Saves\\SAVE_TestEventName.xml";

		// Create the XML document
		Document document = XMLUtils.convertByteArrayToDocument(Files.readAllBytes(new File(fullFilePath).toPath()));

		// Call the method in test (Constructor so instantiate the object)
		SaveGameContent saveGameContent = new SaveGameContent(document);

		String expectedXML = "<Content>" + "<Save>" + "<SaveName>TestSaveName</SaveName>" + "<Seed>AABBCC12</Seed>"
				+ "<DayNumber>42</DayNumber>" + "<Immunity>20</Immunity>" + "<ContagionLevel>15</ContagionLevel>"
				+ "<Karma>16</Karma>" + "<CurrentLocationID>TestLocationID</CurrentLocationID>" + "<CompletedEvents>"
				+ "<CompletedEvent>" + "<EventID>TestEventID1</EventID>"
				+ "<EventOptionID>TestOptionID1</EventOptionID>" + "</CompletedEvent>" + "<CompletedEvent>"
				+ "<EventID>TestEventID2</EventID>" + "<EventOptionID>TestOptionID2</EventOptionID>"
				+ "</CompletedEvent>" + "</CompletedEvents>" + "<SaveItems>" + "<Item>" + "<ItemID>ItemID1</ItemID>"
				+ "<ItemName>ItemNameOne</ItemName>" + "<ItemCount>2</ItemCount>" + "</Item>" + "</SaveItems>"
				+ "</Save>" + "</Content>";
		assertEquals(expectedXML, new String(saveGameContent.serialize()));
	}

}
