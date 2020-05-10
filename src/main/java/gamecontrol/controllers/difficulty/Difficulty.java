package main.java.gamecontrol.controllers.difficulty;

/**
 * Enum representing the different difficulties allowable within the system, as well
 * as the modification factor they allign with
 * 
 * @author Daniel
 *
*/
public enum Difficulty {
	EASY("Easy", 1.0),
	MEDIUM("Medium", 0.9),
	HARD("Hard", 0.8);
	
	private String xmlValue;
	private Double modifier;
	
	private Difficulty(String xmlValue, Double modifier) {
		this.xmlValue = xmlValue;
		this.modifier = modifier;
	}
	
	public String getXmlValue() {
		return xmlValue;
	}
	
	public Double getModifier() {
		return modifier;
	}
	
	/**
	 * Returns the Difficulty which has the XML value matching that passed
	 * @param xmlValuePassed
	 * @return
	 */
	public static Difficulty getByXmlValue(String xmlValuePassed) {
		// Loop over all the possible difficulty types (Should align to those allowable by
		// the XSD)
		for (Difficulty difficultyToCheck : Difficulty.values()) {

			// If the XML tags match then return the Difficulty
			if (difficultyToCheck.getXmlValue().equals(xmlValuePassed)) {
				return difficultyToCheck;
			}
		}

		throw new IllegalArgumentException(String.format("No Difficulty has the XML value %s", xmlValuePassed));
	}
}
