package main.graphical_interface.gameWindows.inGameWindows;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import main.graphical_interface.GUIController;
import main.graphical_interface.gameWindows.constants.SplashScreenConstants;
import main.graphical_interface.util.Command;
import main.java.gamecontrol.gamestate.GameState;
import main.java.logging.SystemLogger;

public class MiniMapController {
	
	private GridPane buttonGrid;
	private AnchorPane tempMap;
	private final Image background = new Image("/main/graphical_interface/util/MapComponents/new_map_layout.png");
	
	private final double LOCBUTTONWIDTH = 80.0;
	private final double LOCBUTTONHEIGHT = 50.0;
	
	private final double CHANGEPAGEHEIGHT = 20.0;
	private final double CHANGEPAGEWIDTH = 30.0;
	
	private final double VGAP = 15.0;
	private final double HGAP = 40.0;
	
	private Button parkButton;
	private Button bankButton;
	private Button superMarketButton;
	private Button hospitalButton;
	private Button pubButton;
	private Button homeButton;
	private Button marketButton;
	private Button policeButton;
	
	public MiniMapController() {
		setup();
	}
	
	private void setup() {
		AnchorPane miniMapTemp = setupBackground();
		
		GridPane inputGrid = setupGrid();
		inputGrid.setPadding(new Insets(10.0));
		this.buttonGrid = inputGrid;
		miniMapTemp.setMinHeight(250.0);
		
		AnchorPane.setTopAnchor(inputGrid, 0.0);
		AnchorPane.setBottomAnchor(inputGrid, 0.0);
		AnchorPane.setLeftAnchor(inputGrid, 0.0);
		AnchorPane.setRightAnchor(inputGrid, 0.0);
		
		inputGrid.setVgap(VGAP);
		inputGrid.setHgap(HGAP);
		
		miniMapTemp.getChildren().add(inputGrid);
		this.tempMap = miniMapTemp;
	}
	
	public AnchorPane getMiniMap() {
		return this.tempMap;
	}
	
	private AnchorPane setupBackground() {
		AnchorPane ap = new AnchorPane();
		return ap;	
	}
	
