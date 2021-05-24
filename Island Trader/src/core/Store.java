package core;
import java.util.ArrayList;

import gui.StoreWindow;

public class Store {
    /**Each Island has a store, stores manage the item buying and selling, items are stored in arrays in each Store object. */
    private ArrayList<Entity> itemsSell = new ArrayList<Entity>(); //List of item objects that the user is able to purchase
    private ArrayList<String[]> itemsBuy = new ArrayList<String[]>(); //List of item names to buy from user with coressponding value
    private Island location;
    private Upgrade upgrade;

    public Store(Island location, Upgrade upgrade) {
        /**Create items arrays, setting buy and sell items */
        this.location = location;
        this.upgrade = upgrade;
        this.itemsSell.add(this.upgrade);

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

    public ArrayList<Entity> getItemsSell() {
        return itemsSell;
    }

    public void displayStore() {
        /**Print The Store Terminal */
        //Print items available to sell to player
        System.out.println("\nSTORE");
        System.out.println("Items for Sale:\tWallet: $" + GameEnvironment.game.getPlayer().getWallet());
        int i = 1;
        for (Entity item : itemsSell) {
            //The following prints an orgonized grid
            Object[] preFormat = new String[] {
                "\t" + i + ": ",
                item.getName() + ": " + item.getDescription(),
                "Price: $" + item.getPurchasePrice(),
                "Weight: " + item.getWeight() + "kg"
            };
            //Sizing can be changed by altering the % numbers below
            String format = String.format("%-6s%-90s%-15s%-15s", preFormat);
            System.out.println(format);
            i++;
        }

        //Print items that are available to buy from player
        System.out.println("");
        System.out.println("Items we would like:");
        i = 1;
        boolean printed = false;
        //Check that both the store and the playerShip has the items, if they do then list them
        

        if (!printed) {
            System.out.println("\tWe do not require any of your items");
        }
    }

    public Object[][] getItemSellObjects() {
        Object[][] result = new Object[itemsSell.size()][5];
        int i = 0;
        for (Entity item : itemsSell) {
            result[i][0] = item.getItemDetails()[0];
            result[i][1] = item.getItemDetails()[1];
            result[i][2] = item.getItemDetails()[2];
            result[i][3] = item.getItemDetails()[3];
            result[i][4] = item;
            i++;
        }

        return result;
    }

    public Object[][] getItemsBuyObjects() {
        Object[][] result = new Object[itemsBuy.size()][5];
        int i = 0;
        for (String[] itemBuy : itemsBuy) {
            for  (Entity item : GameEnvironment.game.getPlayer().getShip().getCargo()) {
                if (item.getName() == itemBuy[0]) {
                    item.setSalePrice(Integer.parseInt(itemBuy[1]));
                    result[i][0] = item.getItemDetails()[0];
                    result[i][1] = item.getItemDetails()[1];
                    result[i][2] = item.getItemDetails()[4];
                    result[i][3] = item.getItemDetails()[3];
                    result[i][4] = item;
                    i++;
                }
            }
        }

        if (result.length >= 1 && result[0][0] == null) {
            result[i][0] = "No Items To Sell";
            result[i][1] = "No Items To Sell";
            result[i][2] = "No Items To Sell";
            result[i][3] = "No Items To Sell";
        }

        return result;
    }

    public String sell(StoreWindow state, int index) {
        /**Sells items to the user if they have enough money in thier wallet*/
        int playerWallet = GameEnvironment.game.getPlayer().getWallet();
        int playerCapacity = GameEnvironment.game.getPlayer().getShip().getCapacity();
        if (index < 0) {
            return "NoneSelected";
        }

        Entity selectedItem = (Entity)state.getItemsForPurchase()[index][4];
        if (selectedItem == null) {
            return "NoneSelected";
        }

        //Try to add item to the selectedItem, if the item is affordable and can fit on the ship
        //If player has enough money
        if (selectedItem.getPurchasePrice() <= playerWallet) {
            //If player has enough cargo space
            if (selectedItem.getWeight() <= playerCapacity) {
                //If its an upgrade add it directly to the ship
                if (selectedItem instanceof Upgrade) {
                    //Add upgrade to the ship
                    GameEnvironment.game.getPlayer().getShip().addUpgrade((Upgrade)selectedItem);
                }
            } else {
                return "WeightError";
            }
        }  else {
            return "CostError";
        }


        Ship shipToLoad = GameEnvironment.game.getPlayer().getShip();

        //Add cargo to the ship
        shipToLoad.addCargo(selectedItem);

        //Remove item from Store
        for (int j=0; j < itemsSell.size(); j++) {
            if (itemsSell.get(j).getName() == selectedItem.getName()) {
                if (itemsSell.get(j).getPurchasePrice() == selectedItem.getPurchasePrice()) {
                    GameEnvironment.game.getPlayer().changeWallet(-selectedItem.getPurchasePrice()); //Take money from wallet
                    itemsSell.remove(j);
                    break;
                }
            }
        }
        return "Success";
    }

    public String purchase(StoreWindow state, int index) {
        /**Buys items off the user*/
        if (index < 0) {
            return "NoneSelected";
        }

        Entity selectedItem = (Entity)state.getItemsToBuy()[index][4];
        if (selectedItem == null) {
            return "NoneSelected";
        }
        Ship playersShip = GameEnvironment.game.getPlayer().getShip();

        //Remove cargo from the ship
        playersShip.removeCargo(selectedItem);

        //change location of item and update wallet
        selectedItem.setLocationOfStore(this.location);
        GameEnvironment.game.getPlayer().changeWallet(selectedItem.getSalePrice()); //Add profits to wallet
        return "Success";
    }
}
