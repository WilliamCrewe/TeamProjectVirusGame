package main;

import main.graphical_interface.AlertQueueObserver;
import main.graphical_interface.GUIController;
import main.java.event.EventQueue;
import main.java.event.EventQueueObserver;
import main.java.gamecontrol.GameController;
import main.java.gamecontrol.gamestate.GameState;
import main.java.gamecontrol.gamestate.GameStateInitialisationHelper;
import main.java.logging.SystemLogger;
import main.java.properties.PropertyManager;

/**
 * The main class of the application, it is the originator thread of all behaviours
 * @author Daniel
 *
 */
public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// If there were args passed in then it will be the filepath to the properties file used for development testing
		if (args.length > 0) {
			PropertyManager.initialiseProperties(args[0]);
		}
		
		// Create a new thread for the GUI
		Thread thread = new Thread() {
			@Override
			public void run() {
				SystemLogger.config("Starting thread for GUI");
				GUIController.begin(args);
			}
		};
		thread.start();

		// Start the initialisation of the singleton objects
		SystemLogger.config("Initialising Event Queue observer and gamestate");

		// Create the event queue observer (And event queue)
		new EventQueueObserver();
		
		//Create the alert queue observer 
		new AlertQueueObserver();
		
		// Create the gamestate
		GameState.getInstance();
		
		// Initialise the game contents
		SystemLogger.config("Initialising the game content");
		GameStateInitialisationHelper.intitialiseGameState();
	}

	/**
	 * Handle the next event in the eventQueue
	 */
	public static void handleEvent() {
		GameController.handleEvent(EventQueue.getInstance().remove());
	}

}
