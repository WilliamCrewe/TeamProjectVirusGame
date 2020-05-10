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
	
	public static void handleWait(TimeEvent timeEvent) {
		
		TimeLord timeLord = GameState.getInstance().getTimeLord();
		
		timeLord.setTime(timeEvent.getHours(), timeEvent.getMinutes());
		
		GameState.getInstance().notifyListenersOfGameState();
	}
	
	public static void handleDayEnd() {
		int currentDayNumber = GameState.getInstance().getSave().getDayNumber();
		GameState.getInstance().getSave().setDayNumber(currentDayNumber + 1);
		
		
		TimeLord timeLord = GameState.getInstance().getTimeLord();
		timeLord.setTime(0, 0);
		
		InfectionController.handleInfection();
		
		GameState.getInstance().notifyListenersOfGameState();
	}
}
