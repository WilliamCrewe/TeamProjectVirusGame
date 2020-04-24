package test.java.filehandling.gamecontent.realisations.components;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import main.java.filehandling.gamecontent.realisations.components.ItemType;
import main.java.filehandling.xml.XMLUtils;
import main.java.filehandling.xml.exception.XMLParseException;

public class ItemTypeTest {

	/**
	 * Positive test case for when the constructor is called
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
				
		// Get the Item nodes
		NodeList itemNodeList = document.getElementsByTagName("Item");
		
		// Call the method in test (Constructor so instantiate the object)
		ItemType itemType = new ItemType(itemNodeList.item(0));
		
		// Assert the object has the expected values
		assertEquals("ItemIDOne", itemType.getItemID());
		assertEquals("ItemNameOne", itemType.getItemName());
		assertEquals(5, itemType.getItemCount());
	}
	
	/**
	 * negative test case for when the constructor is called but an unexpected tag (Not XSD valid) was found
	 * @throws XMLParseException
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorUnexpectedTag() throws XMLParseException {
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
				"                       <UNEXPECTED_TAG>text</UNEXPECTED_TAG>" +
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
		
		// Get the Item nodes
		NodeList itemNodeList = document.getElementsByTagName("Item");
		
		// Call the method in test (Constructor so instantiate the object)
		new ItemType(itemNodeList.item(0));
	}
	
	/**
	 * Positive test case for when the serialize method is called
	 * @throws XMLParseException
	 */
	@Test
	public void testSerialize() throws XMLParseException {
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
		
		// Get the Item nodes
		NodeList itemNodeList = document.getElementsByTagName("Item");
		
		// Create the object in test
		ItemType completedEvent = new ItemType(itemNodeList.item(0));
		
		// Call the method in test
		byte[] serializedObject = completedEvent.serialize();
		
		// Check the the bytes represent the string expected
		assertEquals("<Item><ItemID>ItemIDOne</ItemID><ItemName>ItemNameOne</ItemName><ItemCount>5</ItemCount></Item>", new String(serializedObject));
	}
}
