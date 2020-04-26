package main.java.gamecontrol;

import main.java.event.types.AbstractEvent;
import main.java.event.types.EventActionEvent;
import main.java.event.types.EventType;
import main.java.gamecontrol.controllers.EventController;
import main.java.logging.SystemLogger;

public class GameController {

	private GameController() {
		// private Constructor to prevent instantiation
	}
	
	public static void handleEvent(AbstractEvent event) {
		EventType eventType = event.getEventType();
		
		SystemLogger.config("An event of type %s will be actioned", eventType);
		switch (eventType) {
		case MOVE:
		case SET_EVENT:
		case PASSIVE_EVENT:
		case ACTION_EVENT:
			EventController.handleEvent((EventActionEvent) event);
			break;
		case EXIT:
		}
	}
}
