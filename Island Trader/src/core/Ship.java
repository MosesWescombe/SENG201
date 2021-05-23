package core; 
import java.util.ArrayList;

public class Ship {
    /**Ship object class, contains all ship status variables and methods that interact directly 
     * with them.
     */
    //------------Variables
    private static final String[] shipTypes = {
        "Cargo",
        "Battle",
        "Cruiser",
        "Dingy",
    };
    private final String name;
    private String type;
    private int crew;
    private int crewWage;
    private int maxCapacity; 
    private int capacity;
    private int health;
    private int maxHealth;
    private int sailSpeed;
    private ArrayList<Entity> cargo;
    private ArrayList<Upgrade> upgrades;
    private Island location;
    private ArrayList<String[]> transactionHistory = new ArrayList<String[]>();


    //-----------Methods

    //Constructor
    public Ship(String name, int shipType){
        /** Creates the ship taking in a Name and Type, Type can be accessed by ShipTypes.<type> */
        this.name = name;
        this.cargo = new ArrayList<Entity>();
        this.upgrades = new ArrayList<Upgrade>();

        //Set the type of the ship
        switch(shipType) {
            //Also add variables for cargo upgrades and location
            case 1:
                this.type = shipTypes[0] + "Ship";
                this.maxCapacity = 100; 
                this.maxHealth = 80;
                this.sailSpeed = 20;
                this.crewWage = 10;
                this.crew = 8;
                break;
            case 2:
                this.type = shipTypes[1] + "Ship";
                this.maxCapacity = 50; 
                this.maxHealth = 100;
                this.sailSpeed = 30;
                this.crewWage = 15;
                this.crew = 6;
                break;
            case 3:
                this.type = shipTypes[2] + "Ship";
                this.maxCapacity = 50; 
                this.maxHealth = 80;
                this.sailSpeed = 40;
                this.crewWage = 15;
                this.crew = 5;
                break;
            case 4:
                this.type = shipTypes[3];
                this.maxCapacity = 10; 
                this.maxHealth = 10;
                this.sailSpeed = 10;
                this.crewWage = 5;
                this.crew = 2;
                break;
        }

        this.health = this.maxHealth;
        this.capacity = this.maxCapacity;
    }

    public void displayTransactions() {
        /**Print the history of all transactions made so far in the game */
        System.out.println("\nTransaction History:");

        //Print all Transactions
        for (String[] transaction : transactionHistory) {
            Object[] preFormat;
            if (transaction.length > 2) {
                //If the item was Sold:
                preFormat = new String[] {
                    "\t" + transaction[0],
                    "Purchase Price: $" + transaction[1],
                    "Sale Price: $" + transaction[2]
                };

                System.out.println(String.format("%-55s%-55s%-30s", preFormat));
            } else {
                //The Item was bought
                preFormat = new String[] {
                    "\t" + transaction[0],
                    "Purchase Price: $" + transaction[1]
                };

                System.out.println(String.format("%-55s%-55s", preFormat));
            }
        }
    }

    public void addCargo(Entity item) {
        /**Add cargo to the ship and update capacity*/
        
        //Update the ships cargo and capacity level

        this.cargo.add(item);
        this.capacity -= item.getWeight();

        //Update Transaction History
        String[] transaction = {item.getName(), Integer.toString(item.getPurchasePrice())};
        transactionHistory.add(transaction);
    }
    
    public void addUpgrade(Upgrade upgrade) {
        /**Add upgrade to the ship */
    	this.upgrades.add(upgrade);

        int upgradeType = upgrade.getType();

        //Improve ship stats
        switch(upgradeType) {
            case 4:
                this.maxCapacity += 50;
                this.capacity += 50;
                break;
            case 5:
                this.sailSpeed += 15;
                break;
        }
    }

    public void removeCargo(Entity item) {
        /**Remove cargo from the ship and update capacity and players wallet*/

        //Update the ships cargo and capacity level
        int index = this.cargo.indexOf(item);
        Entity itemTemp = this.cargo.get(index);
        this.capacity += itemTemp.getWeight(); //Remove the weight

        //Update Transaction History
        String[] transaction = {itemTemp.getName(), Integer.toString(itemTemp.getPurchasePrice()), Integer.toString(itemTemp.getSalePrice())};
        transactionHistory.add(transaction);
        this.cargo.remove(index);
    }

    public void removeCargo(ArrayList<Entity> cargoList, boolean pirates) {
        /**Remove cargo from the ship, do not update transaction as this was pirates*/
        if (!pirates) {
            return;
        }
        
        //Update the ships cargo and capacity level
        for (Entity item : cargoList) {
            int index = this.cargo.indexOf(item);
            Entity itemTemp = this.cargo.get(index);
            this.capacity += itemTemp.getWeight(); //Remove the weight

            this.cargo.remove(index);

            //Remove from upgrades list
            if (item instanceof Upgrade) {
                Upgrade upgrade = (Upgrade)item;
                index = this.upgrades.indexOf(upgrade);
                this.upgrades.remove(index);

                int upgradeType = upgrade.getType();

                //Remove ship stats
                switch(upgradeType) {
                    case 4:
                        this.maxCapacity -= 50;
                        break;
                    case 5:
                        this.sailSpeed -= 15;
                        break;
                }
            }
        }
    }

    public void takeDamage(int amount) {
        /** Decreases the ships health, game ends if you have no health */
        this.health -= amount;

        //check ship destroyed
        if (this.health <= 0) {
            System.out.println("Game over, your ship is destroyed");
            GameEnvironment.exit();
        }
    }

    public void repair() {
        /** Increases ships health, cant go above the ships maxHealth Variable */
        int difference = this.maxHealth - this.health;
        int cost = difference * 10;

        //If the player can afford to repair the ship, do so and charge the costs
        if (GameEnvironment.game.getPlayer().getWallet() >= cost) {
            GameEnvironment.game.getPlayer().changeWallet(cost);
            this.health = this.maxHealth;

            System.out.println("Ship Repaired for $" + cost + ". You can now sail.");
        } else { 
            //Otherwise end the game
            System.out.println("You do not have enough money to repair your ship. Unfortunatly you are now bankrupt.");
            GameEnvironment.exit();
        }
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

    public int getmaxCapacity() {
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

    public static String[] getShipTypes() {
        return shipTypes;
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

    public ArrayList<Upgrade> getUpgrades() {
        return upgrades;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    @Override
    public String toString() {
        String format = "Name: " + name +
            "\n\tType: " + type + 
            "\n\tHealth: " + health + "/" + maxHealth +
            "\n\tCrew Size: " + crew + "\tDaily Wage: $" + crewWage +
            "\n\tShip Speed: " + sailSpeed + "km/h"+
            "\n\tRemaining cargo capacity: " + capacity + "/" + maxCapacity + "kg" +
            "\n\tCargo: ";

        for (Entity item : cargo) {
            Object[] preFormat = new String[] {
                "\n\t\t" + item.getName(),
                "\tPurchase Price: $" + item.getPurchasePrice()
            };

            //Sizing can be changed by altering the % numbers below
            format += String.format("%-55s%-60s", preFormat);
        }

        return format;
    }

    
}