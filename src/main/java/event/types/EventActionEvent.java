package main.java.event.types;

/**
 * AbstractEvent specialisation for when an event option has been selected and needs to be handled
 * @author Daniel
 *
 */
public class EventActionEvent extends AbstractEvent {

	private String eventID;
	private String eventOptionID;
	
	public EventActionEvent(String eventID, String eventOptionID) {
		eventType = EventType.ACTION_EVENT;
		this.eventID = eventID;
		this.eventOptionID = eventOptionID;
	}

	/**
	 * @return the eventID
	 */
	public String getEventID() {
		return eventID;
	}

	/**
	 * @return the eventOptionID
	 */
	public String getEventOptionID() {
		return eventOptionID;
	}
}
