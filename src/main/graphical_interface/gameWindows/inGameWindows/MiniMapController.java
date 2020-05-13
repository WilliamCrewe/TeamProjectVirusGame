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
import main.graphical_interface.util.Command;

public class MiniMapController {
	
	private GridPane buttonGrid;
	private AnchorPane tempMap;
	private final Image background = new Image("/main/graphical_interface/util/MapComponents/map_layout.png");
	
	private final double LOCBUTTONWIDTH = 80.0;
	private final double LOCBUTTONHEIGHT = 50.0;
	
	private final double CHANGEPAGEHEIGHT = 20.0;
	private final double CHANGEPAGEWIDTH = 30.0;
	
	private final double VGAP = 15.0;
	private final double HGAP = 40.0;
	
	
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
		/*
		BackgroundSize size = new BackgroundSize(100.0, 100.0, true, true, false, true);
		BackgroundImage bcImg = new BackgroundImage(this.background, BackgroundRepeat.NO_REPEAT, 
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
		Background newbg = new Background(bcImg);
		ap.setBackground(newbg);
		*/
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
		
		/*
		Button b1 = addMapButton("1", true);
			b1.setOnAction(clicked -> {GUIController.update(Command.MOVE_1);});
		Button b2 = addMapButton("2", true);
			b2.setOnAction(clicked -> {GUIController.update(Command.MOVE_2);});
		Button b3 = addMapButton("3", true);
			b3.setOnAction(clicked -> {GUIController.update(Command.MOVE_3);});
		Button b4 = addMapButton("4", true);
			b4.setOnAction(clicked -> {GUIController.update(Command.MOVE_4);});
		Button b5 = addMapButton("5", true);
			b5.setOnAction(clicked -> {GUIController.update(Command.MOVE_5);});
		Button b6 = addMapButton("6", true);
			b6.setOnAction(clicked -> {GUIController.update(Command.MOVE_6);});
		Button b7 = addMapButton("7", true);
			b7.setOnAction(clicked -> {GUIController.update(Command.MOVE_7);});
		Button b8 = addMapButton("8", true);
			b8.setOnAction(clicked -> {GUIController.update(Command.MOVE_8);});
		
		Button next = addLocMoveButton("->", true);
			next.setOnAction(clicked -> {GUIController.update(Command.LOC_MOVE_NEXT);});
		Button prev = addLocMoveButton("<-", true);
			prev.setOnAction(clicked -> {GUIController.update(Command.LOC_MOVE_PREV);});
		
		buttonGrid.add(b1, 0, 0);
		buttonGrid.add(b2, 1, 0);
		buttonGrid.add(b3, 2, 0);
		buttonGrid.add(b4, 0, 1);
		buttonGrid.add(b5, 2, 1);
		buttonGrid.add(b6, 0, 2);
		buttonGrid.add(b7, 1, 2);
		buttonGrid.add(b8, 2, 2);
		
		buttonGrid.add(next, 2, 4);
		buttonGrid.add(prev, 1, 4);
		*/
		
