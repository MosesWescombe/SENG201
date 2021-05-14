package core;

public class Island {
    /**Island class, each island has a store, and multiple routes */
    private String name;
    private Store store;
    private Route[] routes;

    public void openStore() {
        /**Opens the store by calling the stores functions */
        //this.store.open()
    }

    public void viewRoutes() {
        /**Prints the different route options. */

        for (int i=0; i < routes.length; i++) {
            System.out.println(Integer.toString(i + 1) + ": " + routes[i]);
        }
    }

    public String getName() {
        return this.name;
    }
}
