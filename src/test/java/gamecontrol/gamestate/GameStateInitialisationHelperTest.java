package test.java.gamecontrol.gamestate;

import org.junit.Test;

import main.java.gamecontrol.gamestate.GameStateInitialisationHelper;
import main.java.properties.PropertyManager;

public class GameStateInitialisationHelperTest {

	@Test
	public void testInitialiseGamestate() {
		PropertyManager.setBaseDirectory(System.getProperty("user.dir") + "\\Resources\\TestFiles\\");
		PropertyManager.setXSDLocation(System.getProperty("user.dir") + "\\Resources\\XSD/VirusGameXSD.xsd");
		
		GameStateInitialisationHelper.intitialiseGameState();
	}
}
