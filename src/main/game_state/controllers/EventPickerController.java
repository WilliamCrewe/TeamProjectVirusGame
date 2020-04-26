package main.game_state.controllers;

import java.util.ArrayList;

import main.game_state.GameState;
import main.java.random.RandomnessHandler;
import main.java.filehandling.gamecontent.realisations.EventGameContent;

public class EventPickerController {
	
	public static void pickEvent() {
		//make sure event is not in completed events 
		ArrayList<EventGameContent> applicableEvents = new ArrayList<>(GameState.getEvents());
		for(EventGameContent e : applicableEvents) {
			if(e.isSingleOccurence() && GameState.getGameState().getSaveGame().getCompletedEvents().contains(e.getEventID())) {
				applicableEvents.remove(e);
			}
			if(e.getEventLocationID() != GameState.getGameState().getSaveGame().getCurrentLocationID()) {
				applicableEvents.remove(e);
			}
			if(e.isPassiveEvent()) {
				applicableEvents.remove(e);
			}
		}
		for(EventGameContent e : applicableEvents) {
			
		}
		RandomnessHandler.getIntInRange(100);
		
	}

}
