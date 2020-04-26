package main.game_state.controllers;

import java.util.ArrayList;

import main.game_state.GameState;
import main.java.random.RandomnessHandler;
import main.java.random.Rarity;
import main.java.filehandling.gamecontent.realisations.EventGameContent;
import main.java.logging.SystemLogger;

public class EventPickerController {
	
	public static void pickEvent() {
		if(GameState.getGameState().getActiveEvent() == null) {
			ArrayList<EventGameContent> applicableEvents = new ArrayList<>(GameState.getEvents().values());
			for(EventGameContent e : GameState.getEvents().values()) {
				if((e.getEventLocationID() == null) || (e.isSingleOccurence() && GameState.getGameState().getSaveGame().getCompletedEvents().contains(e.getEventID())) || !(e.getEventLocationID().equals(GameState.getGameState().getSaveGame().getCurrentLocationID())) || (e.isPassiveEvent())) {
					if(applicableEvents.contains(e)) { 
						applicableEvents.remove(e);
					}
				}
			}
			Rarity randomRarity = RandomnessHandler.getRandomRarity();
			
			whileNoActiveEvent:
			while(GameState.getGameState().getActiveEvent() == null) {
				for(EventGameContent e : applicableEvents) {
					if(e.getRarity() == randomRarity) {
						GameState.getGameState().setActiveEvent(e);
						SystemLogger.fine("Event picked!");
						break whileNoActiveEvent;
					}
				}
				if(randomRarity.equals(Rarity.COMMON) ) {
					SystemLogger.fine("No events applicable for current scenario!");
					break;
				}
				randomRarity = Rarity.getNextLowerRarity(randomRarity);
			}

		} else {
			SystemLogger.fine("Event active, cannot pick new event.");
		}
		
	}

}
