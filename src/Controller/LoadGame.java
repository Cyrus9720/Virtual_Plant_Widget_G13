package Controller;

import Model.*;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.List;

/**
 * A class for loading game data from a saved file.
 * This class provides a static method to load game data from a file and populate a list of Plants.
 * Each line in the save file should contain data for a single Plant object, with attributes separated by '|' characters.
 * The expected format for each line is:
 * "Plant art: [ART] | Plant name: [NAME] | Plant level: [LEVEL] | Times watered: [WATERED] | Number of lives: [LIVES] | Plant picture: [PICTURE_PATH]"
 *
 * @author Anna Granberg
 */
public class LoadGame {

    /**
     * Loads game data from a saved file and populates a list of Plant objects.
     *
     * @param plantList The list to populate with loaded Plant objects.
     * @return The list of Plant objects populated with data from the save file.
     */
    public static List<Plant> loadGame(List<Plant> plantList) {
        try (BufferedReader reader = new BufferedReader(new FileReader("game_save.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] plantData = line.split("\\|"); // Split
                if (plantData.length != 6) { // Check if the data format is valid
                    System.err.println("Invalid data format in save file: " + line);
                    continue;
                }
                // Extract data for each attribute
                PlantArt plantArt = PlantArt.valueOf(plantData[0].trim().split(":")[1].trim());
                String name = plantData[1].trim().split(":")[1].trim();
                int plantLevel = Integer.parseInt(plantData[2].trim().split(":")[1].trim());
                int timesWatered = Integer.parseInt(plantData[3].trim().split(":")[1].trim());
                int nbrOfLives = Integer.parseInt(plantData[4].trim().split(":")[1].trim());
                ImageIcon plantPicture = new ImageIcon(plantData[5].trim().split(":")[1].trim());

                // Check if a plant with the same name and art already exists in plantList to not duplicate plants
                boolean alreadyExists = false;
                for (Plant plant : plantList) {
                    if (plant.getPlantName().equals(name) && plant.getPlantArt() == plantArt) {
                        alreadyExists = true;
                        break;
                    }
                }

                // Add the plant to plantList only if it doesn't already exist
                if (!alreadyExists) {
                    Plant plant = new Plant(name, plantArt, nbrOfLives, plantPicture, plantLevel);
                    plantList.add(plant);
                }
            }
            System.out.println("Game loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error loading game: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error parsing data from save file: " + e.getMessage());
        }
        String filePath = "game_save.txt";
        clearFile(filePath);

        return plantList; // Return the list of Plant objects
    }


    public static void clearFile(String filePath) {
        try {
            FileWriter writer = new FileWriter(filePath, false);
            writer.write(""); // Skriver över befintligt innehåll med en tom sträng
            writer.close();
            System.out.println("File content cleared successfully.");
        } catch (IOException e) {
            System.err.println("Error clearing file content: " + e.getMessage());
        }
    }
}
