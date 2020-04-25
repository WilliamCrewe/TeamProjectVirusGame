package test.java.filehandling.gamecontent.realisations.components;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.nio.file.Files;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import main.java.filehandling.gamecontent.realisations.components.SaveItems;
import main.java.filehandling.xml.XMLUtils;
import main.java.filehandling.xml.exception.XMLParseException;

public class SaveItemsTest {

	/**
	 * Positive test case for when the constructor is called and the XML is valid
	 * and contains all expected values
	 * 
	 * @throws XMLParseException
	 */
	@Test
	public void testConstructor() throws Exception {
		// This XML is for the save event type
		String fullFilePath = System.getProperty("user.dir") + "\\Resources\\TestFiles\\Saves\\SAVE_TestEventName.xml";

		// Create the XML document
		Document document = XMLUtils.convertByteArrayToDocument(Files.readAllBytes(new File(fullFilePath).toPath()));

		// Get the CompletedEvent nodes
		NodeList completedEventNodeList = document.getElementsByTagName("SaveItems");
	
		// Call the method in test (Constructor so instantiate the object)
		SaveItems saveItems = new  SaveItems(completedEventNodeList.item(0));

		// Assert the values on the object are as expected
		assertEquals(1, saveItems.getSaveItemsValues().size());
		assertEquals("ItemID1", saveItems.getSaveItemsValues().get(0).getItemID());
	}
	
	/**
	 * Positive test case for when the serialize method is called
	 * 
	 * @throws XMLParseException
	 */
	@Test
	public void testSerialize() throws Exception {
		// This XML is for the save event type
		String fullFilePath = System.getProperty("user.dir") + "\\Resources\\TestFiles\\Saves\\SAVE_TestEventName.xml";

		// Create the XML document
		Document document = XMLUtils.convertByteArrayToDocument(Files.readAllBytes(new File(fullFilePath).toPath()));

		// Get the CompletedEvent nodes
		NodeList completedEventNodeList = document.getElementsByTagName("SaveItems");
	
		// Call the method in test (Constructor so instantiate the object)
		SaveItems saveItems = new  SaveItems(completedEventNodeList.item(0));

		assertEquals("<SaveItems><Item><ItemID>ItemID1</ItemID><ItemName>ItemNameOne</ItemName><ItemCount>2</ItemCount><ItemUsageEventID>UseItem1EventID</ItemUsageEventID></Item></SaveItems>", new String(saveItems.serialize()));
	}
}
