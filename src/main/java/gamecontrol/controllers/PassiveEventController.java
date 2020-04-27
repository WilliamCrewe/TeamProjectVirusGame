package main.java.gamecontrol.controllers;

import main.java.gamecontrol.gamestate.GameState;
import main.java.logging.SystemLogger;

/**
 * Controller to handle the initialisation of the passive event for the current location
 * @author Daniel
 *
 */
public class PassiveEventController {

	private PassiveEventController() {
		// Private constructor to prevent instantation
	}
	
	/**
	 * Sets the passive event for the current location
	 */
	public static void setActiveEventToPassiveForLocation() {
		SystemLogger.config("Starting the passive event for the current location");
		GameState.getInstance().setCurrentEventToPassiveLocationEvent();
	}
}
