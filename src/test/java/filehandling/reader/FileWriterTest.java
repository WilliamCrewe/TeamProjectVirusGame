package test.java.filehandling.reader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import main.java.filehandling.reader.FileWriter;

public class FileWriterTest {

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	private File baseFolder;

	@Before
	public void setUp() throws Exception {
		folder.create();
		baseFolder = folder.newFolder();
	}
	
	/**
	 * Positive test case for when the contents of the file is written
	 * @throws Exception
	 */
	@Test
	public void testWriteContentsToFile() throws Exception {
		Path filePath = baseFolder.toPath().resolve("TestFile.xml");
		
		// Check the file doesn't exist before
		File fileBefore = filePath.toFile();
		assertFalse(fileBefore.exists());
		
		String fileContent = "<Content>\r\n" + 
				"	<Save>\r\n" + 
				"		<SaveName>TestSaveName</SaveName>\r\n" + 
				"		<Seed>TestSeed</Seed>\r\n" + 
				"		<DayNumber>42</DayNumber>\r\n" + 
				"	</Save>\r\n" + 
				"</Content>"; 
		
		// Call the method in test
		FileWriter.writeContentsToFile(filePath.toString(), fileContent.getBytes());
		
		// Assert the file now exists and has the expected contents
		File file = filePath.toFile();
		assertTrue(file.exists());
		
		assertEquals(fileContent, new String(Files.readAllBytes(filePath)));
	}
}
