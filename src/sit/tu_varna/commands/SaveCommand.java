package sit.tu_varna.commands;

import sit.tu_varna.functionality.DataStore;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Команда за запазване на текущото състояние на данните във файл.
 * Тази команда позволява на потребителя да запази текущите данни в системата в посочен от него файл.
 */
public class SaveCommand implements Command {
    private DataStore dataStore;

    /**
     * Конструктор за SaveCommand с подадения DataStore.
     *
     * @param dataStore инстанция на DataStore, която съдържа данните, които ще бъдат запазени
     */
    public SaveCommand(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    /**
     * Изпълнява командата за запазване на данните във файл.
     * Очакваният формат на командата е: save <filename>
     * Данните от текущия DataStore се записват в посочения файл.
     *
     * @param args аргументите на командата, където:
     *             - args[1] е името на файла, в който ще се запазят данните.
     */
    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: save <filename>");
            return;
        }

        String filename = args[1];
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(dataStore);
            System.out.println("File " + filename + " saved successfully.");
        } catch (Exception e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
