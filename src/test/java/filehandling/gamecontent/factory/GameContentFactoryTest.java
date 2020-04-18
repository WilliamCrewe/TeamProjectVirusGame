package test.java.filehandling.gamecontent.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import main.java.filehandling.gamecontent.AbstractGameContent;
import main.java.filehandling.gamecontent.ContentType;
import main.java.filehandling.gamecontent.factory.GameContentFactory;
import main.java.filehandling.gamecontent.realisations.SaveGameContent;
import main.java.filehandling.xml.exception.XMLParseException;

public class GameContentFactoryTest {

	/**
	 * Positive test case for when the contents of the XML file are successfully converted to the correct realisation of the AbstractGameContent
	 * @throws XMLParseException
	 */
	@Test
	public void testSeriliazeFileContentSave() throws XMLParseException {
		String testXML = "<Content>" + 
				"	<Save>" + 
				"		<SaveName>TestSaveName</SaveName>" + 
				"		<Seed>TestSeed</Seed>" + 
				"		<DayNumber>42</DayNumber>" + 
				"	</Save>" + 
				"</Content>";
		
		// Convert the testXML to a byte array for the method in test
		byte[] xmlBytes = testXML.getBytes();
		
		// Call the method in test
		AbstractGameContent gameContent = GameContentFactory.seriliazeFileContent(xmlBytes);
		
		// Assert the ContentType is correct
		assertEquals(ContentType.SAVE, gameContent.getContentType());
		
		// Assert the object returned is of SaveGameContent type
		if (!(gameContent instanceof SaveGameContent)) {
			fail();
		}
		
		SaveGameContent saveGameContent = (SaveGameContent) gameContent;
		
		// Assert the values of the object are set as expected
		assertEquals("TestSaveName", saveGameContent.getSaveName());
		assertEquals("TestSeed", saveGameContent.getSeed());
		assertEquals(42, saveGameContent.getDayNumber());
		assertEquals("SaveGameContent object with values: saveName=TestSaveName, seed=TestSeed, dayNumber=42", saveGameContent.toString());
		
	}
}
