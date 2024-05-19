package Controller;

import Model.*;
import View.MainFrame;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * A utility class for loading game data from a saved file and populating a list of Plant objects.
 * @author anna granberg
 */
public class LoadGame {
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private static LocalDateTime timestamp;
    private static boolean fileNotEmpty;
    private static MainFrame view;
    private static Plant plant;

    /**
     * Loads game data from a saved file and populates a list of Plant objects.
     *
     * @param plantList The list to populate with loaded Plant objects.
     * @return The list of Plant objects populated with data from the save file.
     */
    public static List<Plant> loadGame(List<Plant> plantList, Controller controller) {

        view = controller.getView();

        try (BufferedReader reader = new BufferedReader(new FileReader("game_save.txt"))) {

            String line;
            fileNotEmpty = false; // Flag to check if the file is empty

            while ((line = reader.readLine()) != null) {
                fileNotEmpty = true;
                String[] plantData = line.split("\\|"); // Split
                if (plantData.length != 9) { // Check if the data format is valid
                    System.err.println("Invalid data format in save file: " + line);
                    continue;
                }
                // Extract data for each attribute in the string
                String plantType = plantData[0].trim().split(";")[1].trim();
                PlantArt plantArt = PlantArt.valueOf(plantType.toUpperCase());
                String name = plantData[1].trim().split(";")[1].trim();
                int plantLevel = Integer.parseInt(plantData[2].trim().split(";")[1].trim());
                int timesWatered = Integer.parseInt(plantData[3].trim().split(";")[1].trim());
                int nbrOfLives = Integer.parseInt(plantData[4].trim().split(";")[1].trim());
                ImageIcon plantPicture = new ImageIcon(plantData[5].trim().split(";")[1].trim());
                LocalDateTime lastWatered = parseTimestamp(plantData[6].trim().split(";")[1].trim());
                LocalDateTime lastPlayed = parseTimestamp(plantData[7].trim().split(";")[1].trim());
                long remainingDeathTimer = parseDeathTimer(plantData[8].trim().split(";")[1].trim());

                // Create "new" plants depending on plantArt
                switch (plantArt) {
                    case ROSE:
                        plant = new Rose(name, plantArt, nbrOfLives, timesWatered, plantPicture, plantLevel, lastWatered);
                        break;
                    case SUNFLOWER:
                        plant = new Sunflower(name, plantArt, nbrOfLives, timesWatered, plantPicture, plantLevel, lastWatered);
                        break;
                    case TOMATO_PLANT:
                        plant = new TomatoPlant(name, plantArt, nbrOfLives, timesWatered, plantPicture, plantLevel, lastWatered);
                        break;
                    case BLACKBERRY:
                        plant = new Blackberry(name, plantArt, nbrOfLives, timesWatered, plantPicture, plantLevel, lastWatered);
                        break;
                    case CACTUS:
                        plant = new Cactus(name, plantArt, nbrOfLives, timesWatered, plantPicture, plantLevel, lastWatered);
                        break;
                    case MINI_TREE:
                        plant = new MiniTree(name, plantArt, nbrOfLives, timesWatered, plantPicture, plantLevel, lastWatered);
                        break;
                    default:
                        System.err.println("Unknown plant type: " + plantType);
                        continue;
                }

                // Set the remaining death timer for the plant
                controller.setRemainingDeathTimerMilliseconds(remainingDeathTimer);

                // Add the "new" plant to the list
                plantList.add(plant);
                // clearSaveFile();
            }

            if (fileNotEmpty) {  // If the file is not empty
                //  view.welcomeBackMessage(); todo: get this to work?
                // SaveGame.writeGamePlayedNotice();
            } else {
                // controller.firstTimePlaying();
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
    private static LocalDateTime parseTimestamp(String timestampString) {
        try {
            // Manually parse the timestamp
            LocalDateTime parsedDateTime = LocalDateTime.parse(timestampString, dateFormat);
            return parsedDateTime;
        } catch (DateTimeParseException e) {
            // If parsing fails, print error message and return null
            System.err.println("Error parsing timestamp from save file: " + e.getMessage());
            return null;
        }
    }

    /**
     * Parses the death timer string into a long value representing the remaining milliseconds.
     *
     * @param deathTimerString The string representation of the death timer.
     * @return The parsed long value representing the remaining milliseconds.
     */
    private static long parseDeathTimer(String deathTimerString) {
        try {
            // Parse the death timer string to a long value
            return Long.parseLong(deathTimerString);
        } catch (NumberFormatException e) {
            // If parsing fails, print error message and return 0
            System.err.println("Error parsing death timer from save file: " + e.getMessage());
            return 0;
        }
    }

    public static Plant getPlant() {
        return plant;
    }

    public static LocalDateTime getTimestamp() {
        return timestamp;
    }

    public static boolean isFileNotEmpty() {
        return fileNotEmpty;
    }
}
