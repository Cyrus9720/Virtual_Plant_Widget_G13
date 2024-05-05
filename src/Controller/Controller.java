package Controller;

import Model.*;
import View.ButtonType;
import View.GameRuleFrame;
import View.MainFrame;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Controller class serves as the main controller for managing the interaction between the model and the view.
 * It handles actions such as switching plants, watering plants, adding new plants, and updating the game state.
 */

public class Controller {
    private MainFrame view;
    private ArrayList<Plant> plantList = new ArrayList<>();
    private Clip wateringSoundClip;
    private int currentPlantIndex;


    private long lastWateringTime = 0; // Variabel för att hålla koll på tiden när plantorna senast vattnades
    private static final long WATERING_INTERVAL = 2 * 60 * 1000; // Vattningstiden i millisekunder (2 minuter)

    /**
     * Constructor for the controller class.
     */

    public Controller() {
        try {
            LoadGame.loadGame(plantList, this); // ifall spelet spelats tidigare kommer plantList hämtas här
        } catch (Exception e) {
            System.err.println("Error loading game data: " + e.getMessage());
        }

        view = new MainFrame(this);
    }

    /**
     *
     * @param id
     */
    public void switchPlant(String id) {
        // Konvertera id till en int för att få plantIndex
        int plantIndex = Integer.parseInt(id);

        // Kontrollera om plantIndex är inom ett giltigt intervall (1 till plantList.size() - 1)
        if (plantIndex >= 0 && plantIndex < plantList.size()) {
            // Om plantIndex är giltigt, hämta växten från plantList med det angivna indexet
            Plant plant = plantList.get(plantIndex);

            // Uppdatera växtbilden i gränssnittet med den nya växten
            view.getCenterPanel().updatePlantImage(plant.getPlantPicture());
            view.getCenterPanel().updatePlantName(plant.getPlantName());
            // view.getSouthPanel().updatePlantInfo(plant.getPlantinfo()); todo: få det att funka

            // Uppdatera currentPlantIndex till det nya växtindexet
            currentPlantIndex = plantIndex;

            // Uppdatera gränssnittet för att visa förändringar
            view.getCenterPanel().getMainPanel().refreshBar();

            // Uppdatera tillståndet för vattenknappen baserat på om växten behöver vattnas eller inte
            // boolean needsWatering = checkWateringStatus();
            // updateWaterButtonStatus(needsWatering);
        } else {
            // Om plantIndex är ogiltigt (utanför intervallet), skriv ut ett felmeddelande
            System.err.println("Invalid plant index: " + id);
        }
    }

    /**
     * Adds a new rose plant to the list of plants.
     * Generates a random name for the rose plant and initializes its properties.
     * @author annagranberg
     */

    public void addNewRose() {
        Random random = new Random();
        int randomNumber = random.nextInt(11); // Generera en slumpmässig siffra mellan 0 och 10Random random = new Random();
        String newRoseName = "Rose" + randomNumber;
        Rose newRose = new Rose(newRoseName, PlantArt.ROSE, 3, 0, new ImageIcon("src/Images/PotArt1.JPG"), 0, null);
        plantList.add(newRose);
    }

    /**
     * Adds a new sunflower plant to the list of plants.
     * Generates a random name for the rose plant and initializes its properties.
     * @author annagranberg
     */
    public void addNewSunflower(){
        Random random = new Random();
        int randomNumber = random.nextInt(11); // Generera en slumpmässig siffra mellan 0 och 10
        String newSunflowerName = "Sunflower" + randomNumber;
        Sunflower newSunflower = new Sunflower(newSunflowerName, PlantArt.SUNFLOWER, 3, 0, new ImageIcon("src/Images/PotArt1.JPG"), 0, null);
        plantList.add(newSunflower);
    }

    /**
     * Adds a new tomato plant to the list of plants.
     * Generates a random name for the rose plant and initializes its properties.
     * @author annagranberg
     */
    public void addNewTomatoPlant(){
        Random random = new Random();
        int randomNumber = random.nextInt(11); // Generera en slumpmässig siffra mellan 0 och 10
        String newTomatoName = "TomatoPlant" + randomNumber;
        TomatoPlant newSunflower = new TomatoPlant(newTomatoName, PlantArt.TOMATO_PLANT, 3, 0, new ImageIcon("src/Images/PotArt1.JPG"), 0, null);
        plantList.add(newSunflower);
    }

