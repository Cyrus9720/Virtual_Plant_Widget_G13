package Controller;

import Model.*;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadGame {

    public static List<Plant> loadGame() {
        List<Plant> plantList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("game_save.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {

                String[] plantData = line.split(","); // Split data assuming comma-separated values
                String name = plantData[0];
                PlantArt plantArt = PlantArt.valueOf(plantData[1]); // Antag att plantArt är en enum
                int nbrOfLives = Integer.parseInt(plantData[2]);
                ImageIcon plantPicture = new ImageIcon(plantData[3]); // Antag att växtbilden är en sträng med filvägen till bilden
                int plantLevel = Integer.parseInt(plantData[4]);

                Plant plant = new Plant(name, plantArt, nbrOfLives, plantPicture, plantLevel);
                plantList.add(plant);
            }
            System.out.println("Game loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error loading game: " + e.getMessage());
        }
        return plantList;
    }
}
