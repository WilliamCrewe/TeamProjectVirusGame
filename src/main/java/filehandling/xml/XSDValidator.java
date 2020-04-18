package main.java.filehandling.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import main.java.filehandling.xml.exception.XMLValidationException;
import main.java.logging.SystemLogger;
import main.java.properties.PropertyManager;

/**
 * Class containing the logic to validate a document against the XSD
 * @author Daniel
 *
 */
public class XSDValidator {

	private XSDValidator() {
		// private constructor to prevent instantiation
	}
	
	/**
	 * Validates the document passed in against the XSD (Throws an XMLValidationException if it fails validation)
	 * @param document
	 * @throws XMLValidationException
	 */
	public static void validateAgainstXSD(Document document) throws XMLValidationException {
		File schemaFile = new File(PropertyManager.getXSDLocation());
		
		SchemaFactory schemaFactory = SchemaFactory
			    .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

		try {

			// Get the schema file 
			Schema schema = schemaFactory.newSchema(schemaFile);
			schemaFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

			Validator validator = schema.newValidator();
			
			// Setup the validator to stop XXE
			validator.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
			validator.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
			
			// Create dom source for the document
		    DOMSource domSource=new DOMSource(document);

			validator.validate(domSource);
		} catch (SAXException | IOException e) {
			SystemLogger.severe("The validation of the xml against the XSD failed, reason was %s", e.getMessage());
			throw new XMLValidationException(String.format("The validation of the xml against the XSD failed, reason was %s", e.getMessage()), e);
		}
	}
}
