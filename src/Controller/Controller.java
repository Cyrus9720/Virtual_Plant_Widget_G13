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
    private ArrayList<Plant> plantList = new ArrayList<>();
    private Plant[] plants;
    private CenterPanel centerPanel;
    private int nbrOfPlants = 0;


    public Controller() {
        // Skapa din lista över plantor och lägg till plantorna
        plantList.add(new Plant("Empty", PlantArt.POT, 0, new ImageIcon("src/Images/PotArt1.JPG"), 0));

        // Skapa ditt MainFrame-objekt efter att plantorna har skapats
        view = new MainFrame(this);
        // Skapa och konfigurera CenterPanel
        centerPanel = new CenterPanel(400, 400);
        view.add(centerPanel);
        garden();
    }

    private void garden() {
        plants = new Plant[] {
            new Plant("Rose", PlantArt.ROSE, 0, new ImageIcon("src/Images/PotArt1.JPG"), 0),
            new Plant("Sunflower", PlantArt.SUNFLOWER, 0, new ImageIcon("src/Images/PotArt1.JPG"), 0),
            new Plant("TomatoPlant", PlantArt.TOMATO_PLANT, 0, new ImageIcon("src/Images/PotArt1.JPG"), 0),
        };

    }

    public void switchPlant(String id){
        System.out.println(id + " " + plants[Integer.parseInt(id)].getPlantName());
        centerPanel.updatePlantImage(plants[Integer.parseInt(id)].getPlantPicture());
        centerPanel.updatePanel();
    }

    public void addPlant(Plant plant) {
        plantList.add(plant);
        nbrOfPlants++;
    }

    public void buttonPressed(ButtonType button) {
        switch (button) {
            case Water:
                Plant plant = plantList.get(0);
                plant.waterPlant();
                ImageIcon updatedImage = plant.getPlantPicture();
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


    public void saveGame() {
        SaveGame saveGame = new SaveGame(plantList);
    }

    public List<Plant> getPlantList() {
        return plantList;
    }
}
