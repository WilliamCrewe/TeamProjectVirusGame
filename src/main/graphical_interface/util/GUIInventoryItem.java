package main.graphical_interface.util;

public class GUIInventoryItem {
	
	private int quantity;
	private String name;
	private String description;
	private boolean usable;
	private boolean droppable;
	private String itemID;
	
	public GUIInventoryItem(String name, String description, int quantity, boolean usable, boolean droppable) {
		this.quantity = quantity;
		this.name = name;
		this.description = description;
		this.usable = usable;
		this.droppable = droppable;
	}

	public GUIInventoryItem() {
		this.quantity = -1;
		this.name = null;
		this.description = null;
		this.usable = false;
		this.droppable = false;
		this.itemID = null;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public void updateQuantity(int amountChange) {
		this.quantity += amountChange;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public boolean isUsable() {
		return usable;
	}

	public boolean isDroppable() {
		return droppable;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setUsable(boolean usable) {
		this.usable = usable;
	}

	public void setDroppable(boolean droppable) {
		this.droppable = droppable;
	}

	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	
	

}
