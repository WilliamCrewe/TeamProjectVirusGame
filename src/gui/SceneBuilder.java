package gui;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.Command;
import util.GameUpdate;

/**
 * @author will_crewe
 *
 */
/**
 * @author wcrewe
 *
 */
/**
 * @author wcrewe
 *
 */
public class SceneBuilder extends Application {

	private final double APPWIDTH = 1000.00;
	private final double APPHEIGHT = 700.00;

	private final double MINWIDTH = 500.00;
	private final double MINHEIGHT = 300.00;


	private TextArea displayPane;
	private HashMap<Command, Button> allButtons;
	private InterfaceAPI interfaceAPI;



	public static void begin(String[] args) {
		launch(args);
	}

	/** 
	 * @param The Root stage for this application (Automatic)
	 * 
	 * Creates the complete Game GUI Window.
	 * Must be launched as the initial Application 
	 * window currently (To be changed).
	 */
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Lockdown");
		this.interfaceAPI = new InterfaceAPI();

		VBox primaryWindowVBox = createGameWindow();


		StackPane root = new StackPane();
		root.getChildren().add(primaryWindowVBox);
		primaryStage.setScene(new Scene(root, APPWIDTH, APPHEIGHT));
		primaryStage.setMinWidth(MINWIDTH);
		primaryStage.setMinHeight(MINHEIGHT);
		primaryStage.show();
	}


	/**
	 * @return A HBox containing both the primary Game Window's
	 * 			menu bar as well as the Save and Load buttons.
	 * 
	 * Creates the game window's menu and save/load buttons, 
	 * collects them in a HBox, and returns the result back
	 * to the calling method.
	 * To edit the Menu or Save/Load bars, please edit the
	 * relevant classes.
	 */
	private HBox setMenuAndSaveLoad() {
		HBox menuAndSaveLoad = new HBox();
		CreateMenuBar cmb = new CreateMenuBar();
		CreateSaveLoad csl = new CreateSaveLoad();

		Control menu = cmb.getMainMenuBar();
		Pane saveLoad = csl.getSaveLoad();

		menuAndSaveLoad.getChildren().add(menu);
		menuAndSaveLoad.getChildren().add(saveLoad);

		HBox.setHgrow(menu, Priority.ALWAYS);
		menuAndSaveLoad.setAlignment(Pos.CENTER_RIGHT);

		return menuAndSaveLoad;
	}

	/**
	 * @return A HBox containing a TextArea (Primary Game 
	 * 			Display) and a VBox with both the Map and 
	 * 			Statistics boxes.
	 * 
	 * Create a HBox containing the primary display and
	 * statistics/map window.
	 * The Display HGrows to fit the total screen size
	 * whenever it won't exceed other windows minimum size.
	 * To edit the Display, edit setDisplayPane() below.
	 * To edit the Map/Stats, edit setMapPane()
	 * and setStatsPane() below (To be later moved into
	 * their own classes once functionality increases).
	 */
	private HBox setDisplayAndMapStats() {
		HBox output = new HBox();

		Control display = setDisplayPane();

		output.getChildren().add(display);
		output.getChildren().add(setMapAndStatsVBox());

		HBox.setHgrow(display, Priority.SOMETIMES);
		output.setAlignment(Pos.TOP_LEFT);

		return output;
	}

	/**
	 * @return A Control (TextArea) to the 
	 * 			setDisplayAndMapStats() method. 
	 * 
	 * Box set to be non-editable by the user and
	 * to not be targettable by tabbing.
	 * The text within Wraps to avoid a Horizontal 
	 * Scroll Bar, but has a Vertical Scroll Bar.
	 * Currently filled with Lorem Ipsum for appearance.
	 */
	private Control setDisplayPane() {
		TextArea output = new TextArea();
		output.setEditable(false);
		output.setWrapText(true);
		output.setFocusTraversable(false);
		output.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas non enim eros. Nulla lacinia, ex sed mattis hendrerit, ipsum ipsum dapibus orci, non pharetra leo quam quis erat. Interdum et malesuada fames ac ante ipsum primis in faucibus. Sed vitae sem elementum, laoreet ex eu, lobortis leo. Sed lacus tortor, porta feugiat augue quis, pulvinar ultricies nulla. Phasellus massa ligula, auctor at mi pharetra, facilisis blandit eros. Nullam nec ligula sit amet velit scelerisque consequat. In ut ultricies tortor. Vestibulum id sapien condimentum, bibendum lacus id, consectetur erat. Nunc nibh augue, dapibus id finibus quis, mattis et mi. Nullam luctus turpis ex, sed elementum velit varius quis. Etiam in quam purus. Phasellus bibendum, metus fermentum vestibulum iaculis, lorem quam maximus ante, efficitur euismod odio purus volutpat nisl.\r\n" + 
				"\r\n" + 
				"Suspendisse a tellus interdum, pharetra lorem ullamcorper, porta justo. Ut id tempor eros. Donec nibh felis, venenatis at eros ut, facilisis varius massa. Donec dictum hendrerit est vel semper. Aliquam sollicitudin, leo tempor ullamcorper elementum, ante sapien elementum est, et iaculis lacus magna rhoncus diam. Praesent accumsan elit at sodales blandit. Nulla eu porttitor lectus. Nullam porttitor fringilla leo, nec ultricies turpis. Sed efficitur vehicula lacus, ac varius ipsum. In hac habitasse platea dictumst. Nunc sit amet urna volutpat, aliquam ex et, lobortis mi.\r\n" + 
				"\r\n" + 
				"Proin vitae elit justo. Ut tempor magna non metus blandit cursus. Sed dapibus rutrum porttitor. Sed nec venenatis justo. Morbi in est diam. Sed euismod semper purus. Suspendisse ornare lobortis nunc, a iaculis tortor efficitur id. Phasellus et ligula viverra metus viverra pretium. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis pretium elementum dolor eget dignissim. Donec in augue id nisi luctus ultricies. Nulla lobortis et turpis eu aliquam.");
		this.displayPane = output;
		return output;
	}

	/**
	 * @return A VBox containing the Map and Statistics
	 * 			Boxes. 
	 * 
	 * Creates the box and adds the Map and Statistics Controls
	 * to these windows.
	 * Currently accepts Statistics as a Control (TextArea).
	 * To Edit the statistics, use setStatsPane() below.
	 * To edit the map, use setMapPane() below.
	 */
	private VBox setMapAndStatsVBox() {
		VBox output = new VBox();

		Control stats = setStatsPane();

		output.getChildren().add(setMapPane());
		output.getChildren().add(stats);

		VBox.setVgrow(stats, Priority.SOMETIMES);
		return output;
	}

	/**
	 * @return An empty Pane right now.
	 * 
	 * Has a minimum Height of 200 for appearance purposes.
	 * To be edited to include a Map once the map design 
	 * has been decided.
	 * To be moved into its own Class for that design.
	 */
	private Pane setMapPane() {
		Pane output = new Pane();
		output.setMinHeight(200.0);


		return output;
	}

	/**
	 * @return An empty Control (TextArea).
	 * 
	 * Has a minimum Width of 200 and Max of 300 for appearance
	 *  purposes. To be edited to include statistics once their  
	 *  design has been decided.
	 *  Cannot be edited by the player. Text Wraps currently
	 *  Likely to be changed into a GridPane of Labels and
	 *  TextFields in the future.
	 * To be moved into its own Class for that design.
	 */
	private Control setStatsPane() {
		TextArea output = new TextArea();
		output.setMinWidth(200.0);
		output.setMaxWidth(300.0);
		output.setEditable(false);
		output.setWrapText(true);
		output.setFocusTraversable(false);
		return output;
	}

	/**
	 * @return A HBox containing one VBox (UpDown) and two
	 * 			GridPanes (Directions and Actions).
	 * 
	 * Uses the CreatePlayerInputsGridPane class to create three 
	 * sets of buttons. 
	 * upDownBox is a VBox with two buttons,
	 * UpButton and DownButton.
	 * directionalGrid is a GridPane with 4 Buttons; one for
	 * each of the four cardinal directions. Has 3 columns and
	 * 2 rows.
	 * primaryInputGrid is a GridPane with 10 Buttons; each
	 * labled 1-0. Has 5 columns and 2 rows.
	 * The buttons currently do nothing.
	 * Finally, the Buttons are attached to an Array
	 * for future use.
	 */
	private HBox setInputOptionsGridPane() {
		HBox output = new HBox();
		output.setPadding(new Insets(10.0));
		output.setSpacing(20.0);

		CreatePlayerInputsGridPane grid = new CreatePlayerInputsGridPane();
		VBox upDownBox = grid.getUpDownVBox();
		GridPane directionalGrid = grid.getDirectionalInputGrid();
		GridPane primaryInputGrid = grid.getPrimaryInputGrid();

		output.getChildren().add(upDownBox);
		output.getChildren().add(directionalGrid);
		output.getChildren().add(primaryInputGrid);

		this.allButtons = setButtonArray(grid);
		setupButtons();

		return output;
	}

	/**
	 * @param 	CreatePlayerInputsGridPane grid : the input grid 
	 * created in setInputOptionsGridPane()
	 * @return	HashMap<String, Button> : A HashMap of Buttons created
	 * that correlates with the buttons usable in this game.
	 * 
	 * This method takes the earlier created buttons and
	 * attaches them to a HashMap<String, Button> which is returned
	 * back to setInputOptionsGridPane().
	 * This assigns that HashMap to the class variable: allButtons.
	 * A HashMap is used for future instances where the button array might
	 * be expanded.
	 */
	private HashMap<Command, Button> setButtonArray(CreatePlayerInputsGridPane grid){
		HashMap<Command, Button> output = new HashMap<>();
		output.put(Command.ACT_UP, grid.getUpButton());
		output.put(Command.ACT_DOWN, grid.getDownButton());

		output.put(Command.ACT_NORTH, grid.getNorthButton());
		output.put(Command.ACT_WEST, grid.getWestButton());
		output.put(Command.ACT_EAST, grid.getEastButton());
		output.put(Command.ACT_SOUTH, grid.getSouthButton());

		output.put(Command.ACT_1, grid.getButton1());
		output.put(Command.ACT_2, grid.getButton2());
		output.put(Command.ACT_3, grid.getButton3());
		output.put(Command.ACT_4, grid.getButton4());
		output.put(Command.ACT_5, grid.getButton5());
		output.put(Command.ACT_6, grid.getButton6());
		output.put(Command.ACT_7, grid.getButton7());
		output.put(Command.ACT_8, grid.getButton8());
		output.put(Command.ACT_9, grid.getButton9());
		output.put(Command.ACT_0, grid.getButton0());


		return output;
	}

	
	/**
	 * This class iterates over all Buttons in the 
	 * assigning their actions to send their respective
	 * Enum<Command> to the InterfaceAPI.
	 */
	private void setupButtons() {
		for (Map.Entry<Command, Button> entry : allButtons.entrySet()) {
			entry.getValue().setOnAction(click ->{
				GameUpdate update = interfaceAPI.initCommand(entry.getKey());
				updateGame(update);
			});
		}
	}
	
	private void updateGame(GameUpdate update) {
		
	}
	

	
	/**
	 * @return A VBox containing all other details assembled
	 * in the other methods. Three HBoxes (Menus/Save/Load, and
	 * Display/Map/Stats, and the player input buttons).
	 * 
	 * Calls the other necessary methods to produce the primary game
	 * window. Sets the Display to VGrow Always to ensure it always
	 * fills as much of the screen as possible.
	 */
	private VBox createGameWindow() {
		VBox output = new VBox();

		HBox titleBoxHBox = setMenuAndSaveLoad();
		HBox displayAndMapInventoryHBox = setDisplayAndMapStats();
		HBox inputOptionsGridPane = setInputOptionsGridPane();

		output.getChildren().add(titleBoxHBox);
		output.getChildren().add(displayAndMapInventoryHBox);
		output.getChildren().add(inputOptionsGridPane);

		VBox.setVgrow(displayAndMapInventoryHBox, Priority.ALWAYS);


		return output;
	}

	protected void updateDisplayPaneText(String inputText) {
		this.displayPane.setText(inputText);
	}
}
