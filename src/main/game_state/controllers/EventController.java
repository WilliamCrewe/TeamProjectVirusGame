package main.game_state.controllers;

import main.game_state.GameState;
import main.java.gamecontrol.helpers.EventOptionsHelper;

public class EventController {
	
	public static void eventChoice(String eventID, String eventOptionID) {
		if(EventOptionsHelper.isEventOptionValid(eventID, eventOptionID)) {
			EventOptionsHelper.addGivenItems(eventID, eventOptionID);
			EventOptionsHelper.removeUsedItems(eventID, eventOptionID);
			GameState.getGameState().getSaveGame().adjustImmunity(EventOptionsHelper.getEventOptionFromID(eventID, eventOptionID).getEventOptionImmunityModification());
			GameState.getGameState().getSaveGame().adjustContagionLevel(EventOptionsHelper.getEventOptionFromID(eventID, eventOptionID).getEventOptionContagionLevelModifier());
			GameState.getGameState().getSaveGame().adjustKarma(EventOptionsHelper.getEventOptionFromID(eventID, eventOptionID).getEventOptionKarmaModification());
			GameState.getGameState().getSaveGame().addCompletedEvent(eventID, eventOptionID);	
			GameState.getGameState().setActiveEvent(null);
		}
	}
	
}