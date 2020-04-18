package main.java.filehandling.reader;

import org.w3c.dom.Document;

import main.java.filehandling.gamecontent.AbstractGameContent;
import main.java.filehandling.gamecontent.factory.GameContentFactory;
import main.java.filehandling.reader.exception.FileReadException;
import main.java.filehandling.xml.XMLUtils;
import main.java.filehandling.xml.XSDValidator;
import main.java.filehandling.xml.exception.XMLParseException;
import main.java.filehandling.xml.exception.XMLValidationException;

/**
 * Handler object to allow the reading in of files and conversion of them to AbstractGameContent objects 
 * @author Daniel
 *
 */
public class GameContentFileHandler {

	public AbstractGameContent getGameContentOfFile(String filePath) throws XMLParseException, FileReadException, XMLValidationException {
		// Read in the contents of the file
		byte[] fileContents = FileReader.readFileContents(filePath);
		
		Document document = XMLUtils.convertByteArrayToDocument(fileContents);
		
		// Validate the contents of the file against the XSD
		XSDValidator.validateAgainstXSD(document);
		
		// Convert the contents to the AbstractGameContent instantiation and return it
		return GameContentFactory.seriliazeFileContent(fileContents);
	}
	
	public void writeGameContentToFile() {
		// Calculate the fileName
		
		// check if a file with that name already exists (If so overwrite)
	}
}
