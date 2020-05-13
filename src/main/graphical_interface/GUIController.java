package main.graphical_interface;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.graphical_interface.gameWindows.AbstractGameWindow;
import main.graphical_interface.gameWindows.InGameWindow;
import main.graphical_interface.gameWindows.StartMenuWindow;
import main.graphical_interface.util.Command;
import main.graphical_interface.util.GUIEventOption;
import main.graphical_interface.util.GUIInventoryItem;
import main.graphical_interface.util.GUILocation;
import main.java.logging.SystemLogger;

/**
 * @author wcrewe
 *
 */
public class GUIController extends Application {

	private static StackPane rootPane; //This is the window that will be changed.
	private static Stage rootStage;
	private boolean isRunning = false; //A check whether the game has started yet.
	
	private final static String bootText = "Welcome to Lockdown! "
			+ ""
			+ "Please choose a location from the minimap (Top Right) to begin your adventure!";
	
	
	/*
	 * These variables perform eventHandling
	 */
	
	static List<GUILocation> allLocations;
	static GUILocation[] currentLocations;
	private static int currentLocationPage;
	private static int finalLocationPage;
	
	//private static Event currentEvent;
	static List<GUIEventOption> allEventOptions;
	static GUIEventOption[] currentEventOptions;
	static GUIEventOption currentEventOption;
	static String currentEventID;
	private static int currentEventPage;
	private static int finalEventPage;
	static boolean isPassiveEvent = false;
	
	public static Stage primaryStage;

	static HashMap<Integer ,GUIInventoryItem> allItems;
	
	private GameStateListener gameStateListener;
	static EventQueueHandler eventQueueHandler;
	
	/*
	 * Constants to be used to determine the size of the
	 * game window (For now)
	 */
	private final static double PREFWIDTH = 1100.00;
	private final static double PREFHEIGHT = 800.00;
	private final double MINWIDTH = 500.00;
	private final double MINHEIGHT = 300.00;
	
	

	public GUIController() {
	}
	
	public static void begin(String[] args) {
		launch(args);
	}

	/**
	 *  @see javafx.application.Application#start(javafx.stage.Stage)
	 *  
	 *  Initialises the GUI variables.
	 *  Generates the MainMenu and displays it on the user's screen.
	 */
	@Override
	public void start(final Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		if (isRunning) {
			System.out.println("Game already running?");
			return;
		}
		initialise(); //initialise variables
		
		GUIController.rootStage = primaryStage;
		primaryStage.setTitle("Lockdown");
		GUIController.rootPane = new StackPane();
		
		AbstractGameWindow menu = new StartMenuWindow();
		rootPane = menu.getWindow();
		
		primaryStage.setScene(updateScene());
		primaryStage.setMinHeight(PREFHEIGHT);
		primaryStage.setMaxHeight(PREFHEIGHT);
		primaryStage.setMinWidth(PREFWIDTH);
		primaryStage.setMaxWidth(PREFWIDTH);
		primaryStage.setResizable(false);
		
		
		this.isRunning = true;
		primaryStage.show();	
	}
	
	/**
	 * @param Command c
	 * 
	 * Receives a Command from the currentWindow which alternates 
	 * the user's stage to the appropriate stage.
	 */
	public static void updateWindow(Command c) {
		switch (c) {
		case SWITCH_GAMEPLAY_UI:
			
			System.out.println("Setting Game Window");
			AbstractGameWindow gameWindow = new InGameWindow();
			GUIController.rootPane = gameWindow.getWindow();
			
			GUIController.rootStage.setScene(updateScene());
			updatePlayerButtonInput();
			updateLocationButtonInput();
			updatePlayerInventory();
			GUIController.setDisplayText(GUIController.bootText);
			break;
		
		case SWITCH_MAINMENU_UI:
			
			System.out.println("Setting Main Menu");
			AbstractGameWindow menu = new StartMenuWindow();
			GUIController.rootPane = menu.getWindow();
			
			GUIController.rootStage.setScene(updateScene());
			break;
		}
	}

	/**
	 * Initialises all variables for GUIController.
	 */
	private void initialise() {
		//Initialise EventVariables
		GUIController.currentEventPage = 1;
		GUIController.finalEventPage = 1;
		GUIController.allEventOptions = new ArrayList<>();
		GUIController.currentEventID = "";
		GUIController.currentEventOptions = new GUIEventOption[10];
		
		GUIController.allLocations = new ArrayList<>();
		GUIController.currentLocations = new GUILocation[8];
		GUIController.currentLocationPage = 1;
		GUIController.finalLocationPage = 1;
		
		GUIController.allItems = new HashMap<>();
		
		this.gameStateListener = new GameStateListener();
		this.eventQueueHandler = new EventQueueHandler();
		
	}
	
