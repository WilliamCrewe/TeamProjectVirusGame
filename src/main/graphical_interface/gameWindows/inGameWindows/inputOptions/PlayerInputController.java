package main.graphical_interface.gameWindows.inGameWindows.inputOptions;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import main.graphical_interface.GUIController;

public abstract class PlayerInputController {
	
	private void handleInput() {
		GUIController.update(null);
	}
	
	public GridPane getInput() {
		return null;
	}

	public VBox getButtonBox() {
		// TODO Auto-generated method stub
		return null;
	}

}