		Button b1 = addMapButton("Park", true);
		b1.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_PARK);});
		buttonGrid.add(b1, 0, 0);
		b1 = setButtonImage(b1, "/main/graphical_interface/util/MapComponents/ColourIcons/icon_park.png");
		Button b2 = addMapButton("", false);
		b2.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_2);});
		buttonGrid.add(b2, 1, 0);
		Button b3 = addMapButton("Bank", true);
		b3.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_BANK);});
		buttonGrid.add(b3, 2, 0);
		b3 = setButtonImage(b3, "/main/graphical_interface/util/MapComponents/ColourIcons/icon_bank.png");
		Button b4 = addMapButton("Supermarket", true);
		b4.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_SUPERMARKET);});
		buttonGrid.add(b4, 3, 0);
		b4 = setButtonImage(b4, "/main/graphical_interface/util/MapComponents/ColourIcons/icon_supermarket.png");
		Button b5 = addMapButton("", false);
		b5.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_5);});
		buttonGrid.add(b5, 4, 0);
		
		Button b6 = addMapButton("Police Station", true);
		b6.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_POLICE);});
		buttonGrid.add(b6, 0, 1);
		b6 = setButtonImage(b6, "/main/graphical_interface/util/MapComponents/ColourIcons/icon_police.png");
		Button b7 = addMapButton("", false);
		b7.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_7);});
		buttonGrid.add(b7, 1, 1);
		Button b8 = addMapButton("", false);
		b8.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_8);});
		buttonGrid.add(b8, 2, 1);
		Button b9 = addMapButton("Market", true);
		b9.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_MARKET);});
		buttonGrid.add(b9, 3, 1);
		b9 = setButtonImage(b9, "/main/graphical_interface/util/MapComponents/ColourIcons/icon_market.png");
		Button b10 = addMapButton("", false);
		b10.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_10);});
		buttonGrid.add(b10, 4, 1);
		
		Button b11 = addMapButton("", false);
		b11.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_11);});
		buttonGrid.add(b11, 0, 2);
		Button b12 = addMapButton("", false);
		b12.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_12);});
		buttonGrid.add(b12, 1, 2);
		Button b13 = addMapButton("", false);
		b13.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_13);});
		buttonGrid.add(b13, 2, 2);
		Button b14 = addMapButton("", false);
		b14.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_14);});
		buttonGrid.add(b14, 3, 2);
		Button b15 = addMapButton("", false);
		b15.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_15);});
		buttonGrid.add(b15, 4, 2);
		
		Button b16 = addMapButton("Hospital", true);
		b16.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_HOSPITAL);});
		buttonGrid.add(b16, 0, 3);
		b16 = setButtonImage(b16, "/main/graphical_interface/util/MapComponents/ColourIcons/icon_hospital.png");
		Button b17 = addMapButton("", false);
		b17.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_17);});
		buttonGrid.add(b17, 1, 3);
		Button b18 = addMapButton("Pub", true);
		b18.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_PUB);});
		buttonGrid.add(b18, 2, 3);
		b18 = setButtonImage(b18, "/main/graphical_interface/util/MapComponents/ColourIcons/icon_pub.png");
		Button b19 = addMapButton("", false);
		b19.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_19);});
		buttonGrid.add(b19, 3, 3);
		Button b20 = addMapButton("", false);
		b20.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_20);});
		buttonGrid.add(b20, 4, 3);
		
		Button b21 = addMapButton("", false);
		b21.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_21);});
		buttonGrid.add(b21, 0, 4);
		Button b22 = addMapButton("", false);
		b22.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_22);});
		buttonGrid.add(b22, 1, 4);
		Button b23 = addMapButton("Home", true);
		b23.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_HOME);});
		buttonGrid.add(b23, 2, 4);
		b23 = setButtonImage(b23, "/main/graphical_interface/util/MapComponents/ColourIcons/icon_home.png");
		Button b24 = addMapButton("", false);
		b24.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_24);});
		buttonGrid.add(b24, 3, 4);
		Button b25 = addMapButton("", false);
		b25.setOnAction(clicked -> {GUIController.moveLocation(Command.MOVE_25);});
		buttonGrid.add(b25, 4, 4);
		
		return buttonGrid;
	}
	
	/*
	private Button addMapButton(String title, boolean visibility) {
		Button button = new Button(title);
		
		button.setVisible(visibility);
		button.setPrefWidth(LOCBUTTONWIDTH);
		button.setPrefHeight(LOCBUTTONHEIGHT);
		GridPane.setHalignment(button, HPos.CENTER);
		GridPane.setValignment(button, VPos.CENTER);
		
		return button;
	}
	*/
	
	private Button setButtonImage(Button b, String imageLoc) {
		Image image = new Image(getClass().getResourceAsStream(imageLoc));
		b.setGraphic(new ImageView(image));
		
		return b;
	}
	
	private Button addMapButton(String tooltip, boolean visibility) {
		
		Button button = new Button();
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
	
	private Button addLocMoveButton(String title, boolean visibility) {
		Button button = new Button(title);
		
		button.setVisible(visibility);
		button.setPrefWidth(CHANGEPAGEWIDTH);
		button.setPrefHeight(CHANGEPAGEHEIGHT);
		
		return button;
	}

	public void setVisibileButtons(boolean[] visibileButtons) {
		if (visibileButtons.length != 10) {
			System.out.println("Array length is "+visibileButtons.length+" and must be 10.");
			return;
		}
		int i = 0;
		/*
		for (Node n : this.buttonGrid.getChildren()) {
			n.setVisible(visibileButtons[i]);
			System.out.println("Button "+(i+1)+" set to "+visibileButtons[i]);
			i++;
		}
		*/
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

}
