package main.game_state;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import main.java.filehandling.gamecontent.realisations.CharacterGameContent;
import main.java.filehandling.gamecontent.realisations.EventGameContent;
import main.java.filehandling.gamecontent.realisations.LocationGameContent;
import main.java.filehandling.gamecontent.realisations.SaveGameContent;

public class GameState extends Observable {
	
	private static GameState GAMESTATEINSTANCE;
	private static SaveGameContent saveGame;
	private static EventGameContent activeEvent;
	
	private final static ArrayList<CharacterGameContent> characters = new ArrayList<>();
	private final static ArrayList<EventGameContent> events = new ArrayList<>();
	private final static ArrayList<LocationGameContent> locations = new ArrayList<>();
	
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
		notifyObservers();
	}
	
	public void increaseDayNumber() {
		saveGame.setDayNumber(saveGame.getDayNumber() + 1);
		notifyObservers();
	}
	
	public void setImmunityLevel(int immunity) {
		saveGame.setImmunity(immunity);
		notifyObservers();
	}
	
	public void setContagionLevel(int contagionLevel) {
		saveGame.setContagionLevel(contagionLevel);
		notifyObservers();
	}
	
	public void setKarma(int karma) {
		saveGame.setKarma(karma);
		notifyObservers();
	}
	
	public static ArrayList<CharacterGameContent> getCharacters() {
		return characters;
	}
	
	public static String getCharacterName(String characterID) {
		for(CharacterGameContent c : characters) {
			if(c.getCharacterID().equals(characterID)) {
				return c.getCharacterName();
			}
		}
		return null;
	}
	
	public static void addCharacter(CharacterGameContent character) {
		characters.add(character);
	}
	
	public static ArrayList<EventGameContent> getEvents() {
		return events;
	}
	
	public static EventGameContent getEvent(String eventID) {
		for(EventGameContent e : events) {
			if(e.getEventID().equals(eventID)) {
				return e;
			}
		}
		return null;
	}
	
	public static void addEvent(EventGameContent event) {
		events.add(event);
	}
	
	public static ArrayList<LocationGameContent> getLocations() {
		return locations;
	}
	
	public static String getLocationName(String locationID) {
		for(LocationGameContent l : locations) {
			if(l.getLocationID().equals(locationID)) {
				return l.getLocationName();
			}
		}
		return "No location found with supplied ID";
	}
	
	public static void addLocation(LocationGameContent location) {
		System.out.println("Location added: " + location.getLocationName());
		locations.add(location);
	}

}
