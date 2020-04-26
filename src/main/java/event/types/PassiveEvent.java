package main.java.event.types;

/**
 * AbstractEvent specialisation for when a passive event option has been selected and needs to be handled
 * @author Daniel
 *
 */
public class PassiveEvent extends AbstractEvent {

	public PassiveEvent() {
		eventType = EventType.PASSIVE_EVENT;
	}
}
