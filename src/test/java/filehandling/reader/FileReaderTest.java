package test.java.filehandling.reader;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

import main.java.filehandling.reader.FileReader;
import main.java.filehandling.reader.exception.FileNotFoundException;
import main.java.filehandling.reader.exception.FileReadException;

public class FileReaderTest {

	/**
	 * Positive test case for when the the file contents are read in successfully
	 * @throws FileReadException
	 */
	@Test
	public void testReadFileContents() throws FileReadException {
		String expectedFileContent = "<Content>\r\n" + 
				"	<Save>\r\n" + 
				"		<SaveName>TestSaveName</SaveName>\r\n" + 
				"		<Seed>TestSeed</Seed>\r\n" + 
				"		<DayNumber>42</DayNumber>\r\n" + 
				"	</Save>\r\n" + 
				"</Content>";
		
		String fullFilePath = System.getProperty("user.dir") + "\\Resources\\TestFiles\\Saves\\SAVE_TestEventName.xml";
				
		// Call the method in test
		byte[] fileContents = FileReader.readFileContents(fullFilePath);
		
		// Assert the contents is as expected
		assertEquals(expectedFileContent, new String(fileContents));
	}
	
	/**
	 * Positive test case for when the the file contents are read in successfully
	 * @throws FileReadException
	 */
	@Test(expected = FileNotFoundException.class)
	public void testReadFileContentsFileNotFound() throws FileReadException {
		String fullFilePath = "NoMatchingFile";
				
		// Call the method in test
		FileReader.readFileContents(fullFilePath);
	}
}
