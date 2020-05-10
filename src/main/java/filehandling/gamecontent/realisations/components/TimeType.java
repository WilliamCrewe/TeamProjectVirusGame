package main.java.filehandling.gamecontent.realisations.components;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import main.java.filehandling.gamecontent.XMLSerializable;

/**
 * Class representing the TimeType item in the XSD
 * @author Daniel
 *
 */
public class TimeType implements XMLSerializable {

	private String time;
	
	private static final String SERIALIZED_FORMAT = "<Time>%s</Time>";
	
	public TimeType(Node completedEventNode) {
		NodeList childNodes = completedEventNode.getChildNodes();
		
		// loop over all the child nodes
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node currentNode = childNodes.item(i);
			
			// Find the correct Enum value for the element and set the field based on it
			TimeTypeTag itemTypeTag = TimeTypeTag.getByTag(currentNode.getNodeName());
			
			switch (itemTypeTag) {
			case TIME:
				time = currentNode.getTextContent();
				break;
			}
		}
	}
	/**
	 * Constructor for the TimeLord to create TimeType objects for storage in SaveItems
	 * @param time
	 */
	public TimeType(String time) {
		this.time = time;
	}
	
	public String getTime() {
		return time;
	}
	
	@Override
	public byte[] serialize() {
		return toXMLString().getBytes();
	}
	
	@Override
	public String toXMLString() {
		return  String.format(SERIALIZED_FORMAT, time);
	}

	/**
	 * private enum representing the tags available under the TimeType element
	 * @author Daniel
	 *
	 */
	private enum TimeTypeTag {
		TIME("ItemID");
		
		private String tag;
		
		private TimeTypeTag(String tag) {
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
		public static TimeTypeTag getByTag(String searchTag) {
			for (TimeTypeTag currentTag : TimeTypeTag.values()) {
				if (currentTag.getTag().equals(searchTag)) {
					return currentTag;
				}
			}
			
			// As the XML passed in has been verified against the XSD, this case cannot occur
			throw new IllegalArgumentException(String.format("No TimeTypeTag has the tag %s", searchTag));
		}
	}
}
