package main.java.random;

import java.util.Random;

import main.java.logging.SystemLogger;

/**
 * Class to handle the randomness of the system based on a seed (To ensure consistency for testing)
 * @author Daniel
 *
 */
public class RandomnessHandler {

	private static Random random = new Random();

	private RandomnessHandler() {
		// private constructor to prevent instantiation
	}
	
	public static void setSeed(String newSeed) {
		long longSeed = Long.parseLong(newSeed, 16);
		random.setSeed(longSeed);
		SystemLogger.config("Seed set to %s", newSeed);
		
	}
	
	/**
	 * Returns a random integer from the seeded Random
	 * @param bound
	 * @return
	 */
	public static int getIntInRange(int bound) {
		return random.nextInt(bound);
	}
	
	/**
	 * Returns a Rarity enum value based on it's probability of occuring
	 * @return
	 */
	public static Rarity getRandomRarity() {
		int valueChosen = getIntInRange(100);
		Rarity rarity =  Rarity.getRarityByIntRange(valueChosen);
		
		SystemLogger.finer("A rarity of %s was generated for the randomly chosen value of %s", rarity, valueChosen);
		return rarity;
	}
}
