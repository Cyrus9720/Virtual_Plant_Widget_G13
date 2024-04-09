package Controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import Model.Plant;

public class GameLoad {
    public static List<String> loadGame() {
        List<String> savedData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("game_save.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                savedData.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error loading game: " + e.getMessage());
        }
        return savedData;
    }
}
