package main.graphical_interface.gameWindows.inGameWindows;

import java.util.HashMap;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
import javafx.scene.text.Font;
import main.graphical_interface.GUIController;
import main.graphical_interface.util.Command;
import main.graphical_interface.util.GUIInventoryItem;

public class InventoryBarController {
	
	private VBox returnedBox;
	private VBox inventoryBox;
	private HBox currencyBox;
	private GUICurrencyElement currency;
	private int numberOfItems;
	
	public InventoryBarController() {
		setup();
	}
	
	private void setup() {
		VBox currencyAndInv = new VBox();
		this.numberOfItems = 0;
		
		//Setup Currency Window
		VBox totalCurrencyVBox = new VBox();
		
		Label currencyTitle = new Label("Currency");
		currencyTitle.setFont(new Font(10.0));
		
		ScrollPane currencyScroll = new ScrollPane();
		this.currencyBox = setupCurrency();
		
		currencyScroll.setContent(this.currencyBox);
		totalCurrencyVBox.getChildren().addAll(currencyTitle, currencyScroll);
		
		//Setup Inventory
		VBox inventoryWrapper = new VBox();
		
		Label inventoryLabel = new Label("Inventory");
		inventoryLabel.setFont(new Font(10.0));
		
		ScrollPane inventoryScroll = new ScrollPane();
		setupInventory();
		
		inventoryScroll.setContent(this.inventoryBox);
		inventoryWrapper.getChildren().addAll(inventoryLabel, inventoryScroll);
		
		
		//Assign currency and Inventory to VBox
		currencyAndInv.getChildren().addAll(totalCurrencyVBox, inventoryWrapper);
		
		
		this.returnedBox = currencyAndInv;
	}
	

	public VBox getInventoryBox() {
		return this.returnedBox;
	}
	
	
	private HBox setupCurrency() {
		HBox currency = new HBox();
		this.currency = new GUICurrencyElement();
		HBox toiletRoll = this.currency.getElement();
		currency.getChildren().add(toiletRoll);
		return currency;
	}
	
	private void setupInventory() {
		VBox inventory = new VBox();
		inventory.setSpacing(10.0);
		
		this.inventoryBox = inventory;
		
		//addNewItem("Easter Egg", true, true);
		//addNewItem("Lucky Rabbit's Foot", true, false);
		
	}
	
	public void createWholeInventory(HashMap<Integer, GUIInventoryItem> newInv) {
		clearInventory();
		for (int i = 0; i < newInv.size(); i++) {
			System.out.println("Inventory Item: "+i+" creation");
			GUIInventoryItem item = newInv.get(i);
			addNewItem(item.getName(), item.getDescription(), item.isUsable(), item.isDroppable(), item.getQuantity());
		}
	}
	
	private Pane createNewItemBox(String itemName, String description, boolean usable, boolean droppable, int quantity) {
		StackPane spacer = new StackPane();
		HBox itemBox = new HBox();
		int currentItem = this.numberOfItems;
		
		//Assign formatting to spacer and itemBox
		spacer.setMinHeight(50.0);
		spacer.setAlignment(Pos.CENTER_LEFT);
		spacer.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		itemBox.setSpacing(10.0);
		itemBox.setAlignment(Pos.CENTER_LEFT);
		
		Label itemNameLabel = new Label(itemName+" (x"+quantity+")");
		itemNameLabel.setPrefWidth(150.0);
		itemNameLabel.setWrapText(true);
		itemNameLabel.setTooltip(new Tooltip(description));
		
		Button useItem = new Button("Use");
		useItem.setPrefWidth(50.0);
		useItem.setVisible(usable);
		useItem.setOnAction(clicked -> {GUIController.updateItem(Command.USE_ITEM, currentItem);});
	
		Button dropItem = new Button("Drop");
		dropItem.setPrefWidth(50.0);
		dropItem.setVisible(droppable);
		dropItem.setOnAction(clicked -> {GUIController.updateItem(Command.DROP_ITEM, currentItem);});
		dropItem.setDisable(true);
		dropItem.setTooltip(new Tooltip("Unavailable in current build."));
		
		itemBox.getChildren().addAll(itemNameLabel, useItem, dropItem);
		spacer.getChildren().add(itemBox);
		
		this.numberOfItems++;
		
		return spacer;
	}
	
	public void addNewItem(String itemName, String description, boolean usable, boolean droppable, int quantity) {
		Pane newItemBox = createNewItemBox(itemName, description, usable, droppable, quantity);
		this.inventoryBox.getChildren().add(newItemBox);
	}
	
	private void clearInventory() {
		this.inventoryBox.getChildren().clear();
		this.numberOfItems = 0;
	}
	
	public void updateCurrency(int i) {
		this.currency.addToCurrencyQuantity(i);
	}

	public void setCurrency(int i) {
		this.currency.changeCurrencyQuantity(i);
		
	}

}
