package main.java.gamecontrol.gamestate;

import java.io.File;
import java.util.List;

import main.java.filehandling.reader.FileReader;
import main.java.filehandling.reader.GameContentFileHandler;
import main.java.filehandling.reader.GameDirectory;
import main.java.filehandling.reader.exception.FileReadException;
import main.java.filehandling.xml.exception.XMLParseException;
import main.java.filehandling.xml.exception.XMLValidationException;
import main.java.logging.SystemLogger;
import main.java.properties.PropertyManager;

/**
 * Class containing the logic to initialise the gamestate with the contents of the various files containing the game contents
 * @author Daniel
 *
 */
public class GameStateInitialisationHelper {

	private GameStateInitialisationHelper() {
		// Private constructor to prevent instantiation
	}
	
	public static void intitialiseGameState() {
		SystemLogger.config("Starting the initialisation of the GameState");
		
		// Read in the characters
		initialiseCharacters();
		
		// Read in the locations
		SystemLogger.fine("Starting the initialisation of the GameState locations");
		SystemLogger.finer("Finished the initialisation of the GameState locations");
		
		// Read in the events
		SystemLogger.fine("Starting the initialisation of the GameState events");
		SystemLogger.finer("Finished the initialisation of the GameState events");
		
	}
	
	private static void initialiseCharacters() {
		SystemLogger.fine("Starting the initialisation of the GameState Characters");
		String characterDirectoryPath = PropertyManager.getBaseDirectory()+ File.separator + GameDirectory.CHARACTERS_DIRECTORY.getDirctoryName();
		String[] fileNames = FileReader.getFilesInDirectory(characterDirectoryPath);
		for (String filePath : fileNames) {
			try {
				GameContentFileHandler.getGameContentOfFile(characterDirectoryPath + File.separator + filePath);
				
				// TODO add to gameState
			} catch (XMLParseException | FileReadException | XMLValidationException e) {
				SystemLogger.severe("An exception occured reading in the contents of file %s, reason was %s", filePath, e.getMessage());
			}
		}
		SystemLogger.finer("Finished the initialisation of the GameState Characters");
		
	}
}
