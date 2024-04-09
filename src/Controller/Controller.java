package Controller;

import Model.*;
import View.ButtonType;
import View.CenterPanel;
import View.MainFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private MainFrame view;
    private List<Plant> plantList = new ArrayList<>();
    private CenterPanel centerPanel;
    private GameProcessor gameProcessor = new GameProcessor();


    public Controller() {
        // Skapa din lista över plantor och lägg till plantorna
        plantList.add(new Rose("Rose", PlantArt.ROSE, 0, new ImageIcon("src/Images/rose 1.jpeg"), 0));

        // Skapa ditt MainFrame-objekt efter att plantorna har skapats
        view = new MainFrame(this);

        // Skapa och konfigurera CenterPanel
        centerPanel = new CenterPanel(400, 400);
        view.add(centerPanel);
    }

    public void buttonPressed(ButtonType button) {
        switch (button) {
            case Water:
                Plant rose = plantList.get(0);
                rose.waterPlant();
                ImageIcon updatedImage = rose.getPlantPicture();
                centerPanel.updatePlantImage(updatedImage);

                break;
            // Handle other button types as needed
        }
    }

    public int getNbrOfLives() {
        if (!plantList.isEmpty()) { // Check if plantList is not empty
            Plant firstPlant = plantList.get(0); // Get the first plant if available
            if (firstPlant != null) { // Check if the first plant is not null
                return firstPlant.getNbrOfLives();
            } else {
                // Handle the case when the first plant is null
                System.err.println("First plant is null");
                return 0; // Return a default value or handle it based on your application logic
            }
        } else {
            // Handle the case when plantList is empty
            System.err.println("Plant list is empty");
            return 0; // Return a default value or handle it based on your application logic
        }
    }

    public int getTimesWatered(){
        if (!plantList.isEmpty()) { // Check if plantList is not empty
            Plant firstPlant = plantList.get(0); // Get the first plant if available
            if (firstPlant != null) { // Check if the first plant is not null
                System.out.println("times watered: " + firstPlant.getTimesWatered());
                return firstPlant.getTimesWatered();

            } else {
                // Handle the case when the first plant is null
                System.err.println("First plant is null");
                return 0; // Return a default value or handle it based on your application logic
            }
        } else {
            System.err.println("Plant list is empty");
            return 0; // Return a default value or handle it based on your application logic
        }
    }

    public int getPlantLevel(){
        if (!plantList.isEmpty()) { // Check if plantList is not empty
            Plant firstPlant = plantList.get(0); // Get the first plant if available
            if (firstPlant != null) { // Check if the first plant is not null
                return firstPlant.getPlantLevel();
            } else {
                // Handle the case when the first plant is null
                System.err.println("First plant is null");
                return 0; // Return a default value or handle it based on your application logic
            }
        } else {
            System.err.println("Plant list is empty");
            return 0; // Return a default value or handle it based on your application logic
        }
    }

    // Metod för att återställa tillståndet för varje växt
    public void restorePlantsState() {
        // Skapa en ny lista för de återställda växterna
        List<Plant> restoredPlants = new ArrayList<>();

        // Läs in sparade tillståndet för varje växt
        List<String> savedData = GameLoad.loadGame();

        // Loopa genom sparade datan och skapa nya växtinstanser med tillståndet
        for (String data : savedData) {
            // Dela upp datasträngen vid kommatecken för att extrahera individuella delar
            String[] parts = data.split(",");

            String plantData = ""; // Variabel för att lagra information om plantor

            // Processa varje del av datasträngen
            for (String part : parts) {
                if (part.startsWith("Plantor:")) {
                    // Extrahera information om plantor
                    plantData = part.substring(part.indexOf("[") + 1, part.indexOf("]"));
                    break; // Vi behöver inte fortsätta loopa efter att vi har hittat plantData
                }
            }

            // Skapa en ny växt med tillståndet från plantData
            Plant restoredPlant = processPlantData(plantData);

            // Lägg till den återställda växten i listan
            restoredPlants.add(restoredPlant);
        }

        // Uppdatera referensen till plantList med den nya listan av återställda växter
        plantList = restoredPlants;

        // Uppdatera vyn för att visa de återställda växterna
        // .updatePlantsView(plantList);
    }

    private Plant processPlantData(String plantData) {
        String[] parts = plantData.split("\\|");
        String name = "";
        PlantArt art = null;
        int level = 0;
        int timesWatered = 0;
        String plantPictureString = "";
        ImageIcon plantPicture = null;

        for (String part : parts) {
            if (part.startsWith("Plant name:")) {
                name = part.substring(part.indexOf(":") + 1).trim();
            } else if (part.startsWith("Plant art:")) {
                String artString = part.substring(part.indexOf(":") + 1).trim();
                art = PlantArt.valueOf(artString.toUpperCase());
            } else if (part.startsWith("Plant level:")) {
                level = Integer.parseInt(part.substring(part.indexOf(":") + 1).trim());
            } else if(part.startsWith("Plant picture:")){
                plantPictureString = part.substring(part.indexOf(":") + 1).trim();
                plantPicture = new ImageIcon(plantPictureString);
            } else if (part.startsWith("Times watered:")) {
                timesWatered = Integer.parseInt(part.substring(part.indexOf(":") + 1).trim());
            }
        }

        // Skapa en ny Plant-instans med extraherad information och returnera den
        return new Plant(name, art, level, plantPicture, timesWatered);
    }


    public void saveGame() {
        SaveGame saveGame = new SaveGame(plantList);
    }

    public List<Plant> getPlantList() {
        return plantList;
    }
}
