package core;
import java.util.ArrayList;

public class Store {
    /**Each Island has a store, stores manage the item buying and selling, items are stored in arrays in each Store object. */
    private ArrayList<Item> itemsSell = new ArrayList<Item>(); //List of item objects that the user is able to purchase
    private ArrayList<String[]> itemsBuy = new ArrayList<String[]>(); //List of item names to buy from user with coressponding value

    public Store() {
        /**Create items arrays, setting buy and sell items */
        
        //Generate items for store to sell
        int numberOfItemsSell =  (int)(Math.random() * (15 - 1 + 1) + 1);
        for (int i=0; i < numberOfItemsSell; i++) {
            this.itemsSell.add(new Item());
            int price = (int)(Math.random() * (100 - 1 + 1) + 1);
            this.itemsSell.get(this.itemsSell.size() - 1).setPurchasePrice(price);;
        }

        //Generate items for store to buy, this only creates an array of item names, not item objects
        //The reason I have done this is because an object cannot be both on the island and in the Store's itemBuy array,
        //this means that there would be no way to check that the user has the item the store wants
        int anumberOfItemsBuy =  (int)(Math.random() * (15 - 1 + 1) + 1);
        for (int i=0; i < anumberOfItemsBuy; i++) {
            String[] values = new String[2];
            values[0] = Item.getRandomItem()[0]; //Get random item name
            values[1] = Integer.toString((int)(Math.random() * (100 - 1 + 1) + 1)); //Random Value, stored as String
            this.itemsBuy.add(values);
        }
    }

    public void openStore() {
        /**Display the options to buy and sell, we can either implement the choice controls in here, or in a seperate
         * fuction with this simply as the display.
        */
        displayStore();

        //TODO Finish implementing choices

        System.out.println("");
    }

    private void displayStore() {
        /**Print The Store Terminal */
        //Print items available to sell to player
        System.out.println("\nSTORE");
        System.out.println("Items for Sale:\tWallet: $" + GameEnvironment.game.getWallet());
        int i = 1;
        for (Item item : itemsSell) {
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
        //Check that both the store and the playerShip has the items, if they do then list them
        for (String[] itemBuy : itemsBuy) {
            for (Item item : GameEnvironment.game.getPlayerShip().getCargo()) {
                if (item.getName() == itemBuy[0]) {
                    //The following prints an orgonized grid
                    Object[] preFormat = new String[] {
                        "\t" + i + ": ",
                        item.getName() + ": " + item.getDescription(),
                        "Value: $" + item.getSalePrice(),
                        "Weight: " + item.getWeight() + "kg"
                    };
                    //Sizing can be changed by altering the % numbers below
                    String format = String.format("%-6s%-90s%-15s%-15s", preFormat);
                    System.out.println(format);
                    i++;
                }
            }
        }
    }

    public boolean sell(Item[] itemsToSell, Ship shipToLoad) {
        /**Sells items to the user if they have enough money in thier wallet*/

        //Check if there is enough funds
        int totalCost = 0;
        for (int i=0; i < itemsToSell.length; i++) {
            totalCost += itemsToSell[i].getPurchasePrice();
        }

        if (GameEnvironment.game.getWallet() < totalCost) {
            System.out.println("Sorry you do not have enough money to purchase these items.");
            return false;
        }

        //Try to add cargo to the ship
        boolean addedToShip = shipToLoad.addCargo(itemsToSell);


        //If user is able to purchase remove items from the store
        if (!addedToShip) {
            return false;
        } else {
            for (int i=0; i < itemsToSell.length; i++) {
                for (int j=0; j < itemsBuy.size(); j++) {
                    if (itemsBuy.get(j)[0] == itemsToSell[i].getName()) {
                        itemsBuy.remove(j);
                    }
                }
            }
            return true;
        }
    }

    public int purchase(Item[] itemsToBuy) {
        /**Buys items of the user*/

        int value = 0;
        return value;
    }
}
