package main.graphical_interface.gameWindows.inGameWindows.constants;

public class InventoryBarControllerConstants {

	private InventoryBarControllerConstants() {
		// Private constructor to prevent instantiation
	}
	
	/**
	 * Configuration for the header labels
	 */
	private static final String HEADER_BACKGROUND_COLOR = "-fx-background-color: transparent;";
	private static final String HEADER_FONT = "-fx-font-family:Impact, Charcoal, sans-serif;";
	private static final String HEADER_LABEL_FONT_SIZE = "-fx-font-size: 30px;";
	private static final String HEADER_LABEL_TEXT_FILL = "-fx-text-fill: #E90418;";
	
	public static final String HEADER_LABEL_STYLE = HEADER_BACKGROUND_COLOR + HEADER_FONT + HEADER_LABEL_FONT_SIZE + HEADER_LABEL_TEXT_FILL;
	
	/**
	 * Configuration for the element labels
	 */
	private static final String ELEMENT_BACKGROUND_COLOR = "-fx-background-color: transparent;";
	private static final String ELEMENT_FONT = "-fx-font-family:Impact, Charcoal, sans-serif;";
	private static final String ELEMENT_LABEL_FONT_SIZE = "-fx-font-size: 26px;";
	private static final String ELEMENT_LABEL_TEXT_FILL = "-fx-text-fill: #F5AA6A;";
	
	public static final String ELEMENT_LABEL_STYLE = ELEMENT_BACKGROUND_COLOR + ELEMENT_FONT + ELEMENT_LABEL_FONT_SIZE + ELEMENT_LABEL_TEXT_FILL;
	
	/**
	 * Configuration for the inventory items
	 */
	private static final String INVENTORY_ITEM_BACKGROUND_COLOR = "-fx-background-color: transparent;";
	private static final String INVENTORY_ITEM_FONT = "-fx-font-family:Impact, Charcoal, sans-serif;";
	private static final String INVENTORY_ITEM_LABEL_FONT_SIZE = "-fx-font-size: 24px;";
	private static final String INVENTORY_ITEM_TEXT_FILL = "-fx-text-fill: #F5AA6A;";
	
	public static final String INVENTORY_ITEM_LABEL_STYLE = INVENTORY_ITEM_BACKGROUND_COLOR + INVENTORY_ITEM_FONT + INVENTORY_ITEM_LABEL_FONT_SIZE + INVENTORY_ITEM_TEXT_FILL;

	/**
	 * Configuration for the inventory item number
	 */
	private static final String INVENTORY_ITEM_NUMBER_LABEL_FONT_SIZE = "-fx-font-size: 24px;";
	private static final String INVENTORY_ITEM_NUMBER_TEXT_FILL = "-fx-text-fill: #66A60C;";
	
	public static final String INVENTORY_ITEM_NUMBER_LABEL_STYLE = INVENTORY_ITEM_BACKGROUND_COLOR + INVENTORY_ITEM_FONT + INVENTORY_ITEM_NUMBER_LABEL_FONT_SIZE + INVENTORY_ITEM_NUMBER_TEXT_FILL;

}
