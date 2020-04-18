package test.java.filehandling.xml;

import static org.junit.Assert.assertEquals;


import org.junit.Test;
import org.w3c.dom.Document;

import main.java.filehandling.xml.XMLUtils;
import main.java.filehandling.xml.exception.XMLParseException;

/**
 * Test class for the XmlUtils
 * @author Daniel
 *
 */
public class XmlUtilsTest {

	/**
	 * Positive test case for when valid XML is converted to a document
	 * @throws Exception
	 */
	@Test
	public void testConvertByteArrayToDocument() throws Exception {
		String testXML = "" + 
				"<Content>" + 
				"	<Event>" + 
				"		<EventName>Test Event Name</EventName>" + 
				"		<EventLocation>School</EventLocation>" + 
				"		<EventOccurenceProbability>0.2</EventOccurenceProbability>" + 
				"		<EventOptions>" + 
				"			<EventOption>" + 
				"				<EventOptionDescription>TestOptionOne</EventOptionDescription>" + 
				"				<EventOptionImmunityModification>-10</EventOptionImmunityModification>" + 
				"				<EventOptionContagionLevelModifier>30</EventOptionContagionLevelModifier>" + 
				"				<EventOptionItems>" + 
				"					<ItemName>TestItemOne</ItemName>" + 
				"					<ItemName>TestItemTwo</ItemName>" + 
				"				</EventOptionItems>" + 
				"			</EventOption>" + 
				"		</EventOptions>" + 
				"	</Event>" + 
				"</Content>";
		
		// Convert the testXML to a byte array for the method in test
		byte[] xmlBytes = testXML.getBytes();
		
		// Call the method in test
		Document document = XMLUtils.convertByteArrayToDocument(xmlBytes);
		
		// Assert the document is for the XML passed in
		assertEquals("Content", document.getFirstChild().getLocalName());
	}
	
	/**
	 * Negative test case for when invalid XML is attempted to be converted to a document
	 * @throws Exception
	 */
	@Test(expected = XMLParseException.class)
	public void testConvertByteArrayToDocumentInvalidXML() throws Exception {
		String testXML = "" + 
				"<Content>" + 
				"	<Event>" + 
				"		<EventName>Test Event Name</EventName>" + 
				"		<EventLocation>School</EventLocation>" + 
				"		<EventOccurenceProbability>0.2</EventOccurenceProbability>" + 
				"		<EventOptions>" + 
				"			<EventOption>" + 
				"				<EventOptionDescription>TestOptionOne</EventOptionDescription>" + 
				"				<EventOptionImmunityModification>-10</EventOptionImmunityModification>" + 
				"				<EventOptionContagionLevelModifier>30</EventOptionContagionLevelModifier>" + 
				"				<EventOptionItems>" + 
				"					<ItemName>TestItemOne</ItemName>" + 
				"					<ItemName>TestItemTwo</ItemName>" + 
				"				</EventOptionItems>" + 
				"			</EventOption>" + 
				"		</EventOptions>" + 
				"	</Event>" + 
				"</UNEXPECTEDTAG>";
		
		// Convert the testXML to a byte array for the method in test
		byte[] xmlBytes = testXML.getBytes();
		
		// Call the method in test
		XMLUtils.convertByteArrayToDocument(xmlBytes);
		
	}
	
	/**
	 * Positive test case for when the tag content in the xml is successfully fetched
	 * @throws XMLParseException
	 */
	@Test
	public void testGetFirstMatchingTagContent() throws XMLParseException {
		String testXML = "" + 
				"<Content>" + 
				"	<Event>" + 
				"		<EventName>Test Event Name</EventName>" + 
				"		<EventLocation>School</EventLocation>" + 
				"		<EventOccurenceProbability>0.2</EventOccurenceProbability>" + 
				"		<EventOptions>" + 
				"			<EventOption>" + 
				"				<EventOptionDescription>TestOptionOne</EventOptionDescription>" + 
				"				<EventOptionImmunityModification>-10</EventOptionImmunityModification>" + 
				"				<EventOptionContagionLevelModifier>30</EventOptionContagionLevelModifier>" + 
				"				<EventOptionItems>" + 
				"					<ItemName>TestItemOne</ItemName>" + 
				"					<ItemName>TestItemTwo</ItemName>" + 
				"				</EventOptionItems>" + 
				"			</EventOption>" + 
				"		</EventOptions>" + 
				"	</Event>" + 
				"</Content>";
		
		// Convert the testXML to a byte array for the method in test
		byte[] xmlBytes = testXML.getBytes();
		
		// Not the best call to use the method in the class in test but will save a lot of time, 
		// if this test is failing ensure the test methods for the method on it's own are fine
		Document document = XMLUtils.convertByteArrayToDocument(xmlBytes);
		
		// Call the method in test
		String result = XMLUtils.getFirstMatchingTagContent(document, "EventLocation");
		
		// Assert the tag content was as expected
		assertEquals("School", result);
	}
	
