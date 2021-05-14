package core;

public class Item {
    /**Item class, holds info about the item */
    private final static String[][] POSSIBILE = {
        {"Tomato", "Juicy little fruit", "2"},
        {"Machette", "Has a prickly edge", "10"},
        {"Loto Ticket", "This one did not win", "1"}
    };
    private String name;
    private int purchasePrice;
    private int salePrice;
    private String description;
    private int weight;
    private Store store;

    public Item(Store store) {
        /**Requires a store by default, use null if stored on ship? Test this later */
        int number = (int)(Math.random() * POSSIBILE.length);

        this.store = store;
        this.name = POSSIBILE[number][0];
        this.description = POSSIBILE[number][1];
        this.weight =  Integer.parseInt(POSSIBILE[number][2]);
        this.purchasePrice = (int)(Math.random() * (100 - 5 + 1)) + 5;
        this.salePrice = (int)(Math.random() * (100 - 5 + 1)) + 5;
    }

    public String getName() {
        return name;
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public String getDescription() {
        return description;
    }

    public int getWeight() {
        return weight;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
