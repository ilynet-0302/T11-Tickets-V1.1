package sit.tu_varna.commands;
import sit.tu_varna.functionality.DataStore;
import sit.tu_varna.functionality.Event;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BookCommand implements Command {
    private DataStore dataStore;

    public BookCommand(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 6) {
            System.out.println("Usage: book <row> <seat> <date> <name> <note>");
            return;
        }

        try {
            int row = Integer.parseInt(args[1]);
            int seat = Integer.parseInt(args[2]);
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(args[3]);
            String eventName = args[4];
            String note = args[5];

            Event event = dataStore.getEvent(date, eventName);
            if (event == null) {
                System.out.println("Error: Event not found.");
                return;
            }

            if (event.book(row, seat, note)) {
                System.out.println("Ticket booked successfully.");
            } else {
                System.out.println("Error: Seat is already booked or sold.");
            }
        } catch (Exception e) {
            System.out.println("Error booking ticket: " + e.getMessage());
        }
    }
}
