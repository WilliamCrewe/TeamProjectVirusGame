package test.java.filehandling.gamecontent.realisations.components;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import main.java.filehandling.gamecontent.realisations.components.EventOption;
import main.java.filehandling.gamecontent.realisations.components.EventOptionItems;
import main.java.filehandling.xml.XMLUtils;
import main.java.filehandling.xml.exception.XMLParseException;

public class EventOptionTest {

	/**
	 * Positive test case for when the constructor is called
	 * 
	 * @throws XMLParseException
	 */
	@Test
	public void testConstructor() throws XMLParseException {
		String testXML = "<Content>" + "	<Event>" + "		<EventID>TestEventID</EventID>"
				+ "		<EventName>Test Event Name</EventName>" + "		<EventLocationID>Hospital</EventLocationID>"
				+ "		<IsSingleOccurence>false</IsSingleOccurence>" + "		<EventOptions>"
				+ "			<EventOption>" + "				<EventOptionID>TestEventIDOptionOne</EventOptionID>"
				+ "				<EventOptionDescription>Test Event Option one description</EventOptionDescription>"
				+ "				<EventOptionImmunityModification>10</EventOptionImmunityModification>"
				+ "				<EventOptionContagionLevelModifier>-10</EventOptionContagionLevelModifier>"
				+ "				<EventOptionKarmaModification>5</EventOptionKarmaModification>"
				+ "				<EventOptionItems>" + "					<Item>"
				+ "						<ItemID>ItemIDOne</ItemID>"
				+ "						<ItemName>ItemNameOne</ItemName>"
				+ "						<ItemCount>5</ItemCount>" + "					</Item>"
				+ "				</EventOptionItems>"
				+ "				<RequiredCompletedEventID>PriorEventID</RequiredCompletedEventID>"
				+ "				<RequiredItemID>TestItemID</RequiredItemID>" + "			</EventOption>"
				+ "			<EventOption>" + "				<EventOptionID>TestEventIDOptionTwo</EventOptionID>"
				+ "				<EventOptionDescription>Test Event Option two description</EventOptionDescription>"
				+ "				<EventOptionImmunityModification>20</EventOptionImmunityModification>"
				+ "				<EventOptionContagionLevelModifier>-20</EventOptionContagionLevelModifier>"
				+ "				<EventOptionKarmaModification>10</EventOptionKarmaModification>"
				+ "			</EventOption>" + "		</EventOptions>"
				+ "		<FollowingEventID>TestEventID2</FollowingEventID>" + "		<Rarity>Common</Rarity>"
				+ "	</Event>" + "</Content>";

		// Convert the testXML to a byte array for the method in test
		byte[] xmlBytes = testXML.getBytes();

		// Call the method in test
		Document document = XMLUtils.convertByteArrayToDocument(xmlBytes);

		// Get the EventOption nodes
		NodeList eventOptionNodeList = document.getElementsByTagName("EventOption");

		// Call the method in test (Constructor so instantiate the object)
		EventOption optionType = new EventOption(eventOptionNodeList.item(0));

		// Assert the object has the expected values
		assertEquals("TestEventIDOptionOne", optionType.getEventOptionID());
		assertEquals("Test Event Option one description", optionType.getEventOptionDescription());
		assertEquals(10, optionType.getEventOptionImmunityModification());
		assertEquals(-10, optionType.getEventOptionContagionLevelModifier());
		assertEquals(5, optionType.getEventOptionKarmaModification());
		
		EventOptionItems eventOptionItems = optionType.getEventOptionItems();
		assertEquals(1, eventOptionItems.getEventOptionItemsValues().size());

		assertEquals("PriorEventID", optionType.getRequiredCompletedEventID());
		assertEquals("TestItemID", optionType.getRequiredItemID());
	}

	/**
	 * negative test case for when the constructor is called but an unexpected tag
	 * (Not XSD valid) was found
	 * 
	 * @throws XMLParseException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorUnexpectedTag() throws XMLParseException {
		String testXML = "<Content>" + "	<Event>" + "		<EventID>TestEventID</EventID>"
				+ "		<EventName>Test Event Name</EventName>" + "		<EventLocationID>Hospital</EventLocationID>"
				+ "		<IsSingleOccurence>false</IsSingleOccurence>" + "		<EventOptions>"
				+ "			<EventOption>" + "				<EventOptionID>TestEventIDOptionOne</EventOptionID>"
				+ "				<EventOptionDescription>Test Event Option one description</EventOptionDescription>"
				+ "				<EventOptionImmunityModification>10</EventOptionImmunityModification>"
				+ "				<EventOptionContagionLevelModifier>-10</EventOptionContagionLevelModifier>"
				+ "				<EventOptionKarmaModification>5</EventOptionKarmaModification>"
				+ "				<EventOptionItems>" + "					<Item>"
				+ "						<ItemID>ItemIDOne</ItemID>"
				+ "						<ItemName>ItemNameOne</ItemName>"
				+ "						<ItemCount>5</ItemCount>"
 + "					</Item>" 
				+ "				</EventOptionItems>"
				+ "                       <UNEXPECTED_TAG>text</UNEXPECTED_TAG>"
				+ "				<RequiredCompletedEventID>PriorEventID</RequiredCompletedEventID>"
				+ "				<RequiredItemID>TestItemID</RequiredItemID>" + "			</EventOption>"
				+ "			<EventOption>" + "				<EventOptionID>TestEventIDOptionTwo</EventOptionID>"
				+ "				<EventOptionDescription>Test Event Option two description</EventOptionDescription>"
				+ "				<EventOptionImmunityModification>20</EventOptionImmunityModification>"
				+ "				<EventOptionContagionLevelModifier>-20</EventOptionContagionLevelModifier>"
				+ "				<EventOptionKarmaModification>10</EventOptionKarmaModification>"
				+ "			</EventOption>" + "		</EventOptions>"
				+ "		<FollowingEventID>TestEventID2</FollowingEventID>" + "		<Rarity>Common</Rarity>"
				+ "	</Event>" + "</Content>";

		// Convert the testXML to a byte array for the method in test
		byte[] xmlBytes = testXML.getBytes();

		// Call the method in test
		Document document = XMLUtils.convertByteArrayToDocument(xmlBytes);

		// Get the EventOption nodes
		NodeList eventOptionNodeList = document.getElementsByTagName("EventOption");

		// Call the method in test (Constructor so instantiate the object)
		new EventOption(eventOptionNodeList.item(0));
	}
}
