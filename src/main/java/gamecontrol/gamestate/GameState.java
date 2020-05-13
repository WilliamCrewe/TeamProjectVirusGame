package main.java.gamecontrol.gamestate;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

import main.java.filehandling.gamecontent.realisations.CharacterGameContent;
import main.java.filehandling.gamecontent.realisations.EventGameContent;
import main.java.filehandling.gamecontent.realisations.LocationGameContent;
import main.java.filehandling.gamecontent.realisations.SaveGameContent;
import main.java.filehandling.gamecontent.realisations.components.CompletedEvent;
import main.java.filehandling.gamecontent.realisations.components.EventOption;
import main.java.gamecontrol.controllers.difficulty.Difficulty;
import main.java.logging.SystemLogger;
import main.java.random.Rarity;
import main.java.time.TimeLord;

/**
 * Singleton class holding the current state of the game. <br>
 * <b> UPDATE </b> methods trigger the notify observers whereas <b> SET </b>
 * methods do not
 * 
 * @author Daniel
 *
 */
public class GameState extends Observable {
	
	// Fields below are standard game content (and so non-save related)
	private Map<String, LocationGameContent> locationMap = new HashMap<>();
	private Map<String, CharacterGameContent> characterMap = new HashMap<>();
	private Map<Rarity, List<EventGameContent>> nonLocationEventsMap = new EnumMap<>(Rarity.class);
	private Map<String, Map<Rarity, List<EventGameContent>>> locationEventMap= new HashMap<>();
	private Set<EventGameContent> singleOccurenceEventsThatHaveOccured = new HashSet<>();
	private Map<String, EventGameContent> passiveEventMap = new HashMap<>();
	
	private Map<String, EventGameContent> untriggeredEventMap = new HashMap<>();
	
	private LocationGameContent currentLocation;
	private EventGameContent currentEvent;
	private SaveGameContent save = new SaveGameContent("SaveGame", "AAAA", Difficulty.EASY);
	
	private boolean gameOver;
	private TimeLord timeLord;
	
	private static GameState instance;

	/**
	 * @return The currently active instance of the GameState (Instantiating one if it doesn't exist)
	 */
	public static GameState getInstance() {
		if (GameState.instance == null) {
			SystemLogger.config("GameState does not yet exist, will instantiate");
			GameState.instance = new GameState();
		}
		return GameState.instance;
	}
	
	/**
	 * Destroys the current GameState so the next time getInstance is called a new instance is created
	 */
	public static void destroy() {
		GameState.instance = null;
	}

	/**
	 * @param newCharacter
	 */
	public void setNewCharacter(final CharacterGameContent newCharacter) {
		SystemLogger.finer("Adding character %s", newCharacter.getCharacterID());
		this.characterMap.put(newCharacter.getCharacterID(), newCharacter);
	}

	/**
	 * @param newLocation
	 */
	public void setNewLocation(final LocationGameContent newLocation) {
		SystemLogger.finer("Adding location %s", newLocation.getLocationID());
		this.locationMap.put(newLocation.getLocationID(), newLocation);
	}

	/**
	 * @param newEvent
	 */
	public void setNewEvent(final EventGameContent newEvent) {
		// If the event is passive but has no location, this means it will only be triggered by other events (Or item usage)
		if (newEvent.isPassiveEvent() && newEvent.getEventLocationID() == null) {
			SystemLogger.finer("The event %s was identified as being only triggered by a call", newEvent.getEventID());
			untriggeredEventMap.put(newEvent.getEventID(), newEvent);
			return;
		}
		
		// If the event is passive, just add it to the passive event map
		if (newEvent.isPassiveEvent()) {
			SystemLogger.finer("The event %s was identified as the passive event for location %s", newEvent.getEventID(), newEvent.getEventLocationID());
			this.passiveEventMap.put(newEvent.getEventLocationID(), newEvent);
			return;
		}

		// If the event does not have a location, it goes into the non-location events map
		if (newEvent.getEventLocationID() == null) {
			SystemLogger.finer("Adding non-location event %s", newEvent.getEventID());
			this.putEventIntoMap(this.nonLocationEventsMap, newEvent);
			return;
		}
		
		// Get the existing events for the location (may not exist yet)
		Map<Rarity, List<EventGameContent>> existingLocationMap = this.locationEventMap
				.get(newEvent.getEventLocationID());
		
		// If the no events already existed for the location then create a new map and list to store it in
		if (existingLocationMap == null) {
			Map<Rarity, List<EventGameContent>> newLocationMap = new EnumMap<>(Rarity.class);
			this.putEventIntoMap(newLocationMap, newEvent);
			this.locationEventMap.put(newEvent.getEventLocationID(), newLocationMap);
		} else {
			this.putEventIntoMap(existingLocationMap, newEvent);
		}
	}

	/**
	 * Utility method that will add to the existing rarity list if there is one, or will create a new one and add it if it's not there already
	 * @param existingMap
	 * @param newEvent
	 */
	private void putEventIntoMap(final Map<Rarity, List<EventGameContent>> existingMap,
			final EventGameContent newEvent) {
		// Try to get the existing list for the new Events rarity
		final List<EventGameContent> existingList = existingMap.get(newEvent.getRarity());
		
		// If the existing list wasn't found create a new one
		if (existingList == null) {
			final List<EventGameContent> newList = new ArrayList<>();
			newList.add(newEvent);
			existingMap.put(newEvent.getRarity(), newList);
		} else {
			existingList.add(newEvent);
		}
	}

