package core;

import java.util.ArrayList;

/**
 * Event class manages all functionality of random events.
 * 
 * EventOveride - Set to 0 to stop events from happening, used for unit tests. 1 Otherwise
 */
public class Event {
    private static int eventOveride = 1;

    /**
     * Decide if an event is going to happen based on a given event chance. Then decide what event and run it.
     * Pirates event is passed back before running as this requires a seperate window. (PiratesWindow).
     * 
     * @param eventChance Chance of an event happening as a decimal percentage (50% = 0.5)
     * @return  Either a message for the window to display showing the result of the eavent, or NONE, PIRATES.
     */
    public static String checkEvent(double eventChance) {
        //Check Event occurance
        boolean eventSuccess = Math.random() < (eventChance * eventOveride);

        //Pick random event
        if (eventSuccess) {
            int eventVal = (int)(Math.random() * 3);

            //Call required functions
            switch(eventVal) {
                case 0:
                    return "PIRATES";
                case 1:
                    return weather();
                case 2:
                    return rescue();
            }
        } 
           
        return "NONE";
    }

    /**
     * Functionality of pirate game, a user selection of a compass will be inputed, and the pirates will choose one at random.
     * The result is either SUCCESS or FAILURE;
     * 
     * @param userSelection Users selection from {'north', 'east', 'south', 'west'}
     * @return  Outcome of the game, either success or failure
     */
    public static String piratesGame(String userSelection) {
        //Make Pirates selection
        String[] direction = {"north", "east", "south", "west"};
        int pirateSelection = (int)(Math.random() * (4));
        String pirateDirection = direction[pirateSelection];

        //If the selections are the same return success
        if (userSelection == pirateDirection) {
            return "SUCCESS";
        }

        return "FAILURE";
    }

    /**
     * Runs functionality based on failure of the pirates mini game. Either loot will be stolen or the game will end, 
     * depending on the value of the players cargo.
     * 
     * @param state PirateWindow holding the state of the current opened window
     * @return  String, A message to display. Or GAMEOVER indicating the game has been closed
     */
    public static String piratesFailure() {
        //Set a random cost for the pirates
        int piratesCost = (int)(Math.random() * (300 - 50 + 1) + 50);
        ArrayList<Entity> piratesLoot = new ArrayList<Entity>();

        //Go through players inventory and add it to the pirates loot untill the cost is met
        int value = 0;
        for (Entity good : GameEnvironment.getPlayerShip().getCargo()) {
            value += good.getPurchasePrice();
            piratesLoot.add(good);

            //Remove cargo
            if (value >= piratesCost) {
                GameEnvironment.getPlayerShip().removeCargo(piratesLoot);

                return "The pirates are satisfied with your items, they re-board thier ship with your items and leave with a smile and a wave.";
            }
        }

    
        return "GAMEOVER";
    }

    /**
     * Returns the number of attempts a player has. This is a set number however certain upgrades can increase this.
     * 
     * @return Int, number of attempts a player has at the Pirate minigame.
     */
    public static int getChances() {
        int numberOfAttempts = 3;
        for (Upgrade upgrade : GameEnvironment.getPlayerShip().getUpgrades()) {
            if (upgrade.getType() == 0) {
                numberOfAttempts++;
            }
        }

        return numberOfAttempts;
    }

    /**
     * Run the weather event, this will damage the players ship by a random amount.
     * 
     * @return String, Message displaying the event
     */
    private static String weather() {
        //Generate random damage amount and remove it from ships health
        int maxDamage = GameEnvironment.getPlayerShip().getHealth();
        int minDamage = 20;
        int damage = (int)(Math.random() * (maxDamage - minDamage + 1) + minDamage);
        GameEnvironment.getPlayerShip().takeDamage(damage);

        return "You and your crew run into some bad weather, big waves and storng winds.\nA giant squid is thrown from a tornado collapses the mast. \nYou must repeair the damage when you arrive at port.";
    }

    /**
     * Run the rescue event, this will reward the player with a random amount of money, potentially increased by certain upgrades.
     * 
     * @return String, Message displaying event
     */
    private static String rescue() {
        //Set number of sailors, accounting for any potential upgrades
        int numberOfSailors = (int)(Math.random() * (5 - 2 + 1) + 2);
        for (Upgrade upgrade : GameEnvironment.getPlayerShip().getUpgrades()) {
            if (upgrade.getType() == 2) {
                numberOfSailors += 2;
            }
        }

        //Update wallet
        GameEnvironment.getPlayer().changeWallet(numberOfSailors * 70);

    	return ("You and your crew spot a ship wreck in the distance, looks like a giant squid caused some serious damage... I wonder how that happened?"
    			+ "\nYou rescue " + numberOfSailors + " thankful saliours\neach gift you $70 for saving their life. +$" + numberOfSailors * 70);
    }


    //Getters/Setters
    public static void setEventOveride(int input) {
        eventOveride = input;
    }
}
