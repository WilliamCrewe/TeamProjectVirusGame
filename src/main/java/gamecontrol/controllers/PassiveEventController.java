package main.java.gamecontrol.controllers;

import main.java.gamecontrol.gamestate.GameState;

public class PassiveEventController {

	public static void setActiveEventToPassiveForLocation() {
		GameState.getInstance().setCurrentEventToPassiveLocationEvent();
	}
}
