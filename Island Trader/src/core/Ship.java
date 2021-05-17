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
    private ArrayList<Item> cargo;
    //private Upgrades[] upgrades;
    private Island location;


    //-----------Methods

    //Constructor
    public Ship(String name, int shipType){
        /** Creates the ship taking in a Name and Type, Type can be accessed by ShipTypes.<type> */
        this.name = name;
        this.cargo = new ArrayList<Item>();

        //Set the type of the ship
        switch(shipType) {
            //TODO, make changes to ship types to better suite the game
            //Also add variables for cargo upgrades and location
            case 1:
                this.type = shipTypes[0] + "Ship";
                this.maxCapacity = 100; 
                this.maxHealth = 80;
                this.sailSpeed = 60;
                this.crewWage = 50;
                this.crew = 80;
                break;
            case 2:
                this.type = shipTypes[1] + "Ship";
                this.maxCapacity = 50; 
                this.maxHealth = 100;
                this.sailSpeed = 80;
                this.crewWage = 80;
                this.crew = 60;
                break;
            case 3:
                this.type = shipTypes[2] + "Ship";
                this.maxCapacity = 50; 
                this.maxHealth = 80;
                this.sailSpeed = 100;
                this.crewWage = 70;
                this.crew = 50;
                break;
            case 4:
                this.type = shipTypes[3] + "Ship";
                this.maxCapacity = 10; 
                this.maxHealth = 10;
                this.sailSpeed = 10;
                this.crewWage = 0;
                this.crew = 10;
                break;
        }

        this.health = this.maxHealth;
        this.capacity = 0;
    }

    public boolean addCargo(Item[] cargo) {
        /**Add cargo, if there is enough space available, else return false */

        //Check all the items fit
        int totalWeight = this.capacity;
        for (int i=0; i < cargo.length; i++) {
            totalWeight += cargo[i].getWeight();
        }
        //If not return false and add nothing
        if (totalWeight > this.maxCapacity) {
            System.out.println("Sorry you do not have enough room for this cargo.");
            return false;
        }

        //Update the ships cargo and capacity level
        for (int i=0; i < cargo.length; i++) {
            this.cargo.add(cargo[i]);
        }
        this.capacity = totalWeight;

        return true;
    }

    public void takeDamage(int amount) {
        /** Decreases the ships health, cant go below 0 */
        this.health -= amount;
        this.health = Math.max(this.health, 0);

        //TODO add death handling if this is required
    }

    public void repair(int amount) {
        /** Increases ships health, cant go above the ships maxHealth Variable */
        this.health += amount;
        this.health = Math.min(this.health, this.maxHealth);
    }

    //TODO Add methods for other currently unimplemented classes (Items, upgrades, islands..)


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

    public ArrayList<Item> getCargo() {
        return cargo;
    }

    public Island getLocation() {
        return location;
    }

    public void setLocation(Island location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Name: " + name +
            "\n\tType: " + type + 
            "\n\tHealth: " + health + "/" + maxHealth +
            "\n\tCrew Size: " + crew + " Daily Wage: " + crewWage +
            "\n\tRemaining cargo capacity: " + (maxCapacity - capacity);
    }

    
}