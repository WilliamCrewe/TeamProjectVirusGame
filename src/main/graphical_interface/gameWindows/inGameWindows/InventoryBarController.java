package main.graphical_interface.gameWindows.inGameWindows;

import java.util.HashMap;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import main.graphical_interface.gameWindows.inGameWindows.constants.InventoryBarControllerConstants;
import main.graphical_interface.util.GUIInventoryItem;
import main.java.logging.SystemLogger;

/**
 * Class controlling the Inventory Bar (Including the currency)
 * @author Daniel
 *
 */
public class InventoryBarController {
	
	private VBox returnedBox;
	private VBox inventoryBox;
	private GUICurrencyElement currency;
	
	public InventoryBarController() {
		setup();
	}
	
	private void setup() {
		VBox currencyAndInv = new VBox();
		
		//Setup Currency Window
		VBox totalCurrencyVBox = new VBox();
		totalCurrencyVBox.setStyle("-fx-background-color: transparent;");
		Label currencyTitle = new Label("Currency");
		currencyTitle.setStyle(InventoryBarControllerConstants.HEADER_LABEL_STYLE);
		
		HBox currencyBox = setupCurrency();
		
		totalCurrencyVBox.getChildren().addAll(currencyTitle, currencyBox);
		
		//Setup Inventory
		VBox inventoryWrapper = new VBox();
		
		Label inventoryLabel = new Label("Inventory");
		inventoryLabel.setStyle(InventoryBarControllerConstants.HEADER_LABEL_STYLE);
		
		setupInventory();
		
		inventoryWrapper.getChildren().addAll(inventoryLabel, inventoryBox);
		
		
		//Assign currency and Inventory to VBox
		currencyAndInv.getChildren().addAll(totalCurrencyVBox, inventoryWrapper);
		currencyAndInv.setStyle("-fx-background-color: transparent;");
		
		
		this.returnedBox = currencyAndInv;
	}
	

	public VBox getInventoryBox() {
		return this.returnedBox;
	}
	
	
	private HBox setupCurrency() {
		this.currency = new GUICurrencyElement();
		HBox toiletRoll = this.currency.getElement();
		toiletRoll.setStyle("-fx-background-color: transparent;");
		
		HBox currencyHBox = new HBox();
		currencyHBox.getChildren().add(toiletRoll);
		currencyHBox.setStyle("-fx-background-color: transparent;");
		return currencyHBox;
	}
	
	private void setupInventory() {
		VBox inventory = new VBox();
		inventory.setSpacing(10.0);
		inventory.setStyle(InventoryBarControllerConstants.INVENTORY_ITEM_LABEL_STYLE);
		
		this.inventoryBox = inventory;
	}
	
	public void createWholeInventory(HashMap<Integer, GUIInventoryItem> newInv) {
		clearInventory();
		for (int i = 0; i < newInv.size(); i++) {
			SystemLogger.finer("Inventory Item: %s creation", i);
			GUIInventoryItem item = newInv.get(i);
			addNewItem(item.getName(), item.getDescription(), item.isUsable(), item.isDroppable(), item.getQuantity());
		}
	}
	
	private Pane createNewItemBox(String itemName, String description, boolean usable, boolean droppable, int quantity) {
		StackPane spacer = new StackPane();
		HBox itemBox = new HBox();
		
		//Assign formatting to spacer and itemBox
		spacer.setMinHeight(50.0);
		spacer.setAlignment(Pos.CENTER_LEFT);
		spacer.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		itemBox.setSpacing(10.0);
		itemBox.setAlignment(Pos.CENTER_LEFT);
		
		Label itemNameLabel = new Label(" - " + itemName);
		itemNameLabel.setPrefWidth(150.0);
		itemNameLabel.setWrapText(true);
		itemNameLabel.setTooltip(new Tooltip(description));
		itemNameLabel.setStyle(InventoryBarControllerConstants.INVENTORY_ITEM_LABEL_STYLE);
		
		Label itemQuantityLabel = new Label(" - x"+quantity);
		itemQuantityLabel.setStyle(InventoryBarControllerConstants.INVENTORY_ITEM_NUMBER_LABEL_STYLE);
		
		itemBox.getChildren().addAll(itemNameLabel, itemQuantityLabel);
		spacer.getChildren().add(itemBox);
		
		return spacer;
	}
	
	public void addNewItem(String itemName, String description, boolean usable, boolean droppable, int quantity) {
		Pane newItemBox = createNewItemBox(itemName, description, usable, droppable, quantity);
		this.inventoryBox.getChildren().add(newItemBox);
	}
	
	private void clearInventory() {
		this.inventoryBox.getChildren().clear();
	}
	
	public void updateCurrency(int i) {
		this.currency.addToCurrencyQuantity(i);
	}

	public void setCurrency(int i) {
		this.currency.changeCurrencyQuantity(i);
		
	}

}
