package test.java.gamecontrol.controllers;

import org.junit.Test;

import main.java.filehandling.gamecontent.realisations.EventGameContent;
import main.java.filehandling.gamecontent.realisations.LocationGameContent;
import main.java.gamecontrol.controllers.EventPickerController;
import main.java.gamecontrol.gamestate.GameState;
import main.java.random.Rarity;

public class EventPickerControllerTest {

	@Test
	public void testPickAndSetRandomEvent() {
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testPickAndSetRandomEventNoEventsFound() {
		// Setup the event
		EventGameContent testEvent = new EventGameContent("TestEventID", "Test event", null, true, true,
				null, Rarity.COMMON);
		GameState.getInstance().setNewEvent(testEvent);
		
		// Mark the event as completed so it cannot be picked again (As it is single occurrence)
		GameState.getInstance().updateCurrentEvent("TestEventID");
		GameState.getInstance().completeEvent("TestEventID");
		
		// Setup the location for the event
		LocationGameContent nextLocation = new LocationGameContent("TestLocationID", null, null, null);
		GameState.getInstance().setNewLocation(nextLocation);
		GameState.getInstance().updateLocation("TestLocationID");
		
		EventPickerController.pickAndSetRandomEvent();
	}
}
