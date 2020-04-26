package main.graphical_interface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import main.game_state.GameState;
import main.graphical_interface.util.Command;
import main.graphical_interface.util.GUIEventOption;
import main.graphical_interface.util.GUIInventoryItem;
import main.graphical_interface.util.GUILocation;
import main.java.filehandling.gamecontent.realisations.EventGameContent;
import main.java.filehandling.gamecontent.realisations.LocationGameContent;
import main.java.filehandling.gamecontent.realisations.components.EventOption;
import main.java.filehandling.gamecontent.realisations.components.ItemType;
import main.java.filehandling.gamecontent.realisations.components.SaveItems;

public class GameStateListener implements Observer{

	private static int eventCountOption;
	private static int eventCountPage;

	private static int locationCountOption;
	private static int locationCountPage;
	

	public GameStateListener() {
		setup();
	}

	private void setup() {
		GameStateListener.eventCountOption = 0;
		GameStateListener.eventCountPage= 0;
		GameStateListener.locationCountOption = 0;
		GameStateListener.locationCountPage = 0;

	}

	@Override
	public void update(Observable o, Object arg) {
		GameStateListener.eventCountOption = 0;
		GameStateListener.eventCountPage= 0;
		GameStateListener.locationCountOption = 0;
		GameStateListener.locationCountPage = 0;
		
		if (arg instanceof GameState) {
			
			HashMap<Integer, GUIInventoryItem> currentInventory = createInventory(
					((GameState)arg).getSaveGame().getSaveItems());
			ArrayList<GUIEventOption> allEventOptions = createEventOptions(
					((GameState)arg).getActiveEvent(), null, null);
			
			String locID = ((GameState)arg).getSaveGame().getCurrentLocationID();
			ArrayList<GUILocation> allLocations = createLocationOptions(
					((GameState)arg).getSaveGame().getCurrentLocation());
			
			GUIController.allItems = currentInventory;
			GUIController.allLocations = allLocations;
			GUIController.allEventOptions = allEventOptions;
			GUIController.currentEventID = ((GameState)arg).getActiveEvent().getEventID();
			
			GUIController.setCurrentEvents();
			GUIController.setCurrentLocations();
			
			GUIController.updateAll();
			
		}

		

	}

	private HashMap<Integer, GUIInventoryItem> createInventory(SaveItems items){
		HashMap<Integer, GUIInventoryItem> inventory = new HashMap<>();

		for (ItemType i : items.getSaveItemsValues()) {
			GUIInventoryItem newItem = new GUIInventoryItem();
			newItem.setName(i.getItemName());
			newItem.setQuantity(i.getItemCount());
			newItem.setItemID(i.getItemID());
			newItem.setDroppable(true);
			newItem.setUsable(true); //For now, all items are usable and droppable.

			inventory.put(inventory.size(), newItem);
		}

		return inventory;
	}

	private ArrayList<GUIEventOption> createEventOptions(EventGameContent event, List<EventOption> availableEventOptions,
			ArrayList<EventOption> unavailableEventOptions){

		ArrayList<GUIEventOption> allEventOptions = new ArrayList<>();

		for (EventOption option : event.getEventOptions().getEventOptionsValues()) {
			GUIEventOption newEventOption = new GUIEventOption();

			newEventOption.setDescription(option.getEventOptionDescription());
			newEventOption.setPostDescription(option.getEventOptionPostDescription());
			newEventOption.setEventID(option.getEventOptionID());

			//Check if the eventOption is able to be clicked
			for (EventOption e : availableEventOptions) {
				if (e.getEventOptionID().equals(option.getEventOptionID())) {
					newEventOption.setEventAvailable(true); //Starts false
					break;
				}
			}

			newEventOption.setFollowingEventID(option.getFollowingEventID());

			getEventOptionCommandAndPage(newEventOption);

			allEventOptions.add(newEventOption);
		}

		return allEventOptions;

	}

	private List<GUILocation> createLocationOptions(LocationGameContent currentLocation){
		ArrayList<GUILocation> allLocations = new ArrayList<>();

		for (String locID : currentLocation.getConnectedLocations().getConnectedLocationIDs()) {
			GUILocation newLocation = new GUILocation();
			newLocation.setLocationID(locID);
			newLocation.setName(GameState.getLocationName(locID));
			getLocationCommandAndPage(newLocation);
			allLocations.add(newLocation);
		}
		return allLocations;
	}

	private void getEventOptionCommandAndPage(GUIEventOption eventOption) {
		if (GameStateListener.eventCountOption < 10) {
			if (GameStateListener.eventCountOption == 1) {
				eventOption.setCommand(Command.ACT_1);
			} else if (eventCountOption == 2) {
				eventOption.setCommand(Command.ACT_2);
			} else if (eventCountOption == 3) {
				eventOption.setCommand(Command.ACT_3);
			} else if (eventCountOption == 4) {
				eventOption.setCommand(Command.ACT_4);
			} else if (eventCountOption == 5) {
				eventOption.setCommand(Command.ACT_5);
			} else if (eventCountOption == 6) {
				eventOption.setCommand(Command.ACT_6);
			} else if (eventCountOption == 7) {
				eventOption.setCommand(Command.ACT_7);
			} else if (eventCountOption == 8) {
				eventOption.setCommand(Command.ACT_8);
			} else if (eventCountOption == 9) {
				eventOption.setCommand(Command.ACT_9);
			} else if (eventCountOption == 0) {
				eventOption.setCommand(Command.ACT_0);
				GameStateListener.eventCountPage++;
			} else {

			}
			eventOption.setPage(GameStateListener.eventCountPage);
			GameStateListener.eventCountOption++;
		}
	}

	private void getLocationCommandAndPage(GUILocation loc) {
		if (GameStateListener.locationCountOption < 10) {
			if (GameStateListener.locationCountOption == 1) {
				loc.setCommand(Command.ACT_1);
			} else if (locationCountOption == 2) {
				loc.setCommand(Command.ACT_2);
			} else if (locationCountOption == 3) {
				loc.setCommand(Command.ACT_3);
			} else if (locationCountOption == 4) {
				loc.setCommand(Command.ACT_4);
			} else if (locationCountOption == 5) {
				loc.setCommand(Command.ACT_5);
			} else if (locationCountOption == 6) {
				loc.setCommand(Command.ACT_6);
			} else if (locationCountOption == 7) {
				loc.setCommand(Command.ACT_7);
			} else if (locationCountOption == 8) {
				loc.setCommand(Command.ACT_8);
			} else if (locationCountOption == 9) {
				loc.setCommand(Command.ACT_9);
			} else if (locationCountOption == 0) {
				loc.setCommand(Command.ACT_0);
				GameStateListener.locationCountPage++;
			} else {

			}

			loc.setPage(GameStateListener.locationCountPage);
			GameStateListener.locationCountOption++;
		}
	}

}