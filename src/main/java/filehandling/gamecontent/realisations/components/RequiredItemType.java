package main.java.filehandling.gamecontent.realisations.components;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Class representing the RequiredItemType item in the XSD
 * @author Daniel
 *
 */
public class RequiredItemType {

	private String itemID;
	private int itemCount;
	
	public RequiredItemType(Node completedEventNode) {
		NodeList childNodes = completedEventNode.getChildNodes();
		
		// loop over all the child nodes
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node currentNode = childNodes.item(i);
			
			// Find the correct Enum value for the element and set the field based on it
			RequiredItemTypeTag requiredItemTypeTag = RequiredItemTypeTag.getByTag(currentNode.getNodeName());
			
			switch (requiredItemTypeTag) {
			case ITEM_ID:
				itemID = currentNode.getTextContent();
				break;
			case ITEM_COUNT:
				itemCount = Integer.parseInt(currentNode.getTextContent());
				break;
			}
		}
	}
	
	/**
	 * Constructor to be used for junit testing
	 * @param itemID
	 * @param itemCount
	 */
	public RequiredItemType(String itemID, int itemCount) {
		this.itemID = itemID;
		this.itemCount = itemCount;
	}
	
	/**
	 * @return the itemID
	 */
	public String getItemID() {
		return itemID;
	}
	/**
	 * @return the itemCount
	 */
	public int getItemCount() {
		return itemCount;
	}

	/**
	 * private enum representing the tags available under the RequiredItemTypeTag element
	 * @author Daniel
	 *
	 */
	private enum RequiredItemTypeTag {
		ITEM_ID("ItemID"),
		ITEM_COUNT("ItemCount");
		
		private String tag;
		
		private RequiredItemTypeTag(String tag) {
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
		public static RequiredItemTypeTag getByTag(String searchTag) {
			for (RequiredItemTypeTag currentTag : RequiredItemTypeTag.values()) {
				if (currentTag.getTag().equals(searchTag)) {
					return currentTag;
				}
			}
			
			// As the XML passed in has been verified against the XSD, this case cannot occur
			throw new IllegalArgumentException(String.format("No RequiredItemTypeTag has the tag %s", searchTag));
		}
	}
}
