package Controller;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Skapa en instans av din Controller-klass
        Controller controller = new Controller();

        // Hämta sparad data från filen eller på annat sätt
        List<String> savedData = GameLoad.loadGame();

        // Skapa en instans av GameProcessor-klassen
        GameProcessor gameProcessor = new GameProcessor();

        // Anropa processSavedData() i GameProcessor med den sparade datan
        gameProcessor.processSavedData(savedData);
    }
}
