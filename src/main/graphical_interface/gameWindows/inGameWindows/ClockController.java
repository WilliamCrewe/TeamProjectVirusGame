package main.graphical_interface.gameWindows.inGameWindows;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import main.graphical_interface.gameWindows.constants.OptionsMenuConstants;
import main.graphical_interface.gameWindows.inGameWindows.constants.ClockControllerConstants;

/**
 * Class containing the logic for displaying the time of the system
 * @author Daniel
 *
 */
public class ClockController {
	
	private AnchorPane clockPane;
	private Label hours;
	private Label spacer;
	private Label minutes;
	
	private Font numberFont = new Font(20.0);

	
	public ClockController() {
		setup();
	}
	
	private void setup() {	
		// Setup the Hours
		hours = new Label("07");
		hours.setFont(numberFont);
		hours.setStyle(ClockControllerConstants.NUMBER_STYLE);
		
		// Setup the separator
		spacer = new Label(":");
		spacer.setStyle(OptionsMenuConstants.INACTIVE_BUTTON_STYLE);
		
		// Make the seperator blink
		FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), spacer);
		fadeTransition.setFromValue(1.0);
		fadeTransition.setToValue(0.0);
		fadeTransition.setCycleCount(Animation.INDEFINITE);
        fadeTransition.play();
        
        // Setup the minutes
		minutes = new Label("30");
		minutes.setFont(numberFont);
		minutes.setStyle(ClockControllerConstants.NUMBER_STYLE);
		
		// Join the Hours + Seperator + Minutes together
		HBox digHBox = new HBox(hours, spacer, minutes);
		digHBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		
		// Setup the label
		Label digClockLabel = new Label("Current Time");
		digClockLabel.setFont(new Font(15.0));
		digClockLabel.setStyle(ClockControllerConstants.HEADER_STYLE);
		
		VBox totalDigitalClock = new VBox(digClockLabel, digHBox);
		totalDigitalClock.setAlignment(Pos.CENTER);
		
		clockPane = new AnchorPane();
		
		
		clockPane.getChildren().add(totalDigitalClock);
	}
	
	public AnchorPane getDigitalClock() {
		return this.clockPane;
	}
	
	/**
	 * Updates the display time of the system
	 * @param hour
	 * @param minute
	 */
	public void updateTime(int hour, int minute) {
		String newHours = "";
		String newMinutes = "";
		
		// If the value is less than 10 then prefix it with 0
		if (hour < 10) {
			newHours = "0"+hour;
		} else {
			newHours = ""+hour;
		}
		hours.setText(newHours);
		
		// If the value is less than 10 then prefix it with 0
		if (minute <10) {
			newMinutes = "0"+minute;
		} else {
			newMinutes = ""+minute;
		}
		minutes.setText(newMinutes);
	}

}
