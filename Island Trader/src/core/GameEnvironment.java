package core;

import java.util.ArrayList;
import java.util.Arrays;

public class GameEnvironment {
    /** Main game environment that holds all key functionality, Application entry point is here.
     * this can also be thought of as the player class.
     */
    private static boolean exit = false;
    public static CommandLine game;
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
        

        //Run Game Loop
        while(!exit) {
            displayChoices();
        }

        //Clean Up
        game.closeCommandLine();
    }

    public static void displayChoices() {
        /**Display All Choices, and get user to choose*/
        System.out.println("\nOPTIONS");
        int i = 0;
        for (String choice : CHOICES) {
            System.out.println("\t" + i + ": " + choice);
            i++;
        }

        int userChoice = Input.getNum("Select your choice by number: ", 0, CHOICES.size()); 

        switch(userChoice) {
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
                viewItems();
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
        game.getPlayerShip().getLocation().openStore();
    }

    private static void viewIslands() {
    }

    private static void viewItems() {
    }

    private static void viewShip() {
        /**View The Ship Properties*/
        System.out.println("Ship Properties :\n");
        System.out.println("/t" + game.getPlayerShip());
    }

    private static void viewBallance() {
        /**Print the wallet and time variables */
        System.out.println("\n\tWallet: $" + game.getWallet());
        System.out.println("\tRemaining Time: " + game.getGameDuration() + " days");
    }

    private static void exit() {
        /**Exit Game and clean up */
        //TODO Display Exit messages based on success or failure of game
        System.out.println("Game Ended");
        exit = true;
    }
}
