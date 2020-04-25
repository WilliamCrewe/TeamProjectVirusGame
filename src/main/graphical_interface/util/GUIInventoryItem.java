package main.graphical_interface.util;

public class GUIInventoryItem {
	
	private int quantity;
	private String name;
	private String description;
	private boolean usable;
	private boolean droppable;
	//private GameItem objectReference; //To be included once integrated
	
	public GUIInventoryItem(String name, String description, int quantity, boolean usable, boolean droppable) {
		this.quantity = quantity;
		this.name = name;
		this.description = description;
		this.usable = usable;
		this.droppable = droppable;
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
	
	

}
