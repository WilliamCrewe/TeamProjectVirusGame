package test.java.filehandling.gamecontent.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.nio.file.Files;

import org.junit.Test;

import main.java.filehandling.gamecontent.AbstractGameContent;
import main.java.filehandling.gamecontent.ContentType;
import main.java.filehandling.gamecontent.factory.GameContentFactory;
import main.java.filehandling.gamecontent.realisations.CharacterGameContent;
import main.java.filehandling.gamecontent.realisations.EventGameContent;
import main.java.filehandling.gamecontent.realisations.LocationGameContent;
import main.java.filehandling.gamecontent.realisations.SaveGameContent;
import main.java.filehandling.xml.exception.XMLParseException;

public class GameContentFactoryTest {

	/**
	 * Positive test case for when the contents of the XML file are successfully converted to the correct realisation of the AbstractGameContent
	 * @throws XMLParseException
	 */
	@Test
	public void testSeriliazeFileContentSave() throws Exception {
		// This XML is for the save event type
		String fullFilePath = System.getProperty("user.dir") + "\\Resources\\TestFiles\\Saves\\SAVE_TestSaveName.xml";
		
		// Call the method in test
		AbstractGameContent gameContent = GameContentFactory.seriliazeFileContent(Files.readAllBytes(new File(fullFilePath).toPath()));
		
		// Assert the ContentType is correct
		assertEquals(ContentType.SAVE, gameContent.getContentType());
		
		// Assert the object returned is of SaveGameContent type
		if (!(gameContent instanceof SaveGameContent)) {
			fail();
		}
	}
	
	/**
	 * Positive test case for when the contents of the XML file are successfully converted to the correct realisation of the AbstractGameContent
	 * @throws XMLParseException
	 */
	@Test
	public void testSeriliazeFileContentLocation() throws Exception {
		String fullFilePath = System.getProperty("user.dir")
				+ "\\Resources\\TestFiles\\Locations\\LOCATION_TestLocationName.xml";

		// Call the method in test
		AbstractGameContent gameContent = GameContentFactory.seriliazeFileContent(Files.readAllBytes(new File(fullFilePath).toPath()));
		
		// Assert the ContentType is correct
		assertEquals(ContentType.LOCATION, gameContent.getContentType());
		
		// Assert the object returned is of LocationGameContent type
		if (!(gameContent instanceof LocationGameContent)) {
			fail();
		}
		
	}
	
	/**
	 * Positive test case for when the contents of the XML file are successfully converted to the correct realisation of the AbstractGameContent
	 * @throws XMLParseException
	 */
	@Test
	public void testSeriliazeFileContentCharacter() throws Exception {
		String fullFilePath = System.getProperty("user.dir") + "\\Resources\\TestFiles\\Characters\\CHARACTER_TestCharacterName.xml";
			
		// Call the method in test
		AbstractGameContent gameContent = GameContentFactory.seriliazeFileContent(Files.readAllBytes(new File(fullFilePath).toPath()));
		
		// Assert the ContentType is correct
		assertEquals(ContentType.CHARACTER, gameContent.getContentType());
		
		// Assert the object returned is of SaveGameContent type
		if (!(gameContent instanceof CharacterGameContent)) {
			fail();
		}
	}
	
	/**
	 * Positive test case for when the contents of the XML file are successfully converted to the correct realisation of the AbstractGameContent
	 * @throws XMLParseException
	 */
	@Test
	public void testSeriliazeFileContentEvent() throws Exception {
		String fullFilePath = System.getProperty("user.dir")
				+ "\\Resources\\TestFiles\\Events\\EVENT_TestEventName.xml";

		// Call the method in test
		AbstractGameContent gameContent = GameContentFactory.seriliazeFileContent(Files.readAllBytes(new File(fullFilePath).toPath()));
		
		// Assert the ContentType is correct
		assertEquals(ContentType.EVENT, gameContent.getContentType());
		
		// Assert the object returned is of SaveGameContent type
		if (!(gameContent instanceof EventGameContent)) {
			fail();
		}
		
	}
}
