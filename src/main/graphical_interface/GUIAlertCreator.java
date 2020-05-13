package main.graphical_interface;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import main.java.logging.SystemLogger;

public class GUIAlertCreator {
	
	public GUIAlertCreator() {
		
	}
	
	public static void createWarningAlert(String title, String descriptionText) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(title);
		alert.setHeaderText(descriptionText);
		alert.showAndWait();
		if (alert.getResult() != ButtonType.OK) {
			return;
		}
	}
	
	public static void createQuitAlert(String title, String descriptionText) {
		SystemLogger.config("Creating a Warning Alert");
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(descriptionText);
		alert.showAndWait();
		if (alert.getResult() != ButtonType.OK) {
			return;
		}
		Runnable x = Platform::exit;
		x.run();
	}

}
