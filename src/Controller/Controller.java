package Controller;

import Model.*;
import View.ButtonType;
import View.CenterPanel;
import View.MainFrame;
import View.SouthPanel;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Controller {

    private MainFrame view;
    private ArrayList<Plant> plantList = new ArrayList<>();
    private Plant[] plants;
    private CenterPanel centerPanel;
    private SouthPanel southPanel;
    private Plant currentPlant;
    private int nbrOfPlants = 0;
    private Clip wateringSoundClip; // Declare wateringSoundClip variable
    private int currentPlantIndex;


    public Controller() {
        garden();
        view = new MainFrame(this);
    }

    /**
     * Create the garden with plants to choose from
     * @author Cyrus Shaerpour
     */
    private void garden() {
        plants = new Plant[] {
                new Rose("Rose", PlantArt.ROSE, 3, 0,new ImageIcon("src/Images/PotArt1.JPG"), 0,
                    "The rose is a type of flowering shrub. Its name comes from the Latin word Rosa. \n " +
                            "The flowers of the rose grow in many different colors, \n " +
                            "from the well-known red rose or yellow roses and sometimes white or purple roses. \n " +
                            "Roses belong to the family of plants called Rosaceae."),

                new Sunflower("Sunflower", PlantArt.SUNFLOWER, 3,0, new ImageIcon("src/Images/PotArt1.JPG"), 0,
                    "The sunflower is a large inflorescence, \n" +
                            " this means that the flower head is actually made of many tiny flowers called florets. \n" +
                            " The central florets look like the center of a normal flower and the outer florets look like yellow petals. \n" +
                            " All together they make up a 'false flower'."),

                new TomatoPlant("TomatoPlant", PlantArt.TOMATO_PLANT, 3,0, new ImageIcon("src/Images/PotArt1.JPG"), 0,
                    "The tomato is the edible berry of the plant Solanum lycopersicum, \n" +
                            " commonly known as a tomato plant. The species originated in western South America and Central America. \n" +
                            " The Nahuatl word tomatl gave rise to the Spanish word tomate, from which the English word tomato derived."),

                new Cactus("Cactus", PlantArt.CACTUS, 3,0, new ImageIcon("src/Images/PotArt1.JPG"), 0,"Cacti are members of the plant family Cactaceae, in the order Caryophyllales. \n" +
                    "The word 'cactus' derives, through Latin, from the Ancient Greek κάκτος, kaktos, a name originally used by Theophrastus for a spiny plant whose identity is now not certain. \n" +
                    "Cacti are native to the Americas, ranging from Patagonia in the south to parts of western Canada in the north—except for Rhipsalis baccifera, which also grows in Africa and Sri Lanka."),

                new MiniTree("MiniTree", PlantArt.MINI_TREE, 3,0, new ImageIcon("src/Images/PotArt1.JPG"), 0,"A tree is a tall plant with a trunk and branches made of wood. Trees can live for many years. \n" +
                    "The oldest tree ever discovered is approximately 5,000 years old. The four main parts of a tree are the roots, the trunk, the branches, and the leaves. \n" +
                    "The roots of a tree are usually under the ground. Most trees have a single trunk. "),

                new Blackberry("Blackberry", PlantArt.BLACKBERRY, 3,0, new ImageIcon("src/Images/PotArt1.JPG"), 0,"The blackberry is an edible fruit produced by many species in the genus Rubus in the family Rosaceae, hybrids among these species within the subgenus Rubus, and hybrids between the subgenera Rubus and Idaeobatus. \n" +
                    "The taxonomy of the blackberries has historically been confused because of hybridization and apomixis, so that species have often been grouped together and called species aggregates. "),
        };
    }

    /**
     * Load the game from the save file
     * @param id
     * @author Cyrus Shaerpour
     */
    public void switchPlant(String id){
        System.out.println(id + " " + plants[Integer.parseInt(id)].getPlantName());
        view.getCenterPanel().updatePlantImage(plants[Integer.parseInt(id)].getPlantPicture());
        if (view.getSouthPanel() != null) {
            view.getSouthPanel().updatePlantInfo(plants[Integer.parseInt(id)].getPlantInfo());
            System.out.println(id + " " + plants[Integer.parseInt(id)].getPlantInfo());
        } else {
            System.err.println("SouthPanel instance is null");
        }
        addPlant(plants[Integer.parseInt(id)]);
        currentPlantIndex = Integer.parseInt(id);
        view.getCenterPanel().getMainPanel().refreshBar();
        //view.getCenterPanel().updatePanel(plantList.getFirst().getPlantPicture());
    }

    /**
     * Add a plant to the plant list
     * @param plant
     * @author Cyrus Shaerpour
     */
    public void addPlant(Plant plant) {
        plantList.add(plant);
        nbrOfPlants++;
    }

    /**
     * Function for the different buttons and what they do
     * @param button
     * @author Anna Granberg & Cyrus Shaerpour & Roa Jamhour
     */
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

                try {
                    // If the sound clip is not initialized or it's not playing, initialize and play it
                    if (wateringSoundClip == null || !wateringSoundClip.isRunning()) {
                        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResourceAsStream("/sounds/watering.wav")));
                        wateringSoundClip = AudioSystem.getClip();
                        wateringSoundClip.open(audioInputStream);
                        wateringSoundClip.setFramePosition(0); // Reset to the beginning
                        wateringSoundClip.start(); // Start playing the sound
                    } else {
                        // If the sound clip is already playing, stop and reset it before playing again
                        wateringSoundClip.stop();
                        wateringSoundClip.setFramePosition(0); // Reset to the beginning
                        wateringSoundClip.start(); // Start playing the sound
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
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

    public ArrayList<Plant> getPlantList() {
        return plantList;
    }

    public Plant getCurrentPlant() {
        return currentPlant;
    }

    public void setCurrentPlant(Plant newPlant) {
        currentPlant = newPlant;
    }

}
