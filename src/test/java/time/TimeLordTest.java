package test.java.time;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.java.filehandling.gamecontent.realisations.components.TimeType;
import main.java.time.TimeLord;

public class TimeLordTest {

	/**
	 * Positive test case from when the TimeLord is constructed from a TimeType object
	 */
	@Test
	public void testConstructorTimeType() {
		TimeType time = new TimeType("6:30");
		
		// call the method in test (Constructor so instantiate)
		TimeLord timeLord = new TimeLord(time);
		
		// Assert the times were set correctly
		assertEquals(6, timeLord.getTimeHour());
		assertEquals(30, timeLord.getTimeMinute());
	}
	
	/**
	 * Positive test case from when the TimeLord is constructed from an int object
	 */
	@Test
	public void testConstructor() {
		// call the method in test (Constructor so instantiate)
		TimeLord timeLord = new TimeLord(6, 30);
		
		// Assert the times were set correctly
		assertEquals(6, timeLord.getTimeHour());
		assertEquals(30, timeLord.getTimeMinute());
	}
	
	/**
	 * Positive test case from when the SetTime method is called
	 */
	@Test
	public void testSetTime() {
		// Construct the object in test
		TimeLord timeLord = new TimeLord(6, 30);
		
		// Call the method in test
		timeLord.setTime(14, 26);
		
		// Assert the times were set correctly
		assertEquals(14, timeLord.getTimeHour());
		assertEquals(26, timeLord.getTimeMinute());
		
		// Assert the pre-time was correct

		assertEquals(6, timeLord.getPreHours());
		assertEquals(30, timeLord.getPreMinutes());
	}
	
	/**
	 * Positive test case from when the AddTime method is called
	 */
	@Test
	public void testAddTime() {
		// Construct the object in test
		TimeLord timeLord = new TimeLord(6, 30);
		
		// Call the method in test
		timeLord.addTime(55);
		
		// Assert the times were set correctly
		assertEquals(7, timeLord.getTimeHour());
		assertEquals(25, timeLord.getTimeMinute());
	}
	
	/**
	 * Positive test case from when the AddTime method is called and the amount added is greater than a day
	 */
	@Test
	public void testAddTimeIsGreaterThanADay() {
		// Construct the object in test
		TimeLord timeLord = new TimeLord(6, 30);
		
		// Call the method in test
		timeLord.addTime(25*60);
		
		// Assert the times were set correctly
		assertEquals(7, timeLord.getTimeHour());
		assertEquals(30, timeLord.getTimeMinute());
		

		assertTrue(timeLord.isExhaustionTimeReached());
	}
	
	/**
	 * Positive test case from when the getTime method is called
	 */
	@Test
	public void testGetTime() {
		// Construct the object in test
		TimeLord timeLord = new TimeLord(6, 30);
		
		// Call the method in test
		assertEquals("6:30", timeLord.getTime().getTime());
				
	}
}
