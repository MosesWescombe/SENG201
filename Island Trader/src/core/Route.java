package core;

public class Route {
	/**A route is a path between two islands, each route has a length in days and a event change as a percentage*/
    private int distance;
    private Island destination;
    private Island origin;
    private String description;
    private double eventChance;
    
    public Route(Island origin, Island destination, int distance, String description, double eventChance) {
		/**Takes an origin and destination island, randomly generates the distance and event chance */
    	this.distance = distance;
    	this.description = description;
    	this.eventChance = eventChance;	
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


	public double getEventChance() {
		return eventChance;
	}
	
	public String viewRoute() {
		return "A distance of " + distance + " days to " + destination.getName()
		+ "\n\t\t The route description is: '" + description + "'\n\t\t The chance of an event is " + eventChance * 100 + "%";
	}

	public String toString() { //auto generated toString method
		return "Route [distance=" + distance + ", origin=" + origin.getName() + ", destination=" + destination.getName() +  ", description="
				+ description + ", eventChance=" + eventChance + "]";
	}
    
    
}
