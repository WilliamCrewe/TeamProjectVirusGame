package main.java.filehandling.gamecontent.realisations.components;

import java.util.HashMap;
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
			addToSaveItemsMap(new ItemType(childNodes.item(i)));
		}
	}
	
	/**
	 * Adds the item to the saveItems
	 * @param itemType
	 */
	public void addToSaveItemsMap(ItemType itemType) {
		saveItemsMap.put(itemType.getItemID(), itemType);
	}
	
	public void removeItems(RequiredItemsType requiredItems) {
		for (RequiredItemType requiredItem : requiredItems.getRequiredItemsTypesValues()) {
			
			ItemType saveItem = saveItemsMap.get(requiredItem.getItemID());
			saveItem.changeItemCount(-requiredItem.getItemCount());
			
			if (saveItem.getItemCount() <= 0) {
				saveItemsMap.remove(saveItem.getItemID());
			}
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
