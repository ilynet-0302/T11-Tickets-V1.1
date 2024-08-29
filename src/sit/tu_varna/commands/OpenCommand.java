package sit.tu_varna.commands;

import sit.tu_varna.functionality.DataStore;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * Команда за отваряне на файл и зареждане на данните в системата.
 * Тази команда позволява на потребителя да зареди състоянието на системата от предварително запазен файл.
 */
public class OpenCommand implements Command {
    private DataStore dataStore;

    /**
     * Конструктор за OpenCommand с подадения DataStore.
     *
     * @param dataStore инстанция на DataStore, в която ще се заредят данните от файла
     */
    public OpenCommand(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    /**
     * Изпълнява командата за отваряне на файл.
     * Очакваният формат на командата е: open <filename>
     * Зарежда данните от посочения файл и ги копира в текущия DataStore.
     *
     * @param args аргументите на командата, където:
     *             - args[1] е името на файла, който трябва да се отвори.
     */
    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: open <filename>");
            return;
        }

        String filename = args[1];
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            DataStore loadedData = (DataStore) ois.readObject();
            dataStore.copyFrom(loadedData);
            System.out.println("File " + filename + " opened successfully.");
        } catch (Exception e) {
            System.out.println("Error opening file: " + e.getMessage());
        }
    }
}
