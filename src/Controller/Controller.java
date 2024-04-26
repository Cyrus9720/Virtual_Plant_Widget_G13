package Controller;

import Model.*;
import View.ButtonType;
import View.CenterPanel;
import View.MainFrame;

import javax.swing.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Controller {
    private MainFrame view;
    private List<Plant> plantList = new ArrayList<>();
    private CenterPanel centerPanel;
    private Plant currentPlant;

    public Controller() {
        // Skapa ditt MainFrame-objekt efter att plantorna har skapats
        view = new MainFrame(this);

        // Skapa och konfigurera CenterPanel
        centerPanel = new CenterPanel(400, 400);
        view.add(centerPanel);

        // Load the game data
        loadGame();
        // initialize garden
        garden();

        checkWateringStatus();
    }

    private void garden() {
        if (plantList.isEmpty()) {
            plantList.add(new Rose("Rose", PlantArt.ROSE, 3, 0,new ImageIcon("src/Images/PotArt1.JPG"), 0));
            plantList.add(new Sunflower("Sunflower", PlantArt.SUNFLOWER, 3,0, new ImageIcon("src/Images/PotArt1.JPG"), 0));
            plantList.add(new TomatoPlant("TomatoPlant", PlantArt.TOMATO_PLANT, 3,0, new ImageIcon("src/Images/PotArt1.JPG"),0));
        }
    }

    public void buttonPressed(ButtonType button) {
        switch (button) {
            case Water:
                Plant plant = plantList.get(0);
                plant.waterPlant();

                plant.setLastWatered(new Timestamp(System.currentTimeMillis()));

                ImageIcon updatedImage = plant.getPlantPicture();
                centerPanel.updatePlantImage(updatedImage);

                break;

        }
    }

    /**
     * Checks if the plants need to be watered based on a certain timestamp (24h).
     *
     * @author Anna Granberg
     */
    private void checkWateringStatus() {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (Plant plant : plantList) {
            Timestamp lastWatered = plant.getLastWatered(); // Retrieve the Timestamp object

            if (lastWatered == null) {
                System.err.println("Plant last watered timestamp is null");
                continue; // Skip this plant and move on to the next one
            }

            String lastWateredString = dateFormat.format(lastWatered); // Format the Timestamp as a string

            long timeSinceLastWatered = currentTimestamp.getTime() - lastWatered.getTime();
            long wateringInterval = 24 * 60 * 60 * 1000; // 24 hours in milliseconds

            System.out.println("Last watered: " + lastWateredString);

            if (timeSinceLastWatered >= wateringInterval) {
                // Plant needs to be watered
                view.timeToWater();
            }
        }
    }


    // fungerar inte för tillfället ?
    public int getNbrOfLives() {
        if (!plantList.isEmpty()) { // Check if plantList is not empty
            Plant firstPlant = plantList.get(0); // Get the first plant if available
            if (firstPlant != null) { // Check if the first plant is not null
                return firstPlant.getNbrOfLives();
            } else {
                // Handle the case when the first plant is null
                System.err.println("First plant is null");
                return 3;
            }
        } else {
             System.err.println("Plant list is empty");
            return 3;
        }
    }

    // fungerar
    public int getTimesWatered(){
        if (!plantList.isEmpty()) { // Check if plantList is not empty
            Plant firstPlant = plantList.get(0); // Get the first plant if available
            if (firstPlant != null) { // Check if the first plant is not null
                System.out.println("times watered: " + firstPlant.getTimesWatered());
                return firstPlant.getTimesWatered();

            } else {
                System.err.println("First plant is null");
                return 0;
            }
        } else {
            System.err.println("Plant list is empty");
            return 0;
        }
    }

    // fungerar
    public int getPlantLevel(){
        if (!plantList.isEmpty()) { // Check if plantList is not empty
            Plant firstPlant = plantList.get(0); // Get the first plant if available
            if (firstPlant != null) { // Check if the first plant is not null
                return firstPlant.getPlantLevel();
            } else {
                System.err.println("First plant is null");
                return 0;
            }
        } else {
            System.err.println("Plant list is empty");
            return 0;
        }
    }

    public List<ImageIcon> getPlantImages() {
        List<Plant> plantList = getPlantList();
        List<ImageIcon> plantImages = new ArrayList<>();

        // Omvandla Plant-objekten till ImageIcon-objekt
        for (Plant plant : plantList) {
            // Kontrollera om Plant-objektet har en bild
            if (plant.getPlantPicture() != null) {
                plantImages.add(plant.getPlantPicture());
            }
        }

        return plantImages;
    }

    public void switchPlant(){
        // @todo implementera logik för att byta planta i gardenview
    }

    public void saveGame() {
        SaveGame.saveGame(getPlantList());
    }

    public List<Plant> loadGame(){
        plantList = LoadGame.loadGame(plantList);
        return plantList;
    }

    public List<Plant> getPlantList() {
        return plantList;
    }

    public Plant getCurrentPlant() {
        return currentPlant;
    }

    public void setCurrentPlant(Plant newPlant) {
        currentPlant = newPlant;
    }
}
