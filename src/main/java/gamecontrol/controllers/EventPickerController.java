package main.java.gamecontrol.controllers;

import java.util.List;
import java.util.Map;

import main.java.filehandling.gamecontent.realisations.EventGameContent;
import main.java.gamecontrol.gamestate.GameState;
import main.java.logging.SystemLogger;
import main.java.random.RandomnessHandler;
import main.java.random.Rarity;

/**
 * 
 * Class to handle the selecting of a new random event and the setting of it on the GameState
 * @author Daniel
 *
 */
public class EventPickerController {

	/**
	 * Picks an event randomly from those available in the current location and sets it on the gameState as the current event
	 */
	public void pickAndSetRandomEvent() {
		GameState gameStateInstance = GameState.getInstance();
		
		// Get all the events available at the current location
		Map<Rarity, List<EventGameContent>> availableEvents = GameState.getInstance().getEventsForCurrentLocation();
		
		// Get a random rarity to start with
		Rarity randomRarity = RandomnessHandler.getRandomRarity();
		
		List<EventGameContent> eventsMatchingRarity = availableEvents.get(randomRarity);
		
		// Handle the scenario where there is no event for the rarity chosen by trying a more common rarity
		Rarity offSetRarity = randomRarity.getMoreCommonRarity();
		while (eventsMatchingRarity.isEmpty()) {
			SystemLogger.finer("No events were found with rarity %s, will attempt to find with rarity %s", randomRarity, offSetRarity);
			eventsMatchingRarity = availableEvents.get(randomRarity);
			
			// Exception will be thrown after common is checked unsuccessfully (So won't go infinite)s
			offSetRarity = randomRarity.getMoreCommonRarity();
			
		}
		
		// Gets a random integer for the index to use
		int indexToReturn = RandomnessHandler.getIntInRange(eventsMatchingRarity.size());
		
		// Update the gameState to have the selected event set as the CurrentEvent
		gameStateInstance.updateCurrentEvent(eventsMatchingRarity.get(indexToReturn));
	}
	
}
