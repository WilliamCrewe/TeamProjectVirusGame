package gui;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * @author will_crewe
 *
 */
public class CreatePlayerInputsGridPane {
	
	GridPane directionalGrid;
	GridPane primaryInputGrid;
	VBox upDownBox;
	
	Button upButton;
	Button downButton;
	
	Button northButton;
	Button westButton;
	Button southButton;
	Button eastButton;
	
	
	Button button1;
	Button button2;
	Button button3;
	Button button4;
	Button button5;
	Button button6;
	Button button7;
	Button button8;
	Button button9;
	Button button0;
	
	final double  vSpacing = 10.0;
	final double hSpacing = 10.0;
	

	/**
	 * Basic constructor to initialise buttons, GridPanes and the VBox.
	 */
	public CreatePlayerInputsGridPane() {
		this.directionalGrid = new GridPane();
		this.primaryInputGrid = new GridPane();
		this.upDownBox = new VBox();
		
		this.upButton = addButton("Up", true);
		this.downButton = addButton("Down", true);
		
		this.northButton = addButton("North", true);
		this.westButton = addButton("West", true);
		this.southButton = addButton("South", true);
		this.eastButton = addButton("East", true);
		
		this.button1 = addButton("1", true);
		this.button2 = addButton("2", true);
		this.button3 = addButton("3", true);
		this.button4 = addButton("4", true);
		this.button5 = addButton("5", true);
		this.button6 = addButton("6", true);
		this.button7 = addButton("7", true);
		this.button8 = addButton("8", true);
		this.button9 = addButton("9", true);
		this.button0 = addButton("0", true);
		
	}

	/**
	 * @return		A GridPane containing four buttons, each
	 * 				relating to the cardinal directions.
	 * 
	 * Has 3 Columns and 2 Rows
	 */
	public GridPane getDirectionalInputGrid() {
		this.directionalGrid.setVgap(vSpacing);
		this.directionalGrid.setHgap(hSpacing);
		
		this.directionalGrid.add(northButton, 1, 0);
		this.directionalGrid.add(westButton, 0, 1);
		this.directionalGrid.add(southButton, 1, 1);
		this.directionalGrid.add(eastButton, 2, 1);
		
		return directionalGrid;
	}
	
	/**
	 * @return		A GridPane containing 10 buttons, each
	 * 				assigned a value between 1 and 0.
	 * 
	 * Has 5 Columns and 2 Rows.
	 */
	public GridPane getPrimaryInputGrid() {
		this.primaryInputGrid.setVgap(vSpacing);
		this.primaryInputGrid.setHgap(hSpacing);
		
		this.primaryInputGrid.add(button1, 0, 0);
		this.primaryInputGrid.add(button2, 1, 0);
		this.primaryInputGrid.add(button3, 2, 0);
		this.primaryInputGrid.add(button4, 3, 0);
		this.primaryInputGrid.add(button5, 4, 0);
		this.primaryInputGrid.add(button6, 0, 1);
		this.primaryInputGrid.add(button7, 1, 1);
		this.primaryInputGrid.add(button8, 2, 1);
		this.primaryInputGrid.add(button9, 3, 1);
		this.primaryInputGrid.add(button0, 4, 1);
		
		
		return primaryInputGrid;
	}
	
	/**
	 * @return		A VBox with the Up and Down buttons.
	 * 
	 * A placeholder method for assigning blanket
	 * conditions to the Up and Down Buttons.
	 */
	public VBox getUpDownVBox() {
		this.upDownBox.setSpacing(vSpacing);
		
		this.upDownBox.getChildren().add(this.upButton);
		this.upDownBox.getChildren().add(this.downButton);
		return this.upDownBox;
	}
	
	/**
	 * @param title			A String to be displayed as Text on the Button
	 * @param visibility	A boolean value for whether the button can be seen.
	 * @return	A Button created using the provided parameters, setting its Width
	 * 			to 100, and Height to 50.
	 */
	private Button addButton(String title, boolean visibility) {
		Button output = new Button();
		
		output.setText(title);
		output.setVisible(visibility);
		output.setPrefWidth(100.0);
		output.setPrefHeight(50.0);
		
		return output;
	}

}
