package test.java.gamecontrol.helpers;

import org.junit.Test;

import main.java.gamecontrol.gamestate.GameStateInitialisationHelper;
import main.java.properties.PropertyManager;

public class GameStateInitialisationHelperTest {
	
	@Test
	public void testGameStateInitialisationHelper() {
		
		PropertyManager.setXSDLocation(PropertyManager.getBaseDirectory() + "\\Resources\\XSD\\VirusGameXSD.xsd");
		PropertyManager.setBaseDirectory(PropertyManager.getBaseDirectory() +  "\\Resources\\GameFiles\\");
		GameStateInitialisationHelper.intitialiseGameState();
	}

}