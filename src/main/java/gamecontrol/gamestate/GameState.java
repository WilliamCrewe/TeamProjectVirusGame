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
import main.java.logging.SystemLogger;
import main.java.random.Rarity;

/**
 * Static class holding the current state of the game. <br>
 * <b> UPDATE </b> methods trigger the notify observers whereas <b> SET </b>
 * methods do not
 * 
 * @author Daniel
 *
 */
public class GameState extends Observable {
	
	// Fields below are standard game content (and so non-save related)
	private Map<String, LocationGameContent> locationMap;
	private Map<String, CharacterGameContent> characterMap;
	private Map<Rarity, List<EventGameContent>> nonLocationEventsMap;
	private Map<String, Map<Rarity, List<EventGameContent>>> locationEventMap;
	private Set<EventGameContent> singleOccurenceEventsThatHaveOccured;
	private Map<String, EventGameContent> passiveEventMap;
	private LocationGameContent currentLocation;
	private EventGameContent currentEvent;
	private SaveGameContent save;
	private static GameState instance;

	public GameState() {
		this.locationMap = new HashMap<>();
		this.characterMap = new HashMap<>();
		this.nonLocationEventsMap = new EnumMap<>(Rarity.class);
		this.locationEventMap = new HashMap<>();
		this.singleOccurenceEventsThatHaveOccured = new HashSet<>();
		this.passiveEventMap = new HashMap<>();
	}

	public static GameState getInstance() {
		if (GameState.instance == null) {
			SystemLogger.config("GameState does not yet exist, will instantiate");
			GameState.instance = new GameState();
		}
		return GameState.instance;
	}

	public void setNewCharacter(final CharacterGameContent newCharacter) {
		SystemLogger.finer("Adding character %s", newCharacter.getCharacterID());
		this.characterMap.put(newCharacter.getCharacterID(), newCharacter);
	}

	public void setNewLocation(final LocationGameContent newLocation) {
		SystemLogger.finer("Adding location %s", newLocation.getLocationID());
		this.locationMap.put(newLocation.getLocationID(), newLocation);
	}

	public void setNewEvent(final EventGameContent newEvent) {
		if (newEvent.isPassiveEvent()) {
			SystemLogger.finer("The event %s was identified as the passive event for location %s", newEvent.getEventID(), newEvent.getEventLocationID());
			this.passiveEventMap.put(newEvent.getEventLocationID(), newEvent);
			return;
		}

		if (newEvent.getEventLocationID() == null) {
			SystemLogger.finer("Adding non-location event %s", newEvent.getEventID());
			this.putEventIntoMap(this.nonLocationEventsMap, newEvent);
			return;
		}
		final Map<Rarity, List<EventGameContent>> existingLocationMap = this.locationEventMap
				.get(newEvent.getEventLocationID());
		if (existingLocationMap == null) {
			final Map<Rarity, List<EventGameContent>> newLocationMap = new HashMap<>();
			this.putEventIntoMap(newLocationMap, newEvent);
			this.locationEventMap.put(newEvent.getEventLocationID(), newLocationMap);
		} else {
			this.putEventIntoMap(existingLocationMap, newEvent);
		}
	}

	private void putEventIntoMap(final Map<Rarity, List<EventGameContent>> existingMap,
			final EventGameContent newEvent) {
		final List<EventGameContent> existingList = existingMap.get(newEvent.getRarity());
		if (existingList == null) {
			final List<EventGameContent> newList = new ArrayList<>();
			newList.add(newEvent);
			existingMap.put(newEvent.getRarity(), newList);
		} else {
			existingList.add(newEvent);
		}
	}

	public void completeEvent(final String eventOptionID) {
		if (this.currentEvent.isSingleOccurence()) {
			this.singleOccurenceEventsThatHaveOccured.add(this.currentEvent);
		}
		this.save.getCompletedEvents()
				.addToCompletedEvent(new CompletedEvent(this.currentEvent.getEventLocationID(), eventOptionID));
		this.currentEvent = null;
		this.notifyListenersOfGameState();
	}

	public void updateCurrentEvent(final EventGameContent newCurrentEvent) {
		this.currentEvent = newCurrentEvent;
		this.notifyListenersOfGameState();
	}

	public Map<Rarity, List<EventGameContent>> getEventsForCurrentLocation() {
		final Map<Rarity, List<EventGameContent>> mapToReturn = new EnumMap<>(
				Rarity.class);
		final Map<Rarity, List<EventGameContent>> currentLocationEvents = this.locationEventMap
				.get(this.currentLocation.getLocationID());
		Rarity[] values;
		for (int length = (values = Rarity.values()).length, i = 0; i < length; ++i) {
			final Rarity rarity = values[i];
			final List<EventGameContent> eventGameContents = new ArrayList<EventGameContent>();
			eventGameContents.addAll(currentLocationEvents.get(rarity));
			eventGameContents.addAll(this.nonLocationEventsMap.get(rarity));
			this.removeSingleOccurenceEvents(eventGameContents);
			mapToReturn.put(rarity, eventGameContents);
		}
		return mapToReturn;
	}

	private void removeSingleOccurenceEvents(final List<EventGameContent> eventList) {
		eventList.removeAll(this.singleOccurenceEventsThatHaveOccured);
	}

	public void updateLocation(final String locationID) {
		this.currentLocation = this.locationMap.get(locationID);
		this.setChanged();
		this.notifyObservers(this);
	}

	public EventGameContent getCurrentEvent() {
		return this.currentEvent;
	}

	public LocationGameContent getCurrentLocation() {
		return this.currentLocation;
	}

	public SaveGameContent getSave() {
		return this.save;
	}

	public synchronized void notifyListenersOfGameState() {
		this.setChanged();
		this.notifyObservers(this);
	}
}