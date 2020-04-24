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

	public static void setSeed(String newSeed) {
		long longSeed = Long.parseLong(newSeed, 16);
		random.setSeed(longSeed);
		SystemLogger.config("Seed set to %s", newSeed);
		
	}
	
	public static int getIntInRange(int bound) {
		
		return random.nextInt(bound);
	}
	
	public static Rarity getRandomRarity() {
		int valueChosen = getIntInRange(100);
		return Rarity.getRarityByIntRange(valueChosen);
	}
}
