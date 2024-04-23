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

    public Controller() {
        // Skapa din lista över plantor och lägg till plantorna
<<<<<<< HEAD
        plantList.add(new Rose("Rose", PlantArt.ROSE, 0, new ImageIcon("src/Images/rose 1.jpeg"), 0));
        plantList.add(new TomatoPlant("Tomato plant", PlantArt.TOMATO_PLANT, 0, new ImageIcon("src/Images/Tomatoe1.JPG"), 0));
        plantList.add(new Sunflower("Sunflower", PlantArt.SUNFLOWER, 0, new ImageIcon("src/Images/Sunflower1.JPG"), 0));
=======
        //plantList.add(new Plant("Empty", PlantArt.POT, 0, new ImageIcon("src/Images/PotArt1.JPG"), 0));

>>>>>>> Cyrus_Branch_2
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
            // Handle the case when plantList is empty
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
            Plant firstPlant = plantList.get(0); // Get the first plant if available
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

<<<<<<< HEAD
    public List<ImageIcon> getPlantImages() {
        List<ImageIcon> allPlantImages = new ArrayList<>();

        for (Plant plant : plantList) {
            if (plant != null) {
                List<ImageIcon> plantImages = (List<ImageIcon>) plant.getPlantPicture();
                if (plantImages != null) {
                    allPlantImages.addAll(plantImages);
                } else {
                    System.err.println("Plant images list is null for plant: " + plant.getName());
                }
            } else {
                System.err.println("Encountered a null plant in the plant list.");
            }
        }

        return allPlantImages;
    }

=======
    public Plant getCurrentPlant() {
        return currentPlant;
    }

    public void setCurrentPlant(Plant newPlant) {
        currentPlant = newPlant;
    }
>>>>>>> Cyrus_Branch_2
}
