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
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private static LocalDateTime timestamp;
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
            boolean fileNotEmpty = false; // Flagga för att checka ifall filen är tom

            while ((line = reader.readLine()) != null) {
                fileNotEmpty = true;
                String[] plantData = line.split("\\|"); // Split
                if (plantData.length != 8) { // Check if the data format is valid
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

                // Skapa "nya" plantor beroende på plantArt
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
                    default:
                        System.err.println("Unknown plant type: " + plantType);
                        continue;
                }

                // Lägg till den "nya" plantan i listan
                plantList.add(plant);
                // clearSaveFile();
            }

            if (fileNotEmpty) {  // ifall fil är tom
                // view.welcomeBackMessage(); todo: få detta att fungera?
                // SaveGame.writeGamePlayedNotice();
            } else{
                controller.firstTimePlaying();
            }
            System.out.println("Game loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error loading game: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error parsing data from save file: " + e.getMessage());
        }
        return plantList; // Returnera listan av Plant objekt
    }

    /**
     * Parses a timestamp string into a LocalDateTime object.
     *
     * @param timestampString The string representation of the timestamp.
     * @return The parsed LocalDateTime object.
     */
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

    /**
     * Empties the contents of the game save file after it has been read.
     */
    public static void clearSaveFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("game_save.txt"))) {
            writer.write(""); // Skriv en tom sträng till filen för att tömma den
            System.out.println("Game save file cleared successfully.");
        } catch (IOException e) {
            System.err.println("Error clearing game save file: " + e.getMessage());
        }
    }


    public static Plant getPlant() {
        return plant;
    }

    public static LocalDateTime getTimestamp() {
        return timestamp;
    }
}
