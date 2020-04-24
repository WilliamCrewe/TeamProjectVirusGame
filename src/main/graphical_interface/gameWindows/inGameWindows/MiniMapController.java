package main.graphical_interface.gameWindows.inGameWindows;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import main.graphical_interface.GUIController;
import main.graphical_interface.util.Command;

public class MiniMapController {
	
	private GridPane buttonGrid;
	private AnchorPane tempMap;
	private final Image background = new Image("/main/graphical_interface/util/Temp.png");
	
	private final double LOCBUTTONWIDTH = 100.0;
	private final double LOCBUTTONHEIGHT = 20.0;
	
	private final double CHANGEPAGEHEIGHT = 20.0;
	private final double CHANGEPAGEWIDTH = 30.0;
	
	private final double VGAP = 10.0;
	private final double HGAP = 10.0;
	
	
	public MiniMapController() {
		setup();
	}
	
	private void setup() {
		AnchorPane miniMapTemp = setupBackground();
		
		GridPane inputGrid = setupGrid();
		this.buttonGrid = inputGrid;
		miniMapTemp.setMinHeight(200.0);
		
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
		BackgroundSize size = new BackgroundSize(100.0, 100.0, true, true, false, true);
		BackgroundImage bcImg = new BackgroundImage(this.background, BackgroundRepeat.NO_REPEAT, 
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
		Background newbg = new Background(bcImg);
		ap.setBackground(newbg);
		return ap;	
	}
	
	private GridPane setupGrid() {
		GridPane buttonGrid = new GridPane();
		
		ColumnConstraints col = new ColumnConstraints();
		col.setPercentWidth(33.3);
		RowConstraints row = new RowConstraints();
		row.setPercentHeight(20);
		
		for (int i = 0; i < 3; i++) {
			buttonGrid.getColumnConstraints().add(col);
		}
		for (int j = 0; j < 5; j++) {
			buttonGrid.getRowConstraints().add(row);
		}
		
		buttonGrid.setStyle("-fx-background-color:orangered");		
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
		
		return buttonGrid;
	}
	
	private Button addMapButton(String title, boolean visibility) {
		Button button = new Button(title);
		
		button.setVisible(visibility);
		button.setPrefWidth(LOCBUTTONWIDTH);
		button.setPrefHeight(LOCBUTTONHEIGHT);
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
		for (Node n : this.buttonGrid.getChildren()) {
			n.setVisible(visibileButtons[i]);
			System.out.println("Button "+(i+1)+" set to "+visibileButtons[i]);
			i++;
		}
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
