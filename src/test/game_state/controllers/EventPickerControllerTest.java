package test.game_state.controllers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import main.game_state.GameState;
import main.game_state.controllers.EventPickerController;
import main.game_state.controllers.MovementController;
import main.java.gamecontrol.helpers.GameStateInitialisationHelper;
import main.java.properties.PropertyManager;

public class EventPickerControllerTest {
	
	@Test
	public void pickEventTest() {
		PropertyManager.setXSDLocation(PropertyManager.getBaseDirectory() + "\\Resources\\XSD\\VirusGameXSD.xsd");
		GameStateInitialisationHelper.intitialiseGameState("TestSave", "000");
		
		MovementController.move("Hospital");
		assertEquals(GameState.getLocation(GameState.getGameState().getSaveGame().getCurrentLocationID()).getLocationName(), "Hospital");
		EventPickerController.pickEvent();
		assertEquals("HospitalCleaner", GameState.getGameState().getActiveEvent().getEventID());
	}
	
	@Test
	public void eventIsInCompletedEvents() {
		PropertyManager.setXSDLocation(PropertyManager.getBaseDirectory() + "\\Resources\\XSD\\VirusGameXSD.xsd");
		GameStateInitialisationHelper.intitialiseGameState("TestSave", "000");
		
		GameState.getGameState().setActiveEvent(null);
		MovementController.move("Hospital");
		assertEquals(GameState.getLocation(GameState.getGameState().getSaveGame().getCurrentLocationID()).getLocationName(), "Hospital");
		GameState.getGameState().getSaveGame().addCompletedEvent("HospitalCleaner", "HospitalCleanerHelpID");
		EventPickerController.pickEvent();
		assertEquals(null, GameState.getGameState().getActiveEvent());
	}
	//Negative testing 
	//Event is in completed events
	//event does not exist for those conditions
	
}