	/**
	 * @param Command c
	 * 
	 * Receives a Command from the User's input and creates an event to 
	 * put into the EventQueue.
	 */
	public static void update(Command c) {
		switch (c) {
		case ACT_1:
			if (GUIController.currentEventOption == null) {
				GUIController.currentEventOption = currentEventOptions[0];
				handledEvent();
			} else {
				update(GUIController.currentEventOption.getCommand());
			}
			break;
		case ACT_2:
			if (GUIController.currentEventOption == null) {
				GUIController.currentEventOption = currentEventOptions[1];
				handledEvent();
			} else {
				update(GUIController.currentEventOption.getCommand());
			}
			break;
		case ACT_3:
			if (GUIController.currentEventOption == null) {
				GUIController.currentEventOption = currentEventOptions[2];
				handledEvent();
			} else {
				update(GUIController.currentEventOption.getCommand());
			}
			break;
		case ACT_4:
			if (GUIController.currentEventOption == null) {
				GUIController.currentEventOption = currentEventOptions[3];
				handledEvent();
			} else {
				update(GUIController.currentEventOption.getCommand());
			}
			break;
		case ACT_5:
			if (GUIController.currentEventOption == null) {
				GUIController.currentEventOption = currentEventOptions[4];
				handledEvent();
			} else {
				update(GUIController.currentEventOption.getCommand());
			}
			break;
		case ACT_6:
			if (GUIController.currentEventOption == null) {
				GUIController.currentEventOption = currentEventOptions[5];
				handledEvent();
			} else {
				update(GUIController.currentEventOption.getCommand());
			}
			break;
		case ACT_7:
			if (GUIController.currentEventOption == null) {
				GUIController.currentEventOption = currentEventOptions[6];
				handledEvent();
			} else {
				update(GUIController.currentEventOption.getCommand());
			}
			break;
		case ACT_8:
			if (GUIController.currentEventOption == null) {
				GUIController.currentEventOption = currentEventOptions[7];
				handledEvent();
			} else {
				update(GUIController.currentEventOption.getCommand());
			}
			break;
		case ACT_9:
			if (GUIController.currentEventOption == null) {
				GUIController.currentEventOption = currentEventOptions[8];
				handledEvent();
			} else {
				update(GUIController.currentEventOption.getCommand());
			}
			break;
		case ACT_0:
			if (GUIController.currentEventOption == null) {
				GUIController.currentEventOption = currentEventOptions[9];
				handledEvent();
			} else {
				update(GUIController.currentEventOption.getCommand());
			}
			break;
		case ACT_EVENT:
			GUIController.eventQueueHandler.addActionEvent(GUIController.currentEventID, GUIController.currentEventOption.getEventID());
			GUIController.currentEventOption = null;
			break;
		case SLEEP:
			GUIController.eventQueueHandler.addSleepEvent();
			break;
		case MOVE_1:
			GUIController.eventQueueHandler.addMoveEvent(GUIController.currentLocations[0].getLocationID());
			break;
		case MOVE_2:
			GUIController.eventQueueHandler.addMoveEvent(GUIController.currentLocations[1].getLocationID());
			break;
		case MOVE_3:
			GUIController.eventQueueHandler.addMoveEvent(GUIController.currentLocations[2].getLocationID());
			break;
		case MOVE_4:
			GUIController.eventQueueHandler.addMoveEvent(GUIController.currentLocations[3].getLocationID());
			break;
		case MOVE_5:
			GUIController.eventQueueHandler.addMoveEvent(GUIController.currentLocations[4].getLocationID());
			break;
		case MOVE_6:
			GUIController.eventQueueHandler.addMoveEvent(GUIController.currentLocations[5].getLocationID());
			break;
		case MOVE_7:
			GUIController.eventQueueHandler.addMoveEvent(GUIController.currentLocations[6].getLocationID());
			break;
		case MOVE_8:
			GUIController.eventQueueHandler.addMoveEvent(GUIController.currentLocations[7].getLocationID());
			break;
		case LOAD:
			FileChooser chooser = new FileChooser();
			chooser.setTitle("Load File");
			//chooser.setInitialDirectory();
			File file = chooser.showOpenDialog(GUIController.primaryStage);
			if (file != null) {
				GUIController.updateWindow(Command.SWITCH_GAMEPLAY_UI);
				GUIController.eventQueueHandler.addLoadEvent(file.getAbsolutePath());
			}
			break;
		case SAVE:
			GUIController.eventQueueHandler.addSaveEvent();
			break;
		case NEW_GAME:
			System.out.println("New Game");
			break;
		case PAGE_NEXT:
			System.out.println("Next Page");
			
			if (finalEventPage == currentEventPage) {
				System.out.println("Last page already!");
				break;
			} else {
				currentEventPage++;
				clearCurrentEventPage();
				int x = 0;
				for (int i = 0; i<allEventOptions.size(); i++) { //for every eventOption stored
					
					
					if (allEventOptions.get(i).getPage()==currentEventPage) { //if event is on new page
						if (x>=10){
							break; //If more than 10 on the bar, end.
						}
						currentEventOptions[x] = allEventOptions.get(i);
						x++;
					}
				}
				
				updatePlayerButtonInput(); //Put the new event options on the player input bar.
			}
			break;
		case PAGE_PREV:
			System.out.println("Prev Page");
			
			if (currentEventPage==1) {
				break;
			} else {
				currentEventPage--;
				clearCurrentEventPage();
				int x = 0; //tracker for event options on bar
				for (int i = 0; i<allEventOptions.size(); i++) { //for every eventOption stored
					
					if (allEventOptions.get(i).getPage()==currentEventPage) {
						if (x>=10){
							break; //If more than 10 on the bar, end.
						}
						currentEventOptions[x] = allEventOptions.get(i);
						x++;
					}
				}
				
				updatePlayerButtonInput(); //Put the new event options on the player input bar.
			}
			break;
		case LOC_MOVE_NEXT:
			System.out.println("LOC_MOVE_NEXT");
			
			if (finalLocationPage == currentLocationPage) {
				System.out.println("Last page already!");
				break;
			} else {
				currentLocationPage++;
				clearLocationPage();
				int x = 0; //tracker for location options on bar
				for (int i = 0; i<allLocations.size(); i++) { //for every location stored
					
					if (allLocations.get(i).getPage()==currentLocationPage) {
						if (x>=8){
							break; //If more than 8 on the bar, end.
						}
						currentLocations[x] = allLocations.get(i);
						x++;
					}
				}
				
				updateLocationButtonInput(); //Put the new locations on the minimap
			}
			break;
		case LOC_MOVE_PREV:
			System.out.println("LOC_MOVE_PREV");
			
			if (currentLocationPage==1) {
				break;
			} else {
				currentLocationPage--;
				clearLocationPage();
				int x = 0; //tracker for location options on bar
				for (int i = 0; i<allLocations.size(); i++) { //for every eventOption stored
					
					if (allLocations.get(i).getPage()==currentLocationPage) {
						if (x>=8){
							break; //If more than 8 on the bar, end.
						}
						currentLocations[x] = allLocations.get(i);
						x++;
					}
				}
				
				updateLocationButtonInput(); //Put the new locations on the minimap
			}
			break;
		case PROPERTIES:
			System.out.println("Properties");
			break;
		default:
			break;
		
		}
	}
	
	
	private static void handledEvent() {

		GUIController.setDisplayText(GUIController.currentEventOption.getPostDescription());
		
		GUIController.clearCurrentEventPage();

		GUIEventOption eventOption = new GUIEventOption();
		eventOption.setCommand(Command.ACT_EVENT);
		eventOption.setDescription("OK");
		eventOption.setEventAvailable(true);
		eventOption.setEventID(GUIController.currentEventOption.getEventID());
		eventOption.setPage(1);

		
		ArrayList<GUIEventOption> newArray = new ArrayList<>();
		newArray.add(eventOption);
		GUIController.allEventOptions = newArray;
		GUIController.currentEventOption = eventOption;
		
		GUIController.setCurrentEvents();
		GUIController.updateAll();
	}
	
