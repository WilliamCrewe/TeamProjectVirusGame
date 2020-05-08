package main.java.event.types;

/**
 * AbstractEvent specialisation for when the game is to be saved
 * @author Daniel
 *
 */
public class SaveEvent extends AbstractEvent {

	public SaveEvent() {
		eventType = EventType.SAVE;
	}
}
