package sit.tu_varna.commands;

import sit.tu_varna.functionality.DataStore;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class OpenCommand implements Command {
    private DataStore dataStore;

    public OpenCommand(DataStore dataStore) {
        this.dataStore = dataStore;
    }

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