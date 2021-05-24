package core;

public class Upgrade extends Entity{
    /**These upgrade items can effect ship stats, or assist in events */
	private int type;
	private String[][] upgradeOptions = {
		{"Cannon", "Extra Chance", "100"},
		{"Telescope", "Improves chance of seeing stranded crew", "100"},
		{"Steel Hull", "Strengthens the ship, adding 50 max health.", "100"},
		{"Larger storage", "Increases the ships capacity by 100", "100"},
		{"Bigger Sails", "Increases the ships sail speed by 15", "100"}
	};
	
	public Upgrade(int type, Island location) {
		//Create an upgrade
		this.type = type;
		this.name = upgradeOptions[type][0];
		this.description = upgradeOptions[type][1];
		this.purchasePrice = Integer.parseInt(upgradeOptions[type][2]);
		this.locationOfStore = location;

		this.salePrice = 0;
		this.weight = 0;
	}

	public int getType() {
		return this.type;
	}
}
