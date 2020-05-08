package main.java.gamecontrol.controllers;

import main.java.event.types.LoadEvent;
import main.java.filehandling.gamecontent.AbstractGameContent;
import main.java.filehandling.gamecontent.realisations.SaveGameContent;
import main.java.filehandling.reader.GameContentFileHandler;
import main.java.filehandling.reader.exception.FileReadException;
import main.java.filehandling.reader.exception.UnableToWriteContentException;
import main.java.filehandling.xml.exception.XMLParseException;
import main.java.filehandling.xml.exception.XMLValidationException;
import main.java.gamecontrol.gamestate.GameState;
import main.java.logging.SystemLogger;

/**
 * Controller that handles the save of the game
 * @author Daniel
 *
 */
public class SaveController {

	private SaveController() {
		// Private constructor to prevent instantiation
	}
	
	/**
	 * Saves the current save to disk
	 */
	public static void handleSave() {
		SystemLogger.fine("Saving the current game");
		
		// Get the current Save from the GameState
		SaveGameContent save = GameState.getInstance().getSave();
		
		// Write the save to a file in the correct directory
		try {
			GameContentFileHandler.writeGameContentToFile(save);
		} catch (UnableToWriteContentException e) {
			SystemLogger.severe("An exception occured whilst saving the game, reason was %s", e.getMessage());
		}
		
		SystemLogger.config("Save operation finished");
	}
	
	/**
	 * Loads the save file chosen into the GameState
	 * @param loadEvent
	 */
	public static void handleLoad(LoadEvent loadEvent) {
		SystemLogger.fine("Loading the save file %s", loadEvent.getFilePath());
		AbstractGameContent abstractGameContent = null;
		try {
			abstractGameContent = GameContentFileHandler.getGameContentOfFile(loadEvent.getFilePath());
		} catch (XMLParseException | FileReadException | XMLValidationException e) {
			SystemLogger.severe("An exception occured whilst loading the save %s, reason was %s", loadEvent.getFilePath(), e.getMessage());
			return;
		}
		
		// Check if the file content was that of a save
		if (!(abstractGameContent instanceof SaveGameContent)) {
			SystemLogger.severe("The file loaded in was of type %s not a Save file", abstractGameContent.getContentType());
			return;
		}
		
		GameState.getInstance().setSave((SaveGameContent) abstractGameContent);
		SystemLogger.config("The loading of the save file %s finished successfully", loadEvent.getFilePath());
	}
}
