package main.java.filehandling.gamecontent.factory;

import org.w3c.dom.Document;

import main.java.filehandling.gamecontent.AbstractGameContent;
import main.java.filehandling.gamecontent.ContentType;
import main.java.filehandling.gamecontent.realisations.CharacterGameContent;
import main.java.filehandling.gamecontent.realisations.EventGameContent;
import main.java.filehandling.gamecontent.realisations.LocationGameContent;
import main.java.filehandling.gamecontent.realisations.SaveGameContent;
import main.java.filehandling.xml.XMLFileConstants;
import main.java.filehandling.xml.XMLUtils;
import main.java.filehandling.xml.exception.XMLParseException;
import main.java.logging.SystemLogger;

/**
 * Factory class that will return the correct implementation of the AbstractGameContent for the file contents passed in
 * @author Daniel
 *
 */
public class GameContentFactory {

	private GameContentFactory() {
		// Private constructor to prevent instantiation
	}
	
	/**
	 * Serialize the byte fileContent into an object representing the content type of the XML
	 * @param fileContent
	 * @return
	 * @throws XMLParseException
	 */
	public static AbstractGameContent seriliazeFileContent(byte[] fileContent) throws XMLParseException {
		// Convert the XML string to a document
		Document document = XMLUtils.convertByteArrayToDocument(fileContent);
		
		// Work out the kind of ContentType it is based on the first child tag of 'Content'
		String contentTag = XMLUtils.getFirstChildTagName(document, XMLFileConstants.CONTENT_TAG);
		ContentType contentType = ContentType.getContentTypeByTag(contentTag);
		
		SystemLogger.finer("Found ContentType of %s for tag %s in xml", contentType, contentTag);
		// Instantiate and return the correct realisation of the AbstractGameContent for the ContentType
		switch(contentType) {
			case SAVE:
				return new SaveGameContent(document);
			case CHARACTER:
				return new CharacterGameContent(document);
			case LOCATION:
				return new LocationGameContent(document);
			case EVENT:
				return new EventGameContent(document);
		}
		
		return null;
	}
	

}
