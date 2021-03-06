package core;

/**
 * Time Class. Manages the time functionality of the game.
 * 
 * TimeRemaining - Remainging time in days
 */
public class Time {
    private int timeRemaining;

    /**
     * Initialise the Time object. Sets the games duration.
     * 
     * @param duration int, number of days you want the game to go
     */
    public Time(int duration) {
        this.timeRemaining = duration;
    }
    
    /**
     * End Day. Subtract day from timeRemaining and subtract crew wages
     */
    public boolean endDay() {
        //Subtract Day
        if (this.timeRemaining > 0) {
            this.timeRemaining -= 1;
        } else {
            GameEnvironment.exit("Game Over. Not enough time to travel to these locations");
            return false;
        }
        
        //Subtract Wage
        int crewCosts = GameEnvironment.getPlayerShip().getCrew() * GameEnvironment.getPlayerShip().getCrewWage();
        if (GameEnvironment.getPlayer().getWallet() >= crewCosts) {
            GameEnvironment.getPlayer().changeWallet(-crewCosts);
        } else {
            GameEnvironment.exit("Game Over. Not enough funds to pay crew, a mutany has occured and you have died!");
            return false;
        }
        return true;
    }

    
    //Getters/Setters
    public int getTimeRemaining() {
        return timeRemaining;
    }
}
