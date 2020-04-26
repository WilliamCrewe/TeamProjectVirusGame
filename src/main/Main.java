package main;

import main.graphical_interface.GUIController;
import main.java.event.EventQueue;
import main.java.event.EventQueueObserver;
import main.java.gamecontrol.GameController;
import main.java.gamecontrol.gamestate.GameState;
import main.java.gamecontrol.gamestate.GameStateInitialisationHelper;
import main.java.logging.SystemLogger;
import main.java.properties.PropertyManager;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		if (args.length > 0) {
			PropertyManager.setBaseDirectory(args[0]);
			PropertyManager.setXSDLocation(args[1]);
		}
		
		Thread thread = new Thread() {
			@Override
			public void run() {
				SystemLogger.config("Starting thread for GUI");
				GUIController.begin(args);
			}
		};
		thread.start();
		SystemLogger.config("Initialising Event Queue observer and gamestate");

		new EventQueueObserver();
		GameState.getInstance();
		
		SystemLogger.config("Initialising the game content");
		GameStateInitialisationHelper.intitialiseGameState();
	}

	public static void handleEvent() {
		GameController.handleEvent(EventQueue.getInstance().remove());
	}

}
