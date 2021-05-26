package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import core.Event;
import core.GameEnvironment;
import core.Item;

class EventTests {	

	/**
	 * Test to see if piratesGame throws errors.
	 */
	@Test
	void piratesGameTest() {
		String result = Event.piratesGame("north");
		assertTrue(("SUCCESS" ==  result || "FAILURE" == result));

		result = Event.piratesGame("east");
		assertTrue(("SUCCESS" ==  result || "FAILURE" == result)); 

		result = Event.piratesGame("south");
		assertTrue(("SUCCESS" ==  result || "FAILURE" == result)); 

		result = Event.piratesGame("west");
		assertTrue(("SUCCESS" ==  result || "FAILURE" == result)); 
	}

	/**
	 * Test piratesFailure. Testing for GAMEOVER event, and stealing cargo event.
	 */
	@Test
	void piratesFailureTest() {
		GameEnvironment.setUpGame("VOID","VOID", 1, 50);
		//Test the game ends if you do not have enough items
		assertEquals("GAMEOVER", Event.piratesFailure());
		
		//Test the game does not end and the pirates take loot
		GameEnvironment.getPlayerShip().setCapacity(999999);
		GameEnvironment.getPlayer().setWallet(1000);
		for (int i=0; i<5; i++) {
			Item newItem = new Item();
			newItem.setPurchasePrice(100);
			newItem.setLocationOfStore(GameEnvironment.getPlayerShip().getLocation());
			GameEnvironment.getPlayerShip().addCargo(newItem);
		}

		int numberItems = GameEnvironment.getPlayerShip().getCargo().size();

		assertEquals("The pirates are satisfied with your items, they re-board thier ship with your items and leave with a smile and a wave.", Event.piratesFailure());

		assertTrue(GameEnvironment.getPlayer().getWallet() == 1000);
		assertTrue(GameEnvironment.getPlayerShip().getCargo().size() < numberItems);
	}
}
