package sit.tu_varna.commands;

public class ExitCommand implements Command {
    @Override
    public void execute(String[] args) {
        System.out.println("Exiting...");
        System.exit(0);
    }
}