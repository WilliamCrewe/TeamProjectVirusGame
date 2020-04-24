package test.java.random;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.java.random.Rarity;

public class RarityTest {

	/**
	 * Positive test case for when the getByXmlValue method is called
	 */
	@Test
	public void testGetByXmlValue() {
		assertEquals(Rarity.COMMON, Rarity.getByXmlValue("Common"));
		assertEquals(Rarity.UNCOMMON, Rarity.getByXmlValue("Uncommon"));
		assertEquals(Rarity.RARE, Rarity.getByXmlValue("Rare"));
		assertEquals(Rarity.VERY_RARE, Rarity.getByXmlValue("VeryRare"));
		assertEquals(Rarity.LEGENDARY, Rarity.getByXmlValue("Legendary"));
		
	}
	
	/**
	 * Negative test case for when the getByXmlValue method is called but the tag passed in did not match
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetByXmlValueUnknownTagValue() {
		Rarity.getByXmlValue("UnknownValue");
	}
	
	/**
	 *  Positive test case for when the getRarityByIntRange method is called
	 */
	@Test
	public void testGetRarityByIntRange() {
		assertEquals(Rarity.COMMON, Rarity.getRarityByIntRange(30));
		assertEquals(Rarity.UNCOMMON, Rarity.getRarityByIntRange(50));
		assertEquals(Rarity.RARE, Rarity.getRarityByIntRange(70));
		assertEquals(Rarity.VERY_RARE, Rarity.getRarityByIntRange(95));
		assertEquals(Rarity.LEGENDARY, Rarity.getRarityByIntRange(99));
	}
	
	/**
	 * Negative test case for when the getRarityByIntRange method is called but the int passed in was greater than 100 (So out of range
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetRarityByIntRangeOutOfRange() {
		Rarity.getRarityByIntRange(101);
	}
	
}
