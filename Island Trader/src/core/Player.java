package core;

public class Player {
    private String name;
    private int wallet;
    private Ship ship;

    public Player() {
        this.wallet = 1000;
        this.name = "User";//Input.get("Choose your player name: ");
        
        //-------------Create Ship
       String nameInput = "Dawn Tredder"; //Input.get("Name your ship: ");
       printOptions(Ship.getShipTypes()); //Print ship options
       int typeInput = 1; // Input.getNum("Choose your Ship type: ", 1, Ship.getShipTypes().length);
       this.ship = new Ship(nameInput, typeInput);
    }

    private void printOptions(String[] array){
        /**Prints an array of options in order, numbered from 1 to n*/
        for (int i=1; i <= array.length; i++) {
            System.out.println(Integer.toString(i) + ": " + array[i - 1]);
        }
    }

    public String getName() {
        return name;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public Ship getShip() {
        return ship;
    }
}
