package main.java.filehandling.gamecontent.realisations.components;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import main.java.filehandling.gamecontent.XMLSerializable;

/**
 * Class representing the SaveItems item in the XSD
 * 
 * @author Daniel
 *
 */
public class SaveItems implements XMLSerializable {

	private final List<ItemType> saveItemsValues = new ArrayList<>();
	private final Set<String> saveItemIDs = new HashSet<>();
	
	private static final String SERIALIZED_FORMAT = "<SaveItems>%s</SaveItems>";

	/**
	 * Constructor to be used when there was no CompletedEvents element in the XML
	 */
	public SaveItems() {
	}

	public SaveItems(Node completedEventsNode) {
		NodeList childNodes = completedEventsNode.getChildNodes();

		// Each child node will be an Item so construct a new ItemType and add it
		for (int i = 0; i < childNodes.getLength(); i++) {
			saveItemsValues.add(new ItemType(childNodes.item(i)));
		}
	}
	
	/**
	 * Adds the item to the saveItems
	 * @param itemType
	 */
	public void addToSaveItems(ItemType itemType) {
		saveItemsValues.add(itemType);
		saveItemIDs.add(itemType.getItemID());
	}
	
	/**
	 * Remove the item with the ItemID passed in from the saveItems
	 * @param itemID
	 */
	public void removeItem(String itemID) {
		saveItemIDs.remove(itemID);
		
		ItemType matchingItem = null;
		// Find any matching items in the itemList
		for (ItemType saveItem : saveItemsValues) {
			if (saveItem.getItemID().equals(itemID)) {
				matchingItem = saveItem;
				break;
			}
		}
		
		// If an item was found in the list remove it
		if (matchingItem != null) {
			saveItemsValues.remove(matchingItem);
		}
	}

	/**
	 * @return The list of all ItemTypes beneath this SaveItems tag
	 */
	public List<ItemType> getSaveItemsValues() {
		return saveItemsValues;
	}
	
	public boolean containsItemID(String itemID) {
		return saveItemIDs.contains(itemID);
	}

	@Override
	public byte[] serialize() {
		return toXMLString().getBytes();
	}

	@Override
	public String toXMLString() {

		StringBuilder saveItemsStringBuilder = new StringBuilder();
		for (ItemType saveItem : saveItemsValues) {
			saveItemsStringBuilder.append(saveItem.toXMLString());
		}

		return String.format(SERIALIZED_FORMAT, saveItemsStringBuilder.toString());
	}
}
