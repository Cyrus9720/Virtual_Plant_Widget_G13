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
    private Plant currentPlant;

    public Controller() {
        // Skapa ditt MainFrame-objekt efter att plantorna har skapats
        view = new MainFrame(this);
        // LoadGame.loadGame();
        // Skapa och konfigurera CenterPanel
        centerPanel = new CenterPanel(400, 400);
        view.add(centerPanel);
        garden();
    }

    private void garden() {
        plantList.add(new Plant("Rose", PlantArt.ROSE, 0, new ImageIcon("src/Images/PotArt1.JPG"),0));
        plantList.add(new Plant("Sunflower", PlantArt.SUNFLOWER, 0, new ImageIcon("src/Images/PotArt1.JPG"), 0));
        plantList.add(new Plant("TomatoPlant", PlantArt.TOMATO_PLANT, 0, new ImageIcon("src/Images/PotArt1.JPG"),0));
    }

    public void buttonPressed(ButtonType button) {
        switch (button) {
            case Water:
                Plant plant = plantList.get(0);
                plant.waterPlant();
                ImageIcon updatedImage = plant.getPlantPicture();
                centerPanel.updatePlantImage(updatedImage);

                break;

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
                return 0;
            }
        } else {
            System.err.println("Plant list is empty");
            return 0;
        }
    }

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
        
    }

    public void saveGame() {
        SaveGame.saveGame(getPlantList());
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
