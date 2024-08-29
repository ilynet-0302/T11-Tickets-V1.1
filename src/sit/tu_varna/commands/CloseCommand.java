package sit.tu_varna.commands;

import sit.tu_varna.functionality.DataStore;

/**
 * Команда за затваряне на текущия файл и изчистване на данните в паметта.
 * Тази команда изчиства всички текущо заредени данни и симулира затваряне на файла.
 */
public class CloseCommand implements Command {
    private DataStore dataStore;

    /**
     * Конструктор за CloseCommand с подадения DataStore.
     *
     * @param dataStore инстанция на DataStore, която управлява събитията, залите и билетите
     */
    public CloseCommand(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    /**
     * Изпълнява командата за затваряне на файла и изчистване на данните.
     * Тази команда премахва всички заредени данни в паметта и изписва съобщение за успешно затваряне на файла.
     *
     * @param args аргументите на командата (не се използват за тази команда)
     */
    @Override
    public void execute(String[] args) {
        dataStore.clear();
        System.out.println("Data cleared. File closed.");
    }
}
