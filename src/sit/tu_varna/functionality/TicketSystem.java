package sit.tu_varna.functionality;
import java.util.Scanner;

public class TicketSystem {
    private CommandManager commandManager;

    public TicketSystem() {
        DataStore dataStore = new DataStore();
        initializeHalls(dataStore);  // Добавяме залите към DataStore
        this.commandManager = new CommandManager(dataStore);
    }

    private void initializeHalls(DataStore dataStore) {
        dataStore.addHall(new Hall("1", 10, 15)); // Зала 1 с 10 реда и 15 места на ред
        dataStore.addHall(new Hall("2", 8, 12));  // Зала 2 с 8 реда и 12 места на ред
        dataStore.addHall(new Hall("3", 12, 20)); // Зала 3 с 12 реда и 20 места на ред
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("Welcome to the Ticket System. Type 'help' to see the list of commands.");

        while (true) {
            System.out.print("> ");
            input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the system.");
                break;
            }

            commandManager.executeCommand(input);
        }

        scanner.close();
    }

    public static void main(String[] args) {
        TicketSystem system = new TicketSystem();
        system.run();
    }
}



