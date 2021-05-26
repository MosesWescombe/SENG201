package core;

/**
 * Player class contains the players information. Including:
 * Name - Players Name
 * Wallet - Amount of money the player has
 * Ship - Players Ship object.
 */
public class Player {
    private String name;
    private int wallet;
    private Ship ship;

    /**
     * Creates a Player object.
     * 
     * @param name String, Name of Island
     * @param ship Ship, players ship
     * @param wallet int, Amount of money in the players wallet
     */
    public Player(String name, Ship ship, int wallet) {
        this.wallet = wallet;
        this.name = name;
        this.ship = ship;
    }

    /**
     * Changes the amount of money in the players wallet. A negative value subtracts.
     * 
     * @param amount Int, positive or negative amount to change the wallet by
     */
    public void changeWallet(int amount) {
        this.wallet += amount;
    }

    
    //Getters/Setters
    public String getName() {
        return this.name;
    }
    public int getWallet() {
        return this.wallet;
    }
    public void setWallet(int amount) {
        this.wallet = amount;
    }
    public Ship getShip() {
        return this.ship;
    }
}
