package main.graphical_interface.gameWindows.inGameWindows;

import javafx.scene.control.Label;
import main.graphical_interface.gameWindows.inGameWindows.constants.DisplayWindowControllerConstants;

/**
 * Class to contain the logic for displaying the location or event information on the screen
 * @author Daniel
 *
 */
public class DisplayWindowController {
	
	private Label textView;
	
	public DisplayWindowController() {
		setup();
	}
	
	private void setup() {
		textView = new Label();
		textView.setStyle(DisplayWindowControllerConstants.TEXT_AREA_STYLE);
		textView.setWrapText(true);
		textView.setFocusTraversable(false);
		textView.setPrefWidth(1000.00);
	}
	
	public Label getTextView() {
		return this.textView;
	}
	
	public void updateDisplayTest(String input) {
		textView.setText(input);
		textView.setStyle(DisplayWindowControllerConstants.TEXT_AREA_STYLE);
		
	}

}
