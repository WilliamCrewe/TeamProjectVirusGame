package main.graphical_interface.gameWindows.constants;

public class OptionsMenuConstants {
	private OptionsMenuConstants() {
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
	

	// Styling for cancel button
	private static final String INACTIVE_CANCEL_BUTTON_FONT_SIZE = "-fx-font-size: 30px;";
	private static final String INACTIVE_CANCEL_BUTTON_TEXT_FILL = "-fx-text-fill: #A60C19;";
	private static final String ACTIVE_CANCEL_BUTTON_FONT_SIZE = "-fx-font-size: 32px;";
	private static final String ACTIVE_CANCEL_BUTTON_TEXT_FILL = "-fx-text-fill: #F0A06A;";
	
	// Styling for Go button
	private static final String INACTIVE_GO_BUTTON_FONT_SIZE = "-fx-font-size: 30px;";
	private static final String INACTIVE_GO_BUTTON_TEXT_FILL = "-fx-text-fill: #66A60C";
	private static final String ACTIVE_GO_BUTTON_FONT_SIZE = "-fx-font-size: 32px;";
	private static final String ACTIVE_GO_BUTTON_TEXT_FILL = "-fx-text-fill: #66D60C";
	
	public static final String INACTIVE_BUTTON_STYLE = BUTTON_BACKGROUND_COLOR + BUTTON_FONT + INACTIVE_BUTTON_FONT_SIZE + INACTIVE_BUTTON_TEXT_FILL;
	public static final String ACTIVE_BUTTON_STYLE = BUTTON_BACKGROUND_COLOR + BUTTON_FONT + ACTIVE_BUTTON_FONT_SIZE + ACTIVE_BUTTON_TEXT_FILL;
	
	public static final String INACTIVE_CANCEL_BUTTON_STYLE = BUTTON_BACKGROUND_COLOR + BUTTON_FONT + INACTIVE_CANCEL_BUTTON_FONT_SIZE + INACTIVE_CANCEL_BUTTON_TEXT_FILL;
	public static final String ACTIVE_CANCEL_BUTTON_STYLE = BUTTON_BACKGROUND_COLOR + BUTTON_FONT + ACTIVE_CANCEL_BUTTON_FONT_SIZE + ACTIVE_CANCEL_BUTTON_TEXT_FILL;
	
	public static final String INACTIVE_GO_BUTTON_STYLE = BUTTON_BACKGROUND_COLOR + BUTTON_FONT + INACTIVE_GO_BUTTON_FONT_SIZE + INACTIVE_GO_BUTTON_TEXT_FILL;
	public static final String ACTIVE_GO_BUTTON_STYLE = BUTTON_BACKGROUND_COLOR + BUTTON_FONT + ACTIVE_GO_BUTTON_FONT_SIZE + ACTIVE_GO_BUTTON_TEXT_FILL;

	/**
	 * Configuration for difficulty radio buttons
	 */
	// Common Radio Button configurations
	private static final String DIFFICULTY_BUTTON_BACKGROUND_COLOR = "-fx-background-color: transparent;";
	private static final String DIFFICULTY_BUTTON_FONT = "-fx-font-family:Impact, Charcoal, sans-serif;";
		
	private static final String DIFFICULTY_BUTTON_FONT_SIZE = "-fx-font-size: 24px;";
	private static final String DIFFICULTY_BUTTON_TEXT_FILL = "-fx-text-fill: #E90418;";
	
	public static final String DIFFICULTY_BUTTON_STYLE = DIFFICULTY_BUTTON_BACKGROUND_COLOR + DIFFICULTY_BUTTON_FONT + DIFFICULTY_BUTTON_FONT_SIZE + DIFFICULTY_BUTTON_TEXT_FILL;
	
	/**
	 * Configuration for title
	 */
	// Styling for active buttons
	private static final String SAVE_GAME_LABEL_FONT_SIZE = "-fx-font-size: 40px;";
	private static final String SAVE_GAME_LABEL_TEXT_FILL = "-fx-text-fill: #E90418;";
	public static final String SAVE_GAME_LABEL_STYLE = BUTTON_BACKGROUND_COLOR + BUTTON_FONT + SAVE_GAME_LABEL_FONT_SIZE + SAVE_GAME_LABEL_TEXT_FILL;
	
	/**
	 * Configuration for TextAreas
	 */
	// Common TextArea configurations
	private static final String TEXTAREA_FONT_SIZE = "-fx-font-size: 30px;";
	private static final String TEXTAREA_TEXT_FILL = "-fx-text-fill: #F5AA6A;";
	private static final String TEXTAREA_PROMT_TEXT_FILL = "-fx-text-fill: #E90418;";
	private static final String TEXTAREA_BACKGROUND_COLOR = "-fx-background-color: rgba(177,177,177,0.6); -fx-prompt-text-fill:  rgba(177,177,177,0.9);";
	private static final String TEXTAREA_BORDER_COLOR = "-fx-text-box-border: black;";
	
	public static final String TEXTAREA_STYLE = TEXTAREA_PROMT_TEXT_FILL + TEXTAREA_FONT_SIZE + TEXTAREA_TEXT_FILL + TEXTAREA_BACKGROUND_COLOR + TEXTAREA_BORDER_COLOR;
	
	public static final String BACKGROUND_IMAGE_PATH = "/main/graphical_interface/util/BrickExtraGloomy.jp";
}
