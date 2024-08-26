package sit.tu_varna.commands;

import java.util.Map;

public class HelpCommand implements Command {
    private Map<String, Command> commands;

    public HelpCommand(Map<String, Command> commands) {
        this.commands = commands;
    }

    @Override
    public void execute(String[] args) {
        System.out.println("Available commands:");
        for (String commandName : commands.keySet()) {
            System.out.println("- " + commandName);
        }
    }
}
