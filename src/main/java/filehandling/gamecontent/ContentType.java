package main.java.filehandling.gamecontent;

/**
 * Enumeration representing the various types of game content in the system,
 * will align one to one with the file types
 * 
 * @author Daniel
 *
 */
public enum ContentType {
	SAVE("Save", "SAVE_%s"),
	EVENT("Event", "EVENT_%s"),
	LOCATION("Location", "LOCATION_%s"),
	CHARACTER("Character", "CHARACTER_%s");
	
	private String tag;
	private String fileNamePrefix;
	
	private ContentType(String tag, String fileNamePrefix) {
		this.tag = tag;
		this.fileNamePrefix = fileNamePrefix;
	}
	
	/**
	 * @return The XML tag associated with this content type
	 */
	public String getTag() {
		return tag;
	}
	
	/**
	 * @return the prefix of the files associated with this content type
	 */
	public String getFileNamePrefix() {
		return fileNamePrefix;
	}
	
	/**
	 * Returns the content type associated with the filename (Based on the prefix of the file)
	 * @param fileName
	 * @return
	 */
	public static ContentType getContentTypeByTag(String tag) {
		for (ContentType contentType : ContentType.values()) {
			if (tag.equals(contentType.getTag())) {
				return contentType;
			}
		}
		
		throw new IllegalArgumentException(String.format("No ContentType has the tag %s", tag));
	}
}
