package main.java.gamecontrol.controllers;

import main.java.filehandling.gamecontent.realisations.SaveGameContent;
import main.java.gamecontrol.gamestate.GameState;
import main.java.random.RandomnessHandler;

/**
 * Controller to handle the infection of the player based on the players
 * immunity and contagion level
 * 
 * @author Daniel
 *
 */
public class InfectionController {

	private InfectionController() {
		// Private method to prevent instantiation
	}

	public static void handleInfection() {
		SaveGameContent loadedSave = GameState.getInstance().getSave();

		int immunity = loadedSave.getImmunity();

		int contagionLevel = loadedSave.getContagionLevel();

		int randomChanceOfInfection = RandomnessHandler.getIntInRange(100);

		// If the chance of infection was greater than or equal to the immunity
		// (positive trait) minus the contagion level (negative trait) the increase the
		// infection level
		if (randomChanceOfInfection >= ((immunity - contagionLevel) * loadedSave.getDifficulty().getModifier())) {

			loadedSave.alterIllnessLevel(1);

			if (loadedSave.getIllnessLevel() >= 10) {
				GameState.getInstance().setGameOver(true);
			}
		}
	}
}
