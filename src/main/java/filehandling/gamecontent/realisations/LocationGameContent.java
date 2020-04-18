package main.java.filehandling.gamecontent.realisations;

import org.w3c.dom.Document;

import main.java.filehandling.gamecontent.AbstractGameContent;
import main.java.filehandling.gamecontent.ContentType;
import main.java.filehandling.xml.XMLUtils;
import main.java.filehandling.xml.exception.XMLParseException;
import main.java.logging.SystemLogger;

/**
 * Holder class containing the content of the Location file type
 * @author Daniel
 *
 */
public class LocationGameContent extends AbstractGameContent {

	private final String locationName;
	
	public LocationGameContent(Document document) throws XMLParseException {
		SystemLogger.finer("Creating a new LocationGameContent object");
		contentType = ContentType.LOCATION;
		locationName = XMLUtils.getFirstMatchingTagContent(document, LocationGameContentTag.LOCATION_NAME.getTag());
		
		SystemLogger.fine("A new %s was created", toString());
	}
	
	/**
	 * @return Returns the name of the location
	 */
	public String getLocationName() {
		return locationName;
	}
	
	@Override
	public String toString() {
		return String.format("LocationGameContent object with values: locationName=%s", locationName);
	}
	
	@Override
	public byte[] serialize() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * private enum representing the tags available under the LOCATION element
	 * @author Daniel
	 *
	 */
	private enum LocationGameContentTag {
		LOCATION_NAME("LocationName");
		
		private String tag;
		
		private LocationGameContentTag(String tag) {
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
