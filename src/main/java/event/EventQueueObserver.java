package main.java.event;

import java.util.Observable;
import java.util.Observer;

import main.Main;
import main.java.logging.SystemLogger;

/**
 * Observer for the event queue, the update method is called when a new event is added the the queue
 * @author Daniel
 *
 */
public class EventQueueObserver implements Observer {

	public EventQueueObserver() {
		EventQueue.getInstance().addObserver(this);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		SystemLogger.fine("Event has been detected, will awaken waiting threads");
		
		// Trigger the handle event on main to process the event that was added
		Main.handleEvent();
	}
}
