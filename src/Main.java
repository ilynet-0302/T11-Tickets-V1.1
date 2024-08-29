import sit.tu_varna.functionality.TicketSystem;

/**
 * Класът Main съдържа основния метод main, който стартира системата за управление на билети.
 */
public class Main {
     /**
     * Главният метод main, който стартира програмата.
     * Създава инстанция на TicketSystem и я стартира чрез извикване на метода run().
     *
     * @param args аргументи от командния ред (не се използват)
     */
    public static void main(String[] args) {
        TicketSystem system = new TicketSystem();
        system.run();
    }
}
