package sit.tu_varna.commands;

import sit.tu_varna.functionality.DataStore;
import sit.tu_varna.functionality.Event;
import sit.tu_varna.functionality.Ticket;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Команда за генериране на отчет за продадените билети в определен времеви период.
 * Тази команда позволява на потребителя да види броя на продадените билети за събитията в определен диапазон от дати
 * и, по избор, за конкретна зала.
 */
public class ReportCommand implements Command {
    private DataStore dataStore;

    /**
     * Конструктор за ReportCommand с подадения DataStore.
     *
     * @param dataStore инстанция на DataStore, която управлява събитията и залите
     */
    public ReportCommand(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    /**
     * Изпълнява командата за генериране на отчет.
     * Очакваният формат на командата е: report <from> <to> [<hall>]
     *
     * @param args аргументите на командата, където:
     *             - args[1] е началната дата на периода във формат "yyyy-MM-dd",
     *             - args[2] е крайната дата на периода във формат "yyyy-MM-dd",
     *             - args[3] (по избор) е името на залата, за която се генерира отчетът.
     */
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
