package Controller;

import Model.Plant;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GameHistoryWriter {
    public static void historyWriter(ArrayList<Plant> deadPlants) {
        // Använd try-with-resources för att automatiskt stänga BufferedWriter
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("gameHistory.txt"))) {
            // Loopa igenom varje död växt i listan
            for (Plant plant : deadPlants) {
                // Konvertera växtens attribut till en sträng representation
                String data = plant.toString(); // Antag att toString-metoden ger en lämplig strängrepresentation

                // Skriv strängrepresentationen till filen och lägg till en ny rad
                writer.write(data);
                writer.newLine();
            }
            System.out.println("Game history saved successfully.");
        } catch (IOException e) {
            // Hantera eventuella IO-fel
            System.err.println("Error saving game history: " + e.getMessage());
        }
    }
}