	private GridPane setupGrid() {
		GridPane buttonGrid = new GridPane();
		
		BackgroundSize size = new BackgroundSize(100.0, 100.0, true, true, false, true);
		BackgroundImage bcImg = new BackgroundImage(this.background, BackgroundRepeat.NO_REPEAT, 
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
		Background newbg = new Background(bcImg);
		
		buttonGrid.setBackground(newbg);
		
		ColumnConstraints col = new ColumnConstraints();
		col.setPercentWidth(20.0);
		RowConstraints row = new RowConstraints();
		row.setPercentHeight(20.0);
		
		for (int i = 0; i < 5; i++) {
			buttonGrid.getColumnConstraints().add(col);
		}
		for (int j = 0; j < 5; j++) {
			buttonGrid.getRowConstraints().add(row);
		}
		
		String currentLocation = "Home";
		
		if (GameState.getInstance()  != null && GameState.getInstance().getCurrentLocation() != null) {
			currentLocation = GameState.getInstance().getCurrentLocation().getLocationName();
		}
		
		System.out.println("CurrentLocation" + currentLocation);
		
		parkButton = addMapButton("Park", currentLocation.equals("Park"), true);
		parkButton.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_PARK);});
		buttonGrid.add(parkButton, 0, 0);
		parkButton = setButtonImage(parkButton, "/main/graphical_interface/util/MapComponents/ColourIcons/icon_park.png");
		
		Button b2 = addMapButton("", false, false);
		b2.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_2);});
		buttonGrid.add(b2, 1, 0);
		
		bankButton = addMapButton("Bank", currentLocation.equals("Bank"),true);
		bankButton.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_BANK);});
		buttonGrid.add(bankButton, 2, 0);
		bankButton = setButtonImage(bankButton, "/main/graphical_interface/util/MapComponents/ColourIcons/icon_bank.png");
		
		superMarketButton = addMapButton("Supermarket", currentLocation.equals("Supermarket"),true);
		superMarketButton.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_SUPERMARKET);});
		buttonGrid.add(superMarketButton, 3, 0);
		superMarketButton = setButtonImage(superMarketButton, "/main/graphical_interface/util/MapComponents/ColourIcons/icon_supermarket.png");
		
		Button b5 = addMapButton("", false, false);
		b5.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_5);});
		buttonGrid.add(b5, 4, 0);
		
		policeButton = addMapButton("Police Station", currentLocation.equals("PoliceStation"),true);
		policeButton.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_POLICE);});
		buttonGrid.add(policeButton, 0, 1);
		policeButton = setButtonImage(policeButton, "/main/graphical_interface/util/MapComponents/ColourIcons/icon_police.png");
		
		Button b7 = addMapButton("", false, false);
		b7.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_7);});
		buttonGrid.add(b7, 1, 1);
		Button b8 = addMapButton("", false, false);
		b8.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_8);});
		buttonGrid.add(b8, 2, 1);
		
		marketButton = addMapButton("Market", currentLocation.equals("Market"),true);
		marketButton.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_MARKET);});
		buttonGrid.add(marketButton, 3, 1);
		marketButton = setButtonImage(marketButton, "/main/graphical_interface/util/MapComponents/ColourIcons/icon_market.png");
		
		Button b10 = addMapButton("", false, false);
		b10.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_10);});
		buttonGrid.add(b10, 4, 1);
		
		Button b11 = addMapButton("", false, false);
		b11.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_11);});
		buttonGrid.add(b11, 0, 2);
		Button b12 = addMapButton("", false, false);
		b12.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_12);});
		buttonGrid.add(b12, 1, 2);
		Button b13 = addMapButton("", false, false);
		b13.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_13);});
		buttonGrid.add(b13, 2, 2);
		Button b14 = addMapButton("", false, false);
		b14.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_14);});
		buttonGrid.add(b14, 3, 2);
		Button b15 = addMapButton("", false, false);
		b15.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_15);});
		buttonGrid.add(b15, 4, 2);
		

		hospitalButton = addMapButton("Hospital", currentLocation.equals("Hospital"),true);
		hospitalButton.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_HOSPITAL);});
		buttonGrid.add(hospitalButton, 0, 3);
		hospitalButton = setButtonImage(hospitalButton, "/main/graphical_interface/util/MapComponents/ColourIcons/icon_hospital.png");
		
		Button b17 = addMapButton("", false, false);
		b17.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_17);});
		buttonGrid.add(b17, 1, 3);
		
		pubButton = addMapButton("Pub", currentLocation.equals("Pub"),true);
		pubButton.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_PUB);});
		buttonGrid.add(pubButton, 2, 3);
		pubButton = setButtonImage(pubButton, "/main/graphical_interface/util/MapComponents/ColourIcons/icon_pub.png");
		
		
		Button b19 = addMapButton("", false, false);
		b19.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_19);});
		buttonGrid.add(b19, 3, 3);
		Button b20 = addMapButton("", false, false);
		b20.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_20);});
		buttonGrid.add(b20, 4, 3);
		
		Button b21 = addMapButton("", false, false);
		b21.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_21);});
		buttonGrid.add(b21, 0, 4);
		Button b22 = addMapButton("", false, false);
		b22.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_22);});
		buttonGrid.add(b22, 1, 4);
		
		homeButton = addMapButton("Home", currentLocation.equals("Home"),true);
		homeButton.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_HOME);});
		buttonGrid.add(homeButton, 2, 4);
		homeButton = setButtonImage(homeButton, "/main/graphical_interface/util/MapComponents/ColourIcons/icon_home.png");
		Button b24 = addMapButton("", false, false);
		b24.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_24);});
		buttonGrid.add(b24, 3, 4);
		Button b25 = addMapButton("", false, false);
		b25.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_25);});
		buttonGrid.add(b25, 4, 4);
		
		updateCurrentLocationHighlighting(Command.MOVE_HOME);
		return buttonGrid;
	}
	
	private Button setButtonImage(Button b, String imageLoc) {
		Image image = new Image(getClass().getResourceAsStream(imageLoc));
		b.setGraphic(new ImageView(image));
		
		return b;
	}
	
	private Button addMapButton(String tooltip, boolean isCurrentLocation, boolean visibility) {
		
		Button button = new Button();
		button.setStyle("-fx-background-color: transparent;");
		
		button.setOnMouseEntered(e -> button.setStyle("-fx-background-color:rgba(255,0,0,0.8);"));
		button.setOnMouseExited(e -> button.setStyle("-fx-background-color: transparent;"));
		
		button.setTooltip(new Tooltip(tooltip));
		button.setVisible(visibility);
		button.setPrefWidth(LOCBUTTONWIDTH);
		GridPane.setHgrow(button, Priority.ALWAYS);
		button.setPrefHeight(LOCBUTTONHEIGHT);
		GridPane.setVgrow(button, Priority.ALWAYS);
		GridPane.setHalignment(button, HPos.CENTER);
		GridPane.setValignment(button, VPos.CENTER);
		
		return button;
	}
	
	public void setVisibileButtons(boolean[] visibileButtons) {
		if (visibileButtons.length != 10) {
			System.out.println("Array length is "+visibileButtons.length+" and must be 10.");
			return;
		}
		int i = 0;
	}

	public void setButtonText(String[] buttonText) {
		if (buttonText.length != 8) {
			System.out.println("Array length is "+buttonText.length+" and must be 10.");
			return;
		}
		int i = 0;
		for (Node n : this.buttonGrid.getChildren()) {
			if (buttonText[i]!=null) {
				((Button) n).setText(buttonText[i]);
				i++;
			}
		}
	}
	
	private void setButtonStyling(Button button) {
		button.setStyle("-fx-background-color: transparent;");
		
		button.setOnMouseEntered(e -> button.setStyle("-fx-background-color:rgba(255,0,0,0.8);"));
		button.setOnMouseExited(e -> button.setStyle("-fx-background-color: transparent;"));
	}
	
	private void setButtonStylingCurrentLocation(Button button) {
		button.setStyle("-fx-background-color:rgba(99,193,193,0.8);");
		
		button.setOnMouseEntered(null);
		button.setOnMouseExited(null);
	}
	
	public void updateCurrentLocationHighlighting(Command moveCommand) {
		System.out.println("DingUpdate" + moveCommand.name());
		
		if (!(moveCommand == Command.MOVE_BANK || moveCommand == Command.MOVE_HOME || moveCommand == Command.MOVE_HOSPITAL
				|| moveCommand == Command.MOVE_MARKET || moveCommand == Command.MOVE_PARK ||
				moveCommand == Command.MOVE_POLICE || moveCommand == Command.MOVE_PUB || moveCommand == Command.MOVE_SUPERMARKET)) {
			SystemLogger.severe("Unexpected move command of %s", moveCommand.name());
			return;
		}
		// Set all the locations to default
		setButtonStyling(bankButton);
		setButtonStyling(parkButton);
		setButtonStyling(superMarketButton);
		setButtonStyling(hospitalButton);
		setButtonStyling(pubButton);
		setButtonStyling(homeButton);
		setButtonStyling(marketButton);
		setButtonStyling(policeButton);
		
		// Set the new location to active
		switch (moveCommand) {
		case MOVE_BANK:
			setButtonStylingCurrentLocation(bankButton);
			break;
		case MOVE_HOME:
			setButtonStylingCurrentLocation(homeButton);
			break;
		case MOVE_HOSPITAL:
			setButtonStylingCurrentLocation(hospitalButton);
			break;
		case MOVE_MARKET:
			setButtonStylingCurrentLocation(marketButton);
			break;
		case MOVE_PARK:
			setButtonStylingCurrentLocation(parkButton);
			break;
		case MOVE_POLICE:
			setButtonStylingCurrentLocation(policeButton);
			break;
		case MOVE_PUB:
			setButtonStylingCurrentLocation(pubButton);
			break;
		case MOVE_SUPERMARKET:
			setButtonStylingCurrentLocation(superMarketButton);
			break;
		default:
			SystemLogger.severe("Unexpected move command of %s", moveCommand.name());
		}
	}

}
