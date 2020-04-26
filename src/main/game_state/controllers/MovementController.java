package main.game_state.controllers;

import main.game_state.GameState;
import main.java.logging.SystemLogger;

public class MovementController {
	
	public static void move(String newLocationID) {
		GameState.getGameState().getSaveGame().setCurrentLocationID(newLocationID);
		SystemLogger.fine("Moved to " + newLocationID);
	}
}
