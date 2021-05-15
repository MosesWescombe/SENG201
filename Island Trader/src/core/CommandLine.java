package core;

public class CommandLine {
    /**Command line functions, anything that is only used for the command line section should be run from here
     * using number inputs as this is easier to link to gui later.
    */
    private static final int numberOfIslands = 5;
    public static Island[] islands = new Island[numberOfIslands];
    public static Ship playerShip;
    public static String playerName;
    public static int wallet;
    public static int gameDuration;

    public CommandLine() {
        /**Starts the game with required info*/
       
       //Set some intial values
       wallet = 100;
       playerName = "User";//Input.get("Choose your player name: ");
       gameDuration = 20; //Input.getNum("Choose game duration (days): ", 20, 50); // added upper and lower param to inputs with getNum - j

       //Create Ship
       String nameInput = "Dawn Tredder"; //Input.get("Name your ship: ");
       printOptions(Ship.getShipTypes()); //Print ship options
       int typeInput = 1; // Input.getNum("Choose your Ship type: ", 1, Ship.getShipTypes().length);
       playerShip = new Ship(nameInput, typeInput);

       //Set up Islands
       for (int i=0; i < numberOfIslands; i++) {
           islands[i] = new Island();
       }
       //Set starting Island
       playerShip.setLocation(islands[(int)(Math.random() * (islands.length))]);

       //Set up routes

       //Set up random Events?
    }

    public void printOptions(String[] array){
        /**Prints an array of options in order, numbered from 1 to n */
        for (int i=1; i <= array.length; i++) {
            System.out.println(Integer.toString(i) + ": " + array[i - 1]);
        }
    }

    public void closeGame() {
       /** Tidy up the game, close open objects */
       Input.closeInput();
    }
}