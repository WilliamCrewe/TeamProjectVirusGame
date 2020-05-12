package test.java.filehandling.reader;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

import main.java.filehandling.reader.FileReader;
import main.java.filehandling.reader.exception.FileNotFoundException;
import main.java.filehandling.reader.exception.FileReadException;

public class FileReaderTest {

	/**
	 * Positive test case for when the filesNames in a directory are read
	 */
	@Test
	public void testgetFilesInDirectory() {
		String fullFilePath = System.getProperty("user.dir") + "\\Resources\\TestFiles\\Saves\\";
		
		// Call the method in test
		String[] fileNamesFound = FileReader.getFilesInDirectory(fullFilePath);
		
		// Assert the correct filename was found
		assertEquals(1, fileNamesFound.length);
		assertEquals("SAVE_TestSaveName.xml", fileNamesFound[0]);
	}
	
	/**
	 * Positive test case for when the the file contents are read in successfully
	 * @throws FileReadException
	 */
	@Test
	public void testReadFileContents() throws FileReadException {
		String expectedFileContent = "<Content>\r\n" + 
				"	<Save>\r\n" + 
				"		<SaveName>TestSaveName</SaveName>\r\n" + 
				"		<Seed>AABBCC12</Seed>\r\n" + 
				"		<Difficulty>Easy</Difficulty>\r\n" + 
				"		<DayNumber>42</DayNumber>\r\n" + 
				"		<Time>6:30</Time>\r\n" + 
				"		<CurrentLocationID>TestLocationID</CurrentLocationID>\r\n" + 
				"		<Immunity>20</Immunity>\r\n" + 
				"		<ContagionLevel>15</ContagionLevel>\r\n" + 
				"		<IllnessLevel>5</IllnessLevel>\r\n" + 
				"		<Karma>16</Karma>\r\n" + 
				"		<CompletedEvents>\r\n" + 
				"			<CompletedEvent>\r\n" + 
				"				<EventID>TestEventID1</EventID>\r\n" + 
				"				<EventOptionID>TestOptionID1</EventOptionID>\r\n" + 
				"			</CompletedEvent>\r\n" + 
				"			<CompletedEvent>\r\n" + 
				"				<EventID>TestEventID2</EventID>\r\n" + 
				"				<EventOptionID>TestOptionID2</EventOptionID>\r\n" + 
				"			</CompletedEvent>\r\n" + 
				"		</CompletedEvents>\r\n" + 
				"		<SaveItems>\r\n" + 
				"			<Item>\r\n" + 
				"				<ItemID>ItemID1</ItemID>\r\n" + 
				"				<ItemName>ItemNameOne</ItemName>\r\n" + 
				"				<ItemCount>2</ItemCount>\r\n" + 
				"				<ItemUsageEventID>UseItem1EventID</ItemUsageEventID>\r\n" + 
				"			</Item>\r\n" + 
				"		</SaveItems>\r\n" + 
				"	</Save>\r\n" + 
				"</Content>";
		
		String fullFilePath = System.getProperty("user.dir") + "\\Resources\\TestFiles\\Saves\\SAVE_TestSaveName.xml";
				
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
