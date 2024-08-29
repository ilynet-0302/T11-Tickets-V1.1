package sit.tu_varna.commands;

import sit.tu_varna.functionality.DataStore;
import sit.tu_varna.functionality.Event;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Команда за извеждане на списък с всички събития в системата.
 * Тази команда показва всички налични събития с информация за името на събитието, датата и залата, в която ще се проведе.
 */
public class ListEventsCommand implements Command {
    private DataStore dataStore;

    /**
     * Конструктор за ListEventsCommand с подадения DataStore.
     *
     * @param dataStore инстанция на DataStore, която управлява събитията и залите
     */
    public ListEventsCommand(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    /**
     * Изпълнява командата за извеждане на списък с всички събития.
     * Събитията се извеждат във формат: име на събитието, дата (ден от седмицата, ден месец година) и зала.
     *
     * @param args аргументите на командата (не се използват за тази команда)
     */
    @Override
    public void execute(String[] args) {
        List<Event> events = dataStore.getAllEvents();
        if (events.isEmpty()) {
            System.out.println("No events found.");
        } else {
            // Форматиране на датата като "ден от седмицата, ден месец година"
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy");
            for (Event event : events) {
                String formattedDate = dateFormat.format(event.getDate());
                System.out.println("Event: " + event.getName() + ", Date: " + formattedDate + ", Hall: " + event.getHall().getName());
            }
        }
    }
}
