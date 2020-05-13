package main.java.gamecontrol.controllers;

import main.java.alert.AlertQueue;
import main.java.alert.types.AbstractAlert;
import main.java.alert.types.DefaultAlert;
import main.java.gamecontrol.gamestate.GameState;
import main.java.logging.SystemLogger;

/**
 * Controller that checks the currrent GameState and, if required, causes the
 * game to finish
 * 
 * @author Daniel
 *
 */
public class GameLossController {

	public void handleGameLossIfRequired() {
		SystemLogger.fine("Checking if the game has finished");

		// Check if the game should be finished
		if (GameState.getInstance().isGameOver()) {
			SystemLogger.config("The game has been set as over, adding an alert to the queue for handling");

			AbstractAlert alert = new DefaultAlert("Game Over",
					"Unfortunately due to the decisions you have made, you have no choice but to go to hospital for medical assistance. Think carefully about the decisions you have made and how you could last longer next time");
			AlertQueue.getInstance().add(alert);
			return;
		}

		SystemLogger.config("The game is not over, will continue");

	}
}
