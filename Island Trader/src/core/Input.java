package core;
import java.util.Scanner;

public class Input {
    /** User input functionality. Use getNum for numbers*/
    //Scanner Object
    private static Scanner in = new Scanner(System.in);

    //Methods
    public static String get(String message) {
        if (message != "") {
            System.out.print(message);
        }
        
        String result = in.nextLine();
        return result;
    }

    public static int getNum(String message, int lower, int upper) {
        int intInputValue = 0;
        while (true) {
            if (message != "") {
                System.out.print(message);
            }
            String input = in.nextLine(); //changed next() to nextLine() - j
            intInputValue = 0;
            try {
                intInputValue = Integer.parseInt(input);
                if (intInputValue <= upper && intInputValue >= lower) { // only exit loop when input is between lower and upper - j
                	break;
                } else {
                	System.out.println("The input needs to be between " + lower + " and " + upper);
                }
            } catch (NumberFormatException ne) {
                System.out.println("Sorry we need a number from the options above.");
            }
        }
        
        return intInputValue;
    }

    public static void closeInput() {
        in.close();
    }
}
