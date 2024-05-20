package Controller;

import Model.Plant;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * A utility class for saving game data to a file.
 * @author annagranberg
 */
public class SaveGame {
    private static LocalDateTime timestamp;
    private static Controller controller;

    /**
     * Saves the game data to a file, including a timestamp at the end of each line.
     *
     * @param plantList the list of plants to save
     */
    public static void saveGame(ArrayList<Plant> plantList, Controller controller) {
        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("game_save.txt"))) {
            for (Plant plant : plantList) {
                String data = plant.toString(); // returns the plant's attributes as a String

                // Add the formatted timestamp to the end of the line
                data += " | Timestamp; " + timestamp.format(formatter);
                data += " | Death time; " + getFormattedDeathTimer(controller.getRemainingDeathTimerMilliseconds(plant));

                writer.write(data);
                writer.newLine();
            }
            System.out.println("Game saved successfully.");
            setTimestamp(timestamp); // Set the timestamp after saving
        } catch (IOException e) {
            System.err.println("Error saving game: " + e.getMessage());
        }
    }

    /**
     * Converts milliseconds to a formatted time string in mm:ss format.
     *
     * @param milliseconds the time in milliseconds
     * @return the formatted time string
     */
    private static String getFormattedDeathTimer(long milliseconds) {
        long totalSeconds = milliseconds / 1000;
        long minutes = totalSeconds / 60;
        long seconds = totalSeconds % 60;

        return String.format("%02d:%02d", minutes, seconds);
    }

    public static LocalDateTime getTimestamp() {
        return timestamp;
    }

    private static void setTimestamp(LocalDateTime timestamp) {
        SaveGame.timestamp = timestamp;
    }

}
