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
    private Plant currentPlant;
    private int nbrOfPlants = 0;

    private int currentPlantIndex;


    public Controller() {
        // Skapa din lista över plantor och lägg till plantorna
       // plantList.add(new Rose("Empty", PlantArt.POT, 0, new ImageIcon("src/Images/PotArt1.JPG"), 0));

        // Skapa ditt MainFrame-objekt efter att plantorna har skapats
        view = new MainFrame(this);
        // Skapa och konfigurera CenterPanel
        //centerPanel = new CenterPanel(400, 400);
        //view.add(centerPanel);
        garden();
    }

    private void garden() {
        plants = new Plant[] {
            new Rose("Rose", PlantArt.ROSE, 0, new ImageIcon("src/Images/PotArt1.JPG"), 0),
            new Sunflower("Sunflower", PlantArt.SUNFLOWER, 0, new ImageIcon("src/Images/PotArt1.JPG"), 0),
            new TomatoPlant("TomatoPlant", PlantArt.TOMATO_PLANT, 0, new ImageIcon("src/Images/PotArt1.JPG"), 0),
        };

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

    public void buttonPressed(ButtonType button) {
        switch (button) {
            case Water:
                Plant plant = plants[currentPlantIndex]; //plantList.get(currentPlantIndex); //plantList.get(0);
                plant.waterPlant();
                ImageIcon updatedImage = plant.getPlantPicture();
                System.out.println(updatedImage);
                view.getCenterPanel().updatePlantImage(updatedImage);
                break;
        }
    }

    public int getNbrOfLives() {
        if (!plantList.isEmpty()) { // Check if plantList is not empty
            Plant firstPlant = plants[currentPlantIndex];//plantList.get(0); // Get the first plant if available
            if (firstPlant != null) { // Check if the first plant is not null
                return firstPlant.getNbrOfLives();
            } else {
                // Handle the case when the first plant is null
                System.err.println("First plant is null");
                return 0;
            }
        } else {
            // Handle the case when plantList is empty
            System.err.println("Plant list is empty");
            return 0;
        }
    }

    public int getTimesWatered(){
        if (!plantList.isEmpty()) { // Check if plantList is not empty
            Plant firstPlant = plants[currentPlantIndex];//plantList.get(0); // Get the first plant if available
            if (firstPlant != null) { // Check if the first plant is not null
                System.out.println("times watered: " + firstPlant.getTimesWatered());
                return firstPlant.getTimesWatered();

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

    public int getPlantLevel(){
        if (!plantList.isEmpty()) { // Check if plantList is not empty
            Plant firstPlant = plants[currentPlantIndex];//plantList.get(0); // Get the first plant if available
            if (firstPlant != null) { // Check if the first plant is not null
                return firstPlant.getPlantLevel();
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

    public void saveGame() {
        SaveGame saveGame = new SaveGame(plantList);
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
