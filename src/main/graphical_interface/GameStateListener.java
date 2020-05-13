package main.graphical_interface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import main.graphical_interface.gameWindows.InGameWindow;
import main.graphical_interface.util.Command;
import main.graphical_interface.util.GUIEventOption;
import main.graphical_interface.util.GUIInventoryItem;
import main.graphical_interface.util.GUILocation;
import main.java.alert.AlertQueue;
import main.java.event.EventQueue;
import main.java.filehandling.gamecontent.realisations.EventGameContent;
import main.java.filehandling.gamecontent.realisations.LocationGameContent;
import main.java.filehandling.gamecontent.realisations.components.EventOption;
import main.java.filehandling.gamecontent.realisations.components.ItemType;
import main.java.filehandling.gamecontent.realisations.components.SaveItems;
import main.java.gamecontrol.gamestate.GameState;
import main.java.logging.SystemLogger;

/**
 * @author wcrewe
 *
 */
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

		GameState.getInstance().addObserver(this);

	}

	/** 
	 *  @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 *  
	 *  Accepts the GameState and generates objects to be used by the GUIController based on the current GameState.
	 */
	@Override
	public void update(Observable o, Object arg) {

		GameStateListener.eventCountOption = 0;
		GameStateListener.eventCountPage= 0;
		GameStateListener.locationCountOption = 0;
		GameStateListener.locationCountPage = 0;


		List<GUILocation> allLocations = createLocationOptions(
				((GameState)o).getCurrentLocation());



		if (o instanceof GameState) {

			setNewTime((GameState)o);

			if (((GameState)o).getCurrentLocation().getLocationName().contains("Home")){
				GUIController.setDisplayText("Your Home."
						+ "The standard apparel is dotted around your shocking plainly described home. Blank white walls. Blank white floors."
						+ "Oh, and a bed."
						+ "(You can rest here)");
				GUIController.clearCurrentEventPage();

				GUIEventOption sleepEvent = new GUIEventOption();
				sleepEvent.setCommand(Command.SLEEP);
				sleepEvent.setDescription("Go to Sleep");
				sleepEvent.setEventAvailable(true);
				sleepEvent.setPage(1);

				ArrayList<GUIEventOption> newArray = new ArrayList<>();
				newArray.add(sleepEvent);
				GUIController.allEventOptions = newArray;
				GUIController.setCurrentEvents();
				GUIController.updateAll();

				return;
			}



			HashMap<Integer, GUIInventoryItem> currentInventory = createInventory(
					((GameState)o).getSave().getSaveItems());

			GUIController.clearCurrentEventPage();
			GUIController.allItems = currentInventory;
			GUIController.allLocations = allLocations;


			if (((GameState)o).getActiveOptions() != null && ((GameState)o).getCurrentEvent() != null) {
				ArrayList<GUIEventOption> allEventOptions = createEventOptions(
						((GameState)o).getCurrentEvent(), ((GameState)o).getActiveOptions(), null);
				GUIController.allEventOptions = allEventOptions;
				GUIController.currentEventID = ((GameState)o).getCurrentEvent().getEventID();
				GUIController.setCurrentEvents();
				GUIController.setDisplayText(((GameState)o).getCurrentEvent().getEventName());
			} else {
				GUIController.eventQueueHandler.addPassiveEvent();
			}




			GUIController.setCurrentLocations();

			GUIController.updateAll();

		}



	}

	private void setNewTime(GameState o) {
		int hour = o.getTimeLord().getTimeHour();
		int minute = o.getTimeLord().getTimeMinute();

		SystemLogger.fine("Time update recieved of %d : %d", hour, minute);

		GUIController.updateTime(hour, minute);

	}

	/**
	 * @param items
	 * @return Map of Key: Intenger, Object: GUIInventoryItem
	 * 
	 * Creates a HashMap of GUIInventoryItems from the current Player inventory items.
	 */
	private HashMap<Integer, GUIInventoryItem> createInventory(SaveItems items){
		HashMap<Integer, GUIInventoryItem> inventory = new HashMap<>();

		for (ItemType i : items.getSaveItemsValues()) {
			InGameWindow.setCurrency(0);
			if (i.getItemName().contains("Toilet Roll")) { //Toilet roll is a currency, not an inventory item for the UI
				GUIController.changeCurrency(i.getItemCount());
			} else {
				GUIInventoryItem newItem = new GUIInventoryItem();
				newItem.setName(i.getItemName());
				newItem.setQuantity(i.getItemCount());
				newItem.setItemID(i.getItemID());
				newItem.setDroppable(true);
				newItem.setUsable(true); //For now, all items are usable and droppable.

				inventory.put(inventory.size(), newItem);
			}

		}

		return inventory;
	}

	/**
	 * @param event
	 * @param availableEventOptions
	 * @param unavailableEventOptions
	 * @return ArrayList<GUIEventOption>
	 * 
	 * Creates an array of GUIEventOptions using the currentActiveEvent. This method checks whether each
	 * EventOption should be displayed or not from the availableEventOptions list.
	 * EventOptions are initialised disable to later be enabled if they are on the availableEventOptions list.
	 */
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

	/**
	 * @param currentLocation
	 * @return List<GUILocation>
	 * 
	 * Creates a List of GUILocations based on the current location and its connecting locations.
	 */
	private List<GUILocation> createLocationOptions(LocationGameContent currentLocation){
		ArrayList<GUILocation> allLocations = new ArrayList<>();

		for (String locID : currentLocation.getConnectedLocations().getConnectedLocationIDs()) {
			GUILocation newLocation = new GUILocation();
			newLocation.setLocationID(locID);
			newLocation.setName(locID);
			getLocationCommandAndPage(newLocation);
			allLocations.add(newLocation);
		}
		return allLocations;
	}

	/**
	 * @param eventOption
	 * 
	 * Generates the appropriate action button input for the EventAction depending
	 * on where it will appear on the current action bar.
	 */
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

	/**
	 * @param GUILocation loc
	 * 
	 * Generates the appropriate location button input for the Location depending
	 * on where it will appear on the current action bar.
	 */
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