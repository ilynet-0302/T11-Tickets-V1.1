package sit.tu_varna.commands;

import sit.tu_varna.functionality.DataStore;
import sit.tu_varna.functionality.Event;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Команда за закупуване на билет за събитие.
 * Тази команда позволява на потребителя да закупи определено място за конкретно събитие на дадена дата.
 */
public class BuyCommand implements Command {
    private DataStore dataStore;

    /**
     * Конструктор за BuyCommand с подадения DataStore.
     *
     * @param dataStore инстанция на DataStore, която управлява събитията и залите
     */
    public BuyCommand(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    /**
     * Изпълнява командата за закупуване на билет.
     * Очакваният формат на командата е: buy <row> <seat> <date> <name>
     *
     * @param args аргументите на командата, където:
     *             - args[1] е редът на мястото, което се закупува,
     *             - args[2] е мястото, което се закупува,
     *             - args[3] е датата на събитието във формат "yyyy-MM-dd",
     *             - args[4] е името на събитието.
     */
    @Override
    public void execute(String[] args) {
        if (args.length < 5) {
            System.out.println("Usage: buy <row> <seat> <date> <name>");
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

            if (event.buy(row, seat)) {
                System.out.println("Ticket purchased successfully.");
            } else {
                System.out.println("Error: Seat is already booked or sold.");
            }
        } catch (Exception e) {
            System.out.println("Error purchasing ticket: " + e.getMessage());
        }
    }
}
