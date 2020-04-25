package main.java.filehandling.reader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import main.java.filehandling.reader.exception.FileNotFoundException;
import main.java.filehandling.reader.exception.FileReadException;
import main.java.logging.SystemLogger;

/**
 * Class that handles the read operations of files
 * @author Daniel
 *
 */
public class FileReader {

	private static final String FILE_NOT_FOUND = "File %s was not found at location %s";
	private static final String ERROR_READING_FILE = "An error occured whilst reading file %s, reason was %s";
	
	private FileReader() {
		// Private constructor to prevent instantiation
	}
	
	/**
	 * Method that returns an array of all the file names in a directory
	 * @param fullDirectoryPath
	 * @return
	 */
	public static String[] getFilesInDirectory(String fullDirectoryPath) {
		SystemLogger.fine("Listing all files under directory %s", fullDirectoryPath);
		File directoryFile = new File(fullDirectoryPath);
		String [] subFiles = directoryFile.list();
		
		SystemLogger.finer("The files %s were found under directory %s", subFiles, fullDirectoryPath);
		return subFiles;
	}
	
	/**
	 * Method to read in the byte content of a file
	 * @param subDirectory
	 * @param fileName
	 * @return
	 * @throws FileReadException
	 */
	public static byte[] readFileContents(String fullFilePath) throws FileReadException {
			SystemLogger.fine("Reading the contents of file %s", fullFilePath);

			// Make sure the file we are trying to use exists and can be read
			if (!verifyFileExists(fullFilePath)) {
				String errorMessage = String.format(FILE_NOT_FOUND, fullFilePath, fullFilePath);
				SystemLogger.severe(errorMessage);
				throw new FileNotFoundException(errorMessage);
			}
			
			// Read in the contents of the file
			byte[] fileContents;
			try {
				fileContents = Files.readAllBytes(new File(fullFilePath).toPath());
			} catch (IOException e) {
				String errorMessage = String.format(ERROR_READING_FILE, fullFilePath, e.getMessage());
				SystemLogger.severe(errorMessage);
				throw new FileReadException(errorMessage, e);
			}
			
			SystemLogger.config("Read out %s bytes from file %s", fileContents.length, fullFilePath);
			return fileContents;
	}
	
	/**
	 * Verifies that the file at the path specified exists and can be read
	 * 
	 * @param filePath
	 * @return
	 */
	private static boolean verifyFileExists(String filePath) {
		File file = new File(filePath);
		return file.exists() && file.canRead();
	}
}
