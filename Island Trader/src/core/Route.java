package core;

public class Route {
    private int distance;
    private Island destination;
    private Island origin;
    private String description;
    private int eventChance;
    
    
    public Route(int distance, Island destination, Island origin, String description, int eventChance) {
    	this.distance = distance;
    	this.destination = destination;
    	this.origin = origin;
    	this.description = description;
    	this.eventChance = eventChance;	
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
		return "Route [distance=" + distance + ", destination=" + destination + ", origin=" + origin + ", description="
				+ description + ", eventChance=" + eventChance + "]";
	}
    
    
}
