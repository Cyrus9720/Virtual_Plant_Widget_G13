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
                // data += " | Death time; " + getFormattedDeathTimer(controller.getRemainingTime());

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
            return "0000-00-00 00:00:00.000"; // or any other default value you prefer
        }

        // Calculate the end time based on the remaining time from the current time
        LocalDateTime endTime = LocalDateTime.now().plus(remainingTime);

        // Format the end time using the specified DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String formattedEndTime = endTime.format(formatter);

        return formattedEndTime;
    }

    public static LocalDateTime getTimestamp() {
        return timestamp;
    }

    private static void setTimestamp(LocalDateTime timestamp) {
        SaveGame.timestamp = timestamp;
    }

}
