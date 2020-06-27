package main.graphical_interface.gameWindows.constants;

/**
 * Class to contain the constants used by the SplashScreen
 * @author Daniel
 *
 */
public class SplashScreenConstants {

	private SplashScreenConstants() {
		// Private constructor to prevent instantiation
	}
	
	/**
	 * Configuration for buttons
	 */
	// Common button configurations
	private static final String BUTTON_BACKGROUND_COLOR = "-fx-background-color: transparent;";
	private static final String BUTTON_FONT = "-fx-font-family:Impact, Charcoal, sans-serif;";
	
	// Styling for inactive buttons
	private static final String INACTIVE_BUTTON_FONT_SIZE = "-fx-font-size: 30px;";
	private static final String INACTIVE_BUTTON_TEXT_FILL = "-fx-text-fill: #E90418;";
	
	// Styling for active buttons
	private static final String ACTIVE_BUTTON_FONT_SIZE = "-fx-font-size: 32px;";
	private static final String ACTIVE_BUTTON_TEXT_FILL = "-fx-text-fill: #F5AA6A;";
	
	
	public static final String INACTIVE_BUTTON_STYLE = BUTTON_BACKGROUND_COLOR + BUTTON_FONT + INACTIVE_BUTTON_FONT_SIZE + INACTIVE_BUTTON_TEXT_FILL;
	
	public static final String ACTIVE_BUTTON_STYLE = BUTTON_BACKGROUND_COLOR + BUTTON_FONT + ACTIVE_BUTTON_FONT_SIZE + ACTIVE_BUTTON_TEXT_FILL;
	
	/**
	 * Wallpaper configuration
	 */
	public static final String BACKGROUND_IMAGE_PATH = "/main/graphical_interface/util/SplashScreen.png";
}
