package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import core.Event;
import core.GameEnvironment;
import core.Route;
import gui.StartMenuWindow;

/**
 * Game Environment Unit Tests.
 */
class GameEnvironmentTests {

	/**
	 * Test if the game gets set up properly
	 */
	@Test
	void setUpGameTest() {
		GameEnvironment.setUpGame("Moses", "Wescombe", 1, 50);
		assertEquals("Moses", GameEnvironment.getPlayer().getName());
		assertEquals("Wescombe", GameEnvironment.getPlayerShip().getName());
		assertEquals("CargoShip", GameEnvironment.getPlayerShip().getType());
		assertEquals(50, GameEnvironment.getTime().getTimeRemaining());
	}
	
	/**
	 * Test checkNameInput
	 */
	@Test
	void checkNameInputTest() {
		StartMenuWindow testWindow = new StartMenuWindow();

		//Test a random good input
		assertEquals(true, GameEnvironment.checkNameInput(testWindow.getShipNameWarning(), "Moses"));

		//Lower length bound
		assertEquals(true, GameEnvironment.checkNameInput(testWindow.getShipNameWarning(), "Mos"));

		//Out of lower bound
		assertEquals(false, GameEnvironment.checkNameInput(testWindow.getShipNameWarning(), "Mo"));

		//Upper length bound
		assertEquals(true, GameEnvironment.checkNameInput(testWindow.getShipNameWarning(), "MosesWecombeABC"));

		//out of upper length bound
		assertEquals(false, GameEnvironment.checkNameInput(testWindow.getShipNameWarning(), "MosesWecombeABCD"));

		//Combination of special and regular characters
		assertEquals(false, GameEnvironment.checkNameInput(testWindow.getShipNameWarning(), "Moses!$!@#%"));

		//Special Characters
		assertEquals(false, GameEnvironment.checkNameInput(testWindow.getShipNameWarning(), "!$!@#%"));

		//Spaced Name
		assertEquals(true, GameEnvironment.checkNameInput(testWindow.getShipNameWarning(), "Moses Wescombe"));

		testWindow.closeWindow();
	}

	/**
	 * Tests for setting up player. Tests various ship types and player/ship names.
	 */
	@Test
	void setUpPlayerTest() {
		GameEnvironment.setUpPlayer("Moses", "Wescombe", 1);
		assertEquals("Moses", GameEnvironment.getPlayer().getName());
		assertEquals("Wescombe", GameEnvironment.getPlayerShip().getName());
		assertEquals("CargoShip", GameEnvironment.getPlayerShip().getType());

		GameEnvironment.setUpPlayer("M", "W", 2);
		assertEquals("M", GameEnvironment.getPlayer().getName());
		assertEquals("W", GameEnvironment.getPlayerShip().getName());
		assertEquals("BattleShip", GameEnvironment.getPlayerShip().getType());

		GameEnvironment.setUpPlayer("Moses", "Wescombe", 3);
		assertEquals("Moses", GameEnvironment.getPlayer().getName());
		assertEquals("Wescombe", GameEnvironment.getPlayerShip().getName());
		assertEquals("CruiserShip", GameEnvironment.getPlayerShip().getType());

		GameEnvironment.setUpPlayer("Moses", "Wescombe", 4);
		assertEquals("Moses", GameEnvironment.getPlayer().getName());
		assertEquals("Wescombe", GameEnvironment.getPlayerShip().getName());
		assertEquals("Dingy", GameEnvironment.getPlayerShip().getType());
	}

	/**
	 * Test the sailToIsland function. Test it by taking multiple routes and checking to see if the location is updated correctly
	 */
	@Test
	void sailToIslandTest() {
		//Set Up game
		GameEnvironment.setUpGame("Moses", "Wescombe", 1, 9999999);
		//Stop events from occuring
		Event.setEventOveride(0);
		//Set wallet high enough that the game wont end
		GameEnvironment.getPlayer().setWallet(9999999);

		//Test all islands
		for (int i=0; i < GameEnvironment.getIslands().size(); i++) {
			//Check if the island has a route
			boolean isRoute = false;
			for (Route route : GameEnvironment.getIslands().get(i).getRoutes()) {
				if (route.getOrigin().getName() == GameEnvironment.getPlayerShip().getLocation().getName()) {
					isRoute = true;
					break;
				}
			}

			//Check if the sailToIsland function works.
			if (isRoute) {
				GameEnvironment.sailToIsland(GameEnvironment.getIslands().get(i));
				assertEquals(GameEnvironment.getIslands().get(i).getName(), GameEnvironment.getPlayerShip().getLocation().getName());
			} else {
				assertEquals("NO_ROUTE", GameEnvironment.sailToIsland(GameEnvironment.getIslands().get(i)));
			}
		}
	}
}