	/**
	 * @param Command c
	 * @param int itemNumber
	 * 
	 * Receives the user's input for an Inventory Item and creates 
	 * an EventAction which is inserted into the EventQueue.
	 * Drop item is current disabled as the backend logic is not created.
	 */
	public static void updateItem(Command c, int itemNumber) {
		switch (c) {
		case USE_ITEM:
			GUIController.eventQueueHandler.addActionEvent(GUIController.allItems.get(itemNumber).getItemID(), "Use");
			System.out.println("Use Item: #"+itemNumber);
			break;
		case DROP_ITEM:
			GUIInventoryItem item = GUIController.allItems.get(itemNumber);
			if (item.getQuantity() == 1) {
				GUIController.allItems.remove(itemNumber);
			} else {
				item.updateQuantity(-1);
				GUIController.allItems.replace(itemNumber, item);
			}
			HashMap<Integer, GUIInventoryItem> newInventory = new HashMap<>();
			for (Integer i : GUIController.allItems.keySet()) {
				newInventory.put(newInventory.size(), GUIController.allItems.get(i));
			}
			GUIController.allItems = newInventory;
			updatePlayerInventory();
			System.out.println("Drop Item: #"+itemNumber);
			break;
		default:
			break;
		}
		
	}
	
	public static void moveLocation(Command c) {
		SystemLogger.fine("Player input the %s command.", c.toString());
		System.out.println("Player moving to: "+c.toString());
		GUIController.currentEventOption = null;
		switch (c) {
		case MOVE_BANK:
			GUIController.eventQueueHandler.addMoveEvent("Bank");
			break;
		case MOVE_HOME:
			GUIController.eventQueueHandler.addMoveEvent("Home");
			break;
		case MOVE_HOSPITAL:
			GUIController.eventQueueHandler.addMoveEvent("Hospital");
			break;
		case MOVE_MARKET:
			GUIController.eventQueueHandler.addMoveEvent("Market");
			break;
		case MOVE_PARK:
			GUIController.eventQueueHandler.addMoveEvent("Park");
			break;
		case MOVE_POLICE:
			GUIController.eventQueueHandler.addMoveEvent("PoliceStation");
			break;
		case MOVE_PUB:
			GUIController.eventQueueHandler.addMoveEvent("Pub");
			break;
		case MOVE_SUPERMARKET:
			GUIController.eventQueueHandler.addMoveEvent("Shop");
			break;
		default:
			break;
		}
	}
	
