package test.java.filehandling.gamecontent.realisations.components;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import main.java.filehandling.gamecontent.realisations.components.EventOptionItems;
import main.java.filehandling.gamecontent.realisations.components.ItemType;
import main.java.filehandling.xml.XMLUtils;
import main.java.filehandling.xml.exception.XMLParseException;

public class EventOptionItemsTest {


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

		// Get the EventOptionItems nodes
		NodeList eventOptionItemsNodeList = document.getElementsByTagName("EventOptionItems");

		// Call the method in test (Constructor so instantiate the object)
		EventOptionItems eventOptionItems = new EventOptionItems(eventOptionItemsNodeList.item(0));

		// Assert the object has the expected values
		List<ItemType> eventOptionItemsValues = eventOptionItems.getEventOptionItemsValues();
		assertEquals(1, eventOptionItemsValues.size());


		
		// Assert the object has the expected values
		assertEquals("ItemIDOne", eventOptionItemsValues.get(0).getItemID());
		assertEquals("ItemNameOne", eventOptionItemsValues.get(0).getItemName());
		assertEquals(5, eventOptionItemsValues.get(0).getItemCount());
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
				+ "                       <UNEXPECTED_TAG>text</UNEXPECTED_TAG>"
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
		NodeList eventOptionNodeList = document.getElementsByTagName("EventOptionItems");

		// Call the method in test (Constructor so instantiate the object)
		new EventOptionItems(eventOptionNodeList.item(0));
	}
}
