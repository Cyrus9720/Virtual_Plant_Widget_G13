package Controller;

import Model.*;
import View.ButtonType;
import View.GameRuleFrame;
import View.MainFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import static jdk.jfr.internal.consumer.EventLog.stop;

/**
 * The Controller class serves as the main controller for managing the interaction between the model and the view.
 * It handles actions such as switching plants, watering plants, adding new plants, and updating the game sstate.
 */

public class Controller {
    private MainFrame view;
    private ArrayList<Plant> plantList = new ArrayList<>();
    private int currentPlantIndex;
    private Plant currentPlant;
    private boolean chosenPlant = false;
    private Timer timer;
    private Map<Plant, Timer> plantTimers;
    private long remainingDeathTimerMilliseconds;

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

        if(!LoadGame.isFileNotEmpty() || GameHistoryReader.getGameHistory().isEmpty()){
            firstTimePlaying();
        }
        plantTimers = new HashMap<>();
    }

    /**
     *
     * @param id
     */
    public void switchPlant(String id) {
        int plantIndex = Integer.parseInt(id);
        if (plantIndex >= 0 && plantIndex < plantList.size()) {
            currentPlantIndex = plantIndex;
            currentPlant = plantList.get(plantIndex); // Uppdatera currentPlant när switchPlant kallas
            updateWaterButtonStatus();
            view.getCenterPanel().updatePlantImage(currentPlant.getPlantPicture());
            view.getCenterPanel().updatePlantName(currentPlant.getPlantName());
            view.getEastPanel().updateAmountOfLife();
            view.getSouthPanel().updatePlantInfo();
            view.getCenterPanel().getMainPanel().refreshBar();
            view.getCenterPanel().repaint();
            view.getEastPanel().repaint();
            setChosenPlant(true);
        } else {
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
        ImageIcon plantImage = new ImageIcon("src/Images/PotArt1.JPG");
        Rose newRose = new Rose(newRoseName, PlantArt.ROSE, 3, 0, plantImage, 0, null);
        plantList.add(newRose);
        view.getMainPanel().updateButtons(getPlantImagePaths());
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
        ImageIcon plantImage = new ImageIcon("src/Images/PotArt1.JPG");
        Sunflower newSunflower = new Sunflower(newSunflowerName, PlantArt.SUNFLOWER, 3, 0, plantImage, 0, null);
        plantList.add(newSunflower);
        view.getMainPanel().updateButtons(getPlantImagePaths());
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
        ImageIcon plantImage = new ImageIcon("src/Images/PotArt1.JPG");
        TomatoPlant newTomatoPlant = new TomatoPlant(newTomatoName, PlantArt.TOMATO_PLANT, 3, 0, plantImage, 0, null);
        plantList.add(newTomatoPlant);
        view.getMainPanel().updateButtons(getPlantImagePaths());
    }

    public void addNewBlackberry(){
        Random random = new Random();
        int randomNumber = random.nextInt(11); // Generera en slumpmässig siffra mellan 0 och 10
        String newBlackberryName = "Blackberry" + randomNumber;
        ImageIcon plantImage = new ImageIcon("src/Images/PotArt1.JPG");
        Blackberry newBlackberry = new Blackberry(newBlackberryName, PlantArt.BLACKBERRY, 3, 0, plantImage, 0, null);
        plantList.add(newBlackberry);
        view.getMainPanel().updateButtons(getPlantImagePaths());
    }

    public void addNewMiniTree(){
        Random random = new Random();
        int randomNumber = random.nextInt(11); // Generera en slumpmässig siffra mellan 0 och 10
        String newMiniTreeName = "Mini Tree" + randomNumber;
        ImageIcon plantImage = new ImageIcon("src/Images/PotArt1.JPG");
        MiniTree newMiniTree = new MiniTree(newMiniTreeName, PlantArt.MINI_TREE, 3, 0, plantImage, 0, null);
        plantList.add(newMiniTree);
        view.getMainPanel().updateButtons(getPlantImagePaths());
    }

    public void addNewCactus(){
        Random random = new Random();
        int randomNumber = random.nextInt(11); // Generera en slumpmässig siffra mellan 0 och 10
        String newCactusName = "Cactus" + randomNumber;
        ImageIcon plantImage = new ImageIcon("src/Images/PotArt1.JPG");
        Cactus newCactus = new Cactus(newCactusName, PlantArt.CACTUS, 3, 0, plantImage, 0, null);
        plantList.add(newCactus);
        view.getMainPanel().updateButtons(getPlantImagePaths());
    }

    /**
     * Handles button presses in the application.
     *
     * @param button The type of button pressed.
     * @author Cyrus och Roa
     */
    public void buttonPressed(ButtonType button) {
        switch (button) {
            case Water:
                if (currentPlant.getNbrOfLives() > 0) {
                    if (plantList.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please select a plant to water.", "No Plant Selected", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    if (currentPlant.getPlantPicture().toString().endsWith("PotArt1.JPG")) {
                        deathTimer(currentPlant);
                        pauseDeathTimer(currentPlant);
                        System.out.println("PotArt1 triggered");
                    }
                    view.getEastPanel().updateHeartLabel();
                    currentPlant = plantList.get(currentPlantIndex);
                    currentPlant.waterPlant();
                    ImageIcon updatedImage = currentPlant.getPlantPicture();
                    view.getCenterPanel().updatePlantImage(updatedImage);
                    currentPlant.setLastWatered(LocalDateTime.now());
                    view.getMainPanel().updateButtons(getPlantImagePaths());
                    updateWaterButtonStatus();
                    pauseDeathTimer(currentPlant);
                    break;
                }
                JOptionPane.showMessageDialog(null, "Your plant is dead! \nWatering won't bring it back ):");
                return;
        }
    }

    /**
     * Starts the timer for the plant's life. Stops when life reaches 0.
     * @author Cyrus Shaerpour
     */
    public long getRemainingDeathTimerMilliseconds() {
        return remainingDeathTimerMilliseconds;
    }

    // Modified deathTimer method to update remainingDeathTimerMilliseconds
    public void deathTimer(Plant plant) {
        if (plant.getPlantLevel() == 0) {
            System.out.println("Timer started for plant: " + plant.getPlantName());
            JOptionPane.showMessageDialog(null, "Congrats on your new plant! \nBut be mindful, it will need water in the coming days!");

            // Create a new timer for the plant
            Timer timer = new Timer(1000 * 10, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    plant.decreaseLife();
                    checkLife();
                    System.out.println("Plant life " + plant.getNbrOfLives() + " " + plant.getPlantName());

                    // Check if the plant's number of lives is zero and stop the timer
                    if (plant.getNbrOfLives() == 0) {
                        Timer timer = plantTimers.get(plant);
                        if (timer != null) {
                            view.getMainPanel().updateButtons(getPlantImagePaths());
                            timer.stop(); // Stop the timer
                            System.out.println("Timer stopped for plant: " + plant.getPlantName());
                        }
                    }
                }
            });

            // Start the timer
            timer.start();

            // Store the timer for the plant in the map
            plantTimers.put(plant, timer);
        }
    }

    public void pauseDeathTimer(Plant plant) {
        Timer timer = plantTimers.get(plant);
        if (timer != null && timer.isRunning()) {
            // Pause the timer
            timer.stop();
            System.out.println("Timer paused for " + plant.getPlantName());
            // Schedule a task to resume the timer after a brief delay
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            timer.start(); // Resume the timer
                        }
                    },
                    1000 * 10 // Delay in milliseconds (e.g., 10 seconds)
            );
        }
    }

    /**
     * Checks the life of the plant and updates the image if the plant has no lives left.
     * @author Cyrus Shaerpour
     */
    public void checkLife(){
        if(currentPlant.getNbrOfLives() == 0){
            view.getCenterPanel().updatePlantImage(currentPlant.getPlantPicture());
            view.getMainPanel().updateButtons(getPlantImagePaths());
            view.getEastPanel().repaint();
            view.getEastPanel().updateAmountOfLife();
        }
    }

    /**
     * Updates the status of the water button based on whether any plant needs watering.
     *
     * @author Anna Granberg
     */
    public void updateWaterButtonStatus() {
        boolean waterstatus = checkWateringStatus();
        if (waterstatus) {
            view.getEastPanel().enableWaterButton();
        } else {
            view.getEastPanel().disableWaterButton();
        }
    }

    /**
     * Checks if the plants need to be watered based on a certain timestamp (24h).
     * @return boolean
     * @auhor annagranberg
     */
    private boolean checkWateringStatus() {
        if (currentPlantIndex >= 0 && currentPlantIndex < plantList.size()) {
            currentPlant = plantList.get(currentPlantIndex);
            LocalDateTime currentDateTime = LocalDateTime.now();
            LocalDateTime lastWatered = currentPlant.getLastWatered();

            if (lastWatered != null) {
                Duration timeSinceLastWatered = Duration.between(lastWatered, currentDateTime);
                Duration wateringInterval = Duration.ofMillis(10 * 1000);

                if (timeSinceLastWatered.compareTo(wateringInterval) >= 0) {
                    System.out.println("Current plant needs to be watered");
                    return true; // Return true if the current plant needs watering
                }
            } else {
                return true; // om något är null
            }
        }
        return false; // Return false if the current plant does not need watering
    }

    public long getTimeUntilNextWatering() {
        if (currentPlantIndex >= 0 && currentPlantIndex < plantList.size()) {
            currentPlant = plantList.get(currentPlantIndex);
            LocalDateTime currentDateTime = LocalDateTime.now();
            LocalDateTime lastWatered = currentPlant.getLastWatered();

            if (lastWatered != null) {
                Duration timeSinceLastWatered = Duration.between(lastWatered, currentDateTime);
                Duration wateringInterval = Duration.ofMillis(10 * 1000); // 30 sek
                // Ska ändras (24 timmar = 24 * 60 * 60 * 1000)

                // Beräkna tiden kvar till nästa vattning i sekunder
                long timeUntilNextWateringSeconds = wateringInterval.minus(timeSinceLastWatered).getSeconds();

                return timeUntilNextWateringSeconds;
            }
        }
        return 0; // Returnera 0 om det inte går att beräkna tiden kvar
    }

    public LocalDateTime wateringPeriod(){
        if(currentPlantIndex >= 0 && currentPlantIndex < plantList.size()){
             currentPlant = plantList.get(currentPlantIndex);
             LocalDateTime lastWatered = currentPlant.getLastWatered();
             Duration wateringInterval = Duration.ofMinutes(1);
             LocalDateTime lastTimeToWater = lastWatered.plus(wateringInterval);

            return lastTimeToWater;
        }
        return null;
    }

    /**
     * Retrieves the number of lives of the first plant in the plant list.
     *
     * @return The number of lives of the first plant, or 0 if the plant list is empty or the first plant is null.
     */
    public int getNbrOfLives() {
        if (!plantList.isEmpty()) {
            Plant firstPlant = plantList.getFirst();
            if (firstPlant != null) {
                return currentPlant.getNbrOfLives();
            } else {
                return 3;
            }
        } else {
            return 3;
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
                currentPlant = plantList.get(currentPlantIndex); // Hämta den aktuella växten från plantList
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
                System.err.println("Invalid current plant index in getTimesWatered");
                return 0;
            }
        } else {
            // Hantera fallet när plantList är tom
            System.err.println("Plant list is empty");
            return 0;
        }
    }

    /**
     * Retrieves the plant level of the first plant in the plant list.
     *
     * @return The plant level of the first plant, or 0 if the plant list is empty or the first plant is null.
     */
    public int getPlantLevel() {
        if (!plantList.isEmpty()) { // Kontrollera om plantList inte är tom
            if (currentPlantIndex >= 0 && currentPlantIndex < plantList.size()) { // Kontrollera om currentPlantIndex är inom rätt intervall
                currentPlant = plantList.get(currentPlantIndex); // Hämta den aktuella växten från plantList
                if (currentPlant != null) { // Kontrollera om den aktuella växten inte är null
                    return currentPlant.getPlantLevel();
                } else {
                    // Hantera fallet när den aktuella växten är null
                    System.err.println("Current plant is null");
                    return 0;
                }
            } else {
                // Hantera fallet när currentPlantIndex är utanför räckvidden för plantList
                System.err.println("Invalid current plant index in getPlantLevel");
                return 0;
            }
        } else {
            // Hantera fallet när plantList är tom
            System.err.println("Plant list is empty");
            return 0;
        }
    }

    /**
     * Retrieves the plant name of the first plant in the plant list.
     *
     * @return The plant name of the first plant, or 0 if the plant list is empty or the first plant is null.
     */
    public String getPlantName() {
        if (!plantList.isEmpty()) { // Kontrollera om plantList inte är tom
            if (currentPlantIndex >= 0 && currentPlantIndex < plantList.size()) { // Kontrollera om currentPlantIndex är inom rätt intervall
                currentPlant = plantList.get(currentPlantIndex); // Hämta den aktuella växten från plantList
                if (currentPlant != null) { // Kontrollera om den aktuella växten inte är null
                    return currentPlant.getPlantName();
                } else {
                    // Hantera fallet när den aktuella växten är null
                    System.err.println("Current plant is null");
                    return null;
                }
            } else {
                // Hantera fallet när currentPlantIndex är utanför räckvidden för plantList
                System.err.println("Invalid current plant index in getPlantName");
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
            currentPlant = plantList.get(currentPlantIndex);
            if (currentPlant != null) {
                // Return the plant art of the current plant
                return currentPlant.getPlantArt();
            } else {
                // Handle the case when the current plant is null
                System.err.println("Current plant is null");
                return null;
            }
        } else {
            // Handle the case when plantList is empty or currentPlantIndex is out of range
            System.err.println("No plant available at the current index in getPlantArt");
            return null;
        }
    }


    /**
     * Retrieves the paths of images associated with each plant in the plant list.
     *
     * @return A list of image paths corresponding to each plant in the plant list.
     */
    public ArrayList<String> getPlantImagePaths() {
        ArrayList<String> imagePaths = new ArrayList<>();
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

    /**
     * Clears the list of plants if the user confirms the action through a JOptionPane.
     *
     * @return True if the user confirms to clear the list, false otherwise.
     * @author Anna Granberg
     */
    public void setGameToNull() {
        if(!plantList.isEmpty() || plantList != null){
            int confirm = JOptionPane.showConfirmDialog(null, "This action will remove all of your plants. Are you sure you want to do this?", "Confirmation", JOptionPane.YES_NO_OPTION);
            javax.swing.UIManager.put("OptionPane.background", new Color(225, 240, 218));
            javax.swing.UIManager.put("Panel.background", new Color(225, 240, 218));
            ArrayList<Plant> deadPlants = new ArrayList<>();
            deadPlants = getPlantList();

            if (confirm == JOptionPane.YES_OPTION) {
                GameHistoryWriter.GameHistoryWriter(deadPlants);
                System.out.println(deadPlants.toString());
                plantList.clear();
                view.getCenterPanel().clearCenterPanel();
                view.getSouthPanel().clearSouthPanel();
                view.getMainPanel().updateButtons(getPlantImagePaths());
                JOptionPane.showMessageDialog(null, "All existing plants have been removed.", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        }else if(plantList.isEmpty() || plantList == null){
            JOptionPane.showMessageDialog(null, "Your garden is empty! Nothing to remove :)", "Information", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    /**
     *
     * @param plantName
     * @author Anna Granberg
     */
    public void removePlant(String plantName) {
        if (plantList != null) {
            // Anpassa färgen på dialogrutan
            javax.swing.UIManager.put("OptionPane.background", new Color(225, 240, 218));
            javax.swing.UIManager.put("Panel.background", new Color(225, 240, 218));

            // Visa bekräftelsedialogrutan
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this plant?", "Confirmation", JOptionPane.YES_NO_OPTION);

            // Loopa genom listan för att hitta rätt växt genom växtnamn
            if (confirm == JOptionPane.YES_OPTION) {
                boolean found = false;
                ArrayList<Plant> deadPlants = new ArrayList<>();
                for (int i = 0; i < plantList.size(); i++) {
                    currentPlant = plantList.get(i);
                    if (currentPlant.getPlantName().equals(plantName)) {
                        // ta bort plantan från listan
                        plantList.remove(i);
                        System.out.println("Växten med namnet \"" + plantName + "\" har tagits bort från listan.");
                        found = true;
                        view.getCenterPanel().clearCenterPanel();
                        view.getSouthPanel().clearSouthPanel();
                        view.getMainPanel().updateButtons(getPlantImagePaths());
                        deadPlants.add(currentPlant);
                        GameHistoryWriter.GameHistoryWriter(deadPlants);
                        break;
                    }
                }

                //ifall namnet inte kan hittas
                if (!found) {
                    System.err.println("Det finns ingen växt med namnet \"" + plantName + "\" i listan.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "You have no plants to remove", ":(", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    public void showNewPlantInGUI(ImageIcon image, String name){
        view.getCenterPanel().updatePlantImage(image);
        view.getCenterPanel().updatePlantName(name);
    }


    public void saveGame() {
        SaveGame.saveGame(plantList);
    }

    public void firstTimePlaying(){
        GameRuleFrame gameRuleFrame = new GameRuleFrame();
    }

    public MainFrame getView() {
        return view;
    }

    public ArrayList<Plant> getPlantList() {
        return plantList;
    }

    public void setChosenPlant(boolean chosenPlant) {
        this.chosenPlant = chosenPlant;
    }
}
