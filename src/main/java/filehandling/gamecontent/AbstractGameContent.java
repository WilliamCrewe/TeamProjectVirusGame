package main.java.filehandling.gamecontent;

/**
 * Abstract class to be extended by all game content classes that are stored in files
 * @author Daniel
 *
 */
public abstract class AbstractGameContent {

	protected ContentType contentType;
	
	/**
	 * @return the contentType for the realisation of this AbstractGameContent class
	 */
	public ContentType getContentType() {
		return contentType;
	}
}
