package main.graphical_interface.gameWindows;

import java.util.HashMap;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import main.graphical_interface.GUIController;
import main.graphical_interface.gameWindows.inGameWindows.ClockController;
import main.graphical_interface.gameWindows.inGameWindows.DisplayWindowController;
import main.graphical_interface.gameWindows.inGameWindows.InventoryBarController;
import main.graphical_interface.gameWindows.inGameWindows.MenuBarController;
import main.graphical_interface.gameWindows.inGameWindows.MiniMapController;
import main.graphical_interface.gameWindows.inGameWindows.inputOptions.ButtonInputOption;
import main.graphical_interface.gameWindows.inGameWindows.inputOptions.PlayerInputController;
import main.graphical_interface.util.Command;
import main.graphical_interface.util.GUIInventoryItem;

public class InGameWindow extends AbstractGameWindow {
	
	private final Image background = new Image("/main/graphical_interface/util/BrickExtraGloomy.jpg");
	
	private StackPane mainWindow;
	private static PlayerInputController input;
	private static MiniMapController miniMap;
	private static DisplayWindowController displayController;
	private static InventoryBarController inventoryController;
	private static ClockController clockController;
	
	public InGameWindow() {
		createWindow();
	}
	
	@Override
	public void createWindow() {
		this.mainWindow = new StackPane();
		AnchorPane mainAnchor = new AnchorPane();
		BackgroundSize size = new BackgroundSize(100.0, 100.0, true, true, false, true);
		BackgroundImage bcImg = new BackgroundImage(this.background, BackgroundRepeat.NO_REPEAT, 
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
		Background background = new Background(bcImg);
		mainAnchor.setBackground(background);
		
		
		VBox allSections = new VBox();
		
		//Create Top Section
		HBox topBar = new HBox();
		
		//Create MenuBar
		MenuBarController menuBar = new MenuBarController();
		Control menu = menuBar.getMainMenuBar();

		//Create Save/Load Buttons
		HBox saveLoad = createLoadSaveBar();

		//Assign to HBox
		topBar.getChildren().add(menu);
		topBar.getChildren().add(saveLoad);

		//Format TopSection
		HBox.setHgrow(menu, Priority.ALWAYS);
		topBar.setAlignment(Pos.CENTER_RIGHT);

		// Create and Assign Bottom Half
		AnchorPane bottomAnchor = new AnchorPane();
		HBox mainBox = new HBox();
		{
			//Create Display/Input
			VBox displayInputBox = new VBox();
			{
				//Create Display Window
				this.displayController = new DisplayWindowController();
				Label display = displayController.getTextView();
				display.setStyle("-fx-background-color: rgba(177,177,177,0.6);");
				display.setAlignment(Pos.TOP_LEFT);
				display.setPadding(new Insets(0.0, 10.0, 0.0,  0.0));
				
				//Create Player Input
				InGameWindow.input = new ButtonInputOption();
//				GridPane playerInput = input.getInput();
				
				//Assign to VBox
				displayInputBox.getChildren().add(display);
				displayInputBox.getChildren().add(input.getButtonBox());
				
				//Format Components
				HBox.setHgrow(display, Priority.ALWAYS);
				VBox.setVgrow(display, Priority.ALWAYS);
//				playerInput.setAlignment(Pos.BOTTOM_LEFT);
				
				//Format DisplayInput
				displayInputBox.setAlignment(Pos.TOP_LEFT);
			}
			
			displayInputBox.setStyle("-fx-background-color: rgba(177,177,177,0.7);");
			
			
			VBox minimapInventoryBox = new VBox();
			{
				//Create MinimapTemp
				InGameWindow.miniMap = new MiniMapController();
				Pane tempMap = InGameWindow.miniMap.getMiniMap();
				
				//Create Inventory Box
				InGameWindow.inventoryController = new InventoryBarController();
				VBox invBox = inventoryController.getInventoryBox();
				invBox.setStyle("-fx-background-color: transparent;");
				
				//Create Clock Box
				InGameWindow.clockController = new ClockController();
				Pane digitalClock = InGameWindow.clockController.getDigitalClock();
				
				//Assign to VBox
				minimapInventoryBox.getChildren().add(tempMap);
				minimapInventoryBox.getChildren().add(invBox);
				minimapInventoryBox.getChildren().add(digitalClock);
				
				//Format minimapInvBox
				VBox.setVgrow(minimapInventoryBox, Priority.SOMETIMES);
			}
			//Assign to mainBox
			mainBox.getChildren().add(displayInputBox);
			mainBox.getChildren().add(minimapInventoryBox);
			
			//Format mainBox
			minimapInventoryBox.setAlignment(Pos.TOP_RIGHT);
			VBox.setVgrow(displayInputBox, Priority.ALWAYS);
			HBox.setHgrow(mainBox, Priority.ALWAYS);
		}
		
		//Assign to Anchor
		bottomAnchor.getChildren().add(mainBox);
		AnchorPane.setTopAnchor(mainBox, 0.0);
		AnchorPane.setBottomAnchor(mainBox, 0.0);
		AnchorPane.setLeftAnchor(mainBox, 0.0);
		AnchorPane.setRightAnchor(mainBox, 0.0);
		
		//Assign to all sections
		allSections.getChildren().add(topBar);
		allSections.getChildren().add(bottomAnchor);
		
		//Format allSections
		mainAnchor.getChildren().add(allSections);
		
		AnchorPane.setTopAnchor(allSections, 0.0);
		AnchorPane.setLeftAnchor(allSections, 0.0);
		AnchorPane.setRightAnchor(allSections, 0.0);
		AnchorPane.setBottomAnchor(allSections, 0.0);
		
		this.mainWindow.getChildren().add(mainAnchor);
	}
		
	
	@Override
	public StackPane getWindow() {
		return this.mainWindow;
	}
	
	public static void setButtonsVisibile(boolean[] visibileButtons) {
		if (InGameWindow.input instanceof ButtonInputOption) {
			((ButtonInputOption)InGameWindow.input).setVisibileButtons(visibileButtons);
		}
	}
	
	public static void setButtonText(String[] buttonText) {
		if (InGameWindow.input instanceof ButtonInputOption) {
			((ButtonInputOption)InGameWindow.input).setButtonText(buttonText);
		}
	}
	
	public static void setLocationVisible(boolean[] visibileButtons) {
		InGameWindow.miniMap.setVisibileButtons(visibileButtons);
	}
	
	public static void setLocationText(String[] buttonText) {
		InGameWindow.miniMap.setButtonText(buttonText);
	}
	
	public static void updateMiniMap() {
		
	}
	
	public static void updateDisplayText(String text) {
		if (InGameWindow.displayController.getTextView() instanceof Label) {
			InGameWindow.displayController.updateDisplayTest(text);
		}
	}
	
	public static void updateCurrenLocation(Command moveCommand) {
		InGameWindow.miniMap.updateCurrentLocationHighlighting(moveCommand);
	}
	
	public static void updatePlayerInventory(HashMap<Integer, GUIInventoryItem> inventoryUpdate) {
		InGameWindow.inventoryController.createWholeInventory(inventoryUpdate);
	}
	
	private HBox createLoadSaveBar() {
		HBox saveLoadBar = new HBox();
		
		Button save = new Button("Save");
		save.setOnAction(clicked -> {
			GUIController.update(Command.SAVE);
		});
		Button load = new Button("Load");
		load.setOnAction(clicked -> {
			GUIController.update(Command.LOAD);
		});
		
		saveLoadBar.getChildren().add(save);
		saveLoadBar.getChildren().add(load);
		
		return saveLoadBar;
	}
	
	public static void changeCurrency(int i) {
		InGameWindow.inventoryController.updateCurrency(i);
	}
	
	public static void setCurrency(int i) {
		InGameWindow.inventoryController.setCurrency(i);
	}
	
	public static void updateTime(int hour, int minute) {
		InGameWindow.clockController.updateTime(hour, minute);
	}

}
