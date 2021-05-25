package core;

/**
 * Route Class. Routes are a path that a Ship may take to travel between two Islands. Each route is stored in its destination Island.
 * 
 * Distance - Distance from the origin to the destination in km
 * Description - A brief description of features of the routes
 * Event Change - A decimal percentage representing the change of an event occuring
 * Origin - The origin Island
 * Destination - The destination Island
 */
public class Route {
    private int distance;
	private String description;
    private double eventChance;
	private Island origin;
    private Island destination;
    
	/**
	 * Creates a Route object, setting its variables.
	 * 
	 * @param origin	Island, start of the route
	 * @param destination	Island, end of the route
	 * @param distance	int, distance between the destination and origin in km
	 * @param description	String, breif description of the route.
	 * @param eventChance	double, decimal percentage representing the chance of an event occuring.
	 */
    public Route(Island origin, Island destination, int distance, String description, double eventChance) {
    	this.distance = distance;
    	this.description = description;
    	this.eventChance = eventChance;	
        this.destination = destination;
    	this.origin = origin;
    }

	/**
	 * Generates a String format displaying route information.
	 * 
	 * @return	String, paragraphed route information
	 */
	public String viewRoute() {
		//Get a route duration in days
		int routeDuration = (distance / GameEnvironment.getPlayer().getShip().getSailSpeed()) / 24;
		return "Route to: " + destination.getName() + 
		"<br/>Distance: " + distance + "km " +  " (" + routeDuration + " days)"
		+ "<br/>Description: '" + description + "<br/>Event Chance: " + eventChance * 100 + "%";
	}
	
    
    //Getters/Setters
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
}
