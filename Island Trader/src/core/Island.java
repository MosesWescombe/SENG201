package core;
import java.util.ArrayList;

public class Island {
    /**Island class, each island has a store, and multiple routes */
    private String name;
    private Store store;
    private ArrayList<Route> routes = new ArrayList<Route>();

    public Island(String name) {
        //**Initialize each island with values */
        this.name = name;
    }

    public void openStore() {
        /**Opens the store by calling the stores functions */
        this.store.openStore();
    }
    
    public void displayStore() {
    	/**only displays the items in the store of the island*/
    	this.store.displayStore();
    }

    public void viewRoutes() {
        /**Prints the different route options. */

        for (int i=0; i < routes.size(); i++) {
            System.out.println((i + 1) + ": " + routes.get(i));
        }
    }

    public String getName() {
        return this.name;
    }

    public void addRoute(Route route) {
        routes.add(route);
    }
    
    public String viewIslandInfo(Island currentIsland) {
    	String finalStr = "Possible routes to " + getName();
    	int routeCount = 1;
		for (int i=0; i < routes.size(); i++) {
			if (currentIsland.getName() == routes.get(i).getOrigin().getName()) {
				finalStr = finalStr + "\n\tRoute " +(routeCount) + ": " + routes.get(i).viewRoute();
				routeCount++;
			}
        }
		
		if (routeCount == 1) {
			finalStr += "\n\tNo possible routes.";
		}
		
		return finalStr;
    }

	public String toString() {
		String finalStr = getName();		
		return finalStr;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public ArrayList<Route> getRoutes() {
        return this.routes;
    }
}
