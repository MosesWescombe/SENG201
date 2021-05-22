package core;
import java.util.ArrayList;

public class GuiController {
    /**Command line functions, anything that is only used for the command line section should be run from here
     * using number inputs as this is easier to link to gui later.
    */
    private ArrayList<Island> islands = new ArrayList<Island>();
    private Player player;
    private int gameDuration;

    public GuiController(String playerName, String shipName, int shipType) {
        /**Starts the game with required info*/
       setUpPlayer(playerName, shipName, shipType);
       setUpIslands();
       setUpRoutes();
    }

    private void setUpPlayer(String playerName, String shipName, int shipType) {
        /**Create the player and ship */
       //Create Ship
       Ship ship = new Ship(shipName, shipType);

       //Create Player
       player = new Player(playerName, ship, 1000);
    }

    private void setUpIslands() {
        /**Create Islands and Stores */
        Island tempIsland;

        //Island 1
        tempIsland = new Island("Nassau");
        tempIsland.setStore(new Store(tempIsland, new Upgrade(0, tempIsland)));
        islands.add(tempIsland);

        //Island 2
        tempIsland = new Island("Timplore");
        tempIsland.setStore(new Store(tempIsland, new Upgrade(1, tempIsland)));
        islands.add(tempIsland);

        //Island 3
        tempIsland = new Island("Ugriad");
        tempIsland.setStore(new Store(tempIsland, new Upgrade(2, tempIsland)));
        islands.add(tempIsland);

        //Island 4
        tempIsland = new Island("St. Gerbal");
        tempIsland.setStore(new Store(tempIsland, new Upgrade(3, tempIsland)));
        islands.add(tempIsland);

        //Island 5
        tempIsland = new Island("Lucia");
        tempIsland.setStore(new Store(tempIsland, new Upgrade(4, tempIsland)));
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