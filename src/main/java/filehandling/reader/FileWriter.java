package main.java.filehandling.reader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import main.java.filehandling.reader.exception.UnableToWriteContentException;
import main.java.logging.SystemLogger;
import main.java.properties.PropertyManager;

/**
 * Class that handles the write operations of files
 * @author Daniel
 *
 */
public class FileWriter {

	private FileWriter() {
		// Private constructor to prevent instantiation
	}
	
	/**
	 * Writes the contents passed in to a file at the filepath. Overwrites existing files with the same path
	 * @param fullFilePath
	 * @param fileContents
	 * @throws UnableToWriteContentException
	 */
	public static void writeContentsToFile(String fullFilePath, byte[] fileContents) throws UnableToWriteContentException {
		SystemLogger.finer("Writing contents to file at path %s (Creating if it does not exist)", fullFilePath);
		try {
			Files.write(new File(fullFilePath).toPath(), fileContents);
			SystemLogger.config("Finished writing contents to file at path %s", fullFilePath);
		} catch (IOException e) {
			SystemLogger.severe("An exception occured whilst writing the contents to file at path %s, reason was %s", fullFilePath, e.getMessage());
			throw new UnableToWriteContentException(String.format("An exception occured whilst writing the contents to file at path %s, reason was %s", fullFilePath, e.getMessage()), e);
		}
	}
}
