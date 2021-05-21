package core;

public class Event {
    /**Contains Common functionality between events */
    public static void checkEvent(int eventChance) {
        /**Decides if an event has happened, calls the event functionality*/
        int numberOfEvents = 3;
        boolean eventSuccess = Math.random() * eventChance > 0.5;

        if (eventSuccess) {
            int eventVal = (int)Math.random() * (numberOfEvents);

            switch(eventVal) {
                case 0:
                    pirates();
                    break;
                case 1:
                    weather();
                    break;
                case 3:
                    rescue();
            }
        }
    }

    private static void pirates() {

    }

    private static void weather() {

    }

    private static void rescue() {

    }
}
