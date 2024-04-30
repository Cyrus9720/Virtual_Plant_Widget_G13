package Controller;

import Model.*;
import View.ButtonType;
import View.MainFrame;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Controller {
    private MainFrame view;
    private ArrayList<Plant> plantList = new ArrayList<>();
    private Plant[] plants;
    private Plant currentPlant;
    private Clip wateringSoundClip;
    private int currentPlantIndex;

    public Controller() {
        plants = plantList.toArray(new Rose[0]);
        plants = plantList.toArray(new Sunflower[1]);
        plants = plantList.toArray(new TomatoPlant[2]);
        garden();
        view = new MainFrame(this);
    }

    private void garden() {
        plants = new Plant[] {
            new Rose("Rose", PlantArt.ROSE, 3, 0,new ImageIcon("src/Images/PotArt1.JPG"), 0),
            new Sunflower("Sunflower", PlantArt.SUNFLOWER, 3,0, new ImageIcon("src/Images/PotArt1.JPG"), 0),
            new TomatoPlant("TomatoPlant", PlantArt.TOMATO_PLANT, 3,0, new ImageIcon("src/Images/PotArt1.JPG"), 0),
        };

    }

    public void switchPlant(String id){
        System.out.println(id + " " + plants[Integer.parseInt(id)].getPlantName());
        view.getCenterPanel().updatePlantImage(plants[Integer.parseInt(id)].getPlantPicture());
        addPlant(plants[Integer.parseInt(id)]);
        currentPlantIndex = Integer.parseInt(id);
        view.getCenterPanel().getMainPanel().refreshBar();
    }

    public void addPlant(Plant plant) {
        plantList.add(plant);
    }

    public void buttonPressed(ButtonType button) {
        switch (button) {
            case Water:
                // Check if the plant list is empty
                if (plantList.isEmpty()) {
                    // Display error message
                    JOptionPane.showMessageDialog(null, "The pot is empty. Choose a plant to water first.", "Empty Pot", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                
                // Get the current plant from the array of plants
                Plant plant = plants[currentPlantIndex];
                // Water the plant
                plant.waterPlant();
                // Update the plant image in the view
                ImageIcon updatedImage = plant.getPlantPicture();
                view.getCenterPanel().updatePlantImage(updatedImage);
                plant.setLastWatered(new Timestamp((System.currentTimeMillis())));

                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/sounds/watering.wav"));
                    wateringSoundClip = AudioSystem.getClip();
                    wateringSoundClip.open(audioInputStream);
                }catch (Exception ex){
                    ex.printStackTrace();
                }

                // Check if the plant was watered successfully before playing the sound
                if (!plantList.isEmpty()) {
                    //Play the watering sound
                    if(wateringSoundClip != null){
                        wateringSoundClip.setFramePosition(0);
                        wateringSoundClip.start(); //to start playing the sound
                    }
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

        for (Plant plant : plantList) {
            Timestamp lastWatered = plant.getLastWatered(); // Retrieve the Timestamp object

            if (lastWatered == null) {
                System.err.println("Plant last watered timestamp is null");
                continue; // Skip this plant and move on to the next one
            }

            long timeSinceLastWatered = currentTimestamp.getTime() - lastWatered.getTime();
            long wateringInterval = 24 * 60 * 60 * 1000; // 24 hours in milliseconds

            if (timeSinceLastWatered >= wateringInterval) {
                // Plant needs to be watered
                view.timeToWater();
            }
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
            System.err.println("Plant list is empty water");
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
            System.err.println("Plant list is empty level");
            return 0;
        }
    }

    public void saveGame() {
        SaveGame.saveGame(plantList);
    }

    public void updateRose(){

    }

    public ArrayList<Plant> getPlantList() {
        return plantList;
    }

    public Plant getCurrentPlant() {
        return currentPlant;
    }

    public void setCurrentPlant(Plant newPlant) {
        currentPlant = newPlant;
    }

    public String getPlantInfo(){
        Plant plant = plantList.get(0);
        String plantInfo = plant.getPlantinfo();
        return plantInfo;
    }


    public long getTimeSinceLastPlayed() {
        Timestamp timeWhenClosed = SaveGame.getTimestamp();
        Timestamp timeWhenOpened = LoadGame.getTimestamp();

        long timeClosedMillis = timeWhenClosed.getTime();
        long timeOpenedMillis = timeWhenOpened.getTime();

        long timeSinceLastPlayedMillis = timeOpenedMillis - timeClosedMillis;

        long timeSinceLastPlayedSeconds = timeSinceLastPlayedMillis / 1000;

        return timeSinceLastPlayedSeconds;
    }

}
