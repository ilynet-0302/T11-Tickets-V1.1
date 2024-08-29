package sit.tu_varna.functionality;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Класът Event представлява събитие, което се провежда в дадена зала на определена дата.
 * Този клас управлява билетите за събитието и предоставя методи за резервация, отмяна на резервация и закупуване на билети.
 */
public class Event implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;  // Име на събитието
    private Date date;  // Дата на събитието
    private Hall hall;  // Зала, в която се провежда събитието
    private Map<String, Ticket> tickets;  // Карта на билетите за събитието

    /**
     * Конструктор за Event, който инициализира събитието с име, дата и зала.
     * Също така инициализира билетите за всички места в залата.
     *
     * @param name името на събитието
     * @param date датата на събитието
     * @param hall залата, в която се провежда събитието
     */
    public Event(String name, Date date, Hall hall) {
        this.name = name;
        this.date = date;
        this.hall = hall;
        this.tickets = new HashMap<>();

        for (int row = 1; row <= hall.getRows(); row++) {
            for (int seat = 1; seat <= hall.getSeatsPerRow(); seat++) {
                tickets.put(formatKey(row, seat), new Ticket(row, seat, date, this.name));
            }
        }
    }

    /**
     * Форматира ключ за достъп до карта на билетите на базата на ред и място.
     *
     * @param row редът на мястото
     * @param seat номерът на мястото
     * @return форматът на ключа като "row-seat"
     */
    private String formatKey(int row, int seat) {
        return row + "-" + seat;
    }

    /**
     * Връща картата на билетите за събитието.
     *
     * @return карта с билетите, където ключът е форматът "row-seat", а стойността е Ticket обект
     */
    public Map<String, Ticket> getTickets() {
        return tickets;
    }

    /**
     * Резервира място за събитието, ако мястото е свободно.
     *
     * @param row редът на мястото
     * @param seat номерът на мястото
     * @param note бележка към резервацията
     * @return true, ако резервацията е успешна, или false, ако мястото вече е резервирано или продадено
     */
    public boolean book(int row, int seat, String note) {
        String key = formatKey(row, seat);
        Ticket ticket = tickets.get(key);
        if (ticket != null && !ticket.isBooked() && !ticket.isPurchased()) {
            ticket.book(note);
            return true;
        }
        return false;
    }

    /**
     * Отменя резервацията на място за събитието, ако мястото е резервирано и не е продадено.
     *
     * @param row редът на мястото
     * @param seat номерът на мястото
     * @return true, ако анулирането е успешно, или false, ако мястото не е било резервирано или вече е продадено
     */
    public boolean unbook(int row, int seat) {
        String key = formatKey(row, seat);
        Ticket ticket = tickets.get(key);
        if (ticket != null && ticket.isBooked() && !ticket.isPurchased()) {
            ticket.unbook();
            return true;
        }
        return false;
    }

    /**
     * Закупува място за събитието, ако мястото не е вече продадено.
     *
     * @param row редът на мястото
     * @param seat номерът на мястото
     * @return true, ако покупката е успешна, или false, ако мястото вече е продадено
     */
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

    /**
     * Извежда на конзолата списък със свободните места за събитието.
     */
    public void printFreeSeats() {
        System.out.println("Free seats for " + name + " on " + date + ":");
        for (Ticket ticket : tickets.values()) {
            if (!ticket.isBooked() && !ticket.isPurchased()) {
                System.out.println("Row " + ticket.getRow() + ", Seat " + ticket.getSeat());
            }
        }
    }

    /**
     * Връща името на събитието.
     *
     * @return името на събитието
     */
    public String getName() {
        return name;
    }

    /**
     * Връща датата на събитието.
     *
     * @return датата на събитието
     */
    public Date getDate() {
        return date;
    }

    /**
     * Връща залата, в която се провежда събитието.
     *
     * @return залата на събитието
     */
    public Hall getHall() {
        return hall;
    }
}
