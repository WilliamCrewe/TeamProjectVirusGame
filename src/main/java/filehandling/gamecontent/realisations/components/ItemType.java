package main.java.filehandling.gamecontent.realisations.components;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import main.java.filehandling.gamecontent.XMLSerializable;

public class ItemType implements XMLSerializable {

	private String itemID;
	private String itemName;
	private int itemCount;
	
	private static final String SERIALIZED_FORMAT = "<Item><ItemID>%s</ItemID><ItemName>%s</ItemName><ItemCount>%s</ItemCount></Item>";
	
	public ItemType(Node completedEventNode) {
		NodeList childNodes = completedEventNode.getChildNodes();
		
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node currentNode = childNodes.item(i);
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
			}
		}
	}
	
	public String getItemID() {
		return itemID;
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public int getItemCount() {
		return itemCount;
	}
	
	@Override
	public byte[] serialize() {
		return toXMLString().getBytes();
	}
	
	@Override
	public String toXMLString() {
		return  String.format(SERIALIZED_FORMAT, itemID, itemName, itemCount);
	}

	/**
	 * private enum representing the tags available under the ItemType element
	 * @author Daniel
	 *
	 */
	private enum ItemTypeTag {
		ITEM_ID("ItemID"),
		ITEM_NAME("ItemName"),
		ITEM_COUNT("ItemCount");
		
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
