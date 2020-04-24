package test.java.filehandling.gamecontent.realisations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.w3c.dom.Document;

import main.java.filehandling.gamecontent.ContentType;
import main.java.filehandling.gamecontent.realisations.EventGameContent;
import main.java.filehandling.gamecontent.realisations.components.EventOption;
import main.java.filehandling.gamecontent.realisations.components.EventOptions;
import main.java.filehandling.xml.XMLUtils;
import main.java.filehandling.xml.exception.XMLParseException;
import main.java.random.Rarity;

public class EventGameContentTest {

	/**
	 * Positive test case for when the constructor is called and the XML is valid and contains all expected values
	 * @throws XMLParseException
	 */
	@Test
	public void testConstructor() throws XMLParseException {
		String testXML = "<Content>" + 
				"	<Event>" + 
				"		<EventID>TestEventID</EventID>" + 
				"		<EventName>Test Event Name</EventName>" + 
				"		<EventLocationID>Hospital</EventLocationID>" + 
				"		<IsSingleOccurence>false</IsSingleOccurence>" + 
				"		<EventOptions>" + 
				"			<EventOption>" + 
				"				<EventOptionID>TestEventIDOptionOne</EventOptionID>" + 
				"				<EventOptionDescription>Test Event Option one description</EventOptionDescription>" + 
				"				<EventOptionImmunityModification>10</EventOptionImmunityModification>" + 
				"				<EventOptionContagionLevelModifier>-10</EventOptionContagionLevelModifier>" + 
				"				<EventOptionKarmaModification>5</EventOptionKarmaModification>" + 
				"				<EventOptionItems>" + 
				"					<Item>" + 
				"						<ItemID>ItemIDOne</ItemID>" + 
				"						<ItemName>ItemNameOne</ItemName>" + 
				"						<ItemCount>5</ItemCount>" + 
				"					</Item>" + 
				"				</EventOptionItems>" + 
				"				<RequiredCompletedEventID>PriorEventID</RequiredCompletedEventID>" + 
				"				<RequiredItemID>TestItemID</RequiredItemID>" + 
				"			</EventOption>" + 
				"			<EventOption>" + 
				"				<EventOptionID>TestEventIDOptionTwo</EventOptionID>" + 
				"				<EventOptionDescription>Test Event Option two description</EventOptionDescription>" + 
				"				<EventOptionImmunityModification>20</EventOptionImmunityModification>" + 
				"				<EventOptionContagionLevelModifier>-20</EventOptionContagionLevelModifier>" + 
				"				<EventOptionKarmaModification>10</EventOptionKarmaModification>" + 
				"			</EventOption>" + 
				"		</EventOptions>" + 
				"		<FollowingEventID>TestEventID2</FollowingEventID>" + 
				"		<Rarity>Common</Rarity>" + 
				"	</Event>" + 
				"</Content>";
		
		// Convert the testXML to a byte array for the method in test
		byte[] xmlBytes = testXML.getBytes();
		
		// Call the method in test
		Document document = XMLUtils.convertByteArrayToDocument(xmlBytes);
		
		// Call the method in test (Constructor so instantiate the object)
		EventGameContent eventGameContent = new EventGameContent(document);
		
		// Assert the values on the object are as expected
		assertEquals(ContentType.EVENT, eventGameContent.getContentType());
		assertEquals("TestEventID", eventGameContent.getEventID());
		assertEquals("Test Event Name", eventGameContent.getEventName());
		assertEquals("Hospital", eventGameContent.getEventLocationID());
		assertEquals("TestEventID", eventGameContent.getEventID());
		assertFalse(eventGameContent.isSingleOccurence());
		
		EventOptions eventOptions = eventGameContent.getEventOptions();
		assertNotNull(eventOptions);
		List<EventOption> eventOptionsValues = eventOptions.getEventOptionsValues();
		assertEquals(2, eventOptionsValues.size());

		assertEquals("TestEventID2", eventGameContent.getFollowingEventID());
		assertEquals(Rarity.COMMON, eventGameContent.getRarity());
		
	}
	
	/**
	 * Negative test case for when the constructor is called but the XML is invalid for this object type
	 * @throws XMLParseException
	 */
	@Test (expected = XMLParseException.class)
	public void testConstructorIncorrectXML() throws XMLParseException {
		// This XML is for the event type not the Save
		String testXML = "<Content>" + 
				"	<Character>" + 
				"		<CharacterName>TestCharacterName</CharacterName>" + 
				"	</Character>" + 
				"</Content>";
		
		// Convert the testXML to a byte array for the method in test
		byte[] xmlBytes = testXML.getBytes();
		
		// Call the method in test
		Document document = XMLUtils.convertByteArrayToDocument(xmlBytes);
		
		// Call the method in test (Constructor so instantiate the object)
		new EventGameContent(document);
	}
}
