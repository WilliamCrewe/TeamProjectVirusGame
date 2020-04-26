package test.game_state.controllers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import main.game_state.GameState;
import main.game_state.controllers.MovementController;
import main.java.gamecontrol.helpers.GameStateInitialisationHelper;
import main.java.properties.PropertyManager;

public class MoveControllerTest {
	
	@Test
	public void moveTest() {
		PropertyManager.setXSDLocation(PropertyManager.getBaseDirectory() + "\\Resources\\XSD\\VirusGameXSD.xsd");
		GameStateInitialisationHelper.intitialiseGameState("TestSave", "000");
		
		assertEquals(GameState.getLocation(GameState.getGameState().getSaveGame().getCurrentLocationID()).getLocationName(), "Home");
		MovementController.move("Market");
		assertEquals(GameState.getLocation(GameState.getGameState().getSaveGame().getCurrentLocationID()).getLocationName(), "Market");
	}
	
	

}
