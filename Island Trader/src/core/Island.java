package core;
import java.util.ArrayList;
import java.util.Arrays;

public class Island {
    /**Island class, each island has a store, and multiple routes */
    private static final ArrayList<String> names = new ArrayList<String>(Arrays.asList(
        "Camoros",
        "Tanzania",
        "Choochoo",
        "Tiquila",
        "Nassau",
        "Juman",
        "Peros",
        "Tina",
        "Limot",
        "Pukakea",
        "St. Jimbob"
    ));
        

    private String name;
    private Store store;
    private ArrayList<Route> routes = new ArrayList<Route>();

    public Island() {
        //**Initialize each island with values */

        //Generate names from the list of names, remove it so that it isnt chosen twice
        int value = (int)(Math.random() * (names.size()));
        this.name = names.get(value);
        names.remove(value);

        //Set the Islands Store
        this.store = new Store();
    }

    public void openStore() {
        /**Opens the store by calling the stores functions */
        this.store.openStore();
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
}
