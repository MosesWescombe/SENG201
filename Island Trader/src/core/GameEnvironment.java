package core;

import java.util.ArrayList;
import java.util.Arrays;

public class GameEnvironment {
    /** Main game environment that holds all key functionality, Application entry point is here.
     * this can also be thought of as the player class.
     */
    public static CommandLine game;
    public static Time time;
    private static final ArrayList<String> CHOICES = new ArrayList<String>(Arrays.asList(
        "Exit",
        "View Ballance",
        "View ship properties",
        "View Purchased Items",
        "View Islands",
        "Visit Store",
        "Sail To New Island"
    ));

    public static void main(String[] args) {
        /** Main game loop, run methods from here */

        //Initialize game environment
        game = new CommandLine();
        time = new Time();
        

        //Run Game Loop
        int actionTracker = 0; //Tracks the number of actions tacken
        while(true) {
            //Run an action
            displayChoices();
            actionTracker++;

            //End Day
            if (actionTracker == 2) {
                time.endDay();
                actionTracker = 0;
            }
        }
    }

    public static void displayChoices() {
        /**Display All Choices, and get user to choose*/
        System.out.println("\nMAIN MENU OPTIONS");
        int i = 0;
        for (String choice : CHOICES) {
            System.out.println("\t" + i + ": " + choice);
            i++;
        }

        int userIn = Input.getNum("Select your choice by number: ", 0, CHOICES.size() - 1); 

        switch(userIn) {
            case 0:
                exit();
                break;
            case 1:
                viewBallance();
                break;
            case 2:
                viewShip();
                break;
            case 3:
                viewTransactions();
                break;
            case 4:
                viewIslands();
                break;
            case 5:
                visitStore();
                break;
            case 6:
                sailToIsland();
                break;
            default:
                System.out.println("This Option is Not Implemented Yet");
        }
    }

    private static void sailToIsland() {
    }

    private static void visitStore() {
        /**Visit Current Islands Store */
        game.getPlayer().getShip().getLocation().openStore();
    }

    private static void viewIslands() {
    	System.out.println("\nyour ship is currently docked at " + game.getPlayer().getShip().getLocation() + " island");
    	for (Island island : game.getIslands()) {
    		System.out.println("\n-------======= Island properties for: " + island + " =======-------");
    		System.out.println(island.viewIslandInfo(game.getPlayer().getShip().getLocation()));
    		System.out.println("\nStore properties at " + island + ":");
    		island.displayStore();
    	}
    }

    private static void viewTransactions() {
        /**View previous transitions. A Transition is made when cargo is added or removed from the ships */
        game.getPlayer().getShip().displayTransactions();
    }

    private static void viewShip() {
        /**View The Ship Properties*/
        System.out.println("Ship Properties :\n");
        System.out.println("\t" + game.getPlayer().getShip());
    }

    private static void viewBallance() {
        /**Print the wallet and time variables */
        System.out.println("\n\tWallet: $" + game.getPlayer().getWallet());
        System.out.println("\tRemaining Time: " + game.getGameDuration() + " days");
    }

    public static void exit() {
        /**Exit Game and clean up */
        System.out.println("Game Ended");

        //Display Exit Screen

        game.closeCommandLine();
        System.exit(0);
    }
}
