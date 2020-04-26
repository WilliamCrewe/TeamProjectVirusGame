package main.java.event.types;

/**
 * AbstractEvent specialisation for when a move option has been selected and needs to be handled
 * @author Daniel
 *
 */
public class MoveEvent extends AbstractEvent {

	private String newLocationID;
	
	public MoveEvent(String newLocationID) {
		eventType = EventType.MOVE;
		this.newLocationID = newLocationID;
	}
	
	/**
	 * @return the newLocationID
	 */
	public String getNewLocationID() {
		return newLocationID;
	}
}
