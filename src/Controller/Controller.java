package Controller;

import Model.*;
import View.ButtonType;
import View.GameRuleFrame;
import View.MainFrame;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

/**
 * The Controller class serves as the main controller for managing the interaction between the model and the view.
 * It handles actions such as switching plants, watering plants, adding new plants, and updating the game sstate.
 */

public class Controller {
    private MainFrame view;
    private ArrayList<Plant> plantList = new ArrayList<>();
    private int currentPlantIndex;
    private Plant currentPlant = null;
    private Map<Plant, Timer> plantTimers;
    private Duration remainingTime;
    private Map<Plant, Long> pauseTimes = new HashMap<>();
    private LoadGame loadGame;
    private boolean isChosen = false;
    public boolean night = false;

    /**
     * Constructor for the controller class.
     */
    public Controller() {
        loadGame = new LoadGame();
        try {
            loadGame.loadGame(plantList, this); // ifall spelet spelats tidigare kommer plantList hämtas här
        } catch (Exception e) {
            System.err.println("Error loading game data: " + e.getMessage());
        }
        view = new MainFrame(this);

        if (loadGame.isFileNotEmpty() || GameHistoryReader.getGameHistory().isEmpty()) {
            firstTimePlaying();
        }
        plantTimers = new HashMap<>();
        resumeAllTimers();
    }

    /**
     * Switches the current plant to the one with the specified ID.
     *
     * @param id The ID of the plant to switch to.
     */
    public void switchPlant(String id) {
        int plantIndex = Integer.parseInt(id);
        if (plantIndex >= 0 && plantIndex < plantList.size()) {
            setIsChosen(true);
            currentPlantIndex = plantIndex;
            currentPlant = plantList.get(plantIndex); // Uppdatera currentPlant när switchPlant kallas
            currentPlant.startNewTimer();
            updateWaterButtonStatus();
            view.getCenterPanel().updatePlantImage(currentPlant.getPlantPicture());
            view.getCenterPanel().updatePlantName(currentPlant.getPlantName());
            view.getEastPanel().updateLives();
            view.getSouthPanel().updatePlantInfo();
            view.getCenterPanel().getMainPanel().refreshBar();
            view.getCenterPanel().repaint();
            view.getEastPanel().repaint();
        } else {
            System.err.println("Invalid plant index: " + id);
        }
    }

    public boolean getIsChosen(){
        return isChosen;
    }

    public void setIsChosen(boolean isChosen){
        this.isChosen = isChosen;
    }

    /**
     * Adds a new rose plant to the list of plants.
     * Generates a random name for the plant if plant name is null
     *
     * @author annagranberg
     */
    public void addNewRose() {
        int response = JOptionPane.showConfirmDialog(null, "Do you want to choose a new name?", "Confirm", JOptionPane.YES_NO_OPTION);
        String newName;
        if (response == JOptionPane.YES_OPTION) {
            newName = JOptionPane.showInputDialog("Please enter the new plant name:");
            if (newName == null || newName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Invalid input. You will get a random name instead, but it can be changed later :)");
                Random random = new Random();
                newName = "Rose" + random.nextInt(11);
            }
        } else {
            Random random = new Random();
            newName = "Rose" + random.nextInt(11);
        }

