package main.graphical_interface.gameWindows.inGameWindows.inputOptions.constants;

/**
 * Class to hold the styling for the user input buttons
 * @author Daniel
 *
 */
public class ButtonInputOptionConstants {
	
	private ButtonInputOptionConstants() {
		// Private constructor to prevent instantition
	}

	/**
	 * Configuration for the Option description
	 */
	private static final String OPTION_DESCRIPTION_BACKGROUND_COLOR = "-fx-background-color: transparent;";
	private static final String OPTION_DESCRIPTION_FONT = "-fx-font-family:Impact, Charcoal, sans-serif;";
	private static final String OPTION_DESCRIPTION_LABEL_FONT_SIZE = "-fx-font-size: 24px;";
	
	private static final String INACTIVE_OPTION_DESCRIPTION_TEXT_FILL = "-fx-text-fill: #A60C19;";
	private static final String ACTIVE_OPTION_DESCRIPTION_TEXT_FILL = "-fx-text-fill: #F0A06A;";
	
	public static final String INACTIVE_OPTION_DESCRIPTION_STYLE = OPTION_DESCRIPTION_BACKGROUND_COLOR + OPTION_DESCRIPTION_FONT + OPTION_DESCRIPTION_LABEL_FONT_SIZE + INACTIVE_OPTION_DESCRIPTION_TEXT_FILL;
	public static final String ACTIVE_OPTION_DESCRIPTION_STYLE = OPTION_DESCRIPTION_BACKGROUND_COLOR + OPTION_DESCRIPTION_FONT + OPTION_DESCRIPTION_LABEL_FONT_SIZE + ACTIVE_OPTION_DESCRIPTION_TEXT_FILL;

}