    /**
     * Handles button presses in the application.
     *
     * @param button The type of button pressed.
     */
    public void buttonPressed(ButtonType button) {
        switch (button) {
            case Water:
                // Kontrollera om ingen växt är vald
                if (plantList.isEmpty()) {
                    // Visa felmeddelande om ingen växt är vald
                    JOptionPane.showMessageDialog(null, "Please select a plant to water.", "No Plant Selected", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                // Hämta den aktuella växten från plantList
                Plant currentPlant = plantList.get(0);
                // Vattna växten
                currentPlant.waterPlant();
                // Uppdatera växtbilden i vyn
                ImageIcon updatedImage = currentPlant.getPlantPicture();
                view.getCenterPanel().updatePlantImage(updatedImage);

                currentPlant.setLastWatered(LocalDateTime.now());

                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/sounds/watering.wav"));
                    wateringSoundClip = AudioSystem.getClip();
                    wateringSoundClip.open(audioInputStream);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                // Spela vattningssoundet om växten vattnades framgångsrikt
                if (wateringSoundClip != null) {
                    wateringSoundClip.setFramePosition(0);
                    wateringSoundClip.start(); // Starta uppspelningen av ljudet
                }
                break;
        }
    }


    /**
     * Checks if the plants need to be watered based on a certain timestamp (24h).
     * @return boolean
     * @auhor annagranberg
     */
    private boolean checkWateringStatus() {
        LocalDateTime currentDateTime = LocalDateTime.now();

        for (Plant plant : plantList) {
            LocalDateTime lastWatered = plant.getLastWatered();

            if (lastWatered == null) {
                System.err.println("Plant last watered timestamp is null");
                continue; // Skip this plant and move on to the next one
            }

            Duration timeSinceLastWatered = Duration.between(lastWatered, currentDateTime);
            Duration wateringInterval = Duration.ofMinutes(2); // 2 minuter

            if (timeSinceLastWatered.compareTo(wateringInterval) >= 0) {
                // Plant needs to be watered
                System.out.println("Plant needs to be watered");
                return true;
            }
        }
        return false; // Ingen växt behöver vattnas
    }

    /**
     * Updates the status of the water button based on whether any plant needs watering.
     *
     * @param waterstatus A boolean indicating whether any plant needs watering.
     * @author Anna Granberg
     */
    public void updateWaterButtonStatus(boolean waterstatus) {
        if (waterstatus) {
            view.getEastPanel().enableWaterButton(); // Aktivera knappen om någon växt behöver vattnas
        } else {
            view.getEastPanel().disableWaterButton(); // Inaktivera knappen om ingen växt behöver vattnas
        }
    }

    /**
     * Retrieves the number of lives of the first plant in the plant list.
     *
     * @return The number of lives of the first plant, or 0 if the plant list is empty or the first plant is null.
     */
    public int getNbrOfLives() {
        if (!plantList.isEmpty()) { // Check if plantList is not empty
            //Plant firstPlant = plants[currentPlantIndex];// Get the first plant if available
            Plant firstPlant = plantList.get(0);
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

    /**
     * Retrieves the number of times watered of the first plant in the plant list.
     *
     * @return The number of times watered of the first plant, or 0 if the plant list is empty or the first plant is null.
     */
    public int getTimesWatered() {
        if (!plantList.isEmpty()) { // Kontrollera om plantList inte är tom
            if (currentPlantIndex >= 0 && currentPlantIndex < plantList.size()) { // Kontrollera om currentPlantIndex är inom rätt intervall
                Plant currentPlant = plantList.get(currentPlantIndex); // Hämta den aktuella växten från plantList
                if (currentPlant != null) { // Kontrollera om den aktuella växten inte är null
                    System.out.println("times watered: " + currentPlant.getTimesWatered());
                    return currentPlant.getTimesWatered();
                } else {
                    // Hantera fallet när den aktuella växten är null
                    System.err.println("Current plant is null");
                    return 0;
                }
            } else {
                // Hantera fallet när currentPlantIndex är utanför räckvidden för plantList
                System.err.println("Invalid current plant index");
                return 0;
            }
        } else {
            // Hantera fallet när plantList är tom
            System.err.println("Plant list is empty");
            return 0;
        }
    }

    /**
     * Retrieves the plantlevel of the first plant in the plant list.
     *
     * @return The plantlevel of the first plant, or 0 if the plant list is empty or the first plant is null.
     */
    public int getPlantLevel() {
        if (!plantList.isEmpty()) { // Kontrollera om plantList inte är tom
            if (currentPlantIndex >= 0 && currentPlantIndex < plantList.size()) { // Kontrollera om currentPlantIndex är inom rätt intervall
                Plant currentPlant = plantList.get(currentPlantIndex); // Hämta den aktuella växten från plantList
                if (currentPlant != null) { // Kontrollera om den aktuella växten inte är null
                    return currentPlant.getPlantLevel();
                } else {
                    // Hantera fallet när den aktuella växten är null
                    System.err.println("Current plant is null");
                    return 0;
                }
            } else {
                // Hantera fallet när currentPlantIndex är utanför räckvidden för plantList
                System.err.println("Invalid current plant index");
                return 0;
            }
        } else {
            // Hantera fallet när plantList är tom
            System.err.println("Plant list is empty");
            return 0;
        }
    }

    public void saveGame() {
        SaveGame.saveGame(plantList);
    }

    public String getPlantInfo(){
        Plant plant = plantList.get(0);
        String plantInfo = plant.getPlantinfo();
        return plantInfo;
    }

    /**
     * Retrieves the plant name of the first plant in the plant list.
     *
     * @return The plant name of the first plant, or 0 if the plant list is empty or the first plant is null.
     */
    public String getPlantName() {
        if (!plantList.isEmpty()) { // Kontrollera om plantList inte är tom
            if (currentPlantIndex >= 0 && currentPlantIndex < plantList.size()) { // Kontrollera om currentPlantIndex är inom rätt intervall
                Plant currentPlant = plantList.get(currentPlantIndex); // Hämta den aktuella växten från plantList
                if (currentPlant != null) { // Kontrollera om den aktuella växten inte är null
                    return currentPlant.getPlantName();
                } else {
                    // Hantera fallet när den aktuella växten är null
                    System.err.println("Current plant is null");
                    return null;
                }
            } else {
                // Hantera fallet när currentPlantIndex är utanför räckvidden för plantList
                System.err.println("Invalid current plant index");
                return null;
            }
        } else {
            // Hantera fallet när plantList är tom
            System.err.println("Plant list is empty");
            return null;
        }
    }

    /**
     * Retrieves the plant art of the first plant in the plant list.
     *
     * @return The plant art of the first plant, or 0 if the plant list is empty or the first plant is null.
     */
    public PlantArt getPlantArt(){
        if (!plantList.isEmpty() && currentPlantIndex >= 0 && currentPlantIndex < plantList.size()) {
            Plant currentPlant = plantList.get(currentPlantIndex);
            if (currentPlant != null) { // Kontrollera om den aktuella växten inte är null
                return currentPlant.getPlantArt();
            } else {
                // Hantera fallet när den aktuella växten är null
                System.err.println("Current plant is null");
                return null;
            }
        } else {
            // Hantera fallet när plantList är tom eller currentPlantIndex är utanför intervallet
            System.err.println("No plant available at the current index");
            return null;
        }
    }

    /**
     * Retrieves the paths of images associated with each plant in the plant list.
     *
     * @return A list of image paths corresponding to each plant in the plant list.
     */
    public List<String> getPlantImagePaths() {
        List<String> imagePaths = new ArrayList<>();
        for (Plant plant : plantList) {
            imagePaths.add(plant.getPlantPicture().toString());
        }
        return imagePaths;
    }

    /**
     * Calculates the time elapsed since the game was last played.
     *
     * @return The time elapsed since the game was last played, in seconds.
     * @author Anna Granberg
     */
    public long getTimeSinceLastPlayed() {
        LocalDateTime timeWhenClosed = SaveGame.getTimestamp();
        LocalDateTime timeWhenOpened = LoadGame.getTimestamp();

        Duration duration = Duration.between(timeWhenClosed, timeWhenOpened);

        long timeSinceLastPlayedSeconds = duration.getSeconds();

        return timeSinceLastPlayedSeconds;
    }

    public void firstTimePlaying(){
        GameRuleFrame gameRuleFrame = new GameRuleFrame();
    }

    public MainFrame getView() {
        return view;
    }
}
