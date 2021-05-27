package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import core.Entity;
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

	/**
	 * Test remove cargo function
	 */
	@Test
	void removeCargoTest() {
		//Set Up game
		GameEnvironment.setUpGame("VOID","VOID", 1, 50);
		Ship ship = GameEnvironment.getPlayerShip();
		
		//Add items to the ship
		ship.setCapacity(999999);
		GameEnvironment.getPlayer().setWallet(1000);
		for (int i=0; i<5; i++) {
			Item newItem = new Item();
			newItem.setPurchasePrice(100);
			newItem.setLocationOfStore(ship.getLocation());
			ship.addCargo(newItem);
		}

		int previousSize = ship.getCapacity();

		Entity itemToRemove = ship.getCargo().get(0);
		ship.removeCargo(itemToRemove);

		//Check capacity is updated
		assertEquals(previousSize + itemToRemove.getWeight(), ship.getCapacity());
		//Check Transaction history is updated
		assertEquals(itemToRemove.getName(), ship.getTransactionHistory().get(ship.getTransactionHistory().size() - 1)[0]);

		//Add upgrade
		Upgrade newUpgrade3 = new Upgrade(2, ship.getLocation());
		newUpgrade3.setPurchasePrice(10);
		ship.addCargo(newUpgrade3);
		assertEquals(130, ship.getHealth());

		//Remove Upgrade
		ship.removeCargo(newUpgrade3);
		assertEquals(80, ship.getHealth());
		assertEquals(0, ship.getUpgrades().size());
	}

	/**
	 * Test the take damage and repair functions
	 */
	@Test
	void shipHealthTest() {
		//Set Up game
		GameEnvironment.setUpGame("VOID","VOID", 1, 50);
		Ship ship = GameEnvironment.getPlayerShip();
		GameEnvironment.getPlayer().setWallet(1000);

		ship.setMaxHealth(80);
		assertEquals("Ship Repaired for $0. You can now sail.", ship.repair());

		ship.takeDamage(79);
		assertEquals(1, ship.getHealth());

		ship.takeDamage(1);
		assertEquals(0, ship.getHealth());

		ship.repair();
		assertEquals(1000 - 80, GameEnvironment.getPlayer().getWallet());
		assertEquals(80, ship.getHealth());
	}
}