	public static void createAlert(String title, String description) {
		
	}
	
	/**
	 * Clears the currentEvent List
	 */
	public static void clearCurrentEventPage() {
		for (int i = 0; i < currentEventOptions.length; i++) {
			currentEventOptions[i] = null;
		}
	}
	
	/**
	 * Clears the currentLocations List
	 */
	public static void clearLocationPage() {
		for (int i = 0; i < currentLocations.length; i++) {
			currentLocations[i] = null;
		}
	}
	
	private static void updatePlayerButtonInput() {
		boolean[] visibleButtons = new boolean[12];
		String[] buttonText = new String[10];
		for (int i = 0; i < GUIController.currentEventOptions.length; i++) {
			if (GUIController.currentEventOptions[i] != null) {
				visibleButtons[i] = true;
				buttonText[i] = GUIController.currentEventOptions[i].getDescription();
			} else {
				visibleButtons[i] = false;
			}
		}
		if (currentEventPage < finalEventPage) {
			visibleButtons[10] = true;
		}
		if (currentEventPage > 1) {
			visibleButtons[11] = true;
		}
		InGameWindow.setButtonsVisibile(visibleButtons);
		InGameWindow.setButtonText(buttonText);
	}
	
	/**
	 * Assigns the first 10 EventOptions to the 
	 * player input buttons and refreshes the window.
	 */
	public static void setCurrentEvents() {
		clearCurrentEventPage();
		int firstPage = 0;
		for (int i = 0; i < GUIController.allEventOptions.size(); i++) {
			if (GUIController.allEventOptions.get(i).getPage() == 1 && firstPage < 10) {
				GUIController.currentEventOptions[i] = GUIController.allEventOptions.get(i);
			}
		}
	}
	
	/**
	 * Sets the first 10 events from allEvents. 
	 */
	public static void setCurrentLocations() {
		clearLocationPage();
		int firstPage = 0;
		for (int i = 0; i < GUIController.allLocations.size(); i++) {
			if (GUIController.allLocations.get(i).getPage() == 1 && firstPage < 8) {
				GUIController.currentLocations[i] = GUIController.allLocations.get(i);
			}
		}
	}
	
	public static void setDisplayText(String text) {
		InGameWindow.updateDisplayText(text);
	}
	
	public static void updateAll() {
		//updateLocationButtonInput();
		updatePlayerInventory();
		updatePlayerButtonInput();
	}
	
	private static void updateLocationButtonInput() {
		boolean[] visibleButtons = new boolean[10];
		String[] buttonText = new String[8];
		for (int i = 0; i < GUIController.currentLocations.length; i++) {
			if (GUIController.currentLocations[i] != null) {
				visibleButtons[i] = true;
				buttonText[i] = GUIController.currentLocations[i].getName();
			} else {
				visibleButtons[i] = false;
			}
		}
		if (currentLocationPage < finalLocationPage) {
			visibleButtons[8] = true;
		}
		if (currentLocationPage > 1) {
			visibleButtons[9] = true;
		}
		InGameWindow.setLocationVisible(visibleButtons);
		InGameWindow.setLocationText(buttonText);
		
	}
	
	private static void updatePlayerInventory() {
		InGameWindow.updatePlayerInventory(GUIController.allItems);
	}
	
	public static void textUpdate(String s, Command c) {
		switch (c) {
		
		}
	}
	
	private static Scene updateScene() {
		return new Scene(GUIController.rootPane, PREFWIDTH, PREFHEIGHT);
	}
	
	public static void changeCurrency(int i) {
		InGameWindow.changeCurrency(i);
	}
	
	public static void updateTime(int hour, int minute) {
		InGameWindow.updateTime(hour, minute);
	}

}
