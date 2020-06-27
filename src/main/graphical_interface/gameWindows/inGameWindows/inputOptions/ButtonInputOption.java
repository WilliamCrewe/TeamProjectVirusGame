package main.graphical_interface.gameWindows.inGameWindows.inputOptions;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import main.graphical_interface.GUIController;
import main.graphical_interface.gameWindows.inGameWindows.inputOptions.constants.ButtonInputOptionConstants;
import main.graphical_interface.util.Command;
import main.java.logging.SystemLogger;

public class ButtonInputOption extends PlayerInputController {
	
	private GridPane primaryGrid;
	private VBox buttonBox;
	
	private final double vSpacing = 10.0;
	private final double hSpacing = 10.0;
	
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
		
		this.primaryGrid.setVgap(vSpacing);
		this.primaryGrid.setHgap(hSpacing);
		
		buttonBox = new VBox(b1, b2, b3, b4, b5, b6);
		boolean[] testButtons = {true, true, true, false, false, true, false, false, true, false, true, false};
		setVisibileButtons(testButtons);
	}
	
	private Button addButton(String title, boolean visibility) {
		Button b = new Button(title);
		b.setStyle(ButtonInputOptionConstants.INACTIVE_OPTION_DESCRIPTION_STYLE);
		b.setOnMouseEntered(e -> b.setStyle(ButtonInputOptionConstants.ACTIVE_OPTION_DESCRIPTION_STYLE));
		b.setOnMouseExited(e -> b.setStyle(ButtonInputOptionConstants.INACTIVE_OPTION_DESCRIPTION_STYLE));
		b.setVisible(visibility);
		b.setWrapText(true);
		
		return b;
	}
	
	public void setVisibileButtons(boolean[] buttonVisibility) {
		SystemLogger.config("Assigning EventLocation Buttons.");
		if (buttonVisibility.length != 12) {
			SystemLogger.fine("Array length is %s and must be 12.", buttonVisibility.length);
			return;
		}
		int i = 0;
		for (Node n : buttonBox.getChildren()) {
			n.setVisible(buttonVisibility[i]);
			i++;
		}
	}
	
	@Override
	public VBox getButtonBox() {
		return buttonBox;
	}
	
	public void setButtonText(String[] buttonText) {
		if (buttonText.length != 10) {
			SystemLogger.fine("Array length is %s and must be 10.", buttonText.length);
			return;
		}
		int i = 0;
		for (Node n : buttonBox.getChildren()) {
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
