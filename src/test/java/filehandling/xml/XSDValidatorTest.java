package test.java.filehandling.xml;

import org.junit.Test;
import org.w3c.dom.Document;

import main.java.filehandling.xml.XMLUtils;
import main.java.filehandling.xml.XSDValidator;
import main.java.filehandling.xml.exception.XMLParseException;
import main.java.filehandling.xml.exception.XMLValidationException;
import main.java.properties.PropertyManager;

public class XSDValidatorTest {

	/**
	 * Positive test case for when a document that is valid against the XSD is passed in
	 * @throws XMLParseException
	 * @throws XMLValidationException
	 */
	@Test
	public void testValidateAgainstXSD() throws XMLParseException, XMLValidationException {
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
		Document document = XMLUtils.convertByteArrayToDocument(xmlBytes);
		
		PropertyManager.setXSDLocation("C:\\Users\\Daniel\\Documents\\Uni\\TeamProject\\GitCopy\\TeamProjectVirusGame\\Resources\\XSD/VirusGameXSD.xsd");
		
		// Call the method in test
		XSDValidator.validateAgainstXSD(document);
	}
	
	/**
	 * Negative test case for when invalid XML is verified
	 * @throws XMLParseException
	 * @throws XMLValidationException
	 */
	@Test(expected = XMLValidationException.class)
	public void testValidateAgainstXSDInvalidXML() throws XMLParseException, XMLValidationException {
		String testXML = "<Content>" + 
				"	<InvalidTagName>" + 
				"		<SaveName>TestSaveName</SaveName>" + 
				"		<Seed>TestSeed</Seed>" + 
				"		<DayNumber>42</DayNumber>" + 
				"	</InvalidTagName>" + 
				"</Content>";
		
		// Convert the testXML to a byte array for the method in test
		byte[] xmlBytes = testXML.getBytes();
		
		// Call the method in test
		Document document = XMLUtils.convertByteArrayToDocument(xmlBytes);
		
		PropertyManager.setXSDLocation("C:\\Users\\Daniel\\Documents\\Uni\\TeamProject\\GitCopy\\TeamProjectVirusGame\\Resources\\XSD/VirusGameXSD.xsd");
		
		// Call the method in test
		XSDValidator.validateAgainstXSD(document);
	}
	
	/**
	 * Negative test case for when XSD could not be found at the configured path
	 * @throws XMLParseException
	 * @throws XMLValidationException
	 */
	@Test(expected = XMLValidationException.class)
	public void testValidateAgainstXSDNoXSDFound() throws XMLValidationException, XMLParseException {
		String testXML = "<Content>" + 
				"	<InvalidTagName>" + 
				"		<SaveName>TestSaveName</SaveName>" + 
				"		<Seed>TestSeed</Seed>" + 
				"		<DayNumber>42</DayNumber>" + 
				"	</InvalidTagName>" + 
				"</Content>";
		
		// Convert the testXML to a byte array for the method in test
		byte[] xmlBytes = testXML.getBytes();
		
		// Call the method in test
		Document document = XMLUtils.convertByteArrayToDocument(xmlBytes);
		
		PropertyManager.setXSDLocation("InvalidPath");
		
		// Call the method in test
		XSDValidator.validateAgainstXSD(document);
	}
}
