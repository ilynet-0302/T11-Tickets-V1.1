package sit.tu_varna.commands;

import sit.tu_varna.functionality.DataStore;
import sit.tu_varna.functionality.Ticket;

/**
 * Команда за проверка на валидността на билет по уникален код.
 * Тази команда позволява на потребителя да провери дали даден билет е валиден,
 * и ако е, показва информация за реда и мястото на билета.
 */
public class CheckCommand implements Command {
    private DataStore dataStore;

    /**
     * Конструктор за CheckCommand с подадения DataStore.
     *
     * @param dataStore инстанция на DataStore, която управлява събитията и билетите
     */
    public CheckCommand(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    /**
     * Изпълнява командата за проверка на билет по код.
     * Очакваният формат на командата е: check <code>
     *
     * @param args аргументите на командата, където:
     *             - args[1] е уникалният код на билета, който се проверява.
     */
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
