package Controller;

import Model.*;
import View.ButtonType;
import View.CenterPanel;
import View.MainFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {

    private MainFrame view;
    private List<Plant> plantList = new ArrayList<>();
    private CenterPanel centerPanel;

    private Timer timer; //Timer for updating plant status

    private static final long UPDATE_INTERVAL = 1000; //update interval (1 second)


    public Controller() {
        // Skapa din lista över plantor och lägg till plantorna
        plantList.add(new Rose("Rose", PlantArt.ROSE, 0, new ImageIcon("src/Images/rose 1.jpeg"), 0));

        // Skapa ditt MainFrame-objekt efter att plantorna har skapats
        view = new MainFrame(this);

        // Skapa och konfigurera CenterPanel
        centerPanel = new CenterPanel(400, 400);
        view.add(centerPanel);

        startTimer(); //start the timer for updating plant status
    }

    private void startTimer(){ //method to start the timer
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updatePlants();
            }
        }, 0, UPDATE_INTERVAL);
    }

    private void updatePlants(){ // method to update plant status
        long elapsedTime = UPDATE_INTERVAL; //Elapsed time since the last update
        for ( Plant plant: plantList){
            plant.update(elapsedTime); // update each plant's status
        }
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
