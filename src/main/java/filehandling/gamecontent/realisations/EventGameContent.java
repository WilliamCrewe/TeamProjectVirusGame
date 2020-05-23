package main.java.filehandling.gamecontent.realisations;

import java.util.Objects;

import org.w3c.dom.Document;

import main.java.filehandling.gamecontent.AbstractGameContent;
import main.java.filehandling.gamecontent.ContentType;
import main.java.filehandling.gamecontent.realisations.components.EventOptions;
import main.java.filehandling.xml.XMLUtils;
import main.java.filehandling.xml.exception.XMLParseException;
import main.java.logging.SystemLogger;
import main.java.random.Rarity;

/**
 * Holder class containing the content of the Event file type
 * 
 * @author Daniel
 *
 */
public class EventGameContent extends AbstractGameContent {

	private final String eventID;
	private final String eventName;
	private final String eventLocationID;
	private final boolean isPassiveEvent;
	private final boolean isSingleOccurence;
	private final EventOptions eventOptions;
	private final Rarity rarity;

	/**
	 * Constructor for when the event XML content is used
	 * 
	 * @param document
	 * @throws XMLParseException
	 */
	public EventGameContent(Document document) throws XMLParseException {
		SystemLogger.finer("Creating a new EventGameContent object");
		contentType = ContentType.EVENT;
		eventID = XMLUtils.getFirstMatchingTagContent(document, EventGameContentTag.EVENT_ID.getTag());
		eventName = XMLUtils.getFirstMatchingTagContent(document, EventGameContentTag.EVENT_NAME.getTag());
		eventLocationID = XMLUtils.getFirstMatchingTagContentOptionalTag(document,
				EventGameContentTag.EVENT_LOCATION_ID.getTag());
		isPassiveEvent = Boolean.parseBoolean(
				XMLUtils.getFirstMatchingTagContent(document, EventGameContentTag.IS_PASSIVE_EVENT.getTag()));
		isSingleOccurence = Boolean.parseBoolean(
				XMLUtils.getFirstMatchingTagContent(document, EventGameContentTag.IS_SINGLE_OCCURENCE.getTag()));
		eventOptions = new EventOptions(
				document.getElementsByTagName(EventGameContentTag.EVENT_OPTIONS.getTag()).item(0));
		rarity = Rarity
				.getByXmlValue(XMLUtils.getFirstMatchingTagContent(document, EventGameContentTag.RARITY.getTag()));

		SystemLogger.fine("A new %s was created", toString());
	}

	/**
	 * Constructor used for junit testing
	 */
	public EventGameContent(String eventID, String eventName, String eventLocationID, boolean isPassiveEvent,
			boolean isSingleOccurence, EventOptions eventOptions, Rarity rarity) {
		this.eventID = eventID;
		this.eventName = eventName;
		this.eventLocationID = eventLocationID;
		this.isPassiveEvent = isPassiveEvent;
		this.isSingleOccurence = isSingleOccurence;
		this.eventOptions = eventOptions;
		this.rarity = rarity;
	}

	/**
	 * @return the eventID
	 */
	public String getEventID() {
		return eventID;
	}

	/**
	 * @return the eventName
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * @return the eventLocationID
	 */
	public String getEventLocationID() {
		return eventLocationID;
	}

	/**
	 * @return the isPassiveEvent
	 */
	public boolean isPassiveEvent() {
		return isPassiveEvent;
	}

	/**
	 * @return the isSingleOccurence
	 */
	public boolean isSingleOccurence() {
		return isSingleOccurence;
	}

	/**
	 * @return the eventOptions
	 */
	public EventOptions getEventOptions() {
		return eventOptions;
	}

	/**
	 * @return the rarity
	 */
	public Rarity getRarity() {
		return rarity;
	}
	
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) { 
  
        // If the object is compared with itself then return true   
        if (o == this) { 
            return true; 
        } 
  
        /* Check if o is an instance of EventGameContent or not 
          "null instanceof [type]" also returns false */
        if (!(o instanceof EventGameContent)) { 
            return false; 
        } 
          
        // typecast o to EventGameContent so that we can compare data members  
        EventGameContent c = (EventGameContent) o; 
          
        // Compare the data members and return accordingly  
        return eventID.equals(c.eventID);
    } 
    
    /* 
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
    	// Only hash the eventID as this is the unique Identifier of the event
        return Objects.hash(eventID);
    }


	/**
	 * private enum representing the tags available under the EVENT element
	 * 
	 * @author Daniel
	 *
	 */
	private enum EventGameContentTag {
		EVENT_ID("EventID"), EVENT_NAME("EventName"), EVENT_LOCATION_ID("EventLocationID"), IS_PASSIVE_EVENT(
				"IsPassiveEvent"), IS_SINGLE_OCCURENCE(
						"IsSingleOccurence"), EVENT_OPTIONS("EventOptions"), RARITY("Rarity");

		private String tag;

		private EventGameContentTag(String tag) {
			this.tag = tag;
		}

		/**
		 * Returns the exact format of the tag in the XSD/XML
		 * 
		 * @return
		 */
		public String getTag() {
			return tag;
		}
	}
}
