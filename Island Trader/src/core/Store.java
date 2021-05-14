package core;
import java.util.ArrayList;

public class Store {
    private ArrayList<Item> itemsBuy = new ArrayList<Item>();
    private ArrayList<Item> itemsSell = new ArrayList<Item>();

    public Store() {
        /**Create items arrays, setting buy and sell items */
        //TODO, add funtionality
    }

    public void displayMenu() {
        /**Display the options to buy and sell, for commandline application this may look like two choices,
         * 1, choose to buy or sell, then choose the items before confirming.
         */
    }

    public void purchase(Item[] itemsToBuy) {
        /**Purchase the items given */
        //Check if there is enough funds
        for (int i=0; i < itemsToBuy.length; i++) {
            if (CommandLine.wallet < itemsToBuy[i].getPurchasePrice()) {
                System.out.println("Sorry you do not have enough money to purchase these items.");
                return;
            }
        }

        //Remove items from store and add to ships cargo
        if (CommandLine.playerShip.addCargo(itemsToBuy)) {
            for (int i=0; i < itemsToBuy.length; i++) {
                itemsToBuy[i].setStore(null); //Set to stored on ship
                this.itemsSell.remove(itemsToBuy[i]);
            }
        }
    }

    public int sell(Item[] itemsToSell) {
        /**Sell the items given */
        int value = 0;
        return value;
    }
}
