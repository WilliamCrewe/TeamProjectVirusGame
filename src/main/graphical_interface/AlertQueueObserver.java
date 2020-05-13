package main.graphical_interface;

import java.util.Observable;
import java.util.Observer;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import main.java.alert.AlertQueue;
import main.java.alert.types.AbstractAlert;
import main.java.alert.types.AlertType;
import main.java.logging.SystemLogger;

public class AlertQueueObserver implements Observer{
	
	public AlertQueueObserver() {
		AlertQueue.getInstance().addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Alerted!");
		handleEvent(AlertQueue.getInstance().remove());
		
	}
	
	private void handleEvent(AbstractAlert alert) {
		SystemLogger.config("Handling alert %s", alert.getEventType().toString());
		AlertType alertType = alert.getEventType();
		switch (alertType) {
		case DEFAULT:
			
			break;
		case EXHAUSTION:
			GUIAlertCreator.createWarningAlert(alertType.toString(), alert.getMessage());
			break;
		default:
			break;
			
		}
	}
	
	private void createAlert() {
		
	}

}
