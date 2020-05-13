package main.java.gamecontrol.controllers;

import main.java.event.types.TimeEvent;
import main.java.gamecontrol.gamestate.GameState;
import main.java.time.TimeLord;

/**
 * Controller that handles the time progression of the game
 * @author Daniel
 *
 */
public class TimeController {

	private TimeController() {
		// Private constructor to prevent instantiation
	}
	
	/**
	 * Handles the waiting until a given time
	 * @param timeEvent
	 */
	public static void handleWait(TimeEvent timeEvent) {
		
		TimeLord timeLord = GameState.getInstance().getTimeLord();
		
		// Set the time to that waited until
		timeLord.setTime(timeEvent.getHours(), timeEvent.getMinutes());
		
		// Make sure the GUI refreshes
		GameState.getInstance().notifyListenersOfGameState();
	}
	
	/**
	 * Handles the end of the day (Incrementing day, resetting time to 7 o'clock)
	 */
	public static void handleDayEnd() {
		int currentDayNumber = GameState.getInstance().getSave().getDayNumber();
		
		// Increment the day number
		GameState.getInstance().getSave().setDayNumber(currentDayNumber + 1);
		
		// Update the TimeLord to 7 o'clock and reset the exhaustion
		TimeLord timeLord = GameState.getInstance().getTimeLord();
		timeLord.setTime(7, 0);
		timeLord.resetExhaustionTimeReached();
		
		// Make sure the GUI refreshes
		GameState.getInstance().notifyListenersOfGameState();
	}
}
