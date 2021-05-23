package core;
import java.util.ArrayList;

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

    public void openStore() {
        /**Display the options to buy and sell, we can either implement the choice controls in here, or in a seperate
         * fuction with this simply as the display.
        */

        //Display Store
        displayStore();

        //Get User Choice
        int userIn = Input.getNum("\n0: Exit Store\n" +
        "1: Buy\n" +
        "2: Sell\n" + 
        "Choice: ", 0, 2);

        switch(userIn) {
            case 0:
                //Exit
                break;
            case 1:
                //Player wants to buy items
                sell();
                break;
            case 2:
                //Player wants to sell items
                purchase();
                break;
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
        Object[][] result = new Object[itemsSell.size()][4];
        int i = 0;
        for (Entity item : itemsSell) {
            result[i][0] = item.getItemDetails()[0];
            result[i][1] = item.getItemDetails()[1];
            result[i][2] = item.getItemDetails()[2];
            result[i][3] = item.getItemDetails()[3];
            i++;
        }

        return result;
    }

    public Object[][] getItemsBuyObjects() {
        Object[][] result = new Object[itemsSell.size()][4];
        int i = 0;
        for  (Entity item : GameEnvironment.game.getPlayer().getShip().getCargo()) {
            for (String[] itemBuy : itemsBuy) {
                if (item.getName() == itemBuy[0]) {
                    result[i][0] = item.getItemDetails()[0];
                    result[i][1] = item.getItemDetails()[1];
                    result[i][2] = item.getItemDetails()[2];
                    result[i][3] = item.getItemDetails()[3];
                }
            }
        }

        return result;
    }

    public void sell() {
        /**Sells items to the user if they have enough money in thier wallet*/
        ArrayList<Entity> cart = new ArrayList<Entity>();
        int totalCost = 0;
        int totalWeight = 0;

        //Ask for item selection
        while (true) {
            int playerWallet = GameEnvironment.game.getPlayer().getWallet();
            int playerCapacity = GameEnvironment.game.getPlayer().getShip().getCapacity();

            //Get Player Selection
            int playerIn = Input.getNum("Select an item or upgrade (0 when complete): ", 0, itemsSell.size());

            if (playerIn == 0) {
                //Exit Store
                break;
            } else {
                //Try to add item to the selectedItem, if the item is affordable and can fit on the ship
                Entity selectedItem = itemsSell.get(playerIn - 1);
                //If player has enough money
                if (totalCost + selectedItem.getPurchasePrice() <= playerWallet) {
                    //If player has enough cargo space
                    if (totalWeight + selectedItem.getWeight() <= playerCapacity) {
                        //If its an upgrade add it directly to the ship
                        if (selectedItem instanceof Upgrade) {
                            //Add upgrade to the ship
                            GameEnvironment.game.getPlayer().getShip().addUpgrade((Upgrade)selectedItem);
                        }
                            
                        cart.add(selectedItem);
                        totalWeight += selectedItem.getWeight();
                        totalCost += selectedItem.getPurchasePrice();
                    } else {
                        System.out.println("Sorry, not enough room for this Item.");
                    }
                }  else {
                    System.out.println("Sorry, not enough money for this Item.");
                }
            }
        }


        Ship shipToLoad = GameEnvironment.game.getPlayer().getShip();

        //Add cargo to the ship
        shipToLoad.addCargo(cart);

        //Remove item from Store
        for (Entity item : cart) {
            for (int j=0; j < itemsSell.size(); j++) {
                if (itemsSell.get(j).getName() == item.getName()) {
                    GameEnvironment.game.getPlayer().changeWallet(-item.getPurchasePrice()); //Take money from wallet
                    itemsSell.remove(j);
                }
            }
        }
    }

    public void purchase() {
        /**Buys items off the user*/
        ArrayList<Entity> cart = new ArrayList<Entity>();
        Ship playersShip = GameEnvironment.game.getPlayer().getShip();

        //Ask for item selection
        while (true) {
            //Get Player Selection
            int playerIn = Input.getNum("Select an item (0 when complete): ", 0, playersShip.getCargo().size());

            if (playerIn == 0) {
                //Exit Store
                break;
            } else {
                //Check the number is in the range of cargo items
                if (playerIn <= playersShip.getCargo().size()) {
                    //Add item to cart
                    Entity selectedItem = playersShip.getCargo().get(playerIn);
                    cart.add(selectedItem);
                } else {
                    System.out.println("Sorry choose a number from the range above.");
                }
            }
        }

        //Remove cargo from the ship
        playersShip.removeCargo(cart);
        //Add cargo to the store
        for (Entity item : cart) {
            item.setLocationOfStore(this.location);
            //itemsSell.add(item);
            GameEnvironment.game.getPlayer().changeWallet(item.getSalePrice()); //Add profits to wallet
        }
    }
}
