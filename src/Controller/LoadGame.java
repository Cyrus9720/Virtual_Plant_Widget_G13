package Controller;

import Model.*;
import View.MainFrame;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * A utility class for loading game data from a saved file and populating a list of Plant objects.
 * @author annagranberg
 */

public class LoadGame {
    private LocalDateTime timestamp;
    private boolean fileNotEmpty;
    private MainFrame view;
    private Plant plant;

    /**
     * Loads game data from a saved file and populates a list of Plant objects.
     *
     * @param plantList The list to populate with loaded Plant objects.
     * @return The list of Plant objects populated with data from the save file.
     */
    public List<Plant> loadGame(List<Plant> plantList, Controller controller) {

        view = controller.getView();

        try (BufferedReader reader = new BufferedReader(new FileReader("game_save.txt"))) {

            String line;
            fileNotEmpty = false; // Flagga för att checka ifall filen är tom

            while ((line = reader.readLine()) != null) {
                fileNotEmpty = true;
                String[] plantData = line.split("\\|"); // Split
                if (plantData.length != 9) { // Check if the data format is valid
                    System.err.println("Invalid data format in save file: " + line);
                    continue;
                }
                // Extrahera data för varje attribut i strängen
                String plantType = plantData[0].trim().split(";")[1].trim();
                PlantArt plantArt = PlantArt.valueOf(plantType.toUpperCase());
                String name = plantData[1].trim().split(";")[1].trim();
                int plantLevel = Integer.parseInt(plantData[2].trim().split(";")[1].trim());
                int timesWatered = Integer.parseInt(plantData[3].trim().split(";")[1].trim());
                int nbrOfLives = Integer.parseInt(plantData[4].trim().split(";")[1].trim());
                ImageIcon plantPicture = new ImageIcon(plantData[5].trim().split(";")[1].trim());
                LocalDateTime lastWatered = parseTimestamp(plantData[6].trim().split(";")[1].trim());
                LocalDateTime lastPlayed = parseTimestamp(plantData[7].trim().split(";")[1].trim());
                LocalDateTime deathTimeData = parseTimestamp(plantData[8].trim().split("; ")[1]);

                // Create new plants based on the plantArt
                switch (plantArt) {
                    case ROSE:
                        plant = new Rose(controller, name, plantArt, nbrOfLives, timesWatered, plantPicture, plantLevel, lastWatered);
                        plant.setDeathTime(deathTimeData);
                        break;
                    case SUNFLOWER:
                        plant = new Sunflower(controller, name, plantArt, nbrOfLives, timesWatered, plantPicture, plantLevel, lastWatered);
                        plant.setDeathTime(deathTimeData);
                        break;
                    case TOMATO_PLANT:
                        plant = new TomatoPlant(controller,name, plantArt, nbrOfLives, timesWatered, plantPicture, plantLevel, lastWatered);
                        plant.setDeathTime(deathTimeData);
                        break;
                    case BLACKBERRY:
                        plant = new Blackberry(controller, name, plantArt, nbrOfLives, timesWatered, plantPicture, plantLevel, lastWatered);
                        plant.setDeathTime(deathTimeData);
                        break;
                    case CACTUS:
                        plant = new Cactus(controller, name, plantArt, nbrOfLives, timesWatered, plantPicture, plantLevel, lastWatered);
                        plant.setDeathTime(deathTimeData);
                        break;
                    case MINI_TREE:
                        plant = new MiniTree(controller, name, plantArt, nbrOfLives, timesWatered, plantPicture, plantLevel, lastWatered);
                        plant.setDeathTime(deathTimeData);
                        break;
                    default:
                        System.err.println("Unknown plant type: " + plantType);
                        continue;
                }

                // Add the new plant to the list
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

    /**
     * Parses a timestamp string into a LocalDateTime object.
     *
     * @param timestampString The string representation of the timestamp.
     * @return The parsed LocalDateTime object.
     */
    private LocalDateTime parseTimestamp(String timestampString) {
        try {
            if ("0000-00-00 00:00:00.000".equals(timestampString)) {
                return null;
            } else {
                // Manually parse the timestamp
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
                LocalDateTime parsedDateTime = LocalDateTime.parse(timestampString, dateFormat);
                return parsedDateTime;
            }
        } catch (DateTimeParseException e) {
            // If parsing fails, print error message and return null
            System.err.println("Error parsing timestamp from save file: " + e.getMessage());
            return null;
        }
    }

    /**
     * Gets the last loaded plant.
     *
     * @return The last loaded plant object.
     */
    public Plant getPlant() {
        return plant;
    }

    /**
     * Gets the timestamp of the last save.
     *
     * @return The timestamp of the last save.
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Checks if the save file was not empty.
     *
     * @return true if the save file was not empty, false otherwise.
     */
    public boolean isFileNotEmpty() {
        return fileNotEmpty;
    }
}
