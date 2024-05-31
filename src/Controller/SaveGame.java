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

    /**
     * Saves the game data to a file, including a timestamp at the end of each line.
     *
     * @param plantList the list of plants to save
     */
    public void saveGame(ArrayList<Plant> plantList, Controller controller) {
        LocalDateTime timestamp = LocalDateTime.now();
        LocalDateTime timeUntilDeath = controller.getTimeUntilDeath();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("game_save.txt"))) {
            for (Plant plant : plantList) {
                String data = plant.toString(); // returns the plant's attributes as a String
                String formattedTime = null;

                // Add the formatted timestamp to the end of the line
                data += " | Closed game; " + timestamp.format(formatter);

                if (timeUntilDeath != null) {
                    formattedTime = timeUntilDeath.format(formatter);
                    // Proceed with saving the game using the formattedTime
                } else {
                    // Handle the case when deathTime is null
                    System.out.println("Death time is not set because it's not time for a new death.");
                }
                data += " | Death time; " + formattedTime;
                data += " | Boolean night; " + controller.night;

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
            return null; // or any other default value you prefer
        } else{
            // Calculate the end time based on the remaining time from the current time
            LocalDateTime endTime = LocalDateTime.now().plus(remainingTime);

            // Format the end time using the specified DateTimeFormatter
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            String formattedEndTime = endTime.format(formatter);

            return formattedEndTime;
        }
    }

    public static LocalDateTime getTimestamp() {
        return timestamp;
    }

    private static void setTimestamp(LocalDateTime timestamp) {
        SaveGame.timestamp = timestamp;
    }

}
