package core;

import java.util.ArrayList;

public class Event {
    /**Contains Common functionality between events */
    public static void checkEvent(double eventChance) {
        /**Decides if an event has happened, calls the event functionality*/
        boolean eventSuccess = Math.random() < eventChance;

        //Pick random event
        if (eventSuccess) {
            int eventVal = (int)(Math.random() * 3);

            switch(eventVal) {
                case 0:
                    pirates();
                    break;
                case 1:
                    weather();
                    break;
                case 2:
                    rescue();
                    break;
            }
        } else {
            System.out.println("\nAn unneventful journey, you have succesfully traveled to a new Island,\n");
        }
    }

    private static void pirates() {
        /**Pirate Event, pirates try to steal goods, must win dice based games. */

        System.out.println("\nPIRATES! They are trying to grapple and board your ship, defend yourself by playing mini games.");

        //Set number of attempts allowed, accounting for ship upgrades
        int numberOfAttempts = 2;
        for (Upgrade upgrade : GameEnvironment.game.getPlayer().getShip().getUpgrades()) {
            if (upgrade.getType() == 0) {
                numberOfAttempts++;
            }
        }

        //Run the mini game untill the player wins or run out of attempts
        for (int i=1; i <= numberOfAttempts; i++) {
            int userIn = Input.getNum("\nEnter a number between 1 and 6!\n", 1, 6);
            int pirateNum = (int)(Math.random() * (6) + 1);

            //The player chose the correct number
            if (pirateNum == userIn) {
                System.out.println("You bank left nearly avoiding their grapple cannons! You have succesfully voided the ship");
                return;
            } else {
                System.out.println("The pirate ship is getting closer!");

                //Player lost
                if (i == numberOfAttempts) {
                    System.out.println("Oh no! The pirates have grappled your ship!");
                }
            }
        }

        //If the user hasn't won yet, play the next mini game
        int userIn = Input.getNum("\nChoose a direction to move in attempt to un-teather the grapples." +
        "\n1: North" + 
        "\n2: East" + 
        "\n3: South" + 
        "\n4: West" + 
        "\nSelection: ", 1, 6);
        int pirateNum = (int)(Math.random() * (4) + 1);

        //User has selected the right option
        if (pirateNum == userIn) {
            System.out.println("Pirates voided! No damage taken");
            return;
        } else {
            System.out.println("The pirates have boarded your ship. Very unfortunate.");
        }

        //Attempt to steal items, if the user does not have the right items, make em walk the plank.
        int piratesCost = (int)(Math.random() * (300 - 50 + 1) + 50);
        ArrayList<Entity> piratesLoot = new ArrayList<Entity>();
        int value = 0;
        for (Entity good : GameEnvironment.game.getPlayer().getShip().getCargo()) {
            value += good.getPurchasePrice();
            piratesLoot.add(good);

            if (value >= piratesCost) {
                System.out.println("The pirates are satisfied with your items, they re-board thier ship with your items and leave with a smile and a wave.");
                GameEnvironment.game.getPlayer().getShip().removeCargo(piratesLoot, true);
                return;
            }
        }

        //Walk the plank
        System.out.println("The Pirates are not satisfied with your items, they force you and the crew to walk the plank.");
        GameEnvironment.exit();
    }

    private static void weather() {
        /**Bad weather event, weather damages the ship */
        
        //Generate random damage amount and remove it from ships health, accounting for ship upgrades
        int maxDamage = GameEnvironment.game.getPlayer().getShip().getHealth();
        int minDamage = 20;
        for (Upgrade upgrade : GameEnvironment.game.getPlayer().getShip().getUpgrades()) {
            if (upgrade.getType() == 2) {
                maxDamage -= minDamage;
            }
        }
        int damage = (int)(Math.random() * (maxDamage - minDamage + 1) + minDamage);
        System.out.println("\nYou and your crew run into some bad weather, big waves and storng winds.\nA giant squid is thrown from a tornado collapses the mast. \nYou must repeair the damage when you arrive at port.");
        GameEnvironment.game.getPlayer().getShip().takeDamage(damage);
    }

    private static void rescue() {
        /**Rescue event, in this the player comes accros a ship wreck and rescues the crew for monetary reward */
        //Set number of sailors, accounting for any potential upgrades
        int numberOfSailors = (int)(Math.random() * (5 - 2 + 1) + 2);
        for (Upgrade upgrade : GameEnvironment.game.getPlayer().getShip().getUpgrades()) {
            if (upgrade.getType() == 2) {
                numberOfSailors += 2;
            }
        }
    	System.out.println("\nYou and your crew spot a ship wreck in the distance, looks like a giant squid caused some serious damage... I wonder how that happened?"
    			+ "\nYou rescue " + numberOfSailors + " thankful saliours\neach gift you $70 for saving their life. +$" + numberOfSailors * 70);
    	GameEnvironment.game.getPlayer().changeWallet(numberOfSailors * 70);
    }
}
