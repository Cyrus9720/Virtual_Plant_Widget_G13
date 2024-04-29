package Controller;

import Model.*;
import View.ButtonType;
import View.CenterPanel;
import View.MainFrame;

import javax.swing.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private MainFrame view;
    private List<Plant> plantList = new ArrayList<>();
    private Plant[] plants;
    private CenterPanel centerPanel;
    private Plant currentPlant;
    private int currentPlantIndex;
    private int nbrOfPlants;

    public Controller() {
        // initialize mainframe
        view = new MainFrame(this);

        // Load the saved game data
        loadGame();

        // initialize garden
        garden();

        // check the watering status
        checkWateringStatus();
    }

    private void garden() {
        plants = new Plant[] {
                new Rose("Rose", PlantArt.ROSE, 3,0, new ImageIcon("src/Images/PotArt1.JPG"), 0),
                new Sunflower("Sunflower", PlantArt.SUNFLOWER, 3,0, new ImageIcon("src/Images/PotArt1.JPG"), 0),
                new TomatoPlant("TomatoPlant", PlantArt.TOMATO_PLANT, 3,0, new ImageIcon("src/Images/PotArt1.JPG"), 0),
        };

    }
    public void buttonPressed(ButtonType button) {
        switch (button) {
            case Water:
                if (!plantList.isEmpty()) {
                    Plant plant = plantList.get(0);
                    plant.waterPlant();
                    plant.setLastWatered(new Timestamp(System.currentTimeMillis()));

                    ImageIcon updatedImage = plant.getPlantPicture();
                    view.getCenterPanel().updatePlantImage(updatedImage);
                } else {
                    System.out.println("No plants available to water.");
                }
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

    public int getTimesWatered(){
        if (!plantList.isEmpty()) {
            Plant firstPlant = plantList.get(0);
            if (firstPlant != null) {
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
        if (!plantList.isEmpty()) {
            Plant firstPlant = plantList.get(0);
            if (firstPlant != null) {
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

    public void switchPlant(String id){
        System.out.println(id + " " + plants[Integer.parseInt(id)].getPlantName());
        view.getCenterPanel().updatePlantImage(plants[Integer.parseInt(id)].getPlantPicture());
        addPlant(plants[Integer.parseInt(id)]);
        currentPlantIndex = Integer.parseInt(id);
        view.getCenterPanel().getMainPanel().refreshBar();
        //view.getCenterPanel().updatePanel(plantList.getFirst().getPlantPicture());
    }

    public void addPlant(Plant plant) {
        plantList.add(plant);
        nbrOfPlants++;
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
