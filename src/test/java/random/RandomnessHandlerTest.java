package test.java.random;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.java.random.RandomnessHandler;
import main.java.random.Rarity;

public class RandomnessHandlerTest {

	/**
	 * Positive test case for when the getIntInRange method is called. Ensures that the same value is given when the seed is set
	 */
	@Test
	public void testGetIntInRange() {
		 RandomnessHandler.setSeed("AAAA");
		 int randomIntOne = RandomnessHandler.getIntInRange(1000);
		 
		 RandomnessHandler.setSeed("AAAA");
		 int randomIntTwo = RandomnessHandler.getIntInRange(1000);
		 
		 assertEquals(randomIntOne, randomIntTwo);
		 assertEquals(685, randomIntOne);
	}
	
	/**
	 * Positive test case for when the getRandomRarityMethod is called
	 */
	@Test
	public void testGetRandomRarity() {
		 RandomnessHandler.setSeed("AAAA");
		 Rarity rarityOne = RandomnessHandler.getRandomRarity();
		 
		 RandomnessHandler.setSeed("AAAA");
		 Rarity rarityTwo = RandomnessHandler.getRandomRarity();
		 
		 assertEquals(rarityOne, rarityTwo);
		 assertEquals(Rarity.RARE, rarityOne);
	}
	
	/**
	 * Positive test case for when the getRandomRarityMethod is called
	 */
	@Test
	public void testGetRandomRarityManyCalls() {
		int commonCount = 0;
		int uncommonCount = 0;
		int rareCount = 0;
		int veryRareCount = 0;
		int legendaryCount = 0;
		
		RandomnessHandler.setSeed("AAAA");
		 for (int i = 0; i < 1000; i++) {
			 Rarity rarity = RandomnessHandler.getRandomRarity();
			
			 switch (rarity) {
			 case COMMON:
				 commonCount++;
				 break;
			 case UNCOMMON:
				 uncommonCount++;
				 break;
			 case RARE:
				 rareCount++;
				 break;
			 case VERY_RARE:
				 veryRareCount++;
				 break;
			 case LEGENDARY:
				 legendaryCount++;
				 break;
			 }
		}
		
		assertEquals(382, commonCount);
		assertEquals(319, uncommonCount);
		assertEquals(211, rareCount);
		assertEquals(76, veryRareCount);
		assertEquals(12, legendaryCount);
	}
}
