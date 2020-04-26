package main.java.event.types;

/**
 * Abstract object from which any actions the system need to take will be realised
 * @author Daniel
 *
 */
public class AbstractEvent {
	
	protected EventType eventType;
	
	public EventType getEventType() {
		return eventType;
	}
}
