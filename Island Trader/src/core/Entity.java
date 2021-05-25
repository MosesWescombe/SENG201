package core;

/**
 * Entity is the parent abstract class of Item and Upgrade. This holds the shared variables and methods of each.
 * 
 * Name - Item Name
 * Description - Item Description
 * LocationOfStore - Island that the item was created at/is currently available
 * Weight - Item weight in kg
 * SalePrice - price a store is willing to pay for the item
 * PurchasePrice - Price the store is/was selling the item for
 */
public abstract class Entity {
    protected String name;
    protected String description;
    protected Island locationOfStore;
    protected int weight;
    protected int purchasePrice;
    protected int salePrice;

    /**
     * Generates an object array consisting of name, description, purchasePrice, weight and salePrice.
     * This format is used to display on JTables.
     * 
     * @return Object[] Holding info on the Entity
     */
    public Object[] getItemDetails() {
        return new Object[] {
            this.name,
            this.description,
            this.getPurchasePrice(),
            this.getWeight(),
            this.getSalePrice()
        };
    }

    
    //Getters/Setters
    public int getWeight() {
        return weight;
    }
    public String getName() {
        return name;
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
    public String getDescription() {
        return description;
    }
    public Island getLocationOfStore() {
        return locationOfStore;
    }
    public void setLocationOfStore(Island locationOfStore) {
        this.locationOfStore = locationOfStore;
    }
}
