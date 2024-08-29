package sit.tu_varna.commands;

import sit.tu_varna.functionality.DataStore;
import sit.tu_varna.functionality.Event;
import sit.tu_varna.functionality.Hall;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Команда за добавяне на ново събитие в системата.
 * Тази команда проверява дали дадена зала е свободна на определена дата,
 * и ако е, създава ново събитие и го добавя в DataStore.
 */
public class AddEventCommand implements Command {
    private DataStore dataStore;

    /**
     * Конструктор за AddEventCommand с подадения DataStore.
     *
     * @param dataStore инстанция на DataStore, където се управляват събитията и залите
     */
    public AddEventCommand(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    /**
     * Изпълнява командата за добавяне на събитие.
     * Очакваният формат на командата е: addevent <date> <hall> <name>
     *
     * @param args аргументите на командата, където:
     *             - args[1] е датата на събитието във формат "yyyy-MM-dd",
     *             - args[2] е името на залата,
     *             - args[3] е името на събитието.
     */
    @Override
    public void execute(String[] args) {
        if (args.length < 4) {
            System.out.println("Usage: addevent <date> <hall> <name>");
            return;
        }

        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(args[1]);
            String hallName = args[2];
            String eventName = args[3];

            Hall hall = dataStore.getHall(hallName);
            if (hall == null) {
                System.out.println("Error: Hall not found.");
                return;
            }

            if (dataStore.hasEvent(date, hallName)) {
                System.out.println("Error: Event already scheduled for this date and hall.");
            } else {
                Event event = new Event(eventName, date, hall);
                dataStore.addEvent(event);
                System.out.println("Event '" + eventName + "' added on " + args[1] + " in hall " + hallName);
            }
        } catch (Exception e) {
            System.out.println("Error adding event: " + e.getMessage());
        }
    }
}
