package sit.tu_varna.commands;

import sit.tu_varna.functionality.DataStore;
import sit.tu_varna.functionality.Ticket;

public class CheckCommand implements Command {
    private DataStore dataStore;

    public CheckCommand(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: check <code>");
            return;
        }

        String code = args[1];
        Ticket ticket = dataStore.getTicketByCode(code);

        if (ticket == null) {
            System.out.println("Error: Invalid ticket code.");
        } else {
            System.out.println("Ticket is valid for seat: Row " + ticket.getRow() + ", Seat " + ticket.getSeat());
        }
    }
}
