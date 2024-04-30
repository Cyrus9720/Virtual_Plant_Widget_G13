package Controller;

import Model.Plant;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class SaveGame {
    private static LocalDateTime timestamp;

    /**
     * Saves the game data to a file, including a timestamp at the end of each line.
     *
     * @param plantList the list of plants to save
     */
    public static void saveGame(ArrayList<Plant> plantList) {
        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("game_save.txt"))) {
            for (Plant plant : plantList) {
                String data = plant.toString(); // Assuming this returns the plant's attributes in the expected format

                // Add the formatted timestamp to the end of the line
                data += " | Timestamp; " + timestamp.format(formatter);

                writer.write(data);
                writer.newLine();
            }
            System.out.println("Game saved successfully.");
            setTimestamp(timestamp); // Set the timestamp after saving
        } catch (IOException e) {
            System.err.println("Error saving game: " + e.getMessage());
        }
    }

    public static LocalDateTime getTimestamp() {
        return timestamp;
    }

    private static void setTimestamp(LocalDateTime timestamp) {
        SaveGame.timestamp = timestamp;
    }
}
