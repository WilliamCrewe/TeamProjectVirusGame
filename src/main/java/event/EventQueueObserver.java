package main.java.event;

import java.util.Observable;
import java.util.Observer;

public class EventQueueObserver implements Observer {

	@Override
	public void update(Observable arg0, Object arg1) {
		notifyAllSychronized();
	}
	
	private synchronized void notifyAllSychronized() {
		notifyAll();
	}
	
	public void waitForEvent() throws InterruptedException {
		wait();
	}

}
