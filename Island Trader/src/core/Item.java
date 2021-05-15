package core;

public class Item {
    /**Item class, holds info about the item */
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
        {"Friends", "Don't have any", "100"},
        {"Popcorn", "Not for the faint of heart (high cholesterol)", "2"},
        {"Fried Rice", "Your diet untill you go home", "2"},
        {"Lost Student ID", "Worthless to all, valuable to one.", "0"},
        {"Spare Concert Ticket from last year", "Only twice the price", "1"},
        {"Pregnancy Test", "Used", "2"},
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
        {"Britney Spears", "Overrated but still in the news", "57"},
        {"Shrek Series", "Started as a classic, went downhill the more they made", "2"},
        {"Dress", "Gold and White?", "3"},
        {"How to train a dragon", "Didn't work, donkey fell in love with it", "2"},
        {"Avatar", "Why are they touching tails?", "140"},
        {"2022 Kia Soul", "Need gone", "1271"},
        {"Covid-19 Vaccine", "Comes with extra 5G", "1"},
        {"Icecream", "Sweet like me", "4"},
        {"Your mom", "Priceless", "9999"}
    };
    private String name;
    private int purchasePrice;
    private int salePrice;
    private String description;
    private int weight;

    public Item() {
        /**Creates an item, Items are stored in ArraList<Item> in the ship and stores*/
        int number = (int)(Math.random() * (POSSIBILE.length));

        this.name = POSSIBILE[number][0];
        this.description = POSSIBILE[number][1];
        this.weight =  Integer.parseInt(POSSIBILE[number][2]);
    }

    public String toString() {
        //**This does not format nicely, mostly used for testing, create manual grid printing per use. */
        String format = this.name + ": " + this.description + "\tWeight: " + this.weight;
        return format;
    }

    public static String[] getRandomItem() {
        /**Returns a random item from POSSIBILE, not this does not return an actual ITEM object */
        return POSSIBILE[(int)(Math.random() * (POSSIBILE.length))];
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getWeight() {
        return weight;
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }
}
