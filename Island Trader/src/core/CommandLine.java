package core;
import java.util.ArrayList;
import java.util.Collections;

public class CommandLine {
    /**Command line functions, anything that is only used for the command line section should be run from here
     * using number inputs as this is easier to link to gui later.
    */
    private final int numberOfIslands = 5;
    private ArrayList<Island> islands = new ArrayList<Island>();
    private Player player;
    private int gameDuration;

    public CommandLine() {
        /**Starts the game with required info*/
       
       //Set some intial values
       player = new Player();
       gameDuration = 20; //Input.getNum("Choose game duration (days): ", 20, 50); // added upper and lower param to inputs with getNum - j
       
       //-------------Set up Islands
       for (int i=0; i < numberOfIslands; i++) {
           islands.add(new Island());
       }
       //Set starting Island
       player.getShip().setLocation(islands.get((int)(Math.random() * (islands.size()))));


       //-------------Set up routes
       Collections.shuffle(islands); //Shuffle Island Order
       for (int i=0; i < islands.size() - 1; i++) { //Create minimim routes
           //Route forwards
           Route route = new Route(islands.get(i), islands.get(i+1));
           islands.get(i + 1).addRoute(route);
       }
       
       //Add extra route to complete loop
       Route r = new Route(islands.get(islands.size() - 1), islands.get(0)); //Final loop back
       islands.get(0).addRoute(r);

       //Generate any extra routes randomly
       int extraRoutes = 2;
       for (int i=0; i < extraRoutes; i++) {
        int origin = (int)(Math.random() * (islands.size()));
        int destination = (int)(Math.random() * (islands.size()));
        //Make sure the random numbers aren't the same
        if (origin == destination) {
            if (origin + 1 < islands.size()) {
            	origin++;
            } else {
            	origin--;
            }
        }
        Route route = new Route(islands.get(origin), islands.get(destination));
        islands.get(destination).addRoute(route);
       }

       
       
       //Set up random Events?
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