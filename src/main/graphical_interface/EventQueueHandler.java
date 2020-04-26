package main.graphical_interface;

import main.java.event.EventQueue;
import main.java.event.types.EventActionEvent;
import main.java.event.types.MoveEvent;
import main.java.event.types.PassiveEvent;

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

}
