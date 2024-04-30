package Controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import Model.Plant;

public class Main {

    public static void main(String[] args) {
        // Skapa en lista för växter
        ArrayList<Plant> plantList = new ArrayList<>();

        // Ladda speldata från sparfilen och uppdatera plantList
       // try {
            LoadGame.loadGame(plantList);
       // } catch (Exception e) {
            //System.err.println("Error loading game data: " + e.getMessage());
            // Hantera eventuella fel här, till exempel genom att skapa en ny speldata om ingen sparfil hittades
       // }

        // Skapa en ny instans av Controller och skicka plantList som argument
        Controller controller = new Controller();
    }
}

