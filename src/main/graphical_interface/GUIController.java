package main.graphical_interface;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.graphical_interface.gameWindows.*;
import main.graphical_interface.util.Command;

public class GUIController extends Application {

	private static StackPane rootPane; //This is the window that will be changed.
	private static Stage rootStage;
	private boolean isRunning = false; //A check whether the game has started yet.
	
	private static boolean singleTestDone = false;
	
	/*
	 * These variables perform eventHandling
	 */
	
	private static ArrayList<GUILocation> allLocations;
	private static GUILocation[] currentLocations;
	private static int currentLocationPage;
	private static int finalLocationPage;
	
	private static ArrayList<GUIEventOption> allEventOptions;
	private static GUIEventOption[] currentEventOptions;
	private static int currentEventPage;
	private static int finalEventPage;
	
	/*
	 * Constants to be used to determine the size of the
	 * game window (For now)
	 */
	private final static double PREFWIDTH = 1000.00;
	private final static double PREFHEIGHT = 700.00;
	private final double MINWIDTH = 500.00;
	private final double MINHEIGHT = 300.00;

	public GUIController() {
	}
	
	public static void begin(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
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
		primaryStage.setMinWidth(MINWIDTH);
		primaryStage.setMinHeight(MINHEIGHT);
		
		this.isRunning = true;
		primaryStage.show();	
	}
	
	public static void updateWindow(Command c) {
		switch (c) {
		case SWITCH_GAMEPLAY_UI:
			
			System.out.println("Setting Game Window");
			AbstractGameWindow gameWindow = new InGameWindow();
			GUIController.rootPane = gameWindow.getWindow();
			
			GUIController.rootStage.setScene(updateScene());
			updatePlayerButtonInput();
			updateLocationButtonInput();
			break;
		
		case SWITCH_MAINMENU_UI:
			
			System.out.println("Setting Main Menu");
			AbstractGameWindow menu = new StartMenuWindow();
			GUIController.rootPane = menu.getWindow();
			
			GUIController.rootStage.setScene(updateScene());
			break;
		}
	}

	private void initialise() {
		//Initialise EventVariables
		GUIController.currentEventPage = 1;
		GUIController.finalEventPage = 1;
		GUIController.allEventOptions = new ArrayList<>();
		GUIController.currentEventOptions = new GUIEventOption[10];
		
		if (!singleTestDone){
			GUIEventOption e1 = new GUIEventOption(Command.ACT_0,"Jump!", 1, "Nout", true);
			GUIEventOption e2 = new GUIEventOption(Command.ACT_1,"Hide!", 1, "Nout", true);
			
			GUIEventOption e3 = new GUIEventOption(Command.ACT_1,"Kiss!", 2, "Nout", false);
			GUIEventOption e4 = new GUIEventOption(Command.ACT_2,"Quack!", 2, "Nout", true);
			GUIEventOption e5 = new GUIEventOption(Command.ACT_3,"Duck!", 2, "Nout", true);
			
			GUIEventOption e6 = new GUIEventOption(Command.ACT_1,"Panic!", 3, "Nout", true);
			GUIEventOption e7 = new GUIEventOption(Command.ACT_2,"Leer!", 3, "Nout", false);
			GUIEventOption e8 = new GUIEventOption(Command.ACT_3,"Thrust!", 3, "Nout", true);
			
			ArrayList<GUIEventOption> test = new ArrayList<>();
			test.add(e1);
			test.add(e2);
			test.add(e3);
			test.add(e4);
			test.add(e5);
			test.add(e6);
			test.add(e7);
			test.add(e8);
			
			GUIController.allEventOptions = test;
			
			GUIController.currentEventOptions[0] = e1;
			GUIController.currentEventOptions[1] = e2;
			
			GUIController.finalEventPage = 3;
			
			
			//Initialise Location Test
			
			GUIController.allLocations = new ArrayList<>();
			GUIController.currentLocations = new GUILocation[8];
			GUIController.currentLocationPage = 1;
			GUIController.finalLocationPage = 1;
			
			GUILocation l1 = new GUILocation(Command.MOVE_1, "Hospital", 1);
			GUILocation l2 = new GUILocation(Command.MOVE_2, "Home", 1);
			GUILocation l3 = new GUILocation(Command.MOVE_3, "Police Station", 2);
			GUILocation l4 = new GUILocation(Command.MOVE_4, "Work", 3);
			GUILocation l5 = new GUILocation(Command.MOVE_5, "Asda", 4);
			
			ArrayList<GUILocation> test1 = new ArrayList<>();
			test1.add(l1);
			test1.add(l2);
			test1.add(l3);
			test1.add(l4);
			test1.add(l5);
			
			GUIController.allLocations = test1;
			
			GUIController.currentLocations[0] = l1;
			GUIController.currentLocations[1] = l2;
			
			GUIController.finalLocationPage = 4;
		}
	}
	
	public static void update(Command c) {
		switch (c) {
		case ACT_1:
			System.out.println(currentEventOptions[0].getDescription());
			break;
		case ACT_2:
			System.out.println(currentEventOptions[1].getDescription());
			break;
		case ACT_3:
			System.out.println(currentEventOptions[2].getDescription());
			break;
		case ACT_4:
			System.out.println(currentEventOptions[3].getDescription());
			break;
		case ACT_5:
			System.out.println(currentEventOptions[4].getDescription());
			break;
		case ACT_6:
			System.out.println(currentEventOptions[5].getDescription());
			break;
		case ACT_7:
			System.out.println(currentEventOptions[6].getDescription());
			break;
		case ACT_8:
			System.out.println(currentEventOptions[7].getDescription());
			break;
		case ACT_9:
			System.out.println(currentEventOptions[8].getDescription());
			break;
		case ACT_0:
			System.out.println(currentEventOptions[9].getDescription());
			break;
		case MOVE_1:
			System.out.println(currentLocations[0].getName());
			break;
		case MOVE_2:
			System.out.println(currentLocations[1].getName());
			break;
		case MOVE_3:
			System.out.println(currentLocations[2].getName());
			break;
		case MOVE_4:
			System.out.println(currentLocations[3].getName());
			break;
		case MOVE_5:
			System.out.println(currentLocations[4].getName());
			break;
		case MOVE_6:
			System.out.println(currentLocations[5].getName());
			break;
		case MOVE_7:
			System.out.println(currentLocations[6].getName());
			break;
		case MOVE_8:
			System.out.println(currentLocations[7].getName());
			break;
		case LOAD:
			System.out.println("Load");
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
		case SAVE:
			System.out.println("Save");
			break;
		default:
			break;
		
		}
	}
	
	private static void clearCurrentEventPage() {
		for (int i = 0; i < currentEventOptions.length; i++) {
			currentEventOptions[i] = null;
		}
	}
	
	private static void clearLocationPage() {
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
	
	private static void setDisplayText(String text) {
		InGameWindow.updateDisplayText(text);
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
	
	public static void textUpdate(String s, Command c) {
		switch (c) {
		
		}
	}
	
	private static Scene updateScene() {
		return new Scene(GUIController.rootPane, PREFWIDTH, PREFHEIGHT);
	}

}
