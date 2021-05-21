package core;
import java.util.ArrayList;

public class CommandLine {
    /**Command line functions, anything that is only used for the command line section should be run from here
     * using number inputs as this is easier to link to gui later.
    */
    private ArrayList<Island> islands = new ArrayList<Island>();
    private Player player;
    private int gameDuration;

    public CommandLine() {
        /**Starts the game with required info*/
       setUpPlayer();
       setUpIslands();
       setUpRoutes();
    }

    private void setUpPlayer() {
        //Set some intial values
       String name = "User";//Input.get("Choose your player name: ");
       int initialWallet = 1000;
        
        //-------------Create Ship
       String nameInput = "Dawn Tredder"; //Input.get("Name your ship: ");
       printOptions(Ship.getShipTypes()); //Print ship options
       int typeInput = 3; // Input.getNum("Choose your Ship type: ", 1, Ship.getShipTypes().length);
       Ship ship = new Ship(nameInput, typeInput);

       //Create Player
       player = new Player(name, ship, initialWallet);
    }

    private void setUpIslands() {
        /**Create Islands and Stores */
        Island tempIsland;

        //Island 1
        tempIsland = new Island("Nassau");
        tempIsland.setStore(new Store(tempIsland));
        islands.add(tempIsland);

        //Island 2
        tempIsland = new Island("Timplore");
        tempIsland.setStore(new Store(tempIsland));
        islands.add(tempIsland);

        //Island 3
        tempIsland = new Island("Ugriad");
        tempIsland.setStore(new Store(tempIsland));
        islands.add(tempIsland);

        //Island 4
        tempIsland = new Island("St. Gerbal");
        tempIsland.setStore(new Store(tempIsland));
        islands.add(tempIsland);

        //Island 5
        tempIsland = new Island("Lucia");
        tempIsland.setStore(new Store(tempIsland));
        islands.add(tempIsland);

        //Set starting Island
        player.getShip().setLocation(islands.get((int)(Math.random() * (islands.size()))));
    }

    private void setUpRoutes() {
        /**Create The routes between islands */
        //Route 1
        islands.get(0).addRoute(new Route(
            islands.get(1),
            islands.get(0),
            1500,
            "Short And 'SNAPPY'",
            0.8
        ));

        //Route 2
        islands.get(1).addRoute(new Route(
            islands.get(2),
            islands.get(1),
            2500,
            "Long and un-eventful",
            0.2
        ));

        //Route 3
        islands.get(2).addRoute(new Route(
            islands.get(3),
            islands.get(2),
            2000,
            "Roller Coaster",
            0.6
        ));

        //Route 4
        islands.get(3).addRoute(new Route(
            islands.get(4),
            islands.get(3),
            3000,
            "Major Tradeing Route",
            0.4
        ));

        //Route 5
        islands.get(4).addRoute(new Route(
            islands.get(0),
            islands.get(4),
            3000,
            "Coastal Hugger",
            0.1
        ));

        //Route 6
        islands.get(2).addRoute(new Route(
            islands.get(4),
            islands.get(2),
            3500,
            "Average Mile",
            0.5
        ));
    }

    private void printOptions(String[] array){
        /**Prints an array of options in order, numbered from 1 to n*/
        for (int i=1; i <= array.length; i++) {
            System.out.println(Integer.toString(i) + ": " + array[i - 1]);
        }
    }

    public void closeCommandLine() {
       /** Tidy up the game, close open objects */
       Input.closeInput();
    }

    public int getGameDuration() {
        return gameDuration;
    }

    public ArrayList<Island> getIslands() {
        return islands;
    }

    public Player getPlayer() {
        return player;
    }
}