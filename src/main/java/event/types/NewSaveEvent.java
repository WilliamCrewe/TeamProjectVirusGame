package main.java.event.types;

/**
 * AbstractEvent specialisation for when a new save is being created
 * @author Daniel
 *
 */
public class NewSaveEvent extends AbstractEvent {

	private String saveName;
	private String seed;
	private String difficulty;
	
	public NewSaveEvent(String saveName, String seed, String difficulty) {
		eventType = EventType.PASSIVE_EVENT;
		this.saveName = saveName;
		this.seed = seed;
		this.difficulty = difficulty;
	}
	
	public String getSaveName() {
		return saveName;
	}
	
	public String getSeed() {
		return seed;
	}
	
	public String getDifficulty() {
		return difficulty;
	}
}
