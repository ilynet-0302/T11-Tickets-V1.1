package sit.tu_varna.commands;

import sit.tu_varna.functionality.DataStore;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SaveAsCommand implements Command {
    private DataStore dataStore;

    public SaveAsCommand(DataStore dataStore) {
        this.dataStore = dataStore;
    }

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

