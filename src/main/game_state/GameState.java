package main.game_state;

import java.util.HashMap;
import java.util.Observable;
import main.java.filehandling.gamecontent.realisations.CharacterGameContent;
import main.java.filehandling.gamecontent.realisations.EventGameContent;
import main.java.filehandling.gamecontent.realisations.LocationGameContent;
import main.java.filehandling.gamecontent.realisations.SaveGameContent;

public class GameState extends Observable {
	
	private static GameState GAMESTATEINSTANCE;
	private static SaveGameContent saveGame;
	private static EventGameContent activeEvent;
	
	private final static HashMap<String, CharacterGameContent> characters = new HashMap<>();
	private final static HashMap<String, EventGameContent> events = new HashMap<>();
	private final static HashMap<String, LocationGameContent> locations = new HashMap<>();
	
	private GameState(String saveName, String seed) {
		GameState.saveGame = new SaveGameContent(saveName, seed);
	}
	
	private GameState(SaveGameContent saveGame) {
		GameState.saveGame = saveGame;
	}
	
	public static GameState createGameState(String saveName, String seed) {
		if(GAMESTATEINSTANCE == null) {
			GAMESTATEINSTANCE = new GameState(saveName, seed);
		}
		return GAMESTATEINSTANCE;
	}
	
	public static GameState loadGameState(SaveGameContent saveGame) {
		if(GAMESTATEINSTANCE == null) {
			GAMESTATEINSTANCE = new GameState(saveGame);
		}
		return GAMESTATEINSTANCE;
	}
	
	public static GameState getGameState() {
		return GAMESTATEINSTANCE;
	}
	
	public SaveGameContent getSaveGame() {
		return saveGame;
	}
	
	public EventGameContent getActiveEvent() {
		return activeEvent;
	}
	
	public void setActiveEvent(EventGameContent activeEvent) {
		GameState.activeEvent = activeEvent;
		notifyObservers(GAMESTATEINSTANCE);
	}
	
	public void increaseDayNumber() {
		saveGame.setDayNumber(saveGame.getDayNumber() + 1);
		notifyObservers(GAMESTATEINSTANCE);
	}
	
	public void setImmunityLevel(int immunity) {
		saveGame.adjustImmunity(immunity);
		notifyObservers(GAMESTATEINSTANCE);
	}
	
	public void setContagionLevel(int contagionLevel) {
		saveGame.adjustContagionLevel(contagionLevel);
		notifyObservers(GAMESTATEINSTANCE);
	}
	
	public void setKarma(int karma) {
		saveGame.adjustKarma(karma);
		notifyObservers(GAMESTATEINSTANCE);
	}
	
	public static HashMap<String, CharacterGameContent> getCharacters() {
		return characters;
	}
	
	public static String getCharacterName(String characterID) {
		if(characters.get(characterID) != null) {
			return characters.get(characterID).getCharacterName();
		}
		return null;
	}
	
	public static void addCharacter(CharacterGameContent character) {
		characters.put(character.getCharacterID(), character);
	}
	
	public static HashMap<String, EventGameContent> getEvents() {
		return events;
	}
	
	public static EventGameContent getEvent(String eventID) {
		if(events.get(eventID) != null) {
			return events.get(eventID);
		}
		return null;
	}
	
	public static void addEvent(EventGameContent event) {
		events.put(event.getEventID(), event);
		System.out.println("Event added: " + event.getEventID());
	}
	
	public static HashMap<String, LocationGameContent> getLocations() {
		return locations;
	}
	
	public static LocationGameContent getLocation(String locationID) {
		if(locations.get(locationID) != null) {
			return locations.get(locationID);
		}
		return null;
	}
	
	public static void addLocation(LocationGameContent location) {
		locations.put(location.getLocationID(), location);
		System.out.println("Location added: " + location.getLocationID());
	}

}
