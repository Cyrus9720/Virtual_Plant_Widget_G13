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
 * @author Anna Granberg
 */
public class SaveGame {
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
        } catch (IOException e) {
            System.err.println("Error saving game: " + e.getMessage());
        }
    }

}