	/**
	 * Marks the event as completed and adds it to the save
	 * @param eventOptionID
	 */
	public void completeEvent(final String eventOptionID) {
		// If the event was single occurence add it to the list of events that has already occured
		if (this.currentEvent.isSingleOccurence()) {
			this.singleOccurenceEventsThatHaveOccured.add(this.currentEvent);
		}
		
		// Update the save to say the event was completed
		this.save.getCompletedEvents()
				.addToCompletedEvent(new CompletedEvent(this.currentEvent.getEventID(), eventOptionID));
		
		// Set the currentEvent back to null
		this.currentEvent = null;
		this.notifyListenersOfGameState();
	}

	/**
	 * Updates the current event to the new one passed in
	 * @param newCurrentEvent
	 */
	public void updateCurrentEvent(final EventGameContent newCurrentEvent) {
		this.currentEvent = newCurrentEvent;
		this.notifyListenersOfGameState();
	}
	
	public void updateCurrentEvent(String eventID) {
		currentEvent = untriggeredEventMap.get(eventID);
	}

	/**
	 * Returns the combined Map of the non-location events as well as those events for the particular location
	 * @return
	 */
	public Map<Rarity, List<EventGameContent>> getEventsForCurrentLocation() {
		SystemLogger.fine("Getting all available events for the current location %s", currentLocation.getLocationID());
		
		// Create a new map to hold the combined values
		Map<Rarity, List<EventGameContent>> mapToReturn = new EnumMap<>(
				Rarity.class);
		
		// Get the events for the current location
		final Map<Rarity, List<EventGameContent>> currentLocationEvents = this.locationEventMap
				.get(this.currentLocation.getLocationID());

		// Loop over each rarity
		for (Rarity rarity : Rarity.values()) {
			final List<EventGameContent> eventGameContents = new ArrayList<>();
			
			List<EventGameContent> locationEvents = new ArrayList<>();
			
			if (currentLocationEvents != null && currentLocationEvents.get(rarity) != null) {
				locationEvents.addAll(currentLocationEvents.get(rarity));
			}
			
			// Add all the location events for this rarity
			eventGameContents.addAll(locationEvents);
			
			List<EventGameContent> nonLocationEvents = nonLocationEventsMap.get(rarity);
			// Add all the non location events for this rarity
			if (nonLocationEvents != null) {
				eventGameContents.addAll(nonLocationEvents);
			}
			
			// Remove any single occurence events that have already run
			this.removeSingleOccurenceEvents(eventGameContents);
			
			// Add the list to the rarity map
			mapToReturn.put(rarity, eventGameContents);
		}
		
		return mapToReturn;
	}

	/**
	 * Removes all single occurence events that have already run from the list passed in
	 * @param eventList
	 */
	private void removeSingleOccurenceEvents(final List<EventGameContent> eventList) {
		eventList.removeAll(this.singleOccurenceEventsThatHaveOccured);
	}

	public void updateLocation(final String locationID) {
		this.currentLocation = this.locationMap.get(locationID);
	}

	public EventGameContent getCurrentEvent() {
		return this.currentEvent;
	}

	public LocationGameContent getCurrentLocation() {
		return this.currentLocation;
	}

	public SaveGameContent getSave() {
		if (timeLord == null) {
			initialiseTimeLordFromSave();
		}
		
		// Make sure the save is up to date 
		save.setTime(timeLord.getTime());
		
		return this.save;
	}
	
	public void setSave(SaveGameContent saveGameContent) {
		this.save = saveGameContent;
		
		initialiseTimeLordFromSave();
		notifyListenersOfGameState();
	}
	
	public void initialiseTimeLordFromSave() {
		timeLord = new TimeLord(save.getTime());
	}

	public synchronized void notifyListenersOfGameState() {
		this.setChanged();
		this.notifyObservers(this);
	}
	
	public void setCurrentEventToPassiveLocationEvent() {
		currentEvent = passiveEventMap.get(currentLocation.getLocationID());
	}
	
	public void setGameOver(boolean newGameOver) {
		gameOver = newGameOver;
		notifyListenersOfGameState();
	}
	
	public boolean isGameOver() {
		return gameOver;
	}
	
	public TimeLord getTimeLord() {
		if (timeLord == null) {
			initialiseTimeLordFromSave();
		}
		
		return timeLord;
	}
	
	/**
	 * @return all the options currently active for the Event
	 */
	public List<EventOption> getActiveOptions() {
		List<EventOption> activeEventOptions = new ArrayList<>();

		if (currentEvent == null) {
			return activeEventOptions;
		}
		
		// Loop over all the event options for the event
		for (EventOption eventOption : currentEvent.getEventOptions().getEventOptionsValues()) {
			// Check both the required item is present as well as the completed event ID
			if (save.getSaveItems().containsItemID(eventOption.getRequiredItemID())
					&& save.getCompletedEvents().containsCompletedEventID(eventOption.getRequiredCompletedEventID())) {
				activeEventOptions.add(eventOption);
			}
		}
		
		return activeEventOptions;
	}
	
	public String toString() {
		return String.format("Current status is location %s, currentEvent %s", currentLocation, currentEvent.getEventID());
	}
}