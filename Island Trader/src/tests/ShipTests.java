package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import core.Event;
import core.GameEnvironment;
import core.Item;
import core.Ship;
import core.Upgrade;

/**
 * Test Ship class
 */
class ShipTests {

	/**
	 * Tests addCargo and addUpgrade
	 */
	@Test
	void addCargoTest() {
		GameEnvironment.setUpGame("Moses", "Wescombe", 1, 50);

		Ship ship = GameEnvironment.getPlayerShip();
		ship.setMaxCapacity(999999);
		ship.setCapacity(ship.getMaxCapacity());

		//Add a couple items and check that they were added
		Item newItem = new Item();
		newItem.setPurchasePrice(10);
		newItem.setLocationOfStore(ship.getLocation());
		ship.addCargo(newItem);
		assertTrue(ship.getCargo().size() == 1);
		assertTrue(ship.getCargo().get(0) == newItem);

		Item newItem2 = new Item();
		newItem2.setPurchasePrice(10);
		newItem2.setLocationOfStore(ship.getLocation());
		ship.addCargo(newItem2);
		assertTrue(ship.getCargo().size() == 2);
		assertTrue(ship.getCargo().get(1) == newItem2);

		assertEquals((ship.getMaxCapacity() - (ship.getCargo().get(0).getWeight() + ship.getCargo().get(1).getWeight())), ship.getCapacity());

		//Add all 4 upgrades and test the implemented correctly
		Upgrade newUpgrade = new Upgrade(0, ship.getLocation());
		newUpgrade.setPurchasePrice(10);
		ship.addCargo(newUpgrade);
		assertEquals(4, Event.getChances());

		Upgrade newUpgrade3 = new Upgrade(2, ship.getLocation());
		newUpgrade3.setPurchasePrice(10);
		ship.addCargo(newUpgrade3);
		assertEquals(130, ship.getHealth());

		Upgrade newUpgrade4 = new Upgrade(3, ship.getLocation());
		newUpgrade4.setPurchasePrice(10);
		ship.addCargo(newUpgrade4);
		assertEquals(999999 + 100, ship.getMaxCapacity());

		Upgrade newUpgrade5 = new Upgrade(4, ship.getLocation());
		newUpgrade5.setPurchasePrice(10);
		ship.addCargo(newUpgrade5);
		assertEquals(35, ship.getSailSpeed());
	}

}
