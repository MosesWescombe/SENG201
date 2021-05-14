import java.util.ArrayList;

public class GameEnvironment {
    /** Main game environment that holds all key functionality, Application entry point is here.
     * this can also be thought of as the player class.
     */
    
    public static void main(String[] args) {
        /** Main game loop, run methods from here */

        //Initialize game environment
        CommandLine game = new CommandLine();
        

        game.updateGame();

        game.closeGame();
    }
}
