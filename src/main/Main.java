package main;

import main.graphical_interface.GUIController;
import main.java.event.EventQueue;
import main.java.event.EventQueueObserver;
import main.java.event.types.EventActionEvent;
import main.java.event.types.MoveEvent;
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
		
		/*

		System.out.println(GameState.getInstance().getSave().toString());
		
		GameController.handleEvent(new MoveEvent("Hospital"));
		System.out.println(GameState.getInstance().getSave().toString());
		
		GameController.handleEvent(new EventActionEvent("HospitalCleaner", "HospitalCleanerHelpID"));
		System.out.println(GameState.getInstance().getSave().toString());
		
		GameController.handleEvent(new MoveEvent("Shop"));
		System.out.println(GameState.getInstance().getSave().toString());
		

		GameController.handleEvent(new EventActionEvent("RacoonArmy", "RacoonArmyRevolt"));
		System.out.println(GameState.getInstance().getSave().toString());
		*/
		
	}

	public static void handleEvent() {
		GameController.handleEvent(EventQueue.getInstance().remove());
	}

}
