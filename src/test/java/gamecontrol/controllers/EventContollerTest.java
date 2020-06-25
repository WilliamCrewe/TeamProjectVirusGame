package test.java.gamecontrol.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import main.java.event.types.EventActionEvent;
import main.java.filehandling.gamecontent.realisations.EventGameContent;
import main.java.filehandling.gamecontent.realisations.components.EventOption;
import main.java.filehandling.gamecontent.realisations.components.EventOptionItems;
import main.java.filehandling.gamecontent.realisations.components.EventOptions;
import main.java.filehandling.gamecontent.realisations.components.ItemType;
import main.java.filehandling.gamecontent.realisations.components.RequiredItemType;
import main.java.filehandling.gamecontent.realisations.components.RequiredItemsType;
import main.java.gamecontrol.controllers.EventController;
import main.java.gamecontrol.gamestate.GameState;
import main.java.random.Rarity;

public class EventContollerTest {

	@Before
	public void beforeTest() {
		GameState.destroy();
	}

	/**
	 * Positive test case for when an event is handled successfully
	 */
	@Test
	public void testHandleEvent() {
		// Initialise the EventGameContent
		EventOptionItems eventOptionItems = new EventOptionItems(new ArrayList<>());
		EventOption testEventOption = new EventOption("TestOptionID1", "Test Option Description",
				"Test Option post Description", 10, 11, 12, eventOptionItems, null, null, null);
		EventOptions testEventOptions = new EventOptions(Arrays.asList(testEventOption));
		EventGameContent testEvent = new EventGameContent("TestEventID", "Test event", "TestLocationID", true, true,
				testEventOptions, Rarity.COMMON);

		// Set the event on the GameState
		GameState.getInstance().setNewEvent(testEvent);
		GameState.getInstance().updateCurrentEvent(testEvent);

		// Create the Event to pass in
		EventActionEvent testEventActionEvent = new EventActionEvent("TestEventID", "TestOptionID1");

		// Call the method in test
		EventController.handleEvent(testEventActionEvent);

		// Confirm the GameState was updated correctly
		assertEquals(10, GameState.getInstance().getSave().getImmunity());
		assertEquals(11, GameState.getInstance().getSave().getContagionLevel());
		assertEquals(12, GameState.getInstance().getSave().getKarma());
	}

	/**
	 * Positive test case for an event is handled successfully when the option has
	 * itmes
	 */
	@Test
	public void testHandleEventOptionHasItems() {
		// Initialise the EventGameContent
		ItemType testItem = new ItemType("TestItemID", "Test Item", 1, null);
		EventOptionItems eventOptionItems = new EventOptionItems(Arrays.asList(testItem));
		EventOption testEventOption = new EventOption("TestOptionID1", "Test Option Description",
				"Test Option post Description", 10, 11, 12, eventOptionItems, null, null, null);
		EventOptions testEventOptions = new EventOptions(Arrays.asList(testEventOption));
		EventGameContent testEvent = new EventGameContent("TestEventID", "Test event", "TestLocationID", true, true,
				testEventOptions, Rarity.COMMON);

		// Set the event on the GameState
		GameState.getInstance().setNewEvent(testEvent);
		GameState.getInstance().updateCurrentEvent(testEvent);

		// Create the Event to pass in
		EventActionEvent testEventActionEvent = new EventActionEvent("TestEventID", "TestOptionID1");

		// Call the method in test
		EventController.handleEvent(testEventActionEvent);

		// Confirm the GameState was updated correctly
		assertEquals(10, GameState.getInstance().getSave().getImmunity());
		assertEquals(11, GameState.getInstance().getSave().getContagionLevel());
		assertEquals(12, GameState.getInstance().getSave().getKarma());

		assertEquals(1, GameState.getInstance().getSave().getSaveItems().getSaveItemsMap().keySet().size());
		assertTrue(GameState.getInstance().getSave().getSaveItems().getSaveItemsMap().keySet().contains("TestItemID"));
	}

	/**
	 * Negative test case for when the event option has a required item which the save does not contain
	 */
	@Test
	public void testHandleEventOptionHasRequiredItemWhichSaveDoesNot() {
		// Initialise the EventGameContent
		EventOptionItems eventOptionItems = new EventOptionItems(new ArrayList<>());
		
		RequiredItemsType requiredItems = new RequiredItemsType(Arrays.asList(new RequiredItemType("RequiredItemID", 1)));
		EventOption testEventOption = new EventOption("TestOptionID1", "Test Option Description",
				"Test Option post Description", 10, 11, 12, eventOptionItems, null, requiredItems, null);
		EventOptions testEventOptions = new EventOptions(Arrays.asList(testEventOption));
		EventGameContent testEvent = new EventGameContent("TestEventID", "Test event", "TestLocationID", true, true,
				testEventOptions, Rarity.COMMON);

		// Set the event on the GameState
		GameState.getInstance().setNewEvent(testEvent);
		GameState.getInstance().updateCurrentEvent(testEvent);

		// Create the Event to pass in
		EventActionEvent testEventActionEvent = new EventActionEvent("TestEventID", "TestOptionID1");

		// Call the method in test
		EventController.handleEvent(testEventActionEvent);

		// Confirm the GameState was not updated
		assertEquals(0, GameState.getInstance().getSave().getImmunity());
		assertEquals(0, GameState.getInstance().getSave().getContagionLevel());
		assertEquals(0, GameState.getInstance().getSave().getKarma());
	}
	
