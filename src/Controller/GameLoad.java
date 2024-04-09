package Controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The GameLoad class provides functionality to load saved game data from a file.
 */
public class GameLoad {

    /**
     * Loads the saved game data from the file "game_save.txt" and returns it as a list of strings.
     * Each line of the file represents a piece of saved data.
     *
     * @return A list of strings containing the saved game data, or an empty list if the file does not exist or an error occurs.
     */
    public static List<String> loadGame() {
        List<String> savedData = new ArrayList<>(); // Create a new list to store the saved data
        try (BufferedReader reader = new BufferedReader(new FileReader("game_save.txt"))) {
            String line;
            // Read each line from the file and add it to the list
            while ((line = reader.readLine()) != null) {
                savedData.add(line);
            }
        } catch (IOException e) {
            // Handle any errors that occur during file reading
            System.err.println("Error loading game: " + e.getMessage());
        }
        return savedData; // Return the list of saved data
    }
}
