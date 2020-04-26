package main.game_state.controllers;

import main.java.gamecontrol.gamestate.GameStateInitialisationHelper;
import main.java.properties.PropertyManager;

public class GameController{

	public GameController(String saveName, String seed) {
		GameStateInitialisationHelper.intitialiseGameState(saveName, seed);
	}
	
	//handle event (event parameter passed in then action, )
	
	public static void main(String[] args) {
		PropertyManager.setXSDLocation(PropertyManager.getBaseDirectory() + "\\Resources\\XSD\\VirusGameXSD.xsd");
		GameController g = new GameController("save1", null);
	}

}
