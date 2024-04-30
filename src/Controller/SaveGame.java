package Controller;
import Model.Plant;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class SaveGame {
    private static Timestamp timestamp;

    /**
     * Saves the game data to a file, including a timestamp at the end of each line.
     *
     * @param plantList the list of plants to save
     */
    public static void saveGame(ArrayList<Plant> plantList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("game_save.txt"))) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            for (Plant plant : plantList) {
                String data = plant.toString(); // Assuming this returns the plant's attributes in the expected format

                // Get the current time
                timestamp = new Timestamp(System.currentTimeMillis());

                // Add the time to the end of the line
                data += " | Timestamp: " + dateFormat.format(timestamp);

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
