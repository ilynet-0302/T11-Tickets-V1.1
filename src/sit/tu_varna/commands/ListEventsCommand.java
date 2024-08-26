package sit.tu_varna.commands;
import sit.tu_varna.functionality.DataStore;
import sit.tu_varna.functionality.Event;

import java.util.*;

public class ListEventsCommand implements Command {
    private DataStore dataStore;

    public ListEventsCommand(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public void execute(String[] args) {
        List<Event> events = dataStore.getAllEvents();
        if (events.isEmpty()) {
            System.out.println("No events found.");
        } else {
            for (Event event : events) {
                System.out.println("Event: " + event.getName() + ", Date: " + event.getDate() + ", Hall: " + event.getHall().getName());
            }
        }
    }
}