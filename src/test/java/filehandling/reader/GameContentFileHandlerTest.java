package test.java.filehandling.reader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.nio.file.Files;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import main.java.filehandling.gamecontent.AbstractGameContent;
import main.java.filehandling.gamecontent.ContentType;
import main.java.filehandling.gamecontent.realisations.SaveGameContent;
import main.java.filehandling.reader.GameContentFileHandler;
import main.java.filehandling.reader.exception.FileReadException;
import main.java.filehandling.reader.exception.UnableToWriteContentException;
import main.java.filehandling.xml.exception.XMLParseException;
import main.java.filehandling.xml.exception.XMLValidationException;
import main.java.properties.PropertyManager;

public class GameContentFileHandlerTest {

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	private File baseFolder;

	@Before
	public void setUp() throws Exception {
		folder.create();
		baseFolder = folder.newFolder();
		baseFolder.toPath().resolve("Saves").toFile().mkdir();
	}
	
	/**
	 * Positive test case for when the contents of the file is successfully read in
	 * @throws XMLParseException
	 * @throws FileReadException
	 * @throws XMLValidationException
	 */
	@Test
	public void testGetGameContentOfFile() throws XMLParseException, FileReadException, XMLValidationException {
		String fullFilePath = System.getProperty("user.dir") + "\\Resources\\TestFiles\\Saves\\SAVE_TestSaveName.xml";
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
	
	/**
	 * 
	 * Positive test case for when the GameContent type can be written to file
	 * @throws Exception
	 */
	@Test
	public void testWriteGameContentToFile() throws Exception {
		PropertyManager.setBaseDirectory(baseFolder.getAbsolutePath());
		
		String fullFilePath = System.getProperty("user.dir") + "\\Resources\\TestFiles\\Saves\\SAVE_TestSaveName.xml";
		PropertyManager.setXSDLocation(System.getProperty("user.dir") + "\\Resources\\XSD/VirusGameXSD.xsd");
		
		// Read in the existing file
		AbstractGameContent gameContent = GameContentFileHandler.getGameContentOfFile(fullFilePath);
		
		// Cast it so we can handle it properly
		SaveGameContent saveGameContent = (SaveGameContent) gameContent;
		
		
		// Write the contents of the file to the temporary folder
		GameContentFileHandler.writeGameContentToFile(gameContent);
		
		assertEquals(saveGameContent.toXMLString(), new String(Files.readAllBytes(new File(baseFolder.getAbsolutePath() + "\\Saves\\SAVE_TestSaveName.xml").toPath())));
	}
	
	/**
	 * 
	 * Negative test case for when the GameContent type does not implement XMLFileWritable
	 * @throws Exception
	 */
	@Test (expected = UnableToWriteContentException.class)
	public void testWriteGameContentToFileNotImplementingXMLFileWritable() throws Exception {
		PropertyManager.setBaseDirectory(baseFolder.getAbsolutePath());
		
		String fullFilePath = System.getProperty("user.dir") + "\\Resources\\TestFiles\\Events\\EVENT_TestEventName.xml";
		PropertyManager.setXSDLocation(System.getProperty("user.dir") + "\\Resources\\XSD/VirusGameXSD.xsd");
		
		// Read in the existing file (EVENT so not implementing XMLFileWritable)
		AbstractGameContent gameContent = GameContentFileHandler.getGameContentOfFile(fullFilePath);
		
		
		// Write the contents of the file to the temporary folder
		GameContentFileHandler.writeGameContentToFile(gameContent);
	}
}
