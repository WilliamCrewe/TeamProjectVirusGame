package main.java.filehandling.gamecontent.realisations.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	private final Map<String, ItemType> saveItemsMap = new HashMap<>();
	private final List<ItemType> saveItemsValues = new ArrayList<>();

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
			ItemType item = new ItemType(childNodes.item(i));
			addToSaveItemsMap(item);
			saveItemsValues.add(item);
		}
	}
	
	/**
	 * Adds the item to the saveItems
	 * @param itemType
	 */
	public void addToSaveItemsMap(ItemType itemType) {
		saveItemsMap.put(itemType.getItemID(), itemType);
		saveItemsValues.add(itemType);
	}
	
	public List<ItemType> getSaveItemsValues() {
		return saveItemsValues;
	}
	
	public void removeItems(RequiredItemsType requiredItems) {
		for (RequiredItemType requiredItem : requiredItems.getRequiredItemsTypesValues()) {
			
			ItemType saveItem = saveItemsMap.get(requiredItem.getItemID());
			saveItem.changeItemCount(-requiredItem.getItemCount());
			
			if (saveItem.getItemCount() <= 0) {
				saveItemsMap.remove(saveItem.getItemID());
				removeItemFromSaveItemValues(saveItem);
			}
		}
	}
	
	private void removeItemFromSaveItemValues(ItemType saveItem) {
		int indexToRemove = -1;
		boolean itemFound = false;
		for (int i = 0; i < saveItemsValues.size(); i++) {
			if (saveItemsValues.get(i) == saveItem) {
				indexToRemove = i;
				itemFound = true;
				break;
			}
		}
		
		if (itemFound) {
			saveItemsValues.remove(indexToRemove);
		}
	}
	
	public ItemType getItemByID(String itemID) {
		return saveItemsMap.get(itemID);
	}
	
	public  Map<String, ItemType> getSaveItemsMap() {
		return saveItemsMap;
	}

	@Override
	public byte[] serialize() {
		return toXMLString().getBytes();
	}

	@Override
	public String toXMLString() {

		StringBuilder saveItemsStringBuilder = new StringBuilder();
		for (ItemType saveItem : saveItemsMap.values()) {
			saveItemsStringBuilder.append(saveItem.toXMLString());
		}

		return String.format(SERIALIZED_FORMAT, saveItemsStringBuilder.toString());
	}
}
