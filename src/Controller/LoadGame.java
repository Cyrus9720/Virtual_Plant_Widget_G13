package Controller;

import Model.*;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadGame {
    public static void loadGame(List<Plant> plantList) {
        try (BufferedReader reader = new BufferedReader(new FileReader("game_save.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] plantData = line.split("\\|"); // Split
                if (plantData.length != 6) { // Check if the data format is valid
                    System.err.println("Invalid data format in save file: " + line);
                    continue; // Skip this line and proceed to the next one
                }
                // Extract data for each attribute
                String name = plantData[1].trim().split(":")[1].trim();
                PlantArt plantArt = PlantArt.valueOf(plantData[0].trim().split(":")[1].trim());
                int nbrOfLives = Integer.parseInt(plantData[4].trim().split(":")[1].trim());
                ImageIcon plantPicture = new ImageIcon(plantData[5].trim().split(":")[1].trim());
                int plantLevel = Integer.parseInt(plantData[2].trim().split(":")[1].trim());

                Plant plant = new Plant(name, plantArt, nbrOfLives, plantPicture, plantLevel);
                plantList.add(plant);
            }
            System.out.println("Game loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error loading game: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error parsing data from save file: " + e.getMessage());
        }
    }


}
