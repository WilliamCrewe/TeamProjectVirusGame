package main.java.logging;

import java.lang.invoke.MethodHandles;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

/**
 * Logger to allow the control of log level as well as whether System.out or the
 * java util logger should be used (Used as part of the debugging process)
 * 
 * @author dan.riggs
 *
 */
public class SystemLogger {

	private SystemLogger() {
		// Private constructor to prevent instantiation
	}

	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass().getCanonicalName());
	private static final String LOGGER_FORMAT = "[%4$s] %5$s %n";
	
	private static final String SYSTEM_OUT_FORMAT = "[%s] %s";
	private static boolean useLogger = false;

	private static Level logLevel = Level.ALL;

	static {
		setupLogger();
	}

	/**
	 * Method to set up the log format and the logger itself
	 */
	private static void setupLogger() {
		System.setProperty("java.util.logging.SimpleFormatter.format", LOGGER_FORMAT);
		StreamHandler handler = new StreamHandler(System.out, new SimpleFormatter());
		handler.setLevel(logLevel);
		LOGGER.addHandler(handler);
		LOGGER.setLevel(logLevel);
		LOGGER.setUseParentHandlers(false);
	}

	/**
	 * Used to initialise the log level and logger as part of the normal execution
	 * 
	 * @param newLogLevel
	 */
	public static void initialise(Level newLogLevel) {
		useLogger = false;
		logLevel = newLogLevel;
		setupLogger();
	}

	/**
	 * Log the message at the SEVERE log level
	 * 
	 * @param message
	 * @param args
	 */
	public static void severe(String message, Object... args) {
		log(Level.SEVERE, message, args);
	}

	/**
	 * Log the message at the CONFIG log level
	 * 
	 * @param message
	 * @param args
	 */
	public static void config(String message, Object... args) {
		log(Level.CONFIG, message, args);
	}

	/**
	 * Log the message at the FINE log level
	 * 
	 * @param message
	 * @param args
	 */
	public static void fine(String message, Object... args) {
		log(Level.FINE, message, args);
	}

	/**
	 * Log the message at the FINER log level
	 * 
	 * @param message
	 * @param args
	 */
	public static void finer(String message, Object... args) {
		log(Level.FINER, message, args);
	}

	/**
	 * Logs the message if the level of the log message is at the minimum required
	 * log level set
	 * 
	 * @param level
	 * @param message
	 * @param args
	 */
	private static void log(Level level, String message, Object... args) {
		// Check if the log level is enabled
		if (logLevel.intValue() > level.intValue()) {
			return;
		}

		// If not using the logger then simply print using System.out
		String logMessage = String.format(message, args);
		if (!useLogger) {
			logMessage = String.format(SYSTEM_OUT_FORMAT, level, logMessage);
			System.out.println(logMessage);
			return;
		}

		// Log at the correct level
		if (level == Level.SEVERE) {
			LOGGER.severe(logMessage);
		} else if (level == Level.CONFIG) {
			LOGGER.config(logMessage);
		} else if (level == Level.FINE) {
			LOGGER.fine(logMessage);
		} else if (level == Level.FINER) {
			LOGGER.finer(logMessage);
		} else {
			LOGGER.finest(logMessage);
		}

	}

}