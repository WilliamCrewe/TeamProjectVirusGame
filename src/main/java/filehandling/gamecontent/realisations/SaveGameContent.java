package main.java.filehandling.gamecontent.realisations;

import org.w3c.dom.Document;

import main.java.filehandling.gamecontent.AbstractGameContent;
import main.java.filehandling.gamecontent.ContentType;
import main.java.filehandling.xml.XMLUtils;
import main.java.filehandling.xml.exception.XMLParseException;
import main.java.logging.SystemLogger;

/**
 * Holder class containing the content of the Save file type
 * @author Daniel
 *
 */
public class SaveGameContent extends AbstractGameContent {

	private final String saveName;
	private final String seed;
	private final int dayNumber;
	
	public SaveGameContent(Document document) throws XMLParseException {
		SystemLogger.finer("Creating a new SaveGameContent object");
		contentType = ContentType.SAVE;
		saveName = XMLUtils.getFirstMatchingTagContent(document, SaveGameContentTag.SAVE_NAME.getTag());
		seed = XMLUtils.getFirstMatchingTagContent(document, SaveGameContentTag.SEED.getTag());
		dayNumber = Integer.parseInt(XMLUtils.getFirstMatchingTagContent(document, SaveGameContentTag.DAY_NUMBER.getTag()));
		
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
	 * @return The number of days that have occured on this seed
	 */
	public int getDayNumber() {
		return dayNumber;
	}
	
	@Override
	public String toString() {
		return String.format("SaveGameContent object with values: saveName=%s, seed=%s, dayNumber=%s", saveName, seed, dayNumber);
	}
	
	@Override
	public byte[] serialize() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * private enum representing the tags available under the SAVE element
	 * @author Daniel
	 *
	 */
	private enum SaveGameContentTag {
		SAVE_NAME("SaveName"),
		SEED("Seed"),
		DAY_NUMBER("DayNumber");
		
		private String tag;
		
		private SaveGameContentTag(String tag) {
			this.tag = tag;
		}
		
		/**
		 * Returns the exact format of the tag in the XSD/XML
		 * @return
		 */
		public String getTag() {
			return tag;
		}
	}

	
}
