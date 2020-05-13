package main.java.gamecontrol;

import main.java.alert.AlertQueue;
import main.java.alert.types.ExhaustionAlert;
import main.java.event.types.AbstractEvent;
import main.java.event.types.EventActionEvent;
import main.java.event.types.EventType;
import main.java.event.types.LoadEvent;
import main.java.event.types.MoveEvent;
import main.java.event.types.NewSaveEvent;
import main.java.event.types.TimeEvent;
import main.java.gamecontrol.controllers.EventController;
import main.java.gamecontrol.controllers.EventPickerController;
import main.java.gamecontrol.controllers.InfectionController;
import main.java.gamecontrol.controllers.MovementController;
import main.java.gamecontrol.controllers.PassiveEventController;
import main.java.gamecontrol.controllers.SaveController;
import main.java.gamecontrol.controllers.TimeController;
import main.java.gamecontrol.gamestate.GameState;
import main.java.logging.SystemLogger;

/**
 * High level controller which delegates the handling of the event passed in to the correct sub-controller
 * @author Daniel
 *
 */
public class GameController {

	private GameController() {
		// private Constructor to prevent instantiation
	}
	
	public static void handleEvent(AbstractEvent event) {
		EventType eventType = event.getEventType();
		
		// If the user is exhausted then block them from doing some actions
		if (GameState.getInstance().getTimeLord().isExhaustionTimeReached() && !(eventType == EventType.MOVE || eventType == EventType.SLEEP)) {
			SystemLogger.config("The user is exhausted, cannot action event %s", eventType);
			AlertQueue.getInstance().add(new ExhaustionAlert());
			return;
		}
		
		SystemLogger.config("An event of type %s will be actioned", eventType);
		// Call the controller for the eventType passed in
		switch (eventType) {
		case MOVE:
			MovementController.handleMove((MoveEvent) event);
			EventPickerController.pickAndSetRandomEvent();
			TimeController.handleWait(new TimeEvent(0, 30));
			break;
		case SET_EVENT:
		case PASSIVE_EVENT:
			PassiveEventController.setActiveEventToPassiveForLocation();
			break;
		case ACTION_EVENT:
			EventController.handleEvent((EventActionEvent) event);
			TimeController.handleWait(new TimeEvent(2, 0));
			break;
		case SAVE:
			SaveController.handleSave();
			break;
		case TIME:
			TimeController.handleWait((TimeEvent) event);
			break;
		case SLEEP:
			TimeController.handleDayEnd();
			InfectionController.handleInfection();
			break;
		case NEW_SAVE:
			SaveController.handleSaveCreation((NewSaveEvent) event); 
			break;
		case LOAD:
			SaveController.handleLoad((LoadEvent) event);
			break;
		case EXIT:
		
		}
	}
}
