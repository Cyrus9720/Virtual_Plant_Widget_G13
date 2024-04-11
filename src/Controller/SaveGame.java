package Controller;
import Model.Plant;

import java.io.*;
import java.util.List;

public class SaveGame {

    public SaveGame(List<Plant> plantList){
        SaveGame(plantList);
    }
    public static void SaveGame(List<Plant> plantList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("game_save.txt", true))) {
            // Skapa en sträng med speldata
            String data = "Plantor:  " + plantList.toString() + "/n tid:" + System.currentTimeMillis();
            System.out.println("Data sparad");
            // Skriv data till filen
            writer.write(data);
            writer.newLine(); // Lägg till en ny rad för nästa inspelning
        } catch (IOException e) {
            System.err.println("Error saving game: " + e.getMessage());
        }
    }
}