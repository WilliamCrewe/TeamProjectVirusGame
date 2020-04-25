package main.java.filehandling.gamecontent.realisations;

import org.w3c.dom.Document;

import main.java.filehandling.gamecontent.AbstractGameContent;
import main.java.filehandling.gamecontent.ContentType;
import main.java.filehandling.xml.XMLUtils;
import main.java.filehandling.xml.exception.XMLParseException;
import main.java.logging.SystemLogger;

/**
 * Holder class containing the content of the Character file type
 * @author Daniel
 *
 */
public class CharacterGameContent extends AbstractGameContent {
	
	private final String characterID;
	private final String characterName;
	
	public CharacterGameContent(Document document) throws XMLParseException {
		SystemLogger.finer("Creating a new CharacterGameContent object");
		contentType = ContentType.CHARACTER;
		
		characterID = XMLUtils.getFirstMatchingTagContent(document, CharacterGameContentTag.CHARACTER_ID.getTag());
		characterName = XMLUtils.getFirstMatchingTagContent(document, CharacterGameContentTag.CHARACTER_NAME.getTag());
		
		SystemLogger.fine("A new %s was created", toString());
	}
	
	/**
	 * @return The Characters unique ID
	 */
	public String getCharacterID() {
		return characterID;
	}
	
	/**
	 * @return Returns the name of the character
	 */
	public String getCharacterName() {
		return characterName;
	}
	
	@Override
	public String toString() {
		return String.format("CharacterGameContent object with values: characterName=%s", characterName);
	}
	
	/**
	 * private enum representing the tags available under the LOCATION element
	 * @author Daniel
	 *
	 */
	private enum CharacterGameContentTag {
		CHARACTER_ID("CharacterID"),
		CHARACTER_NAME("CharacterName");
		
		private String tag;
		
		private CharacterGameContentTag(String tag) {
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
