package test.java.gamecontrol.helpers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.java.gamecontrol.gamestate.GameState;
import main.java.gamecontrol.gamestate.GameStateInitialisationHelper;
import main.java.properties.PropertyManager;

public class GameStateInitialisationHelperTest {
	
	@Test
	public void testGameStateInitialisationHelper() {
		
		PropertyManager.setXSDLocation(PropertyManager.getBaseDirectory() + "\\Resources\\XSD\\VirusGameXSD.xsd");
		GameStateInitialisationHelper.intitialiseGameState();
		
//		assertEquals("Old Man", GameState.getCharacterName("OldMan"));
//		
//		assertEquals("Home", GameState.getLocation("Home").getLocationName());
//		assertEquals("Hospital", GameState.getLocation("Hospital").getLocationName());
//		assertEquals("Market", GameState.getLocation("Market").getLocationName());
//		
//		assertEquals("HospitalCleaner", GameState.getEvent("HospitalCleaner").getEventID());
//		assertEquals("Hospital", GameState.getEvent("HospitalCleaner").getEventLocationID());
//		assertEquals(false, GameState.getEvent("HospitalCleaner").isPassiveEvent()); 
//		assertEquals(true, GameState.getEvent("HospitalCleaner").isSingleOccurence()); 
//		assertEquals(main.java.random.Rarity.COMMON, GameState.getEvent("HospitalCleaner").getRarity());
//		assertEquals(3, GameState.getEvent("HospitalCleaner").getEventOptions().getEventOptionsValues().size());
//		
//		assertEquals("TestSave", GameState.getGameState().getSaveGame().getSaveName());
//		assertEquals("000", GameState.getGameState().getSaveGame().getSeed());	
	}

}