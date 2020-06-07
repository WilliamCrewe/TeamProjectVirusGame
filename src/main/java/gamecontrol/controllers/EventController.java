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

	/**
	 * @param event
	 */
	public static void handleEvent(EventActionEvent event) {
		SystemLogger.config("Handling the event choice %s for event %s", event.getEventOptionID(), event.getEventID());
		EventGameContent currentEvent = GameState.getInstance().getCurrentEvent();
		EventOptions eventOptions = currentEvent.getEventOptions();
		List<EventOption> eventOptionsValues = eventOptions.getEventOptionsValues();

		EventOption chosenOption = null;
		// Get the matching option
		for (EventOption eventOption : eventOptionsValues) {
			if (eventOption.getEventOptionID().equals(event.getEventOptionID())) {
				chosenOption = eventOption;
				break;
			}
		}

		// This situation should be limited by the GUI, simply logging and not altering
		// the GameState is the 'fail-safe' option
		if (chosenOption == null) {
			SystemLogger.severe(
					"An invalid option of %s was chosen for event %s, no update to the gameState will be made",
					event.getEventOptionID(), event.getEventID());
			return;
		}

		SaveGameContent save = GameState.getInstance().getSave();

		// If there were items required for the option check the save has them (This
		// situation should be limited by the GUI)
		if (chosenOption.getRequiredItems() != null
				&& !save.containsRequiredItems(chosenOption.getRequiredItems())) {
			SystemLogger.severe(
					"An invalid option of %s was chosen for event %s as the required items were not held, no update to the gameState will be made",
					event.getEventOptionID(), event.getEventID());
			return;
		}

		// Handle the modification of the integer values
		save.alterImmunity(chosenOption.getEventOptionImmunityModification());
		save.alterContagionLevel(chosenOption.getEventOptionContagionLevelModifier());
		save.alterKarma(chosenOption.getEventOptionKarmaModification());

		// If there were items associated with the event option the add those to the
		// save
		EventOptionItems eventOptionItems = chosenOption.getEventOptionItems();
		if (eventOptionItems != null) {
			for (ItemType item : eventOptionItems.getEventOptionItemsValues()) {
				save.getSaveItems().addToSaveItemsMap(item);
			}
		}

		// If there were items required for the option remove them (All items are single
		// use)
		if (chosenOption.getRequiredItems() != null) {
			save.getSaveItems().removeItems(chosenOption.getRequiredItems());
		}

		GameState.getInstance().completeEvent(event.getEventID());

		// Setup the following event if there is one
		SystemLogger.finer("Setting the current event to %s", chosenOption.getFollowingEventID());
		if (chosenOption.getFollowingEventID() != null) {
			GameState.getInstance().updateCurrentEvent(chosenOption.getFollowingEventID());
		}

		GameState.getInstance().notifyListenersOfGameState();
	}
}
