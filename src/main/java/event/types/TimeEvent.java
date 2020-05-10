package main.java.event.types;

/**
 * AbstractEvent specialisation for when a passive event option has been selected and needs to be handled
 * @author Daniel
 *
 */
public class TimeEvent extends AbstractEvent {

	private int minutes;
	private int hours;
	
	public TimeEvent(int hours, int minutes) {
		eventType = EventType.TIME;
		
		this.hours = hours;
		this.minutes = minutes;
	}
	
	public int getMinutes() {
		return minutes;
	}
	
	public int getHours() {
		return hours;
	}

}
