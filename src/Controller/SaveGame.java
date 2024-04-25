package Controller;

import Model.Plant;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SaveGame {
    public static void saveGame(List<Plant> plantList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("game_save.txt"))) {
            // Write each plant's data to the file
            for (Plant plant : plantList) {
                String data = plant.toString();
                writer.write(data);
                writer.newLine();
            }
            System.out.println("Game saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving game: " + e.getMessage());
        }
    }
}
