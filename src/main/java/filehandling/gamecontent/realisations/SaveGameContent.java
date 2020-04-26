package main.java.filehandling.gamecontent.realisations;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import main.java.filehandling.gamecontent.AbstractGameContent;
import main.java.filehandling.gamecontent.ContentType;
import main.java.filehandling.gamecontent.XMLFileWritable;
import main.java.filehandling.gamecontent.realisations.components.CompletedEvent;
import main.java.filehandling.gamecontent.realisations.components.CompletedEvents;
import main.java.filehandling.gamecontent.realisations.components.ItemType;
import main.java.filehandling.gamecontent.realisations.components.SaveItems;
import main.java.filehandling.reader.GameDirectory;
import main.java.filehandling.xml.XMLUtils;
import main.java.filehandling.xml.exception.XMLParseException;
import main.java.logging.SystemLogger;

/**
 * Holder class containing the content of the Save file type
 * 
 * @author Daniel
 *
 */
public class SaveGameContent extends AbstractGameContent implements XMLFileWritable {

	private final String saveName;
	private String seed;
	private int dayNumber;
	private int immunity;
	private int contagionLevel;
	private int karma;
	private String currentLocationID;
	private CompletedEvents completedEvents;
	private SaveItems saveItems;
	

	private static final String SERIALIZED_FORMAT = "<Content><Save><SaveName>%s</SaveName><Seed>%s</Seed><DayNumber>%s</DayNumber><Immunity>%s</Immunity><ContagionLevel>%s</ContagionLevel><Karma>%s</Karma><CurrentLocationID>%s</CurrentLocationID>%s%s</Save></Content>";

	/**
	 * Constructor to be used when a new save is created
	 * @param saveName
	 * @param seed
	 */
	public SaveGameContent(String saveName, String seed) {
		contentType = ContentType.SAVE;
		this.saveName = saveName;
		this.seed = seed;
		this.dayNumber = 0;
		this.immunity = 0;
		this.contagionLevel = 0;
		this.karma = 0;
		this.currentLocationID = "Home";
		this.completedEvents = new CompletedEvents();
		this.saveItems = new SaveItems();
	}
	
	/**
	 * Constructor to be used when the SaveGameConent is being initialised by an existing XML file
	 * @param document
	 * @throws XMLParseException
	 */
	public SaveGameContent(Document document) throws XMLParseException {
		SystemLogger.finer("Creating a new SaveGameContent object");
		contentType = ContentType.SAVE;
		saveName = XMLUtils.getFirstMatchingTagContent(document, SaveGameContentTag.SAVE_NAME.getTag());
		seed = XMLUtils.getFirstMatchingTagContent(document, SaveGameContentTag.SEED.getTag());
		dayNumber = Integer
				.parseInt(XMLUtils.getFirstMatchingTagContent(document, SaveGameContentTag.DAY_NUMBER.getTag()));
		immunity = Integer
				.parseInt(XMLUtils.getFirstMatchingTagContent(document, SaveGameContentTag.IMMUNITY.getTag()));
		contagionLevel = Integer
				.parseInt(XMLUtils.getFirstMatchingTagContent(document, SaveGameContentTag.CONTAGION_LEVEL.getTag()));
		karma = Integer
				.parseInt(XMLUtils.getFirstMatchingTagContent(document, SaveGameContentTag.KARMA.getTag()));
		
		currentLocationID = XMLUtils.getFirstMatchingTagContent(document, SaveGameContentTag.CURRENT_LOCATION_ID.getTag());
		
		NodeList completedEventsNodeList = document.getElementsByTagName(SaveGameContentTag.COMPLETED_EVENTS.getTag());
		
		// If the CompletedEvents tag was found (This isn't a brand new save) then load in the contents
		if (completedEventsNodeList.getLength() > 0) {
			completedEvents = new CompletedEvents(completedEventsNodeList.item(0));
		} else {
			completedEvents = new CompletedEvents();
		}
		
		NodeList saveItemsNodeList = document.getElementsByTagName(SaveGameContentTag.SAVE_ITEMS.getTag());
		
		// If the SaveItems tag was found (This isn't a brand new save) then load in the contents
		if (saveItemsNodeList.getLength() > 0) {
			saveItems = new SaveItems(saveItemsNodeList.item(0));
		} else {
			saveItems = new SaveItems();
		}
		
		SystemLogger.fine("A new %s was created", toString());
	}

	/**
	 * @return The unique name of the save
	 */
	public String getSaveName() {
		return saveName;
	}

	/**
	 * @return the seed of the save (Used for the pseudo-randomness)
	 */
	public String getSeed() {
		return seed;
	}

	/**
	 * @return The number of days that have occurred on this seed
	 */
	public int getDayNumber() {
		return dayNumber;
	}

	@Override
	public String toString() {
		return String.format("SaveGameContent object with values: saveName=%s, seed=%s, dayNumber=%s", saveName, seed,
				dayNumber);
	}

	@Override
	public byte[] serialize() {
		return toXMLString().getBytes();
	}

	@Override
	public String toXMLString() {
		return String.format(SERIALIZED_FORMAT, saveName, seed, dayNumber, immunity, contagionLevel, karma, currentLocationID, completedEvents.toXMLString(), saveItems.toXMLString());
	}
	
	@Override
	public String getFileName() {
		return String.format("SAVE_%s.xml", saveName);
	}

	@Override
	public String getDirectoryName() {
		return GameDirectory.SAVES_DIRECTORY.getDirctoryName();
	}

	/**
	 * private enum representing the tags available under the SAVE element
	 * @author Daniel
	 *
	 */
	private enum SaveGameContentTag {
		SAVE_NAME("SaveName"),
		SEED("Seed"),
		DAY_NUMBER("DayNumber"),
		IMMUNITY("Immunity"),
		CONTAGION_LEVEL("ContagionLevel"),
		KARMA("Karma"),
		CURRENT_LOCATION_ID("CurrentLocationID"),
		COMPLETED_EVENTS("CompletedEvents"),
		SAVE_ITEMS("SaveItems");

		private String tag;

		private SaveGameContentTag(String tag) {
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
	
	
	public void setDayNumber(int dayNumber) {
		this.dayNumber = dayNumber;
	}
	
	/**
	 * @return The current immunity of this save
	 */
	public int getImmunity() {
		return immunity;
	}
	
	public void adjustImmunity(int immunity) {
		this.immunity += immunity;
	}
	
	/**
	 * @return The current contagion level for this save
	 */	
	public int getContagionLevel() {
		return contagionLevel;
	}
	
	public void adjustContagionLevel(int contagionLevel) {
		this.contagionLevel += contagionLevel;
	}
	
	/**
	 * @return The current karma level for this save
	 */	
	public int getKarma() {
		return karma;
	}
	
	public void adjustKarma(int karma) {
		this.karma += karma;
	}
	
	/**
	 * @return The current location ID
	 */
	public String getCurrentLocationID() {
		return currentLocationID;
	}
	
	public void setCurrentLocationID(String currentLocationID) {
		this.currentLocationID = currentLocationID;
	}
	
	/**
	 * @return The completed events in this save game
	 */
	public CompletedEvents getCompletedEvents() {
		return completedEvents;
	}
	
	public void addCompletedEvent(String eventID, String eventOptionID) {
		completedEvents.addToCompletedEvent(new CompletedEvent(eventID, eventOptionID));
	}
	
	/**
	 * @return The items saved in this save game
	 */
	public SaveItems getSaveItems() {
		return saveItems;
	}
	
	public void addToSaveItems(ItemType item) {
		saveItems.addToSaveItems(item);
	}
	
}
