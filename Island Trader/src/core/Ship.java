package core; 

import java.util.ArrayList;

/**
 * Ship Class. A ship is what the player uses to travel between islands. The items that the player buys also gets stored in the ships cargo.
 * 
 * Name - Ship's name
 * Type - Type of ship, from SHIPTYPES
 * Crew - Number of crew aboard the ship
 * CrewWage - Cost per day per crew member
 * Capacity - Current total weight of the cargo
 * MaxCapacity - Maximum total weight of the cargo
 * Health - Current health of the ship
 * MaxHealth - Maximum health the ship can have
 * SailSpeed - Speed that the ship can travel in km/h
 * Location - Location that the ship is currently docked at
 * Cargo - ArrayList of items that the ship has in its cargo
 * Upgrades - ArrayList containing all the ships upgrades
 * TransactionHistory - ArrayList of transactions with stores
 */
public class Ship {
    private static final String[] SHIPTYPES = {
        "Cargo",
        "Battle",
        "Cruiser",
        "Dingy",
    };
    private String name;
    private String type;
    private int crew;
    private int crewWage;
    private int capacity;
    private int maxCapacity; 
    private int health;
    private int maxHealth;
    private int sailSpeed;
    private Island location;
    private ArrayList<Entity> cargo;
    private ArrayList<Upgrade> upgrades;
    private ArrayList<String[]> transactionHistory = new ArrayList<String[]>();

    /**
     * Create a ship object.
     * @param name String, Name of the ship
     * @param shipType int, 1-4 from SHIPTYPES
     */
    public Ship(String name, int shipType){
        this.name = name;
        this.cargo = new ArrayList<Entity>();
        this.upgrades = new ArrayList<Upgrade>();

        //Set the variables accoriding to the ship type
        switch(shipType) {
            case 1:
                this.type = SHIPTYPES[0] + "Ship";
                this.maxCapacity = 100; 
                this.maxHealth = 80;
                this.sailSpeed = 20;
                this.crewWage = 1;
                this.crew = 8;
                break;
            case 2:
                this.type = SHIPTYPES[1] + "Ship";
                this.maxCapacity = 50; 
                this.maxHealth = 100;
                this.sailSpeed = 30;
                this.crewWage = 2;
                this.crew = 6;
                break;
            case 3:
                this.type = SHIPTYPES[2] + "Ship";
                this.maxCapacity = 50; 
                this.maxHealth = 80;
                this.sailSpeed = 40;
                this.crewWage = 2;
                this.crew = 5;
                break;
            case 4:
                this.type = SHIPTYPES[3];
                this.maxCapacity = 10; 
                this.maxHealth = 10;
                this.sailSpeed = 10;
                this.crewWage = 0;
                this.crew = 2;
                break;
        }

        //Set ships current stats based of max stats
        this.health = this.maxHealth;
        this.capacity = this.maxCapacity;
    }

    /**
     * Add Cargo to the ship
     * 
     * @param item Entity, item to add
     */
    public void addCargo(Entity item) {
        //Update the ships cargo and capacity level
        this.cargo.add(item);
        this.capacity -= item.getWeight();

        if (item instanceof Upgrade) {
            addUpgrade((Upgrade)item);
        }

        //Update Transaction History
        String[] transaction = {item.getName(), item.getLocationOfStore().getName(), Integer.toString(item.getPurchasePrice()), "Not Applicable"};
        transactionHistory.add(transaction);
    }
    
    /**
     * Add upgrade to the ship, and apply the buffs if applicable.
     * 
     * @param upgrade Upgrade, upgrade to add
     */
    public void addUpgrade(Upgrade upgrade) {
    	this.upgrades.add(upgrade);

        int upgradeType = upgrade.getType();

        //Improve ship stats if applicable
        switch(upgradeType) {
            case 2:
                this.maxHealth += 50;
                this.health += 50;
                break;
            case 3:
                this.maxCapacity += 100;
                this.capacity += 100;
                break;
            case 4:
                this.sailSpeed += 15;
                break;
        }
    }

    /**
     * Removes an item from the ship's cargo. Updates the players wallet and the ships capacity.
     * If it is an upgrade it will also remove the upgrades properties.
     * 
     * @param item Entity, Item to remove
     */
    public void removeCargo(Entity item) {
        //Remove item from cargo
        for (int j=0; j < cargo.size(); j++) {
            if (cargo.get(j).getName() == item.getName()) {
                cargo.remove(j);
            }
        }

        //Remove items weight from capacity
        this.capacity += item.getWeight();

        //Remove from upgrades list
        if (item instanceof Upgrade) {
            removeUpgrade((Upgrade) item);
        }

        //Update Transaction History
        String[] transaction = {item.getName(), item.getLocationOfStore().getName(), "Not Applicable", Integer.toString(item.getSalePrice())};
        transactionHistory.add(transaction);
    }

