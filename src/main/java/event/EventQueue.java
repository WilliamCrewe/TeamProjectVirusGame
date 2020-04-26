package main.java.event;

import java.util.ArrayDeque;
import java.util.Observable;
import java.util.Queue;

import main.java.event.types.AbstractEvent;
import main.java.logging.SystemLogger;

/**
 * Singleton object holding the events to be processed by the game
 * @author Daniel
 *
 */
public class EventQueue extends Observable {

	private static Queue<AbstractEvent> queue = new ArrayDeque<>();
	
	private static EventQueue instance;
	
	private EventQueue() {
		// private constructor to make sure the getInstance method is used
	}
	
	/**
	 * @return the instance of the eventQeuee
	 */
	public static EventQueue getInstance() {
		if (instance == null) {
			SystemLogger.config("EventQueue does no yet exist, instantiating");
			instance = new EventQueue();
		}
		
		return instance;
	}
	
	/**
	 * @return The next item in the event queue (Also removes it)
	 */
	public AbstractEvent remove() {
		return queue.remove();
	}
	
	/**
	 * Adds the new Event to the end of the event Queue
	 * @param newEvent
	 */
	public void add(AbstractEvent newEvent) {
		SystemLogger.fine("Adding event to queue");
		queue.add(newEvent);
		setChanged();
		notifyObservers();
	}
}
