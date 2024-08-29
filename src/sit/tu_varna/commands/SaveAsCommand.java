package sit.tu_varna.commands;

import sit.tu_varna.functionality.DataStore;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Команда за запазване на текущото състояние на данните в нов файл.
 * Тази команда позволява на потребителя да запази текущите данни в системата в зададен от него файл.
 */
public class SaveAsCommand implements Command {
    private DataStore dataStore;

    /**
     * Конструктор за SaveAsCommand с подадения DataStore.
     *
     * @param dataStore инстанция на DataStore, която съдържа данните, които ще бъдат запазени
     */
    public SaveAsCommand(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    /**
     * Изпълнява командата за запазване на данните във файл.
     * Очакваният формат на командата е: saveas <filename>
     * Данните от текущия DataStore се записват в посочения файл.
     *
     * @param args аргументите на командата, където:
     *             - args[1] е името на файла, в който ще се запазят данните.
     */
    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: saveas <filename>");
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