        ImageIcon plantImage = new ImageIcon("src/Images/PotArt1.JPG");
        Rose newRose = new Rose(this, newName, PlantArt.ROSE, 3, 0, plantImage, 0, null);
        plantList.add(newRose);
        view.getMainPanel().updateButtons(getPlantImagePaths());
    }

    /**
     * Adds a new sunflower plant to the list of plants.
     * Generates a random name for the plant if plant name is null
     *
     * @author annagranberg
     */
    public void addNewSunflower() {
        int response = JOptionPane.showConfirmDialog(null, "Do you want to choose a new name?", "Confirm", JOptionPane.YES_NO_OPTION);
        String newName;
        if (response == JOptionPane.YES_OPTION) {
            newName = JOptionPane.showInputDialog("Please enter the new plant name:");
            if (newName == null || newName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Invalid input. You will get a random name instead, but it can be changed later :)");
                Random random = new Random();
                newName = "Sunflower" + random.nextInt(11);
            }
        } else {
            Random random = new Random();
            newName = "Sunflower" + random.nextInt(11);
        }

        ImageIcon plantImage = new ImageIcon("src/Images/PotArt1.JPG");
        Sunflower newSunflower = new Sunflower(this, newName, PlantArt.SUNFLOWER, 3, 0, plantImage, 0, null);
        plantList.add(newSunflower);
        view.getMainPanel().updateButtons(getPlantImagePaths());
    }

    /**
     * Adds a new tomato plant to the list of plants.
     * Generates a random name for the plant if plant name is null
     *
     * @author annagranberg
     */
    public void addNewTomatoPlant() {
        int response = JOptionPane.showConfirmDialog(null, "Do you want to choose a new name?", "Confirm", JOptionPane.YES_NO_OPTION);
        String newName;
        if (response == JOptionPane.YES_OPTION) {
            newName = JOptionPane.showInputDialog("Please enter the new plant name:");
            if (newName == null || newName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Invalid input. You will get a random name instead, but it can be changed later :)");
                Random random = new Random();
                newName = "TomatoPlant" + random.nextInt(11);
            }
        } else {
            Random random = new Random();
            newName = "TomatoPlant" + random.nextInt(11);
        }
        ImageIcon plantImage = new ImageIcon("src/Images/PotArt1.JPG");
        TomatoPlant newTomatoPlant = new TomatoPlant(this,newName, PlantArt.TOMATO_PLANT, 3, 0, plantImage, 0, null);
        plantList.add(newTomatoPlant);
        view.getMainPanel().updateButtons(getPlantImagePaths());
    }

    /**
     * Adds a new blackberry plant to the list of plants.
     * Generates a random name for the plant if plant name is null
     *
     * @author annagranberg
     */
    public void addNewBlackberry() {
        int response = JOptionPane.showConfirmDialog(null, "Do you want to choose a new name?", "Confirm", JOptionPane.YES_NO_OPTION);
        String newName;
        if (response == JOptionPane.YES_OPTION) {
            newName = JOptionPane.showInputDialog("Please enter the new plant name:");
            if (newName == null || newName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Invalid input. You will get a random name instead, but it can be changed later :)");
                Random random = new Random();
                newName = "Blackberry" + random.nextInt(11);
            }
        } else {
            Random random = new Random();
            newName = "Blackberry" + random.nextInt(11);
        }
        ImageIcon plantImage = new ImageIcon("src/Images/PotArt1.JPG");
        Blackberry newBlackberry = new Blackberry(this,newName, PlantArt.BLACKBERRY, 3, 0, plantImage, 0, null);
        plantList.add(newBlackberry);
        view.getMainPanel().updateButtons(getPlantImagePaths());
    }

    /**
     * Adds a new mini tree plant to the list of plants.
     * Generates a random name for the plant if plant name is null
     *
     * @author annagranberg
     */
    public void addNewMiniTree() {
        int response = JOptionPane.showConfirmDialog(null, "Do you want to choose a new name?", "Confirm", JOptionPane.YES_NO_OPTION);
        String newName;
        if (response == JOptionPane.YES_OPTION) {
            newName = JOptionPane.showInputDialog("Please enter the new plant name:");
            if (newName == null || newName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Invalid input. You will get a random name instead, but it can be changed later :)");
                Random random = new Random();
                newName = "Minitree" + random.nextInt(11);
            }
        } else {
            Random random = new Random();
            newName = "Minitree" + random.nextInt(11);
        }

        ImageIcon plantImage = new ImageIcon("src/Images/PotArt1.JPG");
        MiniTree newMiniTree = new MiniTree(this, newName, PlantArt.MINI_TREE, 3, 0, plantImage, 0, null);
        plantList.add(newMiniTree);
        view.getMainPanel().updateButtons(getPlantImagePaths());
    }

    /**
     * Adds a new cactus plant to the list of plants.
     * Generates a random name for the plant if plant name is null
     *
     * @author annagranberg
     */
    public void addNewCactus() {
        int response = JOptionPane.showConfirmDialog(null, "Do you want to choose a new name?", "Confirm", JOptionPane.YES_NO_OPTION);
        String newName;
        if (response == JOptionPane.YES_OPTION) {
            newName = JOptionPane.showInputDialog("Please enter the new plant name:");
            if (newName == null || newName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Invalid input. You will get a random name instead, but it can be changed later :)");
                Random random = new Random();
                newName = "Cactus" + random.nextInt(11);
            }
        } else {
            Random random = new Random();
            newName = "Cactus" + random.nextInt(11);
        }
        ImageIcon plantImage = new ImageIcon("src/Images/PotArt1.JPG");
        Cactus newCactus = new Cactus(this, newName, PlantArt.CACTUS, 3, 0, plantImage, 0, null);
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
                if (isChosen) {
                    if (plantList.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please select a plant to water.", "No Plant Selected", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    if (currentPlant.getPlantPicture().toString().endsWith("PotArt1.JPG")) {
                        deathTimer(currentPlant);
                        pauseDeathTimer();
                        System.out.println("PotArt1 triggered");
                    }
                    currentPlant = plantList.get(currentPlantIndex);
                    currentPlant.waterPlant();
                    currentPlant.startNewTimer();
                    ImageIcon updatedImage = currentPlant.getPlantPicture();
                    view.getCenterPanel().updatePlantImage(updatedImage);
                    currentPlant.setLastWatered(LocalDateTime.now());
                    view.getMainPanel().updateButtons(getPlantImagePaths());
                    updateWaterButtonStatus();
                    updateRemainingDeathTimer();
                    pauseDeathTimer();
                    break;
                }else{
                    JOptionPane.showMessageDialog(null, "You must choose a plant before you can water it! ");
                    return;
                }
                //JOptionPane.showMessageDialog(null, "Your plant is dead! \nWatering won't bring it back ):");
            case NightMode:
                System.out.println("Night mode activated");
                    if (night == false) {
                        view.getEastPanel().moonButton();
                        view.getEastPanel().nightColors();
                        view.getCenterPanel().centerNight();
                        night = true;
                    } else {
                        view.getEastPanel().sunButton();
                        view.getEastPanel().dayColors();
                        view.getCenterPanel().centerDay();
                        night = false;
                    }
                    break;
        }
    }

    /**
     * Starts the timer for the plant's life. Stops when life reaches 0.
     *
     * @author Cyrus Shaerpour
     */
    public long getRemainingDeathTimerMilliseconds(Plant plant) {
        Timer timer = plantTimers.get(plant);
        if (timer != null && plant.getDeathTime() != null) {
            Duration remainingDuration = Duration.between(LocalDateTime.now(), plant.getDeathTime());
            return remainingDuration.toMillis();
        }else {
            return 0;
        }
    }
    public void deathTimer(Plant plant) {
        if (plant.getPlantLevel() == 0) {
            System.out.println("Timer started for plant: " + plant.getName());
            JOptionPane.showMessageDialog(null, "Congrats on your new plant! \nBut be mindful, it will need water in the coming days!");

            // Create a new timer for the plant
            Timer timer = new Timer(1000, new ActionListener() { // 1 sec
                public void actionPerformed(ActionEvent e) {
                    updateEastPanel();
                    if (plant.getDeathTime() != null && plant.getDeathTime().isBefore(LocalDateTime.now())) {
                        plantDeathTimerActivation(plant);
                    }
                }
            });

            // Start the timer
            timer.start();

            // Store the timer for the plant in the map
            plantTimers.put(plant, timer);
        }
    }
    public void updateEastPanel() {
        view.getEastPanel().updateLives();
        for (Plant plant : plantTimers.keySet()) {
            if (plant.getDeathTime() != null) {
                Long remainingDeathTime = getRemainingDeathTimerMilliseconds(currentPlant);
                view.getEastPanel().updateTimeUntilDeath(remainingDeathTime);
            }
        }
    }

    public Duration getRemainingTime() {
        return remainingTime;
    }

    public void plantDeathTimerActivation(Plant plant) {
        plant.decreaseLife();
        checkLife();
        System.out.println("Plant life " + plant.getNbrOfLives() + " " + plant.getPlantName());
        view.getEastPanel().updateLives();

        // Check if the plant's number of lives is zero and stop the timer
        if (plant.getNbrOfLives() == 0) {
            Timer timer = plantTimers.get(plant);
            if (timer != null) {
                timer.stop(); // Stop the timer
                plantTimers.remove(plant); // Remove the timer from the map
                view.getMainPanel().updateButtons(getPlantImagePaths());
                System.out.println("Timer stopped for plant: " + plant.getPlantName());
            }
        }
    }
    public void resumeAllTimers() {
        for (Map.Entry<Plant, Timer> entry : plantTimers.entrySet()) {
            Plant plant = entry.getKey();
            Timer timer = entry.getValue();
            Long pauseTime = pauseTimes.get(plant);

            if (timer != null && pauseTime != null) {
                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - pauseTime;
                long delay = Math.max(1000 * 10 - elapsedTime, 0); // Kvarvarande tid

                new java.util.Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        timer.start(); // Återuppta timern
                    }
                }, delay);

                System.out.println("Timer resumed for " + plant.getPlantName() + " after delay: " + delay + "ms");

                // Ta bort paus-tidpunkten
                pauseTimes.remove(currentPlant);            }
        }
    }

    public void setRemainingTime(Duration remainingTime) {
        this.remainingTime = remainingTime;
    }

    public void updateRemainingDeathTimer() {
        for (Plant plant : plantList) {
            if (plant.getDeathTime() != null) {
                Duration remainingTime = Duration.between(LocalDateTime.now(), plant.getDeathTime());
                setRemainingTime(remainingTime);
            }
        }
    }

    public void pauseDeathTimer() {
        Timer timer = plantTimers.get(currentPlant);
        if (timer != null && timer.isRunning()) {
            // Pause the timer
            timer.stop();
            System.out.println("Timer paused for " + currentPlant.getPlantName());
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
     *
     * @author Cyrus Shaerpour
     */
    public void checkLife() {
        if (currentPlant.getNbrOfLives() == 0) {
            view.getCenterPanel().updatePlantImage(currentPlant.getPlantPicture());
            view.getMainPanel().updateButtons(getPlantImagePaths());
            view.getEastPanel().updateAmountOfLife();
            view.getEastPanel().repaint();
        }
    }

    /**
     * Updates the status of the water button based on whether any plant needs watering.
     *
     * @author Anna Granberg
     */
    public void updateWaterButtonStatus() {
        boolean waterstatus = checkWateringStatus();
        if (waterstatus ) {
            view.getEastPanel().enableWaterButton();
        } else {
            view.getEastPanel().disableWaterButton();
        }
    }

    /**
     * Checks if the plants need to be watered based on a certain timestamp (24h).
     *
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

    /**
     * Calculates the time until the next watering for the current plant.
     * If the current plant index is valid and the plant has been watered before,
     * it calculates the time remaining until the next watering based on the specified watering interval.
     *
     * @return The time in seconds until the next watering, or 0 if it cannot be calculated.
     */
    public long getTimeUntilNextWatering() {
        if (currentPlantIndex >= 0 && currentPlantIndex < plantList.size()) {
            currentPlant = plantList.get(currentPlantIndex);
            LocalDateTime currentDateTime = LocalDateTime.now();
            LocalDateTime lastWatered = currentPlant.getLastWatered();

            if (lastWatered != null) {
                Duration timeSinceLastWatered = Duration.between(lastWatered, currentDateTime);
                Duration wateringInterval = Duration.ofSeconds(10); // 10 seconds

                // Calculate the time left until the next watering in seconds
                long timeUntilNextWateringSeconds = wateringInterval.minus(timeSinceLastWatered).getSeconds();

                if (timeUntilNextWateringSeconds <= 0) {
                    //view.getEastPanel().enableWaterButton();
                    return 0; // The plant is due for watering or overdue
                }

                return timeUntilNextWateringSeconds;
            }
        }
        return 0; // Return 0 if it is not possible to calculate the remaining time
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
                System.out.println("current plant är null: " + currentPlant.getNbrOfLives());
                return 0;
            }
        } else {
            // System.out.println("plantList är tom: " + currentPlant.getNbrOfLives());
            return 0;
        }
    }

        /**
         * Retrieves the number of times watered of the first plant in the plant list.
         *
         * @return The number of times watered of the first plant, or 0 if the plant list is empty or the first plant is null.
         */
        public int getTimesWatered () {
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
        public int getPlantLevel () {
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

        public Plant getCurrentPlant() {
        return currentPlant;
        }

    /**
         * Retrieves the plant name of the first plant in the plant list.
         *
         * @return The plant name of the first plant, or 0 if the plant list is empty or the first plant is null.
         */
        public String getPlantName () {
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
        public PlantArt getPlantArt () {
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

        public Timer getPlantTimer(Plant plant) {
            return plantTimers.get(plant);
        }

        /**
         * Retrieves the paths of images associated with each plant in the plant list.
         *
         * @return A list of image paths corresponding to each plant in the plant list.
         */
        public ArrayList<String> getPlantImagePaths () {
            ArrayList<String> imagePaths = new ArrayList<>();
            for (Plant plant : plantList) {
                imagePaths.add(plant.getPlantPicture().toString());
            }
            return imagePaths;
        }

        /**
         * Tells the user to enter a new name for the current plant.
         * If a valid name is provided, updates the plant's name,
         * displays a confirmation message, and updates the view accordingly.
         * If the input is invalid (null or empty), displays an error message.
         * @author Anna Granberg
         */
        public void changePlantName () {
            String newName = JOptionPane.showInputDialog("Please enter the new plant name: ");
            if (newName != null && !newName.trim().isEmpty()) {
                currentPlant.setName(newName);
                view.getCenterPanel().updatePlantName(currentPlant.getPlantName());
            } else {
                JOptionPane.showMessageDialog(null, "Invalid input. Name not changed.");
            }
        }

        /**
         * Calculates the time elapsed since the game was last played.
         *
         * @return The time elapsed since the game was last played, in seconds.
         * @author Anna Granberg
         */
        public long getTimeSinceLastPlayed () {
            LocalDateTime timeWhenClosed = SaveGame.getTimestamp();
            LocalDateTime timeWhenOpened = loadGame.getTimestamp();

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
        public void setGameToNull () {
            if (!plantList.isEmpty() || plantList != null) {
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
            } else if (plantList.isEmpty() || plantList == null) {
                JOptionPane.showMessageDialog(null, "Your garden is empty! Nothing to remove :)", "Information", JOptionPane.INFORMATION_MESSAGE);
            }

        }

        /**
         * Removes a plant from the plant list.
         * Displays a confirmation dialog before removing the plant.
         *
         * @param plantName The name of the plant to be removed.
         * @author Anna Granberg
         */
        public void removePlant (String plantName){
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

        /**
         * Saves the current game state by invoking the SaveGame class's saveGame method with the plantList.
         * @author Anna Granberg
         */
        public void saveGame () {
            SaveGame saveGame = new SaveGame();
            saveGame.saveGame(plantList, this);
        }

        /**
         * Initializes the game rule frame for the first time playing.
         */
        public void firstTimePlaying () {
            GameRuleFrame gameRuleFrame = new GameRuleFrame();
        }

        /**
         * Retrieves the main frame view.
         *
         * @return The main frame view.
         */
        public MainFrame getView () {
            return view;
        }

        /**
         * Retrieves the list of plants.
         *
         * @return The list of plants.
         */
        public ArrayList<Plant> getPlantList () {
            return plantList;
        }
    }

