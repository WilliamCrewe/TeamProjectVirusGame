package main.graphical_interface.gameWindows.inGameWindows;

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
		this.hours = new Label("08");
		hours.setFont(numberFont);
		Label spacer = new Label(":");
		spacer.setFont(numberFont);
		this.minutes = new Label("00");
		minutes.setFont(numberFont);
		this.clockPane = new AnchorPane();
		
		
		HBox digHBox = new HBox(hours, spacer, minutes);
		digHBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		
		Label digClockLabel = new Label("Current Time");
		digClockLabel.setFont(new Font(15.0));
		
		VBox totalDigitalClock = new VBox(digClockLabel, digHBox);
		totalDigitalClock.setAlignment(Pos.CENTER);
		
		
		this.clockPane.getChildren().add(totalDigitalClock);
		
		
	}
	
	public AnchorPane getDigitalClock() {
		return this.clockPane;
	}
	
	public void updateTime(int hour, int minute) {
		String newHours = "";
		String newMinutes = "";
		if (hour < 10) {
			newHours = "0"+hour;
		} else {
			newHours = ""+hour;
		}
		this.hours.setText(newHours);
		
		if (minute <10) {
			newMinutes = "0"+minute;
		} else {
			newMinutes = ""+minute;
		}
		this.minutes.setText(newMinutes);
	}

}
