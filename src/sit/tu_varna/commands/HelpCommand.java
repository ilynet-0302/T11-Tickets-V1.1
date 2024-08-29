package sit.tu_varna.commands;

import java.util.Map;

/**
 * Команда за извеждане на списък с наличните команди.
 * Тази команда позволява на потребителя да види всички команди, които са налични в системата.
 */
public class HelpCommand implements Command {
    private Map<String, Command> commands;

    /**
     * Конструктор за HelpCommand с подадената карта от команди.
     *
     * @param commands карта, съдържаща имената на командите като ключове и съответните обекти на командите като стойности
     */
    public HelpCommand(Map<String, Command> commands) {
        this.commands = commands;
    }

    /**
     * Изпълнява командата за помощ, като извежда списък с всички налични команди.
     * Командите се извеждат по имената им, записани в картата `commands`.
     *
     * @param args аргументите на командата (не се използват за тази команда)
     */
    @Override
    public void execute(String[] args) {
        System.out.println("Available commands:");
        for (String commandName : commands.keySet()) {
            System.out.println("- " + commandName);
        }
    }
}
