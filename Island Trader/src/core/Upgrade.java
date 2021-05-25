package core;

/**
 * Upgrade Class - Extends Entity. Upgrades are treated as items in the store/cargo. However upgrades also have extra buffs to the ship properties
 * or mini game events.
 * 
 * Type - Type of upgrade 0-4 from UPGRADEOPTIONS
 * UPGRADEOPTIONS - Contains info on all of the different types of upgrades
 */
public class Upgrade extends Entity{
	private int type;
	private final String[][] UPGRADEOPTIONS = {
		{"Cannon", "Extra Chance", "100"},
		{"Telescope", "Improves chance of seeing stranded crew", "100"},
		{"Steel Hull", "Strengthens the ship, adding 50 max health.", "100"},
		{"Larger storage", "Increases the ships capacity by 100", "100"},
		{"Bigger Sails", "Increases the ships sail speed by 15", "100"}
	};
	
	/**
	 * Create Upgrade object.
	 * 
	 * @param type int, 0-4 type of upgrade from UPGRADEOPTIONS
	 * @param location Island, location the upgrade is stored/purchased at
	 */
	public Upgrade(int type, Island location) {
		this.type = type;
		this.name = UPGRADEOPTIONS[type][0];
		this.description = UPGRADEOPTIONS[type][1];
		this.purchasePrice = Integer.parseInt(UPGRADEOPTIONS[type][2]);
		this.locationOfStore = location;

		this.salePrice = 0;
		this.weight = 0;
	}


	//Getters/Setters
	public int getType() {
		return this.type;
	}
}
