package main.java.gamecontrol.controllers;

import java.util.Random;

import main.java.event.types.LoadEvent;
import main.java.event.types.NewSaveEvent;
import main.java.filehandling.gamecontent.AbstractGameContent;
import main.java.filehandling.gamecontent.realisations.SaveGameContent;
import main.java.filehandling.reader.GameContentFileHandler;
import main.java.filehandling.reader.exception.FileReadException;
import main.java.filehandling.reader.exception.UnableToWriteContentException;
import main.java.filehandling.xml.exception.XMLParseException;
import main.java.filehandling.xml.exception.XMLValidationException;
import main.java.gamecontrol.controllers.difficulty.Difficulty;
import main.java.gamecontrol.gamestate.GameState;
import main.java.logging.SystemLogger;

/**
 * Controller that handles the save of the game
 * @author Daniel
 *
 */
public class SaveController {

	private static Random random = new Random();
	
	private SaveController() {
		// Private constructor to prevent instantiation
	}
	
	public static void handleSaveCreation(NewSaveEvent newSaveEvent) {
		SystemLogger.fine("Creating a new save with details %s, %s, %s", newSaveEvent.getSaveName(), newSaveEvent.getSeed(), newSaveEvent.getDifficulty());
		
		Difficulty difficulty = Difficulty.getByXmlValue(newSaveEvent.getDifficulty());
		String seed = newSaveEvent.getSeed();
		
		// If the seed was not passed generate a random one
		if (seed == null || seed.isEmpty()) {
			seed = Integer.toHexString(random.nextInt());
			SystemLogger.fine("The random seed of %s was created", seed);
		}
		
		// Create the new save
		SaveGameContent newSave = new SaveGameContent(newSaveEvent.getSaveName(), seed, difficulty);
		
		// Set the new save on the GameState
		GameState.getInstance().setSave(newSave);
		
		// Persist the new save on disk
		handleSave();
		
		SystemLogger.config("The new save %s was created", newSaveEvent.getSaveName());
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
