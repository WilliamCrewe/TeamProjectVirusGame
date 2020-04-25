package main.java.filehandling.reader;

import java.io.File;

import org.w3c.dom.Document;

import main.java.filehandling.gamecontent.AbstractGameContent;
import main.java.filehandling.gamecontent.XMLFileWritable;
import main.java.filehandling.gamecontent.factory.GameContentFactory;
import main.java.filehandling.reader.exception.FileReadException;
import main.java.filehandling.reader.exception.UnableToWriteContentException;
import main.java.filehandling.xml.XMLUtils;
import main.java.filehandling.xml.XSDValidator;
import main.java.filehandling.xml.exception.XMLParseException;
import main.java.filehandling.xml.exception.XMLValidationException;
import main.java.logging.SystemLogger;
import main.java.properties.PropertyManager;

/**
 * Handler object to allow the reading in of files and conversion of them to AbstractGameContent objects 
 * @author Daniel
 *
 */
public class GameContentFileHandler {

	/**
	 * Returns the content of the file in the form of an AbstractGameContent Realisation
	 * @param filePath
	 * @return
	 * @throws XMLParseException
	 * @throws FileReadException
	 * @throws XMLValidationException
	 */
	public static AbstractGameContent getGameContentOfFile(String filePath) throws XMLParseException, FileReadException, XMLValidationException {
		// Read in the contents of the file
		byte[] fileContents = FileReader.readFileContents(filePath);
		
		Document document = XMLUtils.convertByteArrayToDocument(fileContents);
		
		// Validate the contents of the file against the XSD
		XSDValidator.validateAgainstXSD(document);
		
		// Convert the contents to the AbstractGameContent instantiation and return it
		return GameContentFactory.seriliazeFileContent(fileContents);
	}
	
	/**
	 * Writes the AbstractGameContent to the appropriate directory with the correct filename
	 * @param gameContent
	 * @throws UnableToWriteContentException
	 */
	public static void writeGameContentToFile(AbstractGameContent gameContent) throws UnableToWriteContentException {
		if (!(gameContent instanceof XMLFileWritable)) {
			SystemLogger.severe("The AbstractGameContent %s does not implement XMLSerializable so cannot be written to file", gameContent);
			throw new UnableToWriteContentException(String.format("The AbstractGameContent %s does not implement XMLSerializable so cannot be written to file", gameContent));
		}
		
		XMLFileWritable xmlFileWritableObject = (XMLFileWritable) gameContent;
		
		// check if a file with that name already exists (If so overwrite)
		FileWriter.writeContentsToFile(PropertyManager.getBaseDirectory() + File.separator + xmlFileWritableObject.getDirectoryName() + File.separator + xmlFileWritableObject.getFileName(), xmlFileWritableObject.serialize());
	}
}
