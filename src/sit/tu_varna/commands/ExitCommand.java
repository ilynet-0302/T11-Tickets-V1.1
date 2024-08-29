package sit.tu_varna.commands;

/**
 * Команда за излизане от системата.
 * Тази команда прекратява изпълнението на програмата и затваря приложението.
 */
public class ExitCommand implements Command {

    /**
     * Изпълнява командата за излизане от програмата.
     * Извежда съобщение "Exiting..." и след това прекратява изпълнението на програмата с код 0.
     *
     * @param args аргументите на командата (не се използват за тази команда)
     */
    @Override
    public void execute(String[] args) {
        System.out.println("Exiting...");
        System.exit(0);
    }
}
