package test.java.filehandling.reader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import main.java.filehandling.gamecontent.AbstractGameContent;
import main.java.filehandling.gamecontent.ContentType;
import main.java.filehandling.gamecontent.realisations.SaveGameContent;
import main.java.filehandling.reader.GameContentFileHandler;
import main.java.filehandling.reader.exception.FileReadException;
import main.java.filehandling.xml.exception.XMLParseException;
import main.java.filehandling.xml.exception.XMLValidationException;
import main.java.properties.PropertyManager;

public class GameContentFileHandlerTest {

	/**
	 * Positive test case for when the contents of the file is successfully read in
	 * @throws XMLParseException
	 * @throws FileReadException
	 * @throws XMLValidationException
	 */
	@Test
	public void testGetGameContentOfFile() throws XMLParseException, FileReadException, XMLValidationException {
		String fullFilePath = System.getProperty("user.dir") + "\\Resources\\TestFiles\\Saves\\SAVE_TestEventName.xml";
		PropertyManager.setXSDLocation(System.getProperty("user.dir") + "\\Resources\\XSD/VirusGameXSD.xsd");
		
		// call the method in test
		AbstractGameContent gameContent = GameContentFileHandler.getGameContentOfFile(fullFilePath);
		
		// Assert the ContentType is correct
		assertEquals(ContentType.SAVE, gameContent.getContentType());
		
		// Assert the object returned is of SaveGameContent type
		if (!(gameContent instanceof SaveGameContent)) {
			fail();
		}
		
		SaveGameContent saveGameContent = (SaveGameContent) gameContent;
		
		// Assert the values of the object are set as expected
		assertEquals("TestSaveName", saveGameContent.getSaveName());
		assertEquals("AABBCC12", saveGameContent.getSeed());
		assertEquals(42, saveGameContent.getDayNumber());
		assertEquals("SaveGameContent object with values: saveName=TestSaveName, seed=AABBCC12, dayNumber=42", saveGameContent.toString());
	}
}
