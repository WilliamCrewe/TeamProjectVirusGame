package main.graphical_interface.gameWindows.inGameWindows.inputOptions;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import main.graphical_interface.GUIController;
import main.graphical_interface.util.Command;

public class ButtonInputOption extends PlayerInputController {
	
	private GridPane primaryGrid;
	
	private final double vSpacing = 10.0;
	private final double hSpacing = 10.0;
	
	private final double buttonHeight = 50.0;
	private final double buttonWidth = 100.0;
	
	public ButtonInputOption() {
		setup();
	}
	
	private void setup() {
		this.primaryGrid = new GridPane();
		
		Button b1 = addButton("1", true);
			b1.setOnAction(clicked -> {GUIController.update(Command.ACT_1);});
		Button b2 = addButton("2", true);
			b2.setOnAction(clicked -> {GUIController.update(Command.ACT_2);});
		Button b3 = addButton("3", true);
			b3.setOnAction(clicked -> {GUIController.update(Command.ACT_3);});
		Button b4 = addButton("4", true);
			b4.setOnAction(clicked -> {GUIController.update(Command.ACT_4);});
		Button b5 = addButton("5", true);
			b5.setOnAction(clicked -> {GUIController.update(Command.ACT_5);});
		Button b6 = addButton("6", true);
			b6.setOnAction(clicked -> {GUIController.update(Command.ACT_6);});
		Button b7 = addButton("7", true);
			b7.setOnAction(clicked -> {GUIController.update(Command.ACT_7);});
		Button b8 = addButton("8", true);
			b8.setOnAction(clicked -> {GUIController.update(Command.ACT_8);});
		Button b9 = addButton("9", true);
			b9.setOnAction(clicked -> {GUIController.update(Command.ACT_9);});
		Button b0 = addButton("0", true);
			b0.setOnAction(clicked -> {GUIController.update(Command.ACT_0);});
		
		Button next = addButton("Next Page", true);
			next.setOnAction(clicked -> {GUIController.update(Command.PAGE_NEXT);});
		Button prev = addButton("Previous Page", true);
			prev.setOnAction(clicked -> {GUIController.update(Command.PAGE_PREV);});
		
		this.primaryGrid.setVgap(vSpacing);
		this.primaryGrid.setHgap(hSpacing);
		
		primaryGrid.add(b1, 0, 0);
		primaryGrid.add(b2, 1, 0);
		primaryGrid.add(b3, 2, 0);
		primaryGrid.add(b4, 3, 0);
		primaryGrid.add(b5, 4, 0);
		primaryGrid.add(b6, 0, 1);
		primaryGrid.add(b7, 1, 1);
		primaryGrid.add(b8, 2, 1);
		primaryGrid.add(b9, 3, 1);
		primaryGrid.add(b0, 4, 1);
		
		primaryGrid.add(next, 6, 0, 2, 1);
		primaryGrid.add(prev, 6, 1, 2, 1);
		
		boolean[] testButtons = {true, true, true, false, false, true, false, false, true, false, true, false};
		setVisibileButtons(testButtons);
	}
	
	private Button addButton(String title, boolean visibility) {
		Button b = new Button(title);
		
		b.setVisible(visibility);
		b.setPrefWidth(buttonWidth);
		b.setPrefHeight(buttonHeight);
		
		return b;
	}
	
	public void setVisibileButtons(boolean[] buttonVisibility) {
		if (buttonVisibility.length != 12) {
			System.out.println("Array length is "+buttonVisibility.length+" and must be 12.");
			return;
		}
		int i = 0;
		for (Node n : primaryGrid.getChildren()) {
			n.setVisible(buttonVisibility[i]);
			System.out.println("Button "+(i+1)+" set to "+buttonVisibility[i]);
			i++;
		}
	}
	
	public void setButtonText(String[] buttonText) {
		if (buttonText.length != 10) {
			System.out.println("Array length is "+buttonText.length+" and must be 10.");
			return;
		}
		int i = 0;
		for (Node n : primaryGrid.getChildren()) {
			if (buttonText[i]!=null) {
				((Button) n).setText(buttonText[i]);
				i++;
			}
		}
	}
	
	@Override
	public GridPane getInput() {
		return primaryGrid;
	}
	
	

}
