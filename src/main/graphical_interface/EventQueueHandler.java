package main.graphical_interface;

import main.java.event.EventQueue;
import main.java.event.types.EventActionEvent;
import main.java.event.types.LoadEvent;
import main.java.event.types.MoveEvent;
import main.java.event.types.NewSaveEvent;
import main.java.event.types.PassiveEvent;
import main.java.event.types.SaveEvent;
import main.java.event.types.SleepEvent;

/**
 * @author wcrewe
 *
 */
public class EventQueueHandler {
	
	
	public EventQueueHandler() {
	}
	
	/**
	 * @param eventID
	 * @param eventOptionID
	 * 
	 * Creates an ActionEvent from the player's action choice and adds it to the EventQueue
	 */
	public void addActionEvent(String eventID, String eventOptionID) {
		EventQueue.getInstance().add(new EventActionEvent(eventID, eventOptionID));
	}
	
	/**
	 * @param newLocationID
	 * 
	 * Creates a MoveEvent from the player's location choice and adds it to the EventQueue
	 */
	public void addMoveEvent(String newLocationID) {
		EventQueue.getInstance().add(new MoveEvent(newLocationID));
	}
	
	public void addPassiveEvent() {
		EventQueue.getInstance().add(new PassiveEvent());
	}
	
	/**
	 * @param s: String
	 * 
	 * Recieves the file locatation as a String and loads it as an event into the EventQueue.
	 */
	public void loadGame(String s) {
		EventQueue.getInstance().add(new LoadEvent(s));
	}
	
	public void saveGame() {
		EventQueue.getInstance().add(new SaveEvent());
	}
	
	public void addSleepEvent() {
		EventQueue.getInstance().add(new SleepEvent());
	}
	
	public static void addNewSaveEvent(String saveName, String difficulty, String seed) {
		EventQueue.getInstance().add(new NewSaveEvent(saveName, seed, difficulty));
	}

}
