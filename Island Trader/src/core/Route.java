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
		int routeDuration = (distance / GameEnvironment.game.getPlayer().getShip().getSailSpeed()) / 24;
		return "Route to: " + destination.getName() + 
		"<br/>Distance: " + distance + "km " +  " (" + routeDuration + " days)"
		+ "<br/>Description: '" + description + "<br/>Event Chance: " + eventChance * 100 + "%";
	}

	public String toString() { //auto generated toString method
		return "Route [distance=" + distance + ", origin=" + origin.getName() + ", destination=" + destination.getName() +  ", description="
				+ description + ", eventChance=" + eventChance + "]";
	}
    
    
}
