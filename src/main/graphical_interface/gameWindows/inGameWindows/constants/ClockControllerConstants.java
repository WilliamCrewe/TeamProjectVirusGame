package main.graphical_interface.gameWindows.inGameWindows.constants;

/**
 * Class containing the constants for the display of the clock
 * @author Daniel
 *
 */
public class ClockControllerConstants {

	private ClockControllerConstants() {
		// Private constructor to prevent instantiation
	}
	
	/**
	 * Configuration for the header
	 */
	private static final String HEADER_BACKGROUND_COLOR = "-fx-background-color: transparent;";
	private static final String HEADER_FONT = "-fx-font-family:Impact, Charcoal, sans-serif;";

	private static final String HEADER_FONT_SIZE = "-fx-font-size: 32px;";
	private static final String HEADER_TEXT_FILL = "-fx-text-fill: #E90418";
	
	public static final String HEADER_STYLE = HEADER_BACKGROUND_COLOR + HEADER_FONT + HEADER_FONT_SIZE + HEADER_TEXT_FILL;
	
	/**
	 * Configuration for the numbers
	 */
	private static final String NUMBER_FONT_SIZE = "-fx-font-size: 32px;";
	private static final String NUMBER_TEXT_FILL = "-fx-text-fill: #F5AA6A";
	
	public static final String NUMBER_STYLE = NUMBER_FONT_SIZE + NUMBER_TEXT_FILL;
	
}
