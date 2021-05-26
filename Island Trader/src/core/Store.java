package core;

import java.util.ArrayList;
import gui.StoreWindow;

/**
 * Store Class. A store is what the user interacts with when trading goods.
 * 
 * ItemsSell - Items the user is able to purchase
 * ItemsBuy - List of item names the user is able to sell, along with the corresponding value
 * Location - Locatiion of the store
 */
public class Store {
    private ArrayList<Entity> itemsSell = new ArrayList<Entity>();
    private ArrayList<String[]> itemsBuy = new ArrayList<String[]>();
    private Island location;

    /**
     * Create a Store object.
     * 
     * @param location Island, Location of the store
     * @param upgrade Upgrade, upgrade entity that the store will add to the itemsSell array
     */
    public Store(Island location, Upgrade upgrade) {
        this.location = location;
        this.itemsSell.add(upgrade);

        //Generate items for store to sell
        int numberOfItemsSell =  (int)(Math.random() * (12 - 2 + 1) + 2);
        for (int i=0; i < numberOfItemsSell; i++) {
            this.itemsSell.add(new Item());
            int price = (int)(Math.random() * (100 - 1 + 1) + 1);
            this.itemsSell.get(this.itemsSell.size() - 1).setPurchasePrice(price);
            this.itemsSell.get(this.itemsSell.size() - 1).setLocationOfStore(this.location);;
        }

        //Generate items for store to buy, this only creates an array of item names, not item objects
        //The reason I have done this is because an object cannot be both on the island and in the Store's itemBuy array,
        //this means that there would be no way to check that the user has the item the store wants
        int numberOfItemsBuy =  (int)(Math.random() * (12 - 2 + 1) + 2);
        for (int i=0; i < numberOfItemsBuy; i++) {
            String[] values = new String[2];
            values[0] = Item.getRandomItem()[0]; //Get random item name
            values[1] = Integer.toString((int)(Math.random() * (100 - 2 + 1) + 2)); //Random Value, stored as String
            this.itemsBuy.add(values);
        }
    }

    /**
     * Sell an item. A user may purchase an item from the store, this method manages all the sale functions.
     * 
     * @param state StoreWindow, store window to close and reopen to update the items.
     * @param index int, Index of the item in the JTable that the user selected
     * @return String, {'NoneSelected', 'WeightError', 'CostError'} depending on different checks
     */
    public String sell(StoreWindow state, int index) {
        int playerWallet = GameEnvironment.getPlayer().getWallet();
        int playerCapacity = GameEnvironment.getPlayerShip().getCapacity();

        //Check if none are selected
        if (index < 0) {
            return "NoneSelected";
        }   

        //Find the item that the user selected
        Entity selectedItem = (Entity)state.getItemsForPurchase()[index][4];
        if (selectedItem == null) {
            return "NoneSelected";
        }

        //Try to add item to the selectedItem, if the item is affordable and can fit on the ship
        //If player has enough money
        if (selectedItem.getPurchasePrice() <= playerWallet) {
            //If player has enough cargo space
            if (selectedItem.getWeight() > playerCapacity) {
                return "WeightError";
            }
        }  else {
            return "CostError";
        }

        Ship shipToLoad = GameEnvironment.getPlayerShip();

        //Add cargo to the ship
        shipToLoad.addCargo(selectedItem);

        //Remove item from Store
        for (int j=0; j < itemsSell.size(); j++) {
            if (itemsSell.get(j).getName() == selectedItem.getName()) {
                if (itemsSell.get(j).getPurchasePrice() == selectedItem.getPurchasePrice()) {
                    GameEnvironment.getPlayer().changeWallet(-selectedItem.getPurchasePrice()); //Take money from wallet
                    itemsSell.remove(j);
                    break;
                }
            }
        }
        return "Success";
    }

    /**
     * Purchase an item. A user may sell an item from the store, this method manages all the sale functions.
     * 
     * @param state StoreWindow, store window to close and reopen to update the items.
     * @param index int, Index of the item in the JTable that the user selected
     * @return String, {'NoneSelected', 'Success'} depending on different checks
     */
    public String purchase(StoreWindow state, int index) {
        //Check the user selected an item
        if (index < 0) {
            return "NoneSelected";
        }

        //Find the item selected
        Entity selectedItem = (Entity)state.getItemsToBuy()[index][4];
        if (selectedItem == null) {
            return "NoneSelected";
        }

        Ship playersShip = GameEnvironment.getPlayerShip();

        //Remove cargo from the ship
        playersShip.removeCargo(selectedItem);

        //change location of item and update wallet
        selectedItem.setLocationOfStore(this.location);
        GameEnvironment.getPlayer().changeWallet(selectedItem.getSalePrice()); //Add profits to wallet
        return "Success";
    }

    /**
     * Create a Object[][] full of strings representing the items the store is selling to the. Used to display in a JTable.
     *  The array is of the format: {Name, Description, Price, Weight}
     * 
     * @return Object[][], array item info as Strings
     */
    public Object[][] getItemSellObjects() {
        Object[][] result = new Object[itemsSell.size() + 1][5];
        int i = 0;
        for (Entity item : itemsSell) {
            result[i][0] = item.getItemDetails()[0];
            result[i][1] = item.getItemDetails()[1];
            result[i][2] = item.getItemDetails()[2];
            result[i][3] = item.getItemDetails()[3];
            result[i][4] = item;
            i++;
        }

        //If no items to sell return 'No Items To Purchase'
        if (result.length == 1 && result[0][0] == null) {
            result[i][0] = "No Items To Purchase";
            result[i][1] = "N/A";
            result[i][2] = "N/A";
            result[i][3] = "N/A";
        }

        return result;
    }

    /**
     * Create a Object[][] full of strings representing the items the store is wanting from the user. Used to display in a JTable.
     *  The array is of the format: {Name, Description, Price, Weight}
     * 
     * @return Object[][], array item info as Strings
     */
    public Object[][] getItemsBuyObjects() {
        Object[][] result = new Object[itemsBuy.size()][5];
        int i = 0;
        for (String[] itemBuy : itemsBuy) {
            for  (Entity item : GameEnvironment.getPlayerShip().getCargo()) {
                if (item.getName() == itemBuy[0]) {
                    item.setSalePrice(Integer.parseInt(itemBuy[1]));
                    result[i][0] = item.getItemDetails()[0];
                    result[i][1] = item.getItemDetails()[1];
                    result[i][2] = itemBuy[1];
                    result[i][3] = item.getItemDetails()[3];
                    result[i][4] = item;
                    i++;
                }
            }
        }

        //If no items to sell return this
        if (result.length >= 1 && result[0][0] == null) {
            result[i][0] = "No Items To Sell";
            result[i][1] = "N/A";
            result[i][2] = "N/A";
            result[i][3] = "N/A";
        }

        return result;
    }


    //Getters/Setters
    public ArrayList<Entity> getItemsSell() {
        return itemsSell;
    }
}
