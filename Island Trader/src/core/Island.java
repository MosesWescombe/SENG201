package core;

import java.util.ArrayList;

/**
 * Island class. Each Island has:
 * 
 * Name - Name of the island
 * Store - Store that is located at the Island
 * Routes - ArrayList containing all the routes to the Island
 */
public class Island {
    private String name;
    private Store store;
    private ArrayList<Route> routes = new ArrayList<Route>();

    /**
     * Initialise an Island with a name. Store and routes are added later.
     * 
     * @param name String, Island Name
     */
    public Island(String name) {
        this.name = name;
    }

    /**
     * View Island Info, generates a string containing information about an island, formatted nicely.
     * 
     * @param currentIsland Island, the island that you want to get info on
     * @return String[], [0] String displaying island info, [1] 'true' or 'false' declaring if there are any possible routes.
     */
    public String[] viewIslandInfo(Island currentIsland) {
    	String finalStr = "<html>Possible routes to " + getName();

        //Find all routes and display some info from the route.
    	int routeCount = 1;
        String found = "true";
		for (int i=0; i < routes.size(); i++) {
			if (currentIsland.getName() == routes.get(i).getOrigin().getName()) {
				finalStr = finalStr + "<br/>Route " +(routeCount) + ": " + routes.get(i).viewRoute() + "</html>";
				routeCount++;
			}
        }
		
        //If no routes have been found set found to 'false'
		if (routeCount == 1) {
			finalStr += "<br/>No possible routes.</html>";
            found = "false";
		}
		
		return new String[] {finalStr,found};
    }

    
    //Getters/Setters
    public void addRoute(Route route) {
        routes.add(route);
    }
    public String getName() {
        return this.name;
    }
    public Store getStore() {
        return store;
    }
    public void setStore(Store store) {
        this.store = store;
    }
    public ArrayList<Route> getRoutes() {
        return this.routes;
    }
}
