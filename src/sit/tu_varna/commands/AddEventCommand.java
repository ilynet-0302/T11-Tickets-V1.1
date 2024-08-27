package sit.tu_varna.commands;

import sit.tu_varna.functionality.DataStore;
import sit.tu_varna.functionality.Event;
import sit.tu_varna.functionality.Hall;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddEventCommand implements Command {
    private DataStore dataStore;

    public AddEventCommand(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 4) {
            System.out.println("Usage: addevent <date> <hall> <name>");
            return;
        }

        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(args[1]);
            String hallName = args[2];
            String eventName = args[3];

            Hall hall = dataStore.getHall(hallName);
            if (hall == null) {
                System.out.println("Error: Hall not found.");
                return;
            }

            if (dataStore.hasEvent(date, hallName)) {
                System.out.println("Error: Event already scheduled for this date and hall.");
            } else {
                Event event = new Event(eventName, date, hall);
                dataStore.addEvent(event);
                System.out.println("Event '" + eventName + "' added on " + args[1] + " in hall " + hallName);
            }
        } catch (Exception e) {
            System.out.println("Error adding event: " + e.getMessage());
        }
    }
}

