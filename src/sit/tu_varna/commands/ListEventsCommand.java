package sit.tu_varna.commands;

import sit.tu_varna.functionality.DataStore;
import sit.tu_varna.functionality.Event;

import java.text.SimpleDateFormat;
import java.util.List;

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
            // Форматиране на датата като "ден от седмицата, ден месец година"
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy");
            for (Event event : events) {
                String formattedDate = dateFormat.format(event.getDate());
                System.out.println("Event: " + event.getName() + ", Date: " + formattedDate + ", Hall: " + event.getHall().getName());
            }
        }
    }
}