package test.java.gamecontrol.controllers;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import main.java.event.types.LoadEvent;
import main.java.filehandling.reader.FileReader;
import main.java.gamecontrol.controllers.SaveController;
import main.java.gamecontrol.gamestate.GameState;
import main.java.properties.PropertyManager;

public class SaveControllerTest {

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	private File baseFolder;

	@Before
	public void beforeTest() throws Exception {
		folder.create();
		baseFolder = folder.newFolder("Saves");
		PropertyManager.setBaseDirectory(folder.getRoot().getAbsolutePath());
		
		GameState.destroy();
	}
	
	/**
	 * Positive test case for when the file is saved
	 * @throws Exception
	 */
	@Test
	public void testHandleSave() throws Exception {
		// Call the method in test
		SaveController.handleSave();
				
		// Assert the file has been added
		String[] fileNames = FileReader.getFilesInDirectory(baseFolder.toString());
		
		assertEquals(1, fileNames.length);
		
		assertEquals("SAVE_SaveGame.xml", fileNames[0]);
	}
	
	/**
	 * Negative test case for when the file cannot be saved due to the directory not existing
	 * @throws Exception
	 */
	@Test
	public void testHandleSaveNoDirectoryToSave() throws Exception {
		PropertyManager.setBaseDirectory(folder.getRoot().getAbsolutePath() + "NotADirectory");
		
		// Call the method in test
		SaveController.handleSave();
	}
	
	/**
	 * Positive test case for when the save file is successfully loaded
	 */
	@Test
	public void testHandleLoad() {
		// Set the file to load in
		String fullFilePath = System.getProperty("user.dir") + "\\Resources\\TestFiles\\Saves\\SAVE_TestSaveName.xml";
		
		// Set the XSD location
		PropertyManager.setXSDLocation(System.getProperty("user.dir") + "\\Resources\\XSD/VirusGameXSD.xsd");
		
		// Call the method in test
		SaveController.handleLoad(new LoadEvent(fullFilePath));
		
		// Assert the save in the gameState is as expected
		assertEquals("SAVE_TestSaveName.xml", GameState.getInstance().getSave().getFileName());
	}
	
	/**
	 * Negative test case for when the save file is not found and so cannot loaded
	 */
	@Test
	public void testHandleLoadCouldNotFindFIle() {
		// Set the file to load in
		String fullFilePath = System.getProperty("user.dir") + "\\Resources\\TestFiles\\Saves\\NotAFile.xml";
		
		// Set the XSD location
		PropertyManager.setXSDLocation(System.getProperty("user.dir") + "\\Resources\\XSD/VirusGameXSD.xsd");
		
		// Call the method in test
		SaveController.handleLoad(new LoadEvent(fullFilePath));
		
		// Assert the save in the gameState is as expected (The default)
		assertEquals("SAVE_SaveGame.xml", GameState.getInstance().getSave().getFileName());
	}
	
	/**
	 * Negative test case for when the file is found but is not a save and so cannot loaded
	 */
	@Test
	public void testHandleLoadIncorrectFileType() {
		// Set the file to load in
		String fullFilePath = System.getProperty("user.dir") + "\\Resources\\TestFiles\\Events\\EVENT_TestEventName.xml";
		
		// Set the XSD location
		PropertyManager.setXSDLocation(System.getProperty("user.dir") + "\\Resources\\XSD/VirusGameXSD.xsd");
		
		// Call the method in test
		SaveController.handleLoad(new LoadEvent(fullFilePath));
		
		// Assert the save in the gameState is as expected (The default)
		assertEquals("SAVE_SaveGame.xml", GameState.getInstance().getSave().getFileName());
	}
}