    /**
     * Removes cargo from the cargo array, this is called during pirates event, so the transaction history is not updated.
     * This improves the other removeCargo option by accepting an arrayList of items.
     * 
     * @param cargoList ArrayList<Entity>, cargo to remove
     */
    public void removeCargo(ArrayList<Entity> cargoList) {
        //Update the ships cargo and capacity level
        for (Entity item : cargoList) {
            int index = this.cargo.indexOf(item);
            Entity itemTemp = this.cargo.get(index);
            this.capacity += itemTemp.getWeight(); //Remove the weight
            this.cargo.remove(index);

            //Remove from upgrades list
            if (item instanceof Upgrade) {
                removeUpgrade((Upgrade) item);
            }
        }
    }

    /**
     * Remove an upgrade from the upgrades list and remove any buffs.
     * 
     * @param upgrade Upgrade, upgrade to remove
     */
    private void removeUpgrade(Upgrade upgrade) {
        //Remove from upgrades array
        int index = this.upgrades.indexOf(upgrade);
        this.upgrades.remove(index);

        int upgradeType = upgrade.getType();

        //Remove ship stats
        switch(upgradeType) {
            case 2:
                this.maxHealth -= 50;
                this.health -= 50;
                break;
            case 3:
                this.maxCapacity -= 100;
                this.capacity -= 100;
                break;
            case 4:
                this.sailSpeed -= 15;
                break;
        }
    }

    /**
     * Remove health from the ship, end game if required.
     * 
     * @param amount int, Amount of health to remove
     */
    public void takeDamage(int amount) {
        this.health -= amount;

        //check ship destroyed
        if (this.health <= 0) {
            this.health = 0;
            GameEnvironment.exit("Game over, your ship is destroyed");
        }
    }

    /**
     * Repair the ship, and charge the user. This runs checks first.
     * The Game ends if the user cannot afford to repair.
     * 
     * @return String, Statement to print showing the cost of repair. No return if game ends.
     */
    public String repair() {
        /** Increases ships health, cant go above the ships maxHealth Variable */
        int difference = this.maxHealth - this.health;
        int cost = difference * 1;

        //If the player can afford to repair the ship, do so and charge the costs
        if (GameEnvironment.getPlayer().getWallet() >= cost) {
            GameEnvironment.getPlayer().changeWallet(-cost);
            this.health = this.maxHealth;

            return "Ship Repaired for $" + cost + ". You can now sail.";
        } else { 
            //Otherwise end the game
            GameEnvironment.exit("You do not have enough money to repair your ship. Unfortunatly you are now bankrupt.");
            return "";
        }
    }

    /**
     * Create an Object[][] for displaying in a JTable. This returns the upgrades in the form:
     * {Name, Description}
     * 
     * @return  Object[][] of strings detailing the upgrades
     */
    public Object[][] getUpgradeObjects() {
        Object[][] result = new Object[upgrades.size()][2];

        int i = 0;
        for (Upgrade upgrade : upgrades) {
            result[i][0] = upgrade.getName();
            result[i][1] = upgrade.getDescription();
            i++;
        }

        return result;
    }

        /**
     * Create an Object[][] holding an array of trasactions and their details.This is used to format Jtables. A transaction is represented by
     * {Item Name, Island's name of sale location, Purchase price (If applicable), Sale price (If applicable)}
     * 
     * @return Object[][] containing String information on the transactions
     */
    public Object[][] getTransactionHistoryObjects() {
        Object[][] result = new Object[transactionHistory.size()][4];

        //Loop through transactions and insert the information
        int i = 0;
        for (String[] transaction : transactionHistory) {
            result[i][0] = transaction[0];
            result[i][1] = transaction[1];
            result[i][2] = transaction[2];
            result[i][3] = transaction[3];
            i++;
        }

        return result;
    }

    /**
     * Create an Object[][] holding an array of items and their details. This is used to format Jtables. A cargo item is represeted by:
     * {Name, Description, Weight}
     * 
     * @return Object[][] containing String information on the cargo
     */
    public Object[][] getCargoObjects() {
        Object[][] result = new Object[cargo.size()][3];

        int i = 0;
        for (Entity item : cargo) {
            result[i][0] = item.getName();
            result[i][1] = item.getDescription();
            result[i][2] = item.getWeight();
            i++;
        }

        return result;
    }

    
    //Getters And Setters
    public String getName() {
        return name;
    }
    public int getCrew() {
        return crew;
    }
    public int getCrewWage() {
        return crewWage;
    }
    public int getMaxCapacity() {
        return maxCapacity;
    }
    public int getHealth() {
        return health;
    }
    public int getSailSpeed() {
        return sailSpeed;
    }
    public String getType() {
        return type;
    }
    public static String[] getSHIPTYPES() {
        return SHIPTYPES;
    }
    public ArrayList<Entity> getCargo() {
        return cargo;
    }
    public Island getLocation() {
        return location;
    }
    public void setLocation(Island location) {
        this.location = location;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public void setMaxCapacity(int capacity) {
        this.maxCapacity = capacity;
    }
    public ArrayList<Upgrade> getUpgrades() {
        return upgrades;
    }
    public int getMaxHealth() {
        return maxHealth;
    }
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
    public ArrayList<String[]> getTransactionHistory() {
        return transactionHistory;
    }
}