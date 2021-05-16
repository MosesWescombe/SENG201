package core;

public class Route {
	/**A route is a path between two islands, each route has a length in days and a event change as a percentage*/
    private int distance;
    private Island destination;
    private Island origin;
    private String description;
    private int eventChance;
    
    public Route(Island origin, Island destination) {
		/**Takes an origin and destination island, randomly generates the distance and event chance */
    	this.distance = (int)(Math.random() * (5 - 1 + 1) + 1);
    	this.description = "Wild Ride";
    	this.eventChance = (int)(Math.random() * (80 - 0 + 1) + 0);	
        this.destination = destination;
    	this.origin = origin;
    }
    
    
    //getters
	public int getDistance() {
		return distance;
	}


	public Island getDestination() {
		return destination;
	}


	public Island getOrigin() {
		return origin;
	}


	public String getDescription() {
		return description;
	}


	public int getEventChance() {
		return eventChance;
	}

	public String toString() { //auto generated toString method
		return "Route [distance=" + distance + ", destination=" + destination.getName() + ", origin=" + origin.getName() + ", description="
				+ description + ", eventChance=" + eventChance + "]";
	}
    
    
}
