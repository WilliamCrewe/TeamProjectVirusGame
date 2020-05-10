package main.java.alert;

import java.util.ArrayDeque;
import java.util.Observable;
import java.util.Queue;

import main.java.alert.types.AbstractAlert;
import main.java.logging.SystemLogger;

/**
 * Singleton object holding the alerts to be displayed to the user
 * @author Daniel
 *
 */

public class AlertQueue extends Observable {

	private static Queue<AbstractAlert> queue = new ArrayDeque<>();
	
	private static AlertQueue instance;
	
	private AlertQueue() {
		// private constructor to make sure the getInstance method is used
	}
	
	/**
	 * @return the instance of the AlertQeuee
	 */
	public static AlertQueue getInstance() {
		if (instance == null) {
			SystemLogger.config("AlertQueue does no yet exist, instantiating");
			instance = new AlertQueue();
		}
		
		return instance;
	}
	
	/**
	 * @return The next item in the alert queue (Also removes it)
	 */
	public AbstractAlert remove() {
		return queue.remove();
	}
	
	/**
	 * Adds the new Event to the end of the event Queue
	 * @param newEvent
	 */
	public void add(AbstractAlert newAlert) {
		SystemLogger.fine("Adding alert to queue");
		queue.add(newAlert);
		setChanged();
		notifyObservers();
	}
}