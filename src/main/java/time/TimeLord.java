package main.java.time;

import main.java.filehandling.gamecontent.realisations.components.TimeType;

/**
 * Object to manage the time in the system
 * @author Daniel
 *
 */
public class TimeLord {

	private int preHours;
	private int preMinutes;
	private int hours;
	private int minutes;
	private boolean exhaustionTimeReached;
	
	private static final String TIME_SEPERATOR = ":";

	public TimeLord(TimeType time) {
		String[] splitTime = time.getTime().split(TIME_SEPERATOR);
		
		hours = Integer.parseInt(splitTime[0]);
		minutes = Integer.parseInt(splitTime[1]);
	}
	
	/**
	 * method for setting the time to a specific value
	 */
	public TimeLord(int newHour, int newMinute) {
		hours = newHour;
		minutes = newMinute;

		// making sure the value is a in the correct range of a clock
		addTime(0);

	}
	
	public void setTime(int hours, int minutes) {
		preHours = this.hours;
		preMinutes = this.minutes;
		
		this.hours = hours;
		this.minutes = minutes;
	}
	
	public TimeType getTime() {
		return new TimeType(hours + TIME_SEPERATOR + minutes);
	}

	/**
	 * method for increasing the clocks time
	 */
	public void addTime(int timeToAdd) {
		// working out the number minutes in the time passed (discounting hours)
		minutes += timeToAdd;
		// Checking whether or not a minute has passed (or whether the passed value is
		// multiple minutes)
		while (minutes >= 60) {
			minutes -= 60;
			hours++;
		}

		// Checking whether or not a day has passed
		while (hours >= 24) {
			hours -= 24;
			exhaustionTimeReached = true;
		}
	}

	/**
	 * method to get the hour value of the clock
	 */
	public int getTimeHour() {
		return hours;
	}

	/**
	 * method for getting the minute value of the clock
	 */
	public int getTimeMinute() {
		return minutes;
	}
	
	public boolean isExhaustionTimeReached() {
		return exhaustionTimeReached;
	}
}
