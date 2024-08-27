package sit.tu_varna.commands;

import sit.tu_varna.functionality.DataStore;
import sit.tu_varna.functionality.Event;
import sit.tu_varna.functionality.Ticket;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReportCommand implements Command {
    private DataStore dataStore;

    public ReportCommand(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: report <from> <to> [<hall>]");
            return;
        }

        try {
            Date fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(args[1]);
            Date toDate = new SimpleDateFormat("yyyy-MM-dd").parse(args[2]);
            String hallName = args.length > 3 ? args[3] : null;

            List<Event> eventsInRange = dataStore.getEventsInRange(fromDate, toDate, hallName);

            for (Event event : eventsInRange) {
                long soldTickets = event.getTickets().values().stream()
                        .filter(Ticket::isPurchased)  // Филтриране на закупените билети
                        .count();

                System.out.println("Event: " + event.getName() +
                        " | Date: " + new SimpleDateFormat("yyyy-MM-dd").format(event.getDate()) +
                        " | Sold Tickets: " + soldTickets);
            }
        } catch (Exception e) {
            System.out.println("Error generating report: " + e.getMessage());
        }
    }
}
