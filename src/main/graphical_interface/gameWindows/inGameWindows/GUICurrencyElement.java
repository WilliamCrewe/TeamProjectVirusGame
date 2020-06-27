package main.graphical_interface.gameWindows.inGameWindows;

import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import main.graphical_interface.gameWindows.inGameWindows.constants.InventoryBarControllerConstants;

public class GUICurrencyElement {
	
	private HBox element;
	
	private Label currencyNameLabel;
	private String currencyName;
	
	private Label currencyQuantityLabel;
	private int currencyQuantity;
	
	public GUICurrencyElement() {
		setup();
	}
	
	private void setup() {
		HBox whole = new HBox();
		
		this.currencyName = " - Toilet Roll: ";
		this.currencyNameLabel = newLabel(this.currencyName);
		currencyNameLabel.setStyle(InventoryBarControllerConstants.ELEMENT_LABEL_STYLE);
		
		//Spacer Pane
		Pane spacer = new Pane();
		spacer.setStyle("-fx-background-color: transparent;");
		HBox.setHgrow(spacer, Priority.min(Priority.NEVER, Priority.SOMETIMES));
		spacer.setMinWidth(5.0);
		
		this.currencyQuantity = 0;
		this.currencyQuantityLabel = newLabel(""+currencyQuantity);
		currencyQuantityLabel.setStyle("-fx-text-fill: #F5AA6A; -fx-font-family:Impact, Charcoal, sans-serif; -fx-font-size: 24px; -fx-background-color: black;");
		whole.getChildren().addAll(currencyNameLabel, currencyQuantityLabel);
		whole.setStyle("-fx-background-color: transparent;");
		
		this.element = whole;
	}
	
	public HBox getElement() {
		return this.element;
	}
	
	private Label newLabel(String title) {
		Label newLabel = new Label(title);
		
		newLabel.setTooltip(new Tooltip("This is a currency used in the game.\n"
				+ "Be careful how you spend it!"));
		
		return newLabel;
	}
	
	public void changeCurrencyQuantity(int newValue) {
		this.currencyQuantity = newValue;
		this.currencyQuantityLabel.setText(""+this.currencyQuantity);
		currencyQuantityLabel.setStyle(InventoryBarControllerConstants.ELEMENT_LABEL_STYLE);
	}
	
	public void addToCurrencyQuantity(int addedValue) {
		this.currencyQuantity += addedValue;
		this.currencyQuantityLabel.setText(""+this.currencyQuantity);
		currencyQuantityLabel.setStyle(InventoryBarControllerConstants.ELEMENT_LABEL_STYLE);
	}
	
	public void setCurrencyLabel(String labelTitle) {
		this.currencyName = labelTitle;
		this.currencyNameLabel.setText(labelTitle);
		currencyNameLabel.setStyle(InventoryBarControllerConstants.ELEMENT_LABEL_STYLE);
	}
	
	

}
