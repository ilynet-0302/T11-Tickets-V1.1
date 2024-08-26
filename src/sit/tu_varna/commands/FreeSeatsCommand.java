package sit.tu_varna.commands;

import sit.tu_varna.functionality.DataStore;
import sit.tu_varna.functionality.Event;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FreeSeatsCommand implements Command {
    private DataStore dataStore;

    public FreeSeatsCommand(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: freeseats <date> <name>");
            return;
        }

        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(args[1]);
            String eventName = args[2];

            Event event = dataStore.getEvent(date, eventName);
            if (event == null) {
                System.out.println("Error: No event found for the given date and name.");
                return;
            }

            event.printFreeSeats();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
