package Controller;

import Model.Plant;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A class for saving game data to a file
 * This class provides a static method to save game data to a single file
 * Each Line in the file contains data for a single Plant object, with attributes seperated by '|' characters.
 * The line also includes a timeStamp for when the application is closed.
 *
 * @author Anna Granberg
 */

public class SaveGame {
    private static Timestamp timestamp;

    /**
     * Saves the game data to a file, including a timestamp at the end of each line.
     *
     * @param plantList the list of plants to save
     * @return void
     * @author Anna Granberg
     */
    public static void saveGame(ArrayList<Plant> plantList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("game_save.txt"))) {
            // Write each plant's data to the file
            for (Plant plant : plantList) {
                String data = plant.toString();

                // Get the current time
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                timestamp = Timestamp.valueOf(dateFormat.format(new Date()));


                // Add the time to the end of the line
                data += " | Timestamp: " + timestamp;

                writer.write(data);
                writer.newLine();
            }
            System.out.println("Game saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving game: " + e.getMessage());
        }
    }

    public static Timestamp getTimestamp() {
        return timestamp;
    }
}
