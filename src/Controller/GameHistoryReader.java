package Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameHistoryReader {
    private static ArrayList<String> gameHistory;
    /**
     * Retrieves the game history from a file.
     *
     * @return The list containing the game history data.
     */
    public static ArrayList<String> getGameHistory() {
        gameHistory = new ArrayList<>();

        // Read game history data from a file
        try (BufferedReader reader = new BufferedReader(new FileReader("gameHistory.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                gameHistory.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading game history: " + e.getMessage());
        }

        return gameHistory;
    }

}
