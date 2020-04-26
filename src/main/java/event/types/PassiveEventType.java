package main.java.event.types;

/**
 * AbstractEvent specialisation for when a passive event option has been selected and needs to be handled
 * @author Daniel
 *
 */
public class PassiveEventType extends AbstractEvent {

	public PassiveEventType() {
		eventType = EventType.PASSIVE_EVENT;
	}
}
