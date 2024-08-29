package sit.tu_varna.commands;

import sit.tu_varna.functionality.DataStore;
import sit.tu_varna.functionality.Event;
import sit.tu_varna.functionality.Hall;
import sit.tu_varna.functionality.Ticket;

import java.text.SimpleDateFormat;

/**
 * Команда за извеждане на свободните места за дадено събитие.
 * Тази команда позволява на потребителя да види състоянието на всички места в залата за конкретно събитие
 * - дали са свободни, резервирани или продадени.
 */
public class FreeSeatsCommand implements Command {
    private DataStore dataStore;

    /**
     * Конструктор за FreeSeatsCommand с подадения DataStore.
     *
     * @param dataStore инстанция на DataStore, която управлява събитията, залите и билетите
     */
    public FreeSeatsCommand(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    /**
     * Изпълнява командата за извеждане на свободните места за събитие.
     * Очакваният формат на командата е: freeseats <date> <name>
     *
     * @param args аргументите на командата, където:
     *             - args[1] е датата на събитието във формат "yyyy-MM-dd",
     *             - args[2] е името на събитието.
     */
    @Override
    public void execute(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: freeseats <date> <name>");
            return;
        }

        try {
            String dateStr = args[1];
            String eventName = args[2];
            Event event = dataStore.getEvent(new SimpleDateFormat("yyyy-MM-dd").parse(dateStr), eventName);

            if (event == null) {
                System.out.println("Error: Event not found.");
                return;
            }

            Hall hall = event.getHall();
            System.out.println("Free seats for event: " + eventName + " on " + dateStr + " in hall: " + hall.getName());

            for (int row = 1; row <= hall.getRows(); row++) {
                for (int seat = 1; seat <= hall.getSeatsPerRow(); seat++) {
                    String status = "Свободно";
                    Ticket ticket = event.getTickets().get(row + "-" + seat);
                    if (ticket.isPurchased()) {
                        status = "Продадено";
                    } else if (ticket.isBooked()) {
                        status = "Резервирано";
                    }
                    System.out.println("Row " + row + ", Seat " + seat + ": " + status);
                }
            }
        } catch (Exception e) {
            System.out.println("Error fetching free seats: " + e.getMessage());
        }
    }
}
