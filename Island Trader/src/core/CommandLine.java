package core;
public class CommandLine {
    /**Command line functions, anything that is only used for the command line section should be run from here
     * using number inputs as this is easier to link to gui later.
    */
    //Variables
    public static Ship playerShip;
    public static String playerName;
    public static int wallet;


    //Methods
    //Constructor
    public CommandLine() {
        /**Starts the game with required info*/
       
       //Set some intial values
       wallet = 100;
       playerName = Input.get("Choose your player name: ");

       //Create Ship
       String nameInput = Input.get("Name your ship: ");
       printOptions(Ship.getShipTypes()); //Print ship options

       int typeInput;
       do {
        typeInput = Input.getNum("Choose your Ship type: ");
       } while (!(typeInput < Ship.getShipTypes().length) || !(typeInput >= 1));
       
       playerShip = new Ship(nameInput, typeInput);
    }

    public void printOptions(String[] array){
        /**Prints an array of options in order, numbered from 1 to n */
        for (int i=1; i < array.length; i++) {
            System.out.println(Integer.toString(i) + ": " + array[i - 1]);
        }
    }

    public void updateGame() {
        /**This function is temporary, untill we decide how the core game is managed */
        System.out.println("Game Updated");
    }

    public void closeGame() {
       /** Tidy up the game, close open objects */
       Input.closeInput();
    }
}
