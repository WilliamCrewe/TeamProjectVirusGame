package main.java.gamecontrol.helpers;

import java.io.File;
import java.util.List;

import main.game_state.GameState;
import main.java.filehandling.gamecontent.AbstractGameContent;
import main.java.filehandling.gamecontent.realisations.*;
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
	
	public static void intitialiseGameState(String saveName, String seed) {
		SystemLogger.config("Starting the initialisation of the GameState");
		
		
		// Read in the characters
		initialiseCharacters();
		
		// Read in the locations
		initialiseLocations();
		
		// Read in the events
		initialiseEvents();
		
		// Read in a save game
		initialiseSaveGame(saveName, seed);
	}
	
	private static void initialiseCharacters() {
		SystemLogger.fine("Starting the initialisation of the GameState Characters");
		String characterDirectoryPath = PropertyManager.getBaseDirectory() + "\\Resources\\GameFiles" + File.separator + GameDirectory.CHARACTERS_DIRECTORY.getDirctoryName();
		String[] fileNames = FileReader.getFilesInDirectory(characterDirectoryPath);
		for (String filePath : fileNames) {
			try {
				AbstractGameContent aGC = GameContentFileHandler.getGameContentOfFile(characterDirectoryPath + File.separator + filePath);

				if(aGC instanceof CharacterGameContent) {
					GameState.addCharacter((CharacterGameContent) aGC);
				}
				
			} catch (XMLParseException | FileReadException | XMLValidationException e) {
				SystemLogger.severe("An exception occured reading in the contents of file %s, reason was %s", filePath, e.getMessage());
			}
		}
		SystemLogger.finer("Finished the initialisation of the GameState Characters");
		
	}
	
	private static void initialiseLocations() {
		SystemLogger.fine("Starting the initialisation of the GameState Locations");
		String characterDirectoryPath = PropertyManager.getBaseDirectory() + "\\Resources\\GameFiles"  + File.separator + GameDirectory.LOCATIONS_DIRECTORY.getDirctoryName();
		String[] fileNames = FileReader.getFilesInDirectory(characterDirectoryPath);
		for (String filePath : fileNames) {
			try {
				AbstractGameContent aGC = GameContentFileHandler.getGameContentOfFile(characterDirectoryPath + File.separator + filePath);

				if(aGC instanceof LocationGameContent) {
					GameState.addLocation((LocationGameContent) aGC);
				}
				
			} catch (XMLParseException | FileReadException | XMLValidationException e) {
				SystemLogger.severe("An exception occured reading in the contents of file %s, reason was %s", filePath, e.getMessage());
			}
		}
		SystemLogger.finer("Finished the initialisation of the GameState Locations");
		
	}
	
	private static void initialiseEvents() {
		SystemLogger.fine("Starting the initialisation of the GameState Events");
		String characterDirectoryPath = PropertyManager.getBaseDirectory() + "\\Resources\\GameFiles" + File.separator + GameDirectory.EVENTS_DIRECTORY.getDirctoryName();
		String[] fileNames = FileReader.getFilesInDirectory(characterDirectoryPath);
		for (String filePath : fileNames) {
			try {
				AbstractGameContent aGC = GameContentFileHandler.getGameContentOfFile(characterDirectoryPath + File.separator + filePath);

				if(aGC instanceof EventGameContent) {
					GameState.addEvent((EventGameContent) aGC);
				}
				
				
			} catch (XMLParseException | FileReadException | XMLValidationException e) {
				SystemLogger.severe("An exception occured reading in the contents of file %s, reason was %s", filePath, e.getMessage());
			}
		}
		SystemLogger.finer("Finished the initialisation of the GameState Events");
		
	}
	
	private static void initialiseSaveGame(String saveName, String seed) {
		SystemLogger.fine("Starting the initialisation of the GameState Save Game");
		String characterDirectoryPath = PropertyManager.getBaseDirectory() + "\\Resources\\GameFiles" + File.separator + GameDirectory.SAVES_DIRECTORY.getDirctoryName();
		String[] fileNames = FileReader.getFilesInDirectory(characterDirectoryPath);
		if(fileNames != null) {
			for (String filePath : fileNames) {
				try {
					AbstractGameContent sGC = GameContentFileHandler.getGameContentOfFile(characterDirectoryPath + File.separator + filePath);

					if(sGC instanceof SaveGameContent) {
						if(((SaveGameContent) sGC).getSaveName().equals(saveName)) {
							GameState.loadGameState((SaveGameContent) sGC);
						}else {
							GameState.createGameState(saveName, seed);
							SystemLogger.fine("No save found with given save name, new save created; save name: " + saveName + ", seed: " + seed + ".");
						}
					}
					
				} catch (XMLParseException | FileReadException | XMLValidationException e) {
					SystemLogger.severe("An exception occured reading in the contents of file %s, reason was %s", filePath, e.getMessage());
				}
			}
		} else {
			GameState.createGameState(saveName, seed);
			SystemLogger.fine("No save data found, new save created; save name: " + saveName + ", seed: " + seed + ".");
		}
		
		SystemLogger.finer("Finished the initialisation of the GameState Save Game");
		
	}
}