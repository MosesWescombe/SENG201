package core;

public class Upgrade {
    /**These upgrade items can effect ship stats, or assist in events */
	private String name;
	private String description;
	
	public Upgrade(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
		
}
