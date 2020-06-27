package main.java.filehandling.gamecontent.realisations;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import main.java.filehandling.gamecontent.AbstractGameContent;
import main.java.filehandling.gamecontent.ContentType;
import main.java.filehandling.gamecontent.XMLFileWritable;
import main.java.filehandling.gamecontent.realisations.components.CompletedEvents;
import main.java.filehandling.gamecontent.realisations.components.ItemType;
import main.java.filehandling.gamecontent.realisations.components.RequiredItemType;
import main.java.filehandling.gamecontent.realisations.components.RequiredItemsType;
import main.java.filehandling.gamecontent.realisations.components.SaveItems;
import main.java.filehandling.gamecontent.realisations.components.TimeType;
import main.java.filehandling.reader.GameDirectory;
import main.java.filehandling.xml.XMLUtils;
import main.java.filehandling.xml.exception.XMLParseException;
import main.java.gamecontrol.controllers.difficulty.Difficulty;
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
	private Difficulty difficulty;
	private int dayNumber;
	private int immunity;
	private int contagionLevel;
	private int illnessLevel;
	private int karma;
	private String currentLocationID;
	private TimeType time;
	private CompletedEvents completedEvents;
	private SaveItems saveItems;
	

	private static final String SERIALIZED_FORMAT = "<Content><Save><SaveName>%s</SaveName><Seed>%s</Seed><Difficulty>%s</Difficulty><DayNumber>%s</DayNumber>%s<CurrentLocationID>%s</CurrentLocationID><Immunity>%s</Immunity><ContagionLevel>%s</ContagionLevel><IllnessLevel>%s</IllnessLevel><Karma>%s</Karma>%s%s</Save></Content>";

	/**
	 * Constructor to be used when a new save is created
	 * @param saveName
	 * @param seed
	 */
	public SaveGameContent(String saveName, String seed, Difficulty difficulty) {
		SystemLogger.config("Creating new save with name %s and seed %s", saveName, seed);
		contentType = ContentType.SAVE;
		this.saveName = saveName;
		this.seed = seed;
		this.difficulty = difficulty;
		this.dayNumber = 0;
		this.immunity = 0;
		this.contagionLevel = 0;
		this.karma = 0;
		this.currentLocationID = "Home";
		this.setTime(new TimeType("00:00"));
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
		difficulty = Difficulty.getByXmlValue( XMLUtils.getFirstMatchingTagContent(document, SaveGameContentTag.DIFFICULTY.getTag()));
		dayNumber = Integer
				.parseInt(XMLUtils.getFirstMatchingTagContent(document, SaveGameContentTag.DAY_NUMBER.getTag()));
		immunity = Integer
				.parseInt(XMLUtils.getFirstMatchingTagContent(document, SaveGameContentTag.IMMUNITY.getTag()));
		contagionLevel = Integer
				.parseInt(XMLUtils.getFirstMatchingTagContent(document, SaveGameContentTag.CONTAGION_LEVEL.getTag()));
		illnessLevel = Integer
				.parseInt(XMLUtils.getFirstMatchingTagContent(document, SaveGameContentTag.ILLNESS_LEVEL.getTag()));
		karma = Integer
				.parseInt(XMLUtils.getFirstMatchingTagContent(document, SaveGameContentTag.KARMA.getTag()));
		
		currentLocationID = XMLUtils.getFirstMatchingTagContent(document, SaveGameContentTag.CURRENT_LOCATION_ID.getTag());
		
		time = new TimeType(XMLUtils.getFirstMatchingTagContent(document, SaveGameContentTag.TIME.getTag()));
		
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
	
	public Difficulty getDifficulty() {
		return difficulty;
	}

	/**
	 * @return The number of days that have occurred on this seed
	 */
	public int getDayNumber() {
		return dayNumber;
	}

	@Override
	public String toString() {
		return String.format("SaveGameContent object with values: saveName=%s, seed=%s, dayNumber=%s, immunity=%s, contagionLevel=%s, karma=%s, currentLocationID=%s, completedEvents=%s, saveItems=%s", saveName, seed,
				dayNumber, immunity, contagionLevel, karma, currentLocationID, completedEvents.toXMLString(), saveItems.toXMLString());
	}

	@Override
	public byte[] serialize() {
		return toXMLString().getBytes();
	}

	@Override
	public String toXMLString() {
		return String.format(SERIALIZED_FORMAT, saveName, seed, difficulty.getXmlValue(), dayNumber, time.toXMLString(), currentLocationID, immunity, contagionLevel, illnessLevel, karma, completedEvents.toXMLString(), saveItems.toXMLString());
	}
	
	@Override
	public String getFileName() {
		return String.format("SAVE_%s.xml", saveName);
	}

	@Override
	public String getDirectoryName() {
		return GameDirectory.SAVES_DIRECTORY.getDirctoryName();
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
	
	public void alterImmunity(int immunityModification) {
		immunity += immunityModification;
	}
	
	/**
	 * @return The current contagion level for this save
	 */	
	public int getContagionLevel() {
		return contagionLevel;
	}
	
	public void alterContagionLevel(int contagionLevelModification) {
		contagionLevel += contagionLevelModification;
	}
	
	public int getIllnessLevel() {
		return illnessLevel;
	}
	
	public void alterIllnessLevel(int illnessModification) {
		illnessLevel += illnessModification;
	}
	
	/**
	 * @return The current karma level for this save
	 */	
	public int getKarma() {
		return karma;
	}
	
	public void alterKarma(int karmaModification) {
		karma += karmaModification;
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
	
	public TimeType getTime() {
		return time;
	}

	public void setTime(TimeType time) {
		this.time = time;
	}

	/**
	 * @return The completed events in this save game
	 */
	public CompletedEvents getCompletedEvents() {
		return completedEvents;
	}
	
	/**
	 * @return The items saved in this save game
	 */
	public SaveItems getSaveItems() {
		return saveItems;
	}
	
	public boolean containsRequiredItems(RequiredItemsType requiredItems) { 
		
		if (requiredItems == null) {
			return true;
		}
		
		for (RequiredItemType requiredItem : requiredItems.getRequiredItemsTypesValues()) {
			
			// Confirm the save contains the item
			ItemType saveItem = saveItems.getItemByID(requiredItem.getItemID());
			if (saveItem == null) {
				SystemLogger.config("The required item %s was not present in the save", requiredItem.getItemID());
				return false;
			}
			
			// Confirm there are the required number of items
			if (saveItem.getItemCount() < requiredItem.getItemCount()) {
				SystemLogger.config("There were %s items of type %s required, but the save only has %s", requiredItem.getItemCount(), saveItem.getItemCount());
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * private enum representing the tags available under the SAVE element
	 * @author Daniel
	 *
	 */
	private enum SaveGameContentTag {
		SAVE_NAME("SaveName"),
		SEED("Seed"),
		DIFFICULTY("Difficulty"),
		DAY_NUMBER("DayNumber"),
		IMMUNITY("Immunity"),
		CONTAGION_LEVEL("ContagionLevel"),
		ILLNESS_LEVEL("IllnessLevel"),
		KARMA("Karma"),
		CURRENT_LOCATION_ID("CurrentLocationID"),
		TIME("Time"),
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
}
