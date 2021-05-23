package core;
import gui.MainWindow;
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

    private static void sailToIsland() {
        /**Sail to a new Island, consume days and check for events*/
//
//        if (game.getPlayer().getShip().getHealth() < game.getPlayer().getShip().getMaxHealth()) {
//            System.out.println("Your ship needs repairs.");
//            game.getPlayer().getShip().repair();
//        }
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
//        //Get Users selection
//        System.out.println("");
//        int i = 1;
//        for (Route route : routesFrom) {
//            System.out.println("\n" + i + ": " + route.viewRoute());
//            i++;
//        }
//        int userIn = Input.getNum("\nSelect Route (0 for exit): ", 0, routesFrom.size());
//
//        //Pass days
//        for (int j=0; j < routesFrom.get(userIn - 1).getDistance() / game.getPlayer().getShip().getSailSpeed() / 24; j++) {
//            time.endDay();
//        }
//
//        //Check for events
//        Event.checkEvent(routesFrom.get(userIn - 1).getEventChance());
//
//        //Change ships location
//        game.getPlayer().getShip().setLocation(routesFrom.get(userIn - 1).getDestination());
    }

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

    private static void viewTransactions() {
        /**View previous transitions. A Transition is made when cargo is added or removed from the ships */
        game.getPlayer().getShip().displayTransactions();
    }

    public static void exit() {
        /**Exit Game and clean up */
        System.out.println("Game Ended");

        //Display Exit Screen

        game.closeCommandLine();
        System.exit(0);
    }
}
