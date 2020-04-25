package main.java.filehandling.reader;

/**
 * Enum representing the different directories the solution has access to
 * @author Daniel
 *
 */
public enum GameDirectory {
	CHARACTERS_DIRECTORY("Characters"),
	EVENTS_DIRECTORYT("Events"),
	SAVES_DIRECTORY("Saves"),
	LOCATIONS_DIRECTORY("Locations"),
	PROPERTIES_DIRECTORY("Properties");
	
	private String directoryName;
	
	private GameDirectory(String directoryName) {
		this.directoryName = directoryName;
	}
	
	public String getDirctoryName() {
		return directoryName;
	}
}
