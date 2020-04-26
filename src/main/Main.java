package main;

import main.graphical_interface.GUIController;
import main.java.event.EventQueue;
import main.java.event.EventQueueObserver;
import main.java.gamecontrol.GameController;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
        GUIController.begin(args);
        
        while(true) {
        	EventQueueObserver eventQueueObserver = new EventQueueObserver();
        	try {
				eventQueueObserver.waitForEvent();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	GameController.handleEvent(EventQueue.getInstance().remove());
        }
    }

}
