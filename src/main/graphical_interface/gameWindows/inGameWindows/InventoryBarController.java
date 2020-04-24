package main.graphical_interface.gameWindows.inGameWindows;

import javafx.scene.control.TextArea;

public class InventoryBarController {
	
	private TextArea inventoryBox;
	
	public InventoryBarController() {
		setup();
	}
	
	private void setup() {
		TextArea invBox = new TextArea();
		invBox.setMinWidth(200.0);
		invBox.setMaxWidth(300.0);
		invBox.setEditable(false);
		invBox.setWrapText(true);
		invBox.setFocusTraversable(false);
		this.inventoryBox = invBox;
	}
	
	public TextArea getInventoryBox() {
		return this.inventoryBox;
	}

}
