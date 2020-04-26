package test.game_state.controllers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import main.game_state.GameState;
import main.game_state.controllers.EventController;
import main.java.gamecontrol.helpers.GameStateInitialisationHelper;
import main.java.properties.PropertyManager;

public class EventControllerTest {
	
	
	@Test
	public void completeEvent() {
		PropertyManager.setXSDLocation(PropertyManager.getBaseDirectory() + "\\Resources\\XSD\\VirusGameXSD.xsd");
		GameStateInitialisationHelper.intitialiseGameState("TestSave", "000");
		
		assertEquals(0, GameState.getGameState().getSaveGame().getCompletedEvents().getCompletedEvents().size());
		assertEquals(false, GameState.getGameState().getSaveGame().getSaveItems().containsItemID("BananaID"));
		assertEquals(0, GameState.getGameState().getSaveGame().getKarma());
		assertEquals(0, GameState.getGameState().getSaveGame().getContagionLevel());
		assertEquals(0, GameState.getGameState().getSaveGame().getImmunity());
		
		EventController.eventChoice("HospitalCleaner", "HospitalCleanerHelpID");
		
		assertEquals(1, GameState.getGameState().getSaveGame().getCompletedEvents().getCompletedEvents().size());
		assertEquals(true, GameState.getGameState().getSaveGame().getSaveItems().containsItemID("BananaID"));
		assertEquals(10, GameState.getGameState().getSaveGame().getKarma());
		assertEquals(10, GameState.getGameState().getSaveGame().getContagionLevel());
		assertEquals(0, GameState.getGameState().getSaveGame().getImmunity());
		
	}
	
	
	@Test
	public void eventNotFound() {
		
	}

}
