package test.java.gamecontrol.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.java.event.types.MoveEvent;
import main.java.filehandling.gamecontent.realisations.LocationGameContent;
import main.java.gamecontrol.controllers.MovementController;
import main.java.gamecontrol.gamestate.GameState;

public class MovementControllerTest {

	/**
	 * Positive test case for when the movement to the next location occurs
	 */
	@Test
	public void testHandleMove() {
		// Create the location to move to and add it to the gamestate
		LocationGameContent nextLocation = new LocationGameContent("NewLocationID", null, null, null);
		GameState.getInstance().setNewLocation(nextLocation);
		 
		// Create the MoveEvent object to pass in
		MoveEvent moveEvent = new MoveEvent("NewLocationID");
		
		// Call the method in test
		MovementController.handleMove(moveEvent);
		
		// Asssert the location is now the nextLocation
		assertEquals(nextLocation, GameState.getInstance().getCurrentLocation());
	}
}
