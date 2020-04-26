package main.java.gamecontrol.controllers;

import java.util.List;

import main.java.event.types.EventActionEvent;
import main.java.filehandling.gamecontent.realisations.EventGameContent;
import main.java.filehandling.gamecontent.realisations.SaveGameContent;
import main.java.filehandling.gamecontent.realisations.components.EventOption;
import main.java.filehandling.gamecontent.realisations.components.EventOptionItems;
import main.java.filehandling.gamecontent.realisations.components.EventOptions;
import main.java.filehandling.gamecontent.realisations.components.ItemType;
import main.java.gamecontrol.gamestate.GameState;
import main.java.logging.SystemLogger;

/**
 * @author Daniel
 *
 */
public class EventController {

	private EventController() {
		// private constructor to prevent instantiation
	}
	
	public static void handleEvent(EventActionEvent event) {
		SystemLogger.config("Handling the event choice %s for event %s", event.getEventOptionID(), event.getEventID());
		EventGameContent currentEvent = GameState.getInstance().getCurrentEvent();
		EventOptions eventOptions = currentEvent.getEventOptions();
		List<EventOption> eventOptionsValues = eventOptions.getEventOptionsValues();
		
		EventOption chosenOption = null;
		for (EventOption eventOption : eventOptionsValues) {
			if (eventOption.getEventOptionID().equals(event.getEventOptionID())) {
				chosenOption = eventOption;
				break;
			}
		}
		
		if (chosenOption == null) {
			// TODO handle this
			return;
		}
		
		chosenOption.getEventOptionContagionLevelModifier();
		
		SaveGameContent save = GameState.getInstance().getSave();
		
		// Handle the modification of the integer values
		save.alterImmunity(chosenOption.getEventOptionImmunityModification());
		save.alterContagionLevel(chosenOption.getEventOptionContagionLevelModifier());
		save.alterKarma(chosenOption.getEventOptionKarmaModification());
		
		EventOptionItems eventOptionItems = chosenOption.getEventOptionItems();
		if (eventOptionItems != null) {
			for (ItemType item : eventOptionItems.getEventOptionItemsValues()) {
				save.getSaveItems().addToSaveItems(item);
			}
		}
		
		GameState.getInstance().completeEvent(event.getEventID());
	}
}
