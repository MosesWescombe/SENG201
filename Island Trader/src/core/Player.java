package core;

public class Player {
    private String name;
    private int wallet;
    private Ship ship;

    public Player(String name, Ship ship, int wallet) {
        this.wallet = wallet;
        this.name = name;
        this.ship = ship;
    }

    public String getName() {
        return name;
    }

    public int getWallet() {
        return wallet;
    }

    public void changeWallet(int amount) {
        this.wallet += amount;
    }

    public Ship getShip() {
        return ship;
    }
}
