package core;

import java.util.ArrayList;

import gui.PiratesWindow;

public class Event {
    /**Contains Common functionality between events */
    public static String checkEvent(double eventChance) {
        /**Decides if an event has happened, calls the event functionality*/
        boolean eventSuccess = Math.random() < eventChance;

        //Pick random event
        if (eventSuccess) {
            int eventVal = (int)(Math.random() * 3);

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

    public static String piratesGame(String userSelection) {
        /**Pirates Event Handler */
        int pirateSelection = (int)(Math.random() * (4));
        String[] direction = {"north", "east", "south", "west"};
        String pirateDirection = direction[pirateSelection];

        if (userSelection == pirateDirection) {
            return "Success";
        }

        return "Failure";
    }

    public static String piratesFailure(PiratesWindow state) {
        //Attempt to steal items, if the user does not have the right items, make em walk the plank.
        int piratesCost = (int)(Math.random() * (300 - 50 + 1) + 50);
        ArrayList<Entity> piratesLoot = new ArrayList<Entity>();
        int value = 0;
        for (Entity good : GameEnvironment.game.getPlayer().getShip().getCargo()) {
            value += good.getPurchasePrice();
            piratesLoot.add(good);

            if (value >= piratesCost) {
                GameEnvironment.game.getPlayer().getShip().removeCargo(piratesLoot);

                return "The pirates are satisfied with your items, they re-board thier ship with your items and leave with a smile and a wave.";
            }
        }

        //Walk the plank
        state.closeWindow();
        GameEnvironment.exit("The Pirates are not satisfied with your items, they force you and the crew to walk the plank.");
        
        return "GAMEOVER";
    }

    public static int getChances() {
        int numberOfAttempts = 3;
        for (Upgrade upgrade : GameEnvironment.game.getPlayer().getShip().getUpgrades()) {
            if (upgrade.getType() == 0) {
                numberOfAttempts++;
            }
        }

        return numberOfAttempts;
    }

    private static String weather() {
        /**Bad weather event, weather damages the ship */
        
        //Generate random damage amount and remove it from ships health, accounting for ship upgrades
        int maxDamage = GameEnvironment.game.getPlayer().getShip().getHealth();
        int minDamage = 20;
        int damage = (int)(Math.random() * (maxDamage - minDamage + 1) + minDamage);
        GameEnvironment.game.getPlayer().getShip().takeDamage(damage);

        return "You and your crew run into some bad weather, big waves and storng winds.\nA giant squid is thrown from a tornado collapses the mast. \nYou must repeair the damage when you arrive at port.";
    }

    private static String rescue() {
        /**Rescue event, in this the player comes accros a ship wreck and rescues the crew for monetary reward */
        //Set number of sailors, accounting for any potential upgrades
        int numberOfSailors = (int)(Math.random() * (5 - 2 + 1) + 2);
        for (Upgrade upgrade : GameEnvironment.game.getPlayer().getShip().getUpgrades()) {
            if (upgrade.getType() == 2) {
                numberOfSailors += 2;
            }
        }

        GameEnvironment.game.getPlayer().changeWallet(numberOfSailors * 70);

    	return ("You and your crew spot a ship wreck in the distance, looks like a giant squid caused some serious damage... I wonder how that happened?"
    			+ "\nYou rescue " + numberOfSailors + " thankful saliours\neach gift you $70 for saving their life. +$" + numberOfSailors * 70);
    	
    }
}