	/**
	 * Negative test case for when the tag content in the xml is not found
	 * @throws XMLParseException
	 */
	@Test (expected = XMLParseException.class)
	public void testGetFirstMatchingTagContentNoMatch() throws XMLParseException {
		String testXML = "" + 
				"<Content>" + 
				"	<Event>" + 
				"		<EventName>Test Event Name</EventName>" + 
				"		<EventLocation>School</EventLocation>" + 
				"		<EventOccurenceProbability>0.2</EventOccurenceProbability>" + 
				"		<EventOptions>" + 
				"			<EventOption>" + 
				"				<EventOptionDescription>TestOptionOne</EventOptionDescription>" + 
				"				<EventOptionImmunityModification>-10</EventOptionImmunityModification>" + 
				"				<EventOptionContagionLevelModifier>30</EventOptionContagionLevelModifier>" + 
				"				<EventOptionItems>" + 
				"					<ItemName>TestItemOne</ItemName>" + 
				"					<ItemName>TestItemTwo</ItemName>" + 
				"				</EventOptionItems>" + 
				"			</EventOption>" + 
				"		</EventOptions>" + 
				"	</Event>" + 
				"</Content>";
		
		// Convert the testXML to a byte array for the method in test
		byte[] xmlBytes = testXML.getBytes();
		
		// Not the best call to use the method in the class in test but will save a lot of time, 
		// if this test is failing ensure the test methods for the method on it's own are fine
		Document document = XMLUtils.convertByteArrayToDocument(xmlBytes);
		
		// Call the method in test
		XMLUtils.getFirstMatchingTagContent(document, "NoMatchingTag");
	}
	
	/**
	 * Positive test case for when the first child xml tag name is found
	 * @throws XMLParseException
	 */
	@Test
	public void testGetFirstChildTagName() throws XMLParseException {
		String testXML = "" + 
				"<Content>" + 
				"	<Event>" + 
				"		<EventName>Test Event Name</EventName>" + 
				"		<EventLocation>School</EventLocation>" + 
				"		<EventOccurenceProbability>0.2</EventOccurenceProbability>" + 
				"		<EventOptions>" + 
				"			<EventOption>" + 
				"				<EventOptionDescription>TestOptionOne</EventOptionDescription>" + 
				"				<EventOptionImmunityModification>-10</EventOptionImmunityModification>" + 
				"				<EventOptionContagionLevelModifier>30</EventOptionContagionLevelModifier>" + 
				"				<EventOptionItems>" + 
				"					<ItemName>TestItemOne</ItemName>" + 
				"					<ItemName>TestItemTwo</ItemName>" + 
				"				</EventOptionItems>" + 
				"			</EventOption>" + 
				"		</EventOptions>" + 
				"	</Event>" + 
				"</Content>";
		
		// Convert the testXML to a byte array for the method in test
		byte[] xmlBytes = testXML.getBytes();
		
		// Not the best call to use the method in the class in test but will save a lot of time, 
		// if this test is failing ensure the test methods for the method on it's own are fine
		Document document = XMLUtils.convertByteArrayToDocument(xmlBytes);
		
		// Call the method in test
		String result = XMLUtils.getFirstChildTagName(document, "Content");
		
		// Assert the tag content was as expected
		assertEquals("Event", result);
	}
	
	/**
	 * Negative test case for when the parent tag in the xml is not found
	 * @throws XMLParseException
	 */
	@Test (expected = XMLParseException.class)
	public void testGetFirstChildTagNameNoMatchingParentNode() throws XMLParseException {
		String testXML = "" + 
				"<Content>" + 
				"	<Event>" + 
				"		<EventName>Test Event Name</EventName>" + 
				"		<EventLocation>School</EventLocation>" + 
				"		<EventOccurenceProbability>0.2</EventOccurenceProbability>" + 
				"		<EventOptions>" + 
				"			<EventOption>" + 
				"				<EventOptionDescription>TestOptionOne</EventOptionDescription>" + 
				"				<EventOptionImmunityModification>-10</EventOptionImmunityModification>" + 
				"				<EventOptionContagionLevelModifier>30</EventOptionContagionLevelModifier>" + 
				"				<EventOptionItems>" + 
				"					<ItemName>TestItemOne</ItemName>" + 
				"					<ItemName>TestItemTwo</ItemName>" + 
				"				</EventOptionItems>" + 
				"			</EventOption>" + 
				"		</EventOptions>" + 
				"	</Event>" + 
				"</Content>";
		
		// Convert the testXML to a byte array for the method in test
		byte[] xmlBytes = testXML.getBytes();
		
		// Not the best call to use the method in the class in test but will save a lot of time, 
		// if this test is failing ensure the test methods for the method on it's own are fine
		Document document = XMLUtils.convertByteArrayToDocument(xmlBytes);
		
		// Call the method in test
		XMLUtils.getFirstChildTagName(document, "NO_MATCH");
	}
	
	/**
	 * Negative test case for when the parent tag has no child elements
	 * @throws XMLParseException
	 */
	@Test (expected = XMLParseException.class)
	public void testGetFirstChildTagNameParentHasNoChildren() throws XMLParseException {
		String testXML = "" + 
				"<Content>" + 
				"	<Event>" + 
				"		<EventName>Test Event Name</EventName>" + 
				"		<EventLocation>School</EventLocation>" + 
				"		<EventOccurenceProbability>0.2</EventOccurenceProbability>" + 
				"		<EventOptions>" + 
				"			<EventOption>" + 
				"				<EventOptionDescription>TestOptionOne</EventOptionDescription>" + 
				"				<EventOptionImmunityModification>-10</EventOptionImmunityModification>" + 
				"				<EventOptionContagionLevelModifier>30</EventOptionContagionLevelModifier>" + 
				"				<EventOptionItems>" + 
				"					<ItemName>TestItemOne</ItemName>" + 
				"					<ItemName>TestItemTwo</ItemName>" + 
				"				</EventOptionItems>" + 
				"			</EventOption>" + 
				"		</EventOptions>" + 
				"	</Event>" + 
				"</Content>";
		
		// Convert the testXML to a byte array for the method in test
		byte[] xmlBytes = testXML.getBytes();
		
		// Not the best call to use the method in the class in test but will save a lot of time, 
		// if this test is failing ensure the test methods for the method on it's own are fine
		Document document = XMLUtils.convertByteArrayToDocument(xmlBytes);
		
		// Call the method in test
		XMLUtils.getFirstChildTagName(document, "ItemName");
	}
}
