package sit.tu_varna.commands;

import sit.tu_varna.functionality.DataStore;

public class CloseCommand implements Command {
    private DataStore dataStore;

    public CloseCommand(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public void execute(String[] args) {
        dataStore.clear();
        System.out.println("Data cleared. File closed.");
    }
}