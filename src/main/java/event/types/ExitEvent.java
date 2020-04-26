package main.java.event.types;

/**
 *  AbstractEvent specialisation for when the game should be exited
 * @author Daniel
 *
 */
public class ExitEvent extends AbstractEvent {

	public ExitEvent() {
		eventType = EventType.EXIT;
	}
}
