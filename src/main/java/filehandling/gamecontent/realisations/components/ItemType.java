package main.java.filehandling.gamecontent.realisations.components;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import main.java.filehandling.gamecontent.XMLSerializable;

/**
 * Class representing the ItemType item in the XSD
 * @author Daniel
 *
 */
public class ItemType implements XMLSerializable {

	private String itemID;
	private String itemName;
	private int itemCount;
	private String itemUsageEventID;
	
	private static final String SERIALIZED_FORMAT = "<Item><ItemID>%s</ItemID><ItemName>%s</ItemName><ItemCount>%s</ItemCount><ItemUsageEventID>%s</ItemUsageEventID></Item>";
	
	public ItemType(Node completedEventNode) {
		NodeList childNodes = completedEventNode.getChildNodes();
		
		// loop over all the child nodes
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node currentNode = childNodes.item(i);
			
			// Find the correct Enum value for the element and set the field based on it
			ItemTypeTag itemTypeTag = ItemTypeTag.getByTag(currentNode.getNodeName());
			
			switch (itemTypeTag) {
			case ITEM_ID:
				itemID = currentNode.getTextContent();
				break;
			case ITEM_NAME:
				itemName = currentNode.getTextContent();
				break;
			case ITEM_COUNT:
				itemCount = Integer.parseInt(currentNode.getTextContent());
				break;
			case ITEM_USAGE_EVENT_ID:
				itemUsageEventID = currentNode.getTextContent();
				break;
			}
		}
	}
	/**
	 * Constructor for the GameController to create ItemType objects for storage in SaveItems
	 * @param itemID
	 * @param itemName
	 * @param itemCount
	 * @param itemUsageEventID
	 */
	public ItemType(String itemID, String itemName, int itemCount, String itemUsageEventID) {
		this.itemID = itemID;
		this.itemName = itemName;
		this.itemCount = itemCount;
		this.itemUsageEventID = itemUsageEventID;
	}
	
	/**
	 * @return the itemID
	 */
	public String getItemID() {
		return itemID;
	}
	
	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}
	
	/**
	 * @return the itemCount
	 */
	public int getItemCount() {
		return itemCount;
	}
	
	/**
	 * @return the itemUsageEventID
	 */
	public String getItemUsageEventID() {
		return itemUsageEventID;
	}
	
	@Override
	public byte[] serialize() {
		return toXMLString().getBytes();
	}
	
	@Override
	public String toXMLString() {
		return  String.format(SERIALIZED_FORMAT, itemID, itemName, itemCount, itemUsageEventID);
	}

	/**
	 * private enum representing the tags available under the ItemType element
	 * @author Daniel
	 *
	 */
	private enum ItemTypeTag {
		ITEM_ID("ItemID"),
		ITEM_NAME("ItemName"),
		ITEM_COUNT("ItemCount"),
		ITEM_USAGE_EVENT_ID("ItemUsageEventID");
		
		private String tag;
		
		private ItemTypeTag(String tag) {
			this.tag = tag;
		}
		
		/**
		 * Returns the exact format of the tag in the XSD/XML
		 * @return
		 */
		public String getTag() {
			return tag;
		}
		
		/**
		 * Returns the enum value matching the tag passed in
		 * @param searchTag
		 * @return
		 */
		public static ItemTypeTag getByTag(String searchTag) {
			for (ItemTypeTag currentTag : ItemTypeTag.values()) {
				if (currentTag.getTag().equals(searchTag)) {
					return currentTag;
				}
			}
			
			// As the XML passed in has been verified against the XSD, this case cannot occur
			throw new IllegalArgumentException(String.format("No ItemTypeTag has the tag %s", searchTag));
		}
	}

}
