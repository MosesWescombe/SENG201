package core;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JLabel;
import gui.EndWindow;
import gui.MainWindow;
import gui.StartMenuWindow;

/**
 * Trader Game.
 * 
 * In this game the user will travel around different islands and trade goods in attempt to reach a high score.
 * The GameEnvironment is the entry point to the program and all other classes can be accessed statically through this. All key intitalizing and functions are a part of this class.
 * 
 * @author Moses Wescombe (mwe50) & Jeremy Roberts (jro162)
 * @version 1.0 25/05/2021
*/
public class GameEnvironment {
    //Game and Time objects, static so that they can be accessed anywhere
    private static Time time;
    private static ArrayList<Island> islands = new ArrayList<Island>();
    private static Player player;
    private static Ship playerShip;

    /**
     * Entry Point
     * 
     * @param args System run information
     */
    public static void main(String[] args) {
        //Initialize game environment
        new StartMenuWindow();
    }

    /**
     * Test If the users input is of the correct formatt.
     * 
     * @param message JLabel, Reference to the message label to display if incorrect
     * @param input String, Users input
     * @return boolean, true if the tests pass, false otherwise
     */
    public static boolean checkNameInput(JLabel message, String input) {
		//Check no special characters or double spaces
		Pattern pattern = Pattern.compile("[a-z]+[ [a-z]+]*", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(input);
		boolean matchFound = matcher.matches();
		//Check Length
		boolean correctLength = !(input.length() < 3 || input.length() > 15);

		if ( !correctLength || !matchFound) {
			message.setVisible(true);
			return false;
		} else {
			message.setVisible(false);
			return true;
		}
	}

    /**
     * Closes the start menu, sets all the starting variables before opening the main window.
     * 
     * @param playerName Users input for player name
     * @param playerShipName Users input for ship name
     * @param shipType Users selection for ship type, between 1-4
     * @param gameDuration Users selection for game duration, between 20-50
     */
    public static void setUpGame(String playerName, String playerShipName, int shipType, int gameDuration) {
        //Set up all other classes using the new variables as parameters
        time = new Time(gameDuration);
        setUpPlayer(playerName, playerShipName, shipType);
        setUpIslands();
        setUpRoutes();

        //Move on to the main window
        new MainWindow();
    }

    /**
     * Creates The player class and players ship class.
     * 
     * @param playerName String, users chosen player name
     * @param shipName String, users chosen ship name
     * @param shipType int, Number from 1-4 defining the type of ship.
     */
    public static void setUpPlayer(String playerName, String shipName, int shipType) {
       //Create Ship
       playerShip = new Ship(shipName, shipType);

       //Create Player
       player = new Player(playerName, playerShip, 1000);

    }

    /**
     * Set up Island objects and with each Island having its own Store object. These are hard coded and represent the map locations. 
     */
    public static void setUpIslands() {
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

    /**
     * Set up routes by adding them to the Islands, these are hard coded and represent the paths between locations.
     * Routes are stored in the destination island.
     */
    public static void setUpRoutes() {
        //Route 1
        islands.get(0).addRoute(new Route(
            islands.get(1),
            islands.get(0),
            1000,
            "Short And 'SNAPPY'",
            0.8
        ));

        //Route 2
        islands.get(1).addRoute(new Route(
            islands.get(2),
            islands.get(1),
            2000,
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
            4000,
            "Coastal Hugger",
            0.1
        ));

        //Route 6
        islands.get(2).addRoute(new Route(
            islands.get(4),
            islands.get(2),
            3000,
            "Average Mile",
            0.5
        ));
        
      //Route 7
        islands.get(0).addRoute(new Route(
            islands.get(3),
            islands.get(0),
            5000,
            "The long way round",
            0.5
        ));
    }
    
    /**
     * Changes the Ships location and manages time events along the given route.
     * Returns "NO_ROUTE" if there is no route
     * 
     * @param destination The island at the end of the route
     * @return  Information on the event that occurs (if any)
     */
    public static String sailToIsland(Island destination) {
        //Find route based on the current ships location and the destination given
        Route currentRoute = null;
        for (Route route : destination.getRoutes()) {
            //If the islands match
            if (player.getShip().getLocation().getName() == route.getOrigin().getName()) {
                currentRoute = route;
                break;
            }
        }

        if (currentRoute == null) {
            return "NO_ROUTE";
        }

        //Consume days and run all necisarry functions involved
        for (int j=0; j < currentRoute.getDistance() / player.getShip().getSailSpeed() / 24; j++) {
            if (!time.endDay()) {
            	return "END";
            }
        }

        //Change ships location
        player.getShip().setLocation(currentRoute.getDestination());

        //Check for events
        return Event.checkEvent(currentRoute.getEventChance());
    }

    /**
     * Return to a new updated MainWindow 
     */
    public static void returnToMenu() {
        new MainWindow();
    }

    /**
     * Exit the game, this will be called when the game ends.
     * 
     * @param message Message to display on the final screen.
     */
    public static void exit(String message) {
        new EndWindow(message);
    }


    //Getters/Setters
    public static Time getTime() {
        return time;
    }
    public static ArrayList<Island> getIslands() {
        return islands;
    }
    public static Player getPlayer() {
        return player;
    }
    public static Ship getPlayerShip() {
        return playerShip;
    }
}