	/**
	 * Positive test case for when the option has a required item
	 */
	@Test
	public void testHandleEventOptionHasRequiredItem() {
		// Initialise the EventGameContent
		EventOptionItems eventOptionItems = new EventOptionItems(new ArrayList<>());
		RequiredItemsType requiredItems = new RequiredItemsType(Arrays.asList(new RequiredItemType("RequiredItemID", 1)));
		EventOption testEventOption = new EventOption("TestOptionID1", "Test Option Description",
				"Test Option post Description", 10, 11, 12, eventOptionItems, null, requiredItems, null);
		EventOptions testEventOptions = new EventOptions(Arrays.asList(testEventOption));
		EventGameContent testEvent = new EventGameContent("TestEventID", "Test event", "TestLocationID", true, true,
				testEventOptions, Rarity.COMMON);

		// Set the event on the GameState
		GameState.getInstance().setNewEvent(testEvent);
		GameState.getInstance().updateCurrentEvent(testEvent);

		// Set the Required Item on the save
		ItemType testItem = new ItemType("RequiredItemID", "Test Item", 1, null);
		GameState.getInstance().getSave().getSaveItems().addToSaveItemsMap(testItem);
		
		// Create the Event to pass in
		EventActionEvent testEventActionEvent = new EventActionEvent("TestEventID", "TestOptionID1");

		// Call the method in test
		EventController.handleEvent(testEventActionEvent);

		// Confirm the GameState was updated
		assertEquals(10, GameState.getInstance().getSave().getImmunity());
		assertEquals(11, GameState.getInstance().getSave().getContagionLevel());
		assertEquals(12, GameState.getInstance().getSave().getKarma());
		
		// Assert the required item was removed from the save
		assertNull(GameState.getInstance().getSave().getSaveItems().getItemByID("RequiredItemID"));
	}

	/**
	 * Positive test case for when the option chosen has a following event 
	 */
	@Test
	public void testHandleEventOptionHasFollowingEventID() {
		// Initialise the EventGameContent
		EventOptionItems eventOptionItems = new EventOptionItems(new ArrayList<>());
		EventOption testEventOption = new EventOption("TestOptionID1", "Test Option Description",
				"Test Option post Description", 10, 11, 12, eventOptionItems, null, null, "FollowingEventID");
		EventOptions testEventOptions = new EventOptions(Arrays.asList(testEventOption));
		EventGameContent testEvent = new EventGameContent("TestEventID", "Test event", "TestLocationID", true, true,
				testEventOptions, Rarity.COMMON);
		
		EventGameContent testFollowingEvent = new EventGameContent("FollowingEventID", "Test event",null, true, false,
				testEventOptions, Rarity.COMMON);


		// Set the event on the GameState
		GameState.getInstance().setNewEvent(testEvent);
		GameState.getInstance().setNewEvent(testFollowingEvent);
		GameState.getInstance().updateCurrentEvent(testEvent);

		// Create the Event to pass in
		EventActionEvent testEventActionEvent = new EventActionEvent("TestEventID", "TestOptionID1");

		// Call the method in test
		EventController.handleEvent(testEventActionEvent);

		// Confirm the GameState was updated correctly
		assertEquals(10, GameState.getInstance().getSave().getImmunity());
		assertEquals(11, GameState.getInstance().getSave().getContagionLevel());
		assertEquals(12, GameState.getInstance().getSave().getKarma());
		
		// Assert that the following event was set to the current event
		assertEquals(testFollowingEvent, GameState.getInstance().getCurrentEvent());
	}

	/**
	 * Negative test case for when the optionID passed in does not match any of the options available
	 */
	@Test
	public void testHandleEventNoMatchingOption() {
		// Initialise the EventGameContent
		EventOptionItems eventOptionItems = new EventOptionItems(new ArrayList<>());
		EventOption testEventOption = new EventOption("TestOptionID1", "Test Option Description",
				"Test Option post Description", 10, 11, 12, eventOptionItems, null, null, null);
		EventOptions testEventOptions = new EventOptions(Arrays.asList(testEventOption));
		EventGameContent testEvent = new EventGameContent("TestEventID", "Test event", "TestLocationID", true, true,
				testEventOptions, Rarity.COMMON);

		// Set the event on the GameState
		GameState.getInstance().setNewEvent(testEvent);
		GameState.getInstance().updateCurrentEvent(testEvent);

		// Create the Event to pass in
		EventActionEvent testEventActionEvent = new EventActionEvent("TestEventID", "TestOptionID99");

		// Call the method in test
		EventController.handleEvent(testEventActionEvent);

		// Confirm the GameState was not updated
		assertEquals(0, GameState.getInstance().getSave().getImmunity());
		assertEquals(0, GameState.getInstance().getSave().getContagionLevel());
		assertEquals(0, GameState.getInstance().getSave().getKarma());
	}
}
