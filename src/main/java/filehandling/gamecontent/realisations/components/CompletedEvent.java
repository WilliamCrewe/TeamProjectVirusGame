package main.java.filehandling.gamecontent.realisations.components;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import main.java.filehandling.gamecontent.XMLSerializable;

/**
 * Class representing the CompletedEvent item in the XSD
 * @author Daniel
 *
 */
public class CompletedEvent implements XMLSerializable {

	private String eventID;
	private String eventOptionID;
	
	private static final String SERIALIZED_FORMAT = "<CompletedEvent><EventID>%s</EventID><EventOptionID>%s</EventOptionID></CompletedEvent>";
	
	public CompletedEvent(Node completedEventNode) {
		NodeList childNodes = completedEventNode.getChildNodes();
		
		// loop over all the child nodes
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node currentNode = childNodes.item(i);
			
			// Find the correct Enum value for the element and set the field based on it
			CompletedEventTag completedEventTag = CompletedEventTag.getByTag(currentNode.getNodeName());
			
			switch (completedEventTag) {
			case EVENT_ID:
				eventID = currentNode.getTextContent();
				break;
			case EVENT_OPTION_ID:
				eventOptionID = currentNode.getTextContent();
				break;
			}
		}
	}
	
	/**
	 * Constructor for the GameController to create CompletedEvent objects for storage in CompletedEvents
	 * @param EventID
	 * @param eventOptionID
	 */
	public CompletedEvent(String eventID, String eventOptionID) {
		this.eventID = eventID;
		this.eventOptionID = eventOptionID;
	}
	
	/**
	 * @return
	 */
	public String getEventID() {
		return eventID;
	}
	
	/**
	 * @return
	 */
	public String getEventOptionID() {
		return eventOptionID;
	}
	
	@Override
	public byte[] serialize() {
		return toXMLString().getBytes();
	}
	
	@Override
	public String toXMLString() {
		return  String.format(SERIALIZED_FORMAT, eventID, eventOptionID);
	}

	/**
	 * private enum representing the tags available under the CompletedEvent element
	 * @author Daniel
	 *
	 */
	private enum CompletedEventTag {
		EVENT_ID("EventID"),
		EVENT_OPTION_ID("EventOptionID");
		
		private String tag;
		
		private CompletedEventTag(String tag) {
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
		public static CompletedEventTag getByTag(String searchTag) {
			for (CompletedEventTag currentTag : CompletedEventTag.values()) {
				if (currentTag.getTag().equals(searchTag)) {
					return currentTag;
				}
			}
			
			// As the XML passed in has been verified against the XSD, this case cannot occur
			throw new IllegalArgumentException(String.format("No CompletedEventTag has the tag %s", searchTag));
		}
	}
}
