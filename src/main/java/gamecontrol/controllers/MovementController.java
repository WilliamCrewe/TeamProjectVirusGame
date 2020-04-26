package main.java.gamecontrol.controllers;

import main.java.event.types.MoveEvent;
import main.java.gamecontrol.gamestate.GameState;
import main.java.logging.SystemLogger;

/**
 * Controller to handle the movement of the player around the map
 * @author Daniel
 *
 */
public class MovementController {

	private MovementController() {
		// Private method to prevent instantiation
	}
	
	/**
	 * Handles the move of the player to the next location
	 * @param moveEvent
	 */
	public static void handleMove(MoveEvent moveEvent) {
		SystemLogger.config("Moving player to location %s", moveEvent.getNewLocationID());
		GameState.getInstance().updateLocation(moveEvent.getNewLocationID());
	}
}
