package main.java.event;

import java.util.Observable;
import java.util.Observer;

import main.Main;
import main.java.logging.SystemLogger;

public class EventQueueObserver implements Observer {

	public EventQueueObserver() {
		EventQueue.getInstance().addObserver(this);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		SystemLogger.fine("Event has been detected, will awaken waiting threads");
		Main.handleEvent();
	}
}
