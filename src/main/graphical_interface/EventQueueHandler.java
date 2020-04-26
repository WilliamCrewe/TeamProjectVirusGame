package main.graphical_interface;

import main.java.event.EventQueue;
import main.java.event.types.EventActionEvent;
import main.java.event.types.MoveEvent;

public class EventQueueHandler {
	
	
	public EventQueueHandler() {
		setup();
	}
	
	private void setup() {
		
	}
	
	public void addActionEvent(String eventID, String eventOptionID) {
		EventQueue.getInstance().add(new EventActionEvent(eventID, eventOptionID));
	}
	
	public void addMoveEvent(String newLocationID) {
		EventQueue.getInstance().add(new MoveEvent(newLocationID));
	}
	

}
