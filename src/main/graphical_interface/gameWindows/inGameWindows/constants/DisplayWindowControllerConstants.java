package main.graphical_interface.gameWindows.inGameWindows.constants;

/**
 * Class holding the constants for the Display Window stylings
 * @author Daniel
 *
 */
public class DisplayWindowControllerConstants {
	
	private DisplayWindowControllerConstants() {
		// Private constructor to prevent instantiation
	}
	
	/**
	 * Configuration for Text Areas
	 */
	private static final String TEXT_AREA_BACKGROUND_COLOR = "-fx-background-color: transparent;";
	private static final String TEXT_AREA_FONT = "-fx-font-family:Impact, Charcoal, sans-serif;";
	
	private static final String TEXT_AREA_FONT_SIZE = "-fx-font-size: 30px;";
	private static final String TEXT_AREA_TEXT_FILL = "-fx-text-fill: #53DA4B;";

	
	public static final String TEXT_AREA_STYLE = TEXT_AREA_BACKGROUND_COLOR + TEXT_AREA_FONT + TEXT_AREA_FONT_SIZE + TEXT_AREA_TEXT_FILL;
}
