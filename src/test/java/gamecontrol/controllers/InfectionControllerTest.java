package test.java.gamecontrol.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import main.java.filehandling.gamecontent.realisations.SaveGameContent;
import main.java.gamecontrol.controllers.InfectionController;
import main.java.gamecontrol.gamestate.GameState;

public class InfectionControllerTest {

	@After
	public void afterTest() {
		GameState.destroy();
	}
	
	/**
	 * Positive test case for where the illness level of the player increases by 1
	 */
	@Test
	public void testHandleInfection() {
		// Call the method in test
		InfectionController.handleInfection();
		
		SaveGameContent save = GameState.getInstance().getSave();
		
		// Assert the illness level has not increased
		assertEquals(1, save.getIllnessLevel());
	}
	
	/**
	 * Positive test case for where the illness level of the player does not increase due to their immunity level
	 */
	@Test
	public void testHandleInfectionHighImmulity() {
		SaveGameContent save = GameState.getInstance().getSave();
		
		// Add a high value to the immunity to ensure the illness level is not changed
		save.alterImmunity(100);
		
		// Call the method in test
		InfectionController.handleInfection();

		// Assert the illness level has not increased
		assertEquals(0, save.getIllnessLevel());
	}
	
	/**
	 * Positive test case for where the illness level of the player reaches 10 (Game over level)
	 */
	@Test
	public void testHandleInfectionIllnessLevel10Reached() {
		SaveGameContent save = GameState.getInstance().getSave();
		
		// Increase the illness level to 9 (So reaches the limit of 10 when infected again)
		save.alterIllnessLevel(9);
		
		// Call the method in test
		InfectionController.handleInfection();

		// Assert the illness level has increased
		assertEquals(10, save.getIllnessLevel());
		
		// Asssert the game has been set as over
		assertTrue(GameState.getInstance().isGameOver());
	}
}
