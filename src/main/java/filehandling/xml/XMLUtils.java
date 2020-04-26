package main.java.filehandling.xml;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import main.java.filehandling.xml.exception.XMLParseException;
import main.java.logging.SystemLogger;

/**
 * Utility class holding functions related to the xml handling
 * @author Daniel
 *
 */
public class XMLUtils {

	private static final String NO_MATCHING_TAG = "No tags matching %s were found in the xml";
	
	private XMLUtils() {
		// Private constructor to prevent instantiation
	}
	
	/**
	 * Converts the byte array XML contents into a Document for traversal and validation
	 * @param xmlBytes
	 * @return
	 * @throws XMLParseException
	 */
	public static Document convertByteArrayToDocument(byte[] xmlBytes) throws XMLParseException {
		SystemLogger.finer("Converting XML bytes to a document");
		try {
			// Create the DocumentBuilderFactory and setup it's properties
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
		    factory.setNamespaceAware(true);
		    factory.setIgnoringElementContentWhitespace(true);
		    
		    // Create the DocumentBuilder from the factory (Will have all the properties setup prior)
		    DocumentBuilder builder = factory.newDocumentBuilder();
		    
		    // Trim any whitespace between the tags
		    String stringXml = new String(xmlBytes);
		    stringXml = stringXml.replaceAll(">\\s+<", "><").trim();
		    
		     // Stream the bytes into the builder
		    return builder.parse(new ByteArrayInputStream(stringXml.getBytes()));
		} catch (IOException | ParserConfigurationException | SAXException e) {
			SystemLogger.severe("Could not convert the XML to a document, reason was %s", e.getMessage());
			throw new XMLParseException(String.format("Could not convert the XML to a document, reason was %s", e.getMessage()), e);
		}
	}
	
	/**
	 * Returns the string text content for the first tag matching the tagName passed in
	 * @param document
	 * @param tagName
	 * @return
	 * @throws XMLParseException
	 */
	public static String getFirstMatchingTagContent(Document document, String tagName) throws XMLParseException {
		// Get all the nodes matching the tagName
		NodeList nodeList = document.getElementsByTagName(tagName);
		
		// If no tags were found matching then throw an exception
		if (nodeList.getLength() == 0) {
			SystemLogger.severe(NO_MATCHING_TAG, tagName);
			throw new XMLParseException(String.format(NO_MATCHING_TAG, tagName));
		}
		
		// We want the first one found (In a lot of cases there will only be one)
		return nodeList.item(0).getTextContent();
	}
	
	/**
	 * Returns the string text content for the first tag matching the tagName passed in (Handles optional tags by returning null)
	 * @param document
	 * @param tagName
	 * @return
	 * @throws XMLParseException
	 */
	public static String getFirstMatchingTagContentOptionalTag(Document document, String tagName) throws XMLParseException {
		// Get all the nodes matching the tagName
		NodeList nodeList = document.getElementsByTagName(tagName);
		
		// If no tags were found matching then return null
		if (nodeList.getLength() == 0) {
			return null;
		}
		
		// We want the first one found (In a lot of cases there will only be one)
		return nodeList.item(0).getTextContent();
	}
	
	/**
	 * Returns the name of the first child of the XML tag matching that passed in
	 * @param document
	 * @param parentTagName
	 * @return
	 * @throws XMLParseException
	 */
	public static String getFirstChildTagName(Document document, String parentTagName) throws XMLParseException {
		// Get all the nodes matching the tagName
		NodeList nodeList = document.getElementsByTagName(parentTagName);
		
		// If no tags were found matching then throw an exception
		if (nodeList.getLength() == 0) {
			SystemLogger.severe(NO_MATCHING_TAG, parentTagName);
			throw new XMLParseException(String.format(NO_MATCHING_TAG, parentTagName));
		}
		
		// Get the child nodes of the parent
		Node childNode = nodeList.item(0).getFirstChild();

		// If the parent had no children throw an exception
		if (childNode == null || childNode.getNodeType() == Node.TEXT_NODE) {
			SystemLogger.severe("The tag %s had no child nodes", parentTagName);
			throw new XMLParseException(String.format("The tag %s had no child nodes", parentTagName));
		}
		
		// We want the first one found (In a lot of cases there will only be one)
		return childNode.getNodeName();
	}
	
	
	
}
