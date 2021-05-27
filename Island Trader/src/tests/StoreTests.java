package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import core.Entity;
import core.GameEnvironment;
import core.Player;
import core.Ship;
import core.Store;

/**
 * Test the Store class
 */
class StoreTests {

	/**
	 * Test some of the sell funcrtionality
	 */
	@Test
	void sellTest() {
		GameEnvironment.setUpGame("Moses", "Wescombe", 1, 50);
		Ship ship = GameEnvironment.getPlayerShip();
		Store store = ship.getLocation().getStore();
		Player player = GameEnvironment.getPlayer();
		ship.setCapacity(10000);
		player.setWallet(99999);

		ArrayList<Entity> origonalStoreItems = store.getItemsSell();

		for (int i=0; i <= origonalStoreItems.size() - 1; i++) {
			Entity item = store.getItemsSell().get(0);
			int previousWallet = player.getWallet();
			int price = item.getPurchasePrice();
			store.sell(item);

			assertEquals(previousWallet - price, player.getWallet());
			assertEquals(item, ship.getCargo().get(ship.getCargo().size() - 1));
		}
	}
}
