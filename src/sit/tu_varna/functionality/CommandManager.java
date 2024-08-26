package sit.tu_varna.functionality;

import sit.tu_varna.commands.*;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private Map<String, Command> commands = new HashMap<>();

    public CommandManager(DataStore dataStore) {
        commands.put("help", new HelpCommand(commands));
        commands.put("open", new OpenCommand(dataStore));
        commands.put("close", new CloseCommand(dataStore));
        commands.put("save", new SaveCommand(dataStore));
        commands.put("saveas", new SaveAsCommand(dataStore));
        commands.put("exit", new ExitCommand());
        commands.put("addevent", new AddEventCommand(dataStore));
        commands.put("freeseats", new FreeSeatsCommand(dataStore));
        commands.put("book", new BookCommand(dataStore));
        commands.put("unbook", new UnbookCommand(dataStore));
        commands.put("buy", new BuyCommand(dataStore));
        commands.put("check", new CheckCommand(dataStore));
        commands.put("report", new ReportCommand(dataStore));
        commands.put("listevents", new ListEventsCommand(dataStore));  // Регистрация на новата команда
    }

    public void executeCommand(String input) {
        String[] parts = input.split("\\s+");
        String commandName = parts[0].toLowerCase();
        Command command = commands.get(commandName);

        if (command != null) {
            command.execute(parts);
        } else {
            System.out.println("Unknown command: " + commandName);
        }
    }
}

