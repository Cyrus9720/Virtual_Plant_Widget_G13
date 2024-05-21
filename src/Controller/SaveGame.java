package Controller;

import Model.Plant;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
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
    public void saveGame(ArrayList<Plant> plantList, Controller controller) {
        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("game_save.txt"))) {
            for (Plant plant : plantList) {
                String data = plant.toString(); // returns the plant's attributes as a String

                // Add the formatted timestamp to the end of the line
                data += " | Timestamp; " + timestamp.format(formatter);
                data += " | Death time; " + getFormattedDeathTimer(controller.getRemainingTime());

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
     * @param remainingTime
     * @return the formatted time string
     */

    public String getFormattedDeathTimer(Duration remainingTime) {
        if (remainingTime == null) {
            return "00:00:00"; // or any other default value you prefer
        }

        long timeUntilDeathMillis = remainingTime.toMillis();

        // Check if the time is negative and set it to 0 if it is
        if (timeUntilDeathMillis < 0) {
            timeUntilDeathMillis = 0;
        }

        // Convert milliseconds to hours, minutes, and seconds
        long seconds = timeUntilDeathMillis / 1000; // Convert milliseconds to seconds
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        seconds = seconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }



    public static LocalDateTime getTimestamp() {
        return timestamp;
    }

    private static void setTimestamp(LocalDateTime timestamp) {
        SaveGame.timestamp = timestamp;
    }

}
