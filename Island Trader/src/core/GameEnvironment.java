package core;
import java.util.ArrayList;

import gui.MainWindow;
import gui.SailScreen;
import gui.ViewIslands;
import gui.StartMenu;
import gui.StoreWindow;

public class GameEnvironment {
    /** Main game environment that holds all key functionality, Application entry point is here.
     * this can also be thought of as the player class.
     */
    public static GuiController game;
    public static Time time;

    public static void main(String[] args) {
        /** Main game loop, run methods from here */

        //Initialize game environment
        new StartMenu();
    }

    public static void closeStartMenu(StartMenu state) {
        /**This runs when the start menu closes*/
        String playerName = state.getPlayerNameInput().getText();
        String playerShipName = state.getShipNameInput().getText();
        int shipType = state.getShipType();
        int gameDuration = state.getGameDuration().getValue();


        game = new GuiController(playerName, playerShipName, shipType);
        time = new Time(gameDuration);

        state.closeWindow();
        new MainWindow();
    }

    public static void closeMainScreen(MainWindow state) {
        state.closeWindow();
    }
    public static void closeViewIslandsWindow(ViewIslands state) {
    	state.closeWindow();
    }

//    private static void sailToIsland(Route route) {
//        /**Sail to a new Island, consume days and check for events*/
//
//    	ArrayList<Route> routesFrom = new ArrayList<Route>();
//
//        //Create a list of routes from the current island
//        for (Island island : game.getIslands()) {
//            for (Route route : island.getRoutes()) {
//                if (route.getOrigin() == game.getPlayer().getShip().getLocation()) {
//                    routesFrom.add(route);
//                }
//            }
//        }
//
//        //Pass days
//        //for (int j=0; j < routesFrom.get(userIn - 1).getDistance() / game.getPlayer().getShip().getSailSpeed() / 24; j++) {
//        //    time.endDay();
//        //}
//
//        //Check for events
//        //Event.checkEvent(routesFrom.get(userIn - 1).getEventChance());
//
//        //Change ships location
//        game.getPlayer().getShip().setLocation(routesFrom.get(userIn - 1).getDestination());
//    }

    public static void openStoreWindow() {
        /**Visit Current Islands Store*/
        new StoreWindow();
    }

    public static void returnToMenu() {
        new MainWindow();
    }

    public static void viewIslands(MainWindow state) {
    	/**opens the ViewIsland window and displays island info */
    	new ViewIslands();
    	state.closeWindow();
    }
    
    public static void sailScreen(MainWindow state) {
    	/**opens the sail window and avalible islands to sail to*/
    	new SailScreen();
    	state.closeWindow();
    }

    public static void exit() {
        /**Exit Game and clean up */
        System.out.println("Game Ended");

        //Display Exit Screen

        game.closeCommandLine();
        System.exit(0);
    }
}
