package sit.tu_varna.functionality;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Event implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private Date date;
    private Hall hall;
    private Map<String, Ticket> tickets;

    public Event(String name, Date date, Hall hall) {
        this.name = name;
        this.date = date;
        this.hall = hall;
        this.tickets = new HashMap<>();

        // Инициализация на билетите за всички места в залата
        for (int row = 1; row <= hall.getRows(); row++) {
            for (int seat = 1; seat <= hall.getSeatsPerRow(); seat++) {
                tickets.put(formatKey(row, seat), new Ticket(row, seat, date, this.name));
            }
        }
    }

    private String formatKey(int row, int seat) {
        return row + "-" + seat;
    }


    public Map<String, Ticket> getTickets() {
        return tickets;
    }

    public boolean book(int row, int seat, String note) {
        String key = formatKey(row, seat);
        Ticket ticket = tickets.get(key);
        if (ticket != null && !ticket.isBooked() && !ticket.isPurchased()) {
            ticket.book(note);
            return true;
        }
        return false;
    }

    public boolean unbook(int row, int seat) {
        String key = formatKey(row, seat);
        Ticket ticket = tickets.get(key);
        if (ticket != null && ticket.isBooked() && !ticket.isPurchased()) {
            ticket.unbook();
            return true;
        }
        return false;
    }

    public boolean buy(int row, int seat) {
        String key = formatKey(row, seat);
        Ticket ticket = tickets.get(key);
        if (ticket != null && !ticket.isPurchased()) {
            ticket.purchase();
            System.out.println("Your ticket code is: " + ticket.getCode());
            return true;
        }
        System.out.println("Error: Seat is already purchased.");
        return false;
    }
    public void printFreeSeats() {
        System.out.println("Free seats for " + name + " on " + date + ":");
        for (Ticket ticket : tickets.values()) {
            if (!ticket.isBooked() && !ticket.isPurchased()) {
                System.out.println("Row " + ticket.getRow() + ", Seat " + ticket.getSeat());
            }
        }
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public Hall getHall() {
        return hall;
    }
}