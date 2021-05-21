package core;

public class Entity {
    /**Parent class of, upgrade and item*/
    protected String name;
    protected int purchasePrice;
    protected int salePrice;
    protected String description;
    protected Island locationOfStore;
    protected int weight;

    public Entity() {
    }

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
