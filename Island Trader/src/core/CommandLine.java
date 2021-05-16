package core;
import java.util.ArrayList;
import java.util.Collections;

public class CommandLine {
    /**Command line functions, anything that is only used for the command line section should be run from here
     * using number inputs as this is easier to link to gui later.
    */
    private static final int numberOfIslands = 5;
    public static ArrayList<Island> islands = new ArrayList<Island>();
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


       //-------------Create Ship
       String nameInput = "Dawn Tredder"; //Input.get("Name your ship: ");
       printOptions(Ship.getShipTypes()); //Print ship options
       int typeInput = 1; // Input.getNum("Choose your Ship type: ", 1, Ship.getShipTypes().length);
       playerShip = new Ship(nameInput, typeInput);

       
       //-------------Set up Islands
       for (int i=0; i < numberOfIslands; i++) {
           islands.add(new Island());
       }
       //Set starting Island
       playerShip.setLocation(islands.get((int)(Math.random() * (islands.size()))));


       //-------------Set up routes
       Collections.shuffle(islands); //Shuffle Island Order
       for (int i=0; i < islands.size() - 1; i++) { //Create minimim routes
           //Route forwards
           Route route = new Route(islands.get(i), islands.get(i+1));
           islands.get(i).addRoute(route);
       }
       Route r = new Route(islands.get(islands.size() - 1), islands.get(0)); //Final loop back
       islands.get(islands.size() - 1).addRoute(r);

       //Generate any extra routes randomly
       int extraRoutes = 1;
       for (int i=0; i < extraRoutes; i++) {
        int num1 = (int)(Math.random() * (islands.size()));
        int num2 = (int)(Math.random() * (islands.size()));
        //Make sure the random numbers aren't the same
        if (num1 == num2) {
            if (num1 + 1 < islands.size()) {
                num1++;
            } else {
                num1--;
            }
        }
        Route route = new Route(islands.get(num1), islands.get(num2));
        islands.get(i).addRoute(route);
       }


       //Set up random Events?
    }

    public void printOptions(String[] array){
        /**Prints an array of options in order, numbered from 1 to n*/
        for (int i=1; i <= array.length; i++) {
            System.out.println(Integer.toString(i) + ": " + array[i - 1]);
        }
    }

    public void closeGame() {
       /** Tidy up the game, close open objects */
       Input.closeInput();
    }
}