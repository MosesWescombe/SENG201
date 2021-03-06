package core;

/**
 * Item class represents an item, each item has all of the same parameters as an Entity object.
 */
public class Item extends Entity {
    //List of all possible item names, descriptions and weights
    public final static String[][] POSSIBILE = {
        //Name, description, weight
        {"Tomato", "Juicy little fruit", "1"},
        {"Machette", "Has a prickly edge", "8"},
        {"Lotto Ticket", "This one did not win", "1"},
        {"Cookie", "Not the good ones, this is peanut butter", "2"},
        {"Portal Gun", "No one knows how to use this, would have been helpful", "20"},
        {"Zuchini", "Eat it", "5"},
        {"Watch", "Its 1pm in Zambia", "15"},
        {"Pixie", "This one got squished and died", "1"},
        {"Lice Eggs (7 pack)", "Great on toast", "2"},
        {"Umpalumpa finger", "Need a hand? Sorry out of stock", "5"},
        {"Apple", "Tastes like an orange", "5"},
        {"Orange", "Tastes like an apple","5"},
        {"Oil Barrel","Fun to shoot at", "60"},
        {"Cactus", "Little Prickly", "30"},
        {"Popcorn", "Not for the faint of heart (high cholesterol)", "2"},
        {"Fried Rice", "Your diet untill you go home", "2"},
        {"Lost Student ID", "Worthless to all, valuable to one.", "0"},
        {"Treasure Map", "Used", "3"},
        {"Toilet Paper","1-Ply (2.5 rolls, 1 clean)","7"},
        {"10X Bitcoin", "Trust me this is gonna be huge", "0"},
        {"2 Monitors, RGB Setup", "Gets girls trust me", "80"},
        {"Shiny Trading Cards", "Got a weird animal on it", "7"},
        {"Eggs & Ham", "Green", "10"},
        {"Treaty of Waitangi", "Translated by Jim's grandma", "1"},
        {"Bike", "Teslas most eco firendly model yet", "60"},
        {"Apple Pie", "Delxixcous", "5"},
        {"Gorilla", "Someone fell in his cage so we shot him", "90"},
        {"Jar Of Dirt", "Good place to hide a heart", "4"},
        {"Shrek Series", "Started as a classic, went downhill the more they made", "2"},
        {"Dress", "Gold and White?", "3"},
        {"How to train a dragon", "Didn't work, donkey fell in love with it", "2"},
        {"Avatar", "Why are they touching tails?", "140"},
        {"Covid-19 Vaccine", "Comes with extra 5G", "1"},
        {"Icecream", "Sweet like me", "4"},
        {"Sour Krout", "Scientists will reccommend this later", "6"}
    };

    /**
     * Creates an item randomly selecting it from pre defined POSSIBLE items.
     */
    public Item() {
        int number = (int)(Math.random() * (POSSIBILE.length));

        this.name = POSSIBILE[number][0];
        this.description = POSSIBILE[number][1];
        this.weight =  Integer.parseInt(POSSIBILE[number][2]);
    }

    /**
     * Gets a random item from the POSSIBLE array. This does not create or return a item object.
     * 
     * @return  String[], Name, Description, Weight of randomly retrieved item from POSSIBLE.
     */
    public static String[] getRandomItem() {
        return POSSIBILE[(int)(Math.random() * (POSSIBILE.length))];
    }

    /** Generates a string of the item in the format
     * <name>: <description>      Price: $<price>     Weight: <weight>kg
     * Where price is the purchase price.
     * 
     * @return String, formatted string representing the item.
     */
    public String toString() {
        Object[] preFormat = new String[] {
            this.name + ":      " + this.getDescription(),
            "Price: $" + this.getPurchasePrice(),
            "Weight: " + this.getWeight() + "kg"
        };
        //Sizing can be changed by altering the % numbers below
        String format = String.format("%-120s%-30s%-30s", preFormat);
        return format;
    }
}
