package sit.tu_varna.commands;

import sit.tu_varna.functionality.DataStore;
import sit.tu_varna.functionality.Event;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Команда за резервиране на билет за събитие.
 * Тази команда позволява на потребителя да резервира определено място за конкретно събитие на дадена дата.
 */
public class BookCommand implements Command {
    private DataStore dataStore;

    /**
     * Конструктор за BookCommand с подадения DataStore.
     *
     * @param dataStore инстанция на DataStore, която управлява събитията и залите
     */
    public BookCommand(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    /**
     * Изпълнява командата за резервиране на билет.
     * Очакваният формат на командата е: book <row> <seat> <date> <name> <note>
     *
     * @param args аргументите на командата, където:
     *             - args[1] е редът на мястото, което се резервира,
     *             - args[2] е мястото, което се резервира,
     *             - args[3] е датата на събитието във формат "yyyy-MM-dd",
     *             - args[4] е името на събитието,
     *             - args[5] е бележка, свързана с резервацията.
     */
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
