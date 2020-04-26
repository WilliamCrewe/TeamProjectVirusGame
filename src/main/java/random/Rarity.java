package main.java.random;

/**
 * Enum representing the different rarities allowable within the system, as well
 * as the different likelihoods of them occurring (range out of 100)
 * 
 * @author Daniel
 *
 */
public enum Rarity {
	COMMON("Common", 0, 40), // 40% probability
	UNCOMMON("Uncommon", 40, 70), // 30% probability 
	RARE("Rare", 70, 90), // 20% Probability
	VERY_RARE("VeryRare", 90, 99), // 9% probability
	LEGENDARY("Legendary", 99, 100); // 1% probability

	private String xmlValue;
	private int minimumRangeValueInclusive;
	private int maximumRangeValueExclusive;

	private Rarity(String xmlValue, int minimumRangeValueInclusive, int maximumRangeValueExclusive) {
		this.xmlValue = xmlValue;
		this.minimumRangeValueInclusive = minimumRangeValueInclusive;
		this.maximumRangeValueExclusive = maximumRangeValueExclusive;
	}

	public String getXmlValue() {
		return xmlValue;
	}
	
	public int getMinimumRangeValueInclusive() {
		return minimumRangeValueInclusive;
	}
	
	public int getMaximumRangeValueExclusive() {
		return maximumRangeValueExclusive;
	}
	
	/**
	 * @return returns the more common version of this rarity, throws an exception if the rarity is common
	 */
	public Rarity getMoreCommonRarity() {
		if (this == Rarity.COMMON) {
			throw new IllegalArgumentException("There is no rarity below common");
		}
		
		return Rarity.values()[this.ordinal() -1];
	}
	
	/**
	 * Returns the rarity which has the value within it's range
	 * @param value
	 * @return
	 */
	public static Rarity getRarityByIntRange(int value) {
		// Loop over all the possible rarity types (Should align to those allowable by
		// the XSD)
		for (Rarity rarityToCheck : Rarity.values()) {
			// If the value is greater than or equal to the minimum AND less than the maximum then return it
			if (rarityToCheck.getMinimumRangeValueInclusive() <= value && rarityToCheck.getMaximumRangeValueExclusive() > value) {
				return rarityToCheck;
			}
		}
		
		throw new IllegalArgumentException(String.format("No Rarity has the value %d in its range", value));
	}

	/**
	 * Returns the rarity which has the XML value matching that passed
	 * @param xmlValuePassed
	 * @return
	 */
	public static Rarity getByXmlValue(String xmlValuePassed) {
		// Loop over all the possible rarity types (Should align to those allowable by
		// the XSD)
		for (Rarity rarityToCheck : Rarity.values()) {

			// If the XML tags match then return the Rarity
			if (rarityToCheck.getXmlValue().equals(xmlValuePassed)) {
				return rarityToCheck;
			}
		}

		throw new IllegalArgumentException(String.format("No Rarity has the XML value %s", xmlValuePassed));
	}
}
