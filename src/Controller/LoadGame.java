package Controller;

import Model.*;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class LoadGame {
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private static LocalDateTime timestamp;

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
                if (plantData.length != 8) { // Check if the data format is valid
                    System.err.println("Invalid data format in save file: " + line);
                    continue;
                }
                // Extract data for each attribute
                String plantType = plantData[0].trim().split(";")[1].trim();
                PlantArt plantArt = PlantArt.valueOf(plantType.toUpperCase()); // Assuming PlantArt enum values are in uppercase
                String name = plantData[1].trim().split(";")[1].trim();
                int plantLevel = Integer.parseInt(plantData[2].trim().split(";")[1].trim());
                int timesWatered = Integer.parseInt(plantData[3].trim().split(";")[1].trim());
                int nbrOfLives = Integer.parseInt(plantData[4].trim().split(";")[1].trim());
                ImageIcon plantPicture = new ImageIcon(plantData[5].trim().split(";")[1].trim());
                LocalDateTime lastWatered = parseTimestamp(plantData[6].trim().split(";")[1].trim());
                LocalDateTime lastPlayed = parseTimestamp(plantData[7].trim().split(";")[1].trim());

                // Create a new Plant object based on plant type
                Plant plant;
                switch (plantArt) {
                    case ROSE:
                        plant = new Rose(name, plantArt, timesWatered, nbrOfLives, plantPicture,plantLevel);
                        break;
                    case SUNFLOWER:
                        plant = new Sunflower(name, plantArt, timesWatered, nbrOfLives, plantPicture, plantLevel);
                        break;
                    case TOMATO_PLANT:
                        plant = new TomatoPlant(name, plantArt, timesWatered, nbrOfLives, plantPicture, plantLevel);
                        break;
                    default:
                        System.err.println("Unknown plant type: " + plantType);
                        continue;
                }

                // Add the plant to the list
                plantList.add(plant);
            }
            System.out.println("Game loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error loading game: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error parsing data from save file: " + e.getMessage());
        }
        return plantList; // Return the list of Plant objects
    }

    private static LocalDateTime parseTimestamp(String timestampString) {
        try {
            // Manuellt tolka tidsstämpeln
            LocalDateTime parsedDateTime = LocalDateTime.parse(timestampString, dateFormat);
            return parsedDateTime;
        } catch (DateTimeParseException e) {
            // Om tolkningen misslyckas, skriv ut felmeddelande och returnera null
            System.err.println("Error parsing timestamp from save file: " + e.getMessage());
            return null;
        }
    }


    public static Plant findPlantByName(List<Plant> plantList, String name) {
        for (Plant plant : plantList) {
            if (plant.getPlantName().equals(name)) {
                return plant; // Return the plant if found
            }
        }
        return null; // Return null if the plant is not found
    }

    public static LocalDateTime getTimestamp() {
        return timestamp;
    }
}
