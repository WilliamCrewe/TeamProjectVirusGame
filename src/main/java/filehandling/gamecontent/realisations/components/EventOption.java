package main.java.filehandling.gamecontent.realisations.components;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import main.java.logging.SystemLogger;

/**
 * Class representing the EventOption item in the XSD
 * @author Daniel
 *
 */
public class EventOption {

	private String eventOptionID;
	private String eventOptionDescription;
	private int eventOptionImmunityModification;
	private int eventOptionContagionLevelModifier;
	private int eventOptionKarmaModification;
	private EventOptionItems eventOptionItems;
	private String requiredCompletedEventID;
	private String requiredItemID;
	
	public EventOption(Node eventNode) {
		SystemLogger.finer("Creating a new SaveGameContent object");
		NodeList childNodes = eventNode.getChildNodes();
		
		// loop over all the child nodes
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node currentNode = childNodes.item(i);
			
			// Find the correct Enum value for the element and set the field based on it
			EventOptionTag eventOptionTag = EventOptionTag.getByTag(currentNode.getNodeName());
			
			switch (eventOptionTag) {
			case EVENT_OPTION_ID:
				eventOptionID = currentNode.getTextContent();
				break;
			case EVENT_OPTION_DESCRIPTION:
				eventOptionDescription = currentNode.getTextContent();
				break;
			case EVENT_OPTION_IMMUNITY_MODIFICATION:
				eventOptionImmunityModification = Integer.parseInt(currentNode.getTextContent());
				break;
			case EVENT_OPTION_CONTAGION_LEVEL_MODIFIER:
				eventOptionContagionLevelModifier = Integer.parseInt(currentNode.getTextContent());
				break;
			case EVENT_OPTION_KARMA_MODIFICATION:
				eventOptionKarmaModification = Integer.parseInt(currentNode.getTextContent());
				break;
			case EVENT_OPTION_ITEMS:
				eventOptionItems = new EventOptionItems(currentNode);
				break;
			case REQUIRED_COMPLETED_EVENT_ID:
				requiredCompletedEventID = currentNode.getTextContent();
				break;
			case REQUIRED_ITEM_ID:
				requiredItemID = currentNode.getTextContent();
				break;
			}
		}
		SystemLogger.fine("A new %s was created", toString());
	}
	
	/**
	 * @return the eventOptionDescription
	 */
	public String getEventOptionDescription() {
		return eventOptionDescription;
	}

	/**
	 * @return the eventOptionImmunityModification
	 */
	public int getEventOptionImmunityModification() {
		return eventOptionImmunityModification;
	}

	/**
	 * @return the eventOptionContagionLevelModifier
	 */
	public int getEventOptionContagionLevelModifier() {
		return eventOptionContagionLevelModifier;
	}

	/**
	 * @return the eventOptionKarmaModification
	 */
	public int getEventOptionKarmaModification() {
		return eventOptionKarmaModification;
	}

	/**
	 * @return the eventOptionItems
	 */
	public EventOptionItems getEventOptionItems() {
		return eventOptionItems;
	}

	/**
	 * @return the requiredCompletedEventID
	 */
	public String getRequiredCompletedEventID() {
		return requiredCompletedEventID;
	}

	/**
	 * @return the requiredItemID
	 */
	public String getRequiredItemID() {
		return requiredItemID;
	}

	/**
	 * @return
	 */
	public String getEventOptionID() {
		return eventOptionID;
	}
	
	/**
	 * private enum representing the tags available under the CompletedEvent element
	 * @author Daniel
	 *
	 */
	private enum EventOptionTag {
		EVENT_OPTION_ID("EventOptionID"),
		EVENT_OPTION_DESCRIPTION("EventOptionDescription"),
		EVENT_OPTION_IMMUNITY_MODIFICATION("EventOptionImmunityModification"),
		EVENT_OPTION_CONTAGION_LEVEL_MODIFIER("EventOptionContagionLevelModifier"),
		EVENT_OPTION_KARMA_MODIFICATION("EventOptionKarmaModification"),
		EVENT_OPTION_ITEMS("EventOptionItems"),
		REQUIRED_COMPLETED_EVENT_ID("RequiredCompletedEventID"),
		REQUIRED_ITEM_ID("RequiredItemID");
		
		private String tag;
		
		private EventOptionTag(String tag) {
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
		public static EventOptionTag getByTag(String searchTag) {
			for (EventOptionTag currentTag : EventOptionTag.values()) {
				if (currentTag.getTag().equals(searchTag)) {
					return currentTag;
				}
			}
			
			// As the XML passed in has been verified against the XSD, this case cannot occur
			throw new IllegalArgumentException(String.format("No CompletedEventTag has the tag %s", searchTag));
		}
	}
}
