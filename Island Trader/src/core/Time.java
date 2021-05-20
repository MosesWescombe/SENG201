package core;

public class Time {
    /**Time manager, this holds the functions that manage the time. When a day passes multiple functions will need to be called. */

    private int timeRemaining;

    public Time() {
        /**Initialise the game duration */

        //Set Time
        this.timeRemaining = 20; //Input.getNum("Choose game duration (days): ", 20, 50); // added upper and lower param to inputs with getNum - j
    }

    public void endDay() {
        /**Subtract a day, check for game ended and charge player the crews wages */

        //Subtract Day
        if (this.timeRemaining > 0) {
            this.timeRemaining -= 1;
        } else {
            System.out.println("Game Over. Not enough time to travel to these locations");
        }
        
        //Subtract Wage
        int crewCosts = GameEnvironment.game.getPlayer().getShip().getCrew() * GameEnvironment.game.getPlayer().getShip().getCrewWage();
        if (GameEnvironment.game.getPlayer().getWallet() >= crewCosts) {
            GameEnvironment.game.getPlayer().changeWallet(-crewCosts);
        } else {
            System.out.println("Game Over. Not enough funds to pay crew, a mutany has occured and you have died!");
            GameEnvironment.exit();
        }
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }
}
