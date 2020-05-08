package main.java.event.types;

/**
 * AbstractEvent specialisation for when the game is to be loaded
 * @author Daniel
 *
 */
public class LoadEvent extends AbstractEvent {

	private final String filePath;
	
	public LoadEvent(String filePath) {
		eventType = EventType.LOAD;
		this.filePath = filePath;
	}
	
	public String getFilePath() {
		return filePath;
	}
}
