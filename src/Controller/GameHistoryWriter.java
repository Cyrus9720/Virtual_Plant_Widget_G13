package Controller;

import Model.Plant;
import Model.PlantArt;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The GameHistoryWriter class writes the history of dead plants to a file.
 */

public class GameHistoryWriter {

    /**
     * Writes the game history of dead plants to a file.
     * @param deadPlants The list of dead plants to be written to the game history.
     * @author Anna Granberg
     */

    public static void GameHistoryWriter(ArrayList<Plant> deadPlants) {
        // Use try-with-resources to automatically close BufferedWriter
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("gameHistory.txt",true))) {
            // Loop through each dead plant in the list
            for (Plant plant : deadPlants) {
                // Extract the plant name, plant art, and times watered
                String name = plant.getPlantName();
                PlantArt art = plant.getPlantArt();
                int plantLevel = plant.getPlantLevel();
                ImageIcon imagePath = plant.getPlantPicture();

                // Create a string representation with the required data
                String data = "Plant name: " + name + " | Plant art: " + art + " | Plant level: " + plantLevel + " | Plant Picture: " + imagePath;

                // Write the string representation to the file and add a new line
                writer.write(data);
                writer.newLine();
            }
            System.out.println("Game history saved successfully.");
        } catch (IOException e) {
            // Handle any IO errors
            System.err.println("Error saving game history: " + e.getMessage());
        }
    }
}
