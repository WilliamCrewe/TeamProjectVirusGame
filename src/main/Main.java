package main;

import main.graphical_interface.GUIController;
import main.java.event.EventQueue;
import main.java.event.EventQueueObserver;
import main.java.gamecontrol.GameController;
import main.java.gamecontrol.gamestate.GameState;
import main.java.logging.SystemLogger;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Thread thread = new Thread() {
			public void run() {
				SystemLogger.config("Starting thread for GUI");
				GUIController.begin(args);
			}
		};
		thread.start();
		SystemLogger.config("Initialising Event Queue observer and gamestate");

		new EventQueueObserver();
		GameState.getInstance();
	}

	public static void handleEvent() {
		GameController.handleEvent(EventQueue.getInstance().remove());
	}

}
