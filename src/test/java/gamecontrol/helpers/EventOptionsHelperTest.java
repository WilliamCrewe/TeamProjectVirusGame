package test.java.gamecontrol.helpers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import main.game_state.GameState;
import main.java.filehandling.gamecontent.realisations.components.ItemType;
import main.java.gamecontrol.helpers.EventOptionsHelper;
import main.java.gamecontrol.helpers.GameStateInitialisationHelper;
import main.java.properties.PropertyManager;

public class EventOptionsHelperTest {

	@Test
	public void testEventOptionsHelper() {
		PropertyManager.setXSDLocation(PropertyManager.getBaseDirectory() + "\\Resources\\XSD\\VirusGameXSD.xsd");
		GameStateInitialisationHelper.intitialiseGameState("TestSave", "000");
		
		assertEquals("ShopSell", GameState.getEvent("ShopSell").getEventID());
		
		assertEquals(false, EventOptionsHelper.isEventOptionValid("ShopSell", "ShopTradeSellBananaID"));
		
		GameState.getGameState().getSaveGame().addToSaveItems(new ItemType("BananaID", "Banana", 1, "UseBanana"));
		
		assertEquals(true, EventOptionsHelper.isEventOptionValid("ShopSell", "ShopTradeSellBananaID"));
		
	}
}
