package test.java.gamecontrol.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.java.filehandling.gamecontent.realisations.EventGameContent;
import main.java.filehandling.gamecontent.realisations.LocationGameContent;
import main.java.gamecontrol.controllers.PassiveEventController;
import main.java.gamecontrol.gamestate.GameState;
import main.java.random.Rarity;

public class PassiveEventControllerTest {

	/**
	 * Positive test case for when the passive event for the location is set
	 */
	@Test
	public void testSetActiveEventToPassiveForLocation() {
		// Setup the passive event
		EventGameContent testEvent = new EventGameContent("TestEventID", "Test event", "TestLocationID", true, false,
				null, Rarity.COMMON);
		GameState.getInstance().setNewEvent(testEvent);

		// Setup the location for the event
		LocationGameContent nextLocation = new LocationGameContent("TestLocationID", null, null, null);
		GameState.getInstance().setNewLocation(nextLocation);
		
		// Set the current location to that of the passive event
		GameState.getInstance().updateLocation("TestLocationID");
		
		// Call the method in test
		PassiveEventController.setActiveEventToPassiveForLocation();
		
		// Assert the current event is the passive one for the location
		assertEquals(testEvent, GameState.getInstance().getCurrentEvent());
	}
}
