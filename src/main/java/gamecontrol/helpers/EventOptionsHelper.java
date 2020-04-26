package main.java.gamecontrol.helpers;

import java.util.ArrayList;

import main.game_state.GameState;
import main.java.filehandling.gamecontent.realisations.EventGameContent;
import main.java.filehandling.gamecontent.realisations.components.EventOption;
import main.java.filehandling.gamecontent.realisations.components.ItemType;

public class EventOptionsHelper {
	
	public static void removeUsedItems(String eventID, String eventOptionID) {
		String requiredItemID = getEventOptionFromID(eventID, eventOptionID).getRequiredItemID();
		
		ArrayList<ItemType> heldItems = (ArrayList<ItemType>) GameState.getGameState().getSaveGame().getSaveItems().getSaveItemsValues();
		for(ItemType i : heldItems) {
			if(i.getItemID().equals(requiredItemID)) {
				i.changeItemCount(-1);
			}
		}
	}
	
	public static void addGivenItems(String eventID, String eventOptionID) {
		ArrayList<ItemType> optionItems = (ArrayList<ItemType>) getEventOptionFromID(eventID, eventOptionID).getEventOptionItems().getEventOptionItemsValues();
		ArrayList<ItemType> heldItems = (ArrayList<ItemType>) GameState.getGameState().getSaveGame().getSaveItems().getSaveItemsValues();
		for(ItemType i : heldItems) {
			for(ItemType optionItem : optionItems) {
				if(i.getItemID().equals(optionItem.getItemID())) {
					i.changeItemCount(optionItem.getItemCount());
				}else {
					heldItems.add(optionItem);
				}
			}
		}
	}
	
	/**
	 * Checks if the game state save game has the required held items for the given event option
	 * @param eventID
	 * @param eventOptionID
	 * @return
	 */
	public static boolean isEventOptionValid(String eventID, String eventOptionID) {
		EventOption e = getEventOptionFromID(eventID, eventOptionID);
		if(GameState.getGameState().getSaveGame().getSaveItems().containsItemID(e.getRequiredItemID()) || e.getRequiredItemID() == null) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param eventID
	 * @param eventOptionID
	 * @return the event option that can be found under the given eventID and eventOptionID
	 */
	public static EventOption getEventOptionFromID(String eventID, String eventOptionID) {
		for(EventOption eO : GameState.getEvent(eventID).getEventOptions().getEventOptionsValues()) {
			if(eO.getEventOptionID().equals(eventOptionID)) {
				return eO;
			}
		}
		return null;
	}
	
}

