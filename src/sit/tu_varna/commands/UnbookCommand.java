package sit.tu_varna.commands;

import sit.tu_varna.functionality.DataStore;
import sit.tu_varna.functionality.Event;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UnbookCommand implements Command {
    private DataStore dataStore;

    public UnbookCommand(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 5) {
            System.out.println("Usage: unbook <row> <seat> <date> <name>");
            return;
        }

        try {
            int row = Integer.parseInt(args[1]);
            int seat = Integer.parseInt(args[2]);
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(args[3]);
            String eventName = args[4];

            Event event = dataStore.getEvent(date, eventName);
            if (event == null) {
                System.out.println("Error: Event not found.");
                return;
            }

            if (event.unbook(row, seat)) {
                System.out.println("Ticket unbooked successfully.");
            } else {
                System.out.println("Error: Seat is not booked.");
            }
        } catch (Exception e) {
            System.out.println("Error unbooking ticket: " + e.getMessage());
        }
    }
}
