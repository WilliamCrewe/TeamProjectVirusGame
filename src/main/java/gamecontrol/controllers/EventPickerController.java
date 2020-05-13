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

	private EventPickerController() {
		// private method to prevent instantiation
	}
	
	/**
	 * Picks an event randomly from those available in the current location and sets it on the gameState as the current event
	 */
	public static void pickAndSetRandomEvent() {
		SystemLogger.config("Choosing a new random event");
		GameState gameStateInstance = GameState.getInstance();
		
		// No random events should occur at home
		if (gameStateInstance.getCurrentLocation().getLocationID().equals("Home")) {
			return;
		}
		
		// Get all the events available at the current location
		Map<Rarity, List<EventGameContent>> availableEvents = GameState.getInstance().getEventsForCurrentLocation();
		
		// Get a random rarity to start with
		Rarity randomRarity = RandomnessHandler.getRandomRarity();
		
		List<EventGameContent> eventsMatchingRarity = availableEvents.get(randomRarity);
		
		// Handle the scenario where there is no event for the rarity chosen by trying a more common rarity
		Rarity offSetRarity = randomRarity;
		
		while (eventsMatchingRarity.isEmpty()) {
			SystemLogger.finer("No events were found with rarity %s, will attempt to find with rarity %s", offSetRarity, offSetRarity.getMoreCommonRarity());
			
			// Exception will be thrown after common is checked unsuccessfully (So won't go infinite)
			offSetRarity = offSetRarity.getMoreCommonRarity();
			
			eventsMatchingRarity = availableEvents.get(offSetRarity);
		}
		
		// Gets a random integer for the index to use
		int indexToReturn = RandomnessHandler.getIntInRange(eventsMatchingRarity.size());
		
		EventGameContent chosenEvent = eventsMatchingRarity.get(indexToReturn);
		
		SystemLogger.config("The new event %s with rarity %s was randomly chosen", chosenEvent.getEventID(), chosenEvent.getRarity());
		
		// Update the gameState to have the selected event set as the CurrentEvent
		gameStateInstance.updateCurrentEvent(chosenEvent);
	}
	
}
