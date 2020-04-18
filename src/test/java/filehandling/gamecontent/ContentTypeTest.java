package test.java.filehandling.gamecontent;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.java.filehandling.gamecontent.ContentType;

public class ContentTypeTest {

	/**
	 * Positive test case for when the ContentTypes are gotten by their tags
	 */
	@Test
	public void testGetContentTypeByTag() {
		assertEquals(ContentType.SAVE, ContentType.getContentTypeByTag("Save"));
		assertEquals(ContentType.EVENT, ContentType.getContentTypeByTag("Event"));
		assertEquals(ContentType.LOCATION, ContentType.getContentTypeByTag("Location"));
		assertEquals(ContentType.CHARACTER, ContentType.getContentTypeByTag("Character"));
	}
	
	/**
	 * Negative test case for when an unknown tag is passed in
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetContentTypeByTagUnknownTag() {
		 ContentType.getContentTypeByTag("UnknownTag");
	}
}
