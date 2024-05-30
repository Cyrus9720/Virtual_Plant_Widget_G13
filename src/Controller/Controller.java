package Controller;

import Model.*;
import View.ButtonType;
import View.GameRuleFrame;
import View.MainFrame;
import javax.swing.*;
import java.awt.*;
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
    private Duration remainingTime;
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

        if (!loadGame.isFileNotEmpty()) {
            firstTimePlaying();
        }

        //plantTimers = new HashMap<>();
       // resumeAllTimers();
    }

    /**
     * Switches the current plant to the one with the specified ID.
     *
     * @param id The ID of the plant to switch to.
     * @author Cyrus Shaerpour
     */
    public void switchPlant(String id) {
        int plantIndex = Integer.parseInt(id);
        if (plantIndex >= 0 && plantIndex < plantList.size()) {
            setIsChosen(true);
            currentPlantIndex = plantIndex;
            currentPlant = plantList.get(plantIndex); // Uppdatera currentPlant när switchPlant kallas
            currentPlant.setNewDeathTime();
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
     * @author annagranberg & Cyrus Shaerpour
     */
    public void addNewRose() {
        String newName = promptForPlantName("Rose");
        ImageIcon plantImage = new ImageIcon(night ? "src/Images/Night_Empty.JPG" : "src/Images/PotArt1.JPG");
        LocalDateTime deathTime = LocalDateTime.now().plusSeconds(10);
        Rose newRose = new Rose(this, newName, PlantArt.ROSE, 3, 0, plantImage, 0, null, deathTime);
        plantList.add(newRose);
        view.getMainPanel().updateButtons(getPlantImagePaths());
    }

    /**
     * Adds a new sunflower plant to the list of plants.
     * Generates a random name for the plant if plant name is null.
     * @auhor annagranberg & Cyrus Shaerpour
     */
    public void addNewSunflower() {
        String newName = promptForPlantName("Sunflower");
        ImageIcon plantImage = new ImageIcon(night ? "src/Images/Night_Empty.JPG" : "src/Images/PotArt1.JPG");
        LocalDateTime deathTime = LocalDateTime.now().plusSeconds(10);
        Sunflower newSunflower = new Sunflower(this, newName, PlantArt.SUNFLOWER, 3, 0, plantImage, 0, null, deathTime);
        plantList.add(newSunflower);
        view.getMainPanel().updateButtons(getPlantImagePaths());
    }

    /**
     * Adds a new tomato plant to the list of plants.
     * Generates a random name for the plant if plant name is null.
     * @auhor annagranberg & Cyrus Shaerpour
     */
    public void addNewTomatoPlant() {
        String newName = promptForPlantName("TomatoPlant");
        ImageIcon plantImage = new ImageIcon(night ? "src/Images/Night_Empty.JPG" : "src/Images/PotArt1.JPG");
        LocalDateTime deathTime = LocalDateTime.now().plusHours(1);
        TomatoPlant newTomatoPlant = new TomatoPlant(this, newName, PlantArt.TOMATO_PLANT, 3, 0, plantImage, 0, null, deathTime);
        plantList.add(newTomatoPlant);
        view.getMainPanel().updateButtons(getPlantImagePaths());
    }

    /**
     * Adds a new blackberry plant to the list of plants.
     * Generates a random name for the plant if plant name is null.
     * @auhor annagranberg & Cyrus Shaerpour
     */
    public void addNewBlackberry() {
        String newName = promptForPlantName("Blackberry");
        ImageIcon plantImage = new ImageIcon(night ? "src/Images/Night_Empty.JPG" : "src/Images/PotArt1.JPG");
        LocalDateTime deathTime = LocalDateTime.now().plusHours(1);
        Blackberry newBlackberry = new Blackberry(this, newName, PlantArt.BLACKBERRY, 3, 0, plantImage, 0, null, deathTime);
        plantList.add(newBlackberry);
        view.getMainPanel().updateButtons(getPlantImagePaths());
    }

    /**
     * Adds a new mini tree plant to the list of plants.
     * Generates a random name for the plant if plant name is null.
     * @auhor annagranberg & Cyrus Shaerpour
     */
    public void addNewMiniTree() {
        String newName = promptForPlantName("MiniTree");
        ImageIcon plantImage = new ImageIcon(night ? "src/Images/Night_Empty.JPG" : "src/Images/PotArt1.JPG");
        LocalDateTime deathTime = LocalDateTime.now().plusHours(1);
        MiniTree newMiniTree = new MiniTree(this, newName, PlantArt.MINI_TREE, 3, 0, plantImage, 0, null, deathTime);
        plantList.add(newMiniTree);
        view.getMainPanel().updateButtons(getPlantImagePaths());
    }

    /**
     * Adds a new cactus plant to the list of plants.
     * Generates a random name for the plant if plant name is null.
     * @auhor annagranberg & Cyrus Shaerpour
     */
    public void addNewCactus() {
        String newName = promptForPlantName("Cactus");
        ImageIcon plantImage = new ImageIcon(night ? "src/Images/Night_Empty.JPG" : "src/Images/PotArt1.JPG");
        LocalDateTime deathTime = LocalDateTime.now().plusHours(1);
        Cactus newCactus = new Cactus(this, newName, PlantArt.CACTUS, 3, 0, plantImage, 0, null, deathTime);
        plantList.add(newCactus);
        view.getMainPanel().updateButtons(getPlantImagePaths());
    }

    /**
     * Prompts the user to enter a plant name. If the user chooses not to enter a name,
     * or enters an invalid name, a random name is generated.
     *
     * @param plantType The type of plant (e.g., "Rose", "Sunflower").
     * @return The entered or randomly generated plant name.
     * @auhor annagranberg & Cyrus Shaerpour
     */
    private String promptForPlantName(String plantType) {
        int response = JOptionPane.showConfirmDialog(null, "Do you want to choose a new name?", "Confirm", JOptionPane.YES_NO_OPTION);
        String newName;
        if (response == JOptionPane.YES_OPTION) {
            newName = JOptionPane.showInputDialog("Please enter the new plant name:");
            if (newName == null || newName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Invalid input. You will get a random name instead, but it can be changed later :)");
                Random random = new Random();
                newName = plantType + random.nextInt(11);
            }
        } else {
            Random random = new Random();
            newName = plantType + random.nextInt(11);
        }
        return newName;
    }


    /**
     * Handles button presses in the application.
     * Waters the plant and handles calls to change the gui to night mode.
     * @param button The type of button pressed.
     *
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

                    currentPlant = plantList.get(currentPlantIndex);
                    currentPlant.setLastWatered(LocalDateTime.now());

                    currentPlant.waterPlant();
                    currentPlant.setNewDeathTime(); // sätter en ny tid för plantan o dö
                    ImageIcon updatedImage = currentPlant.getPlantPicture();
                    view.getCenterPanel().updatePlantImage(updatedImage);
                    view.getMainPanel().updateButtons(getPlantImagePaths());
                    updateWaterButtonStatus();
                    view.getEastPanel().updateTimeUntilDeath(currentPlant.getDeathTime()); // uppdatera eastpanel med ny deathtime
                    break;
                }else{
                    JOptionPane.showMessageDialog(null, "You must choose a plant before you can water it! ");
                    return;
                }
                //JOptionPane.showMessageDialog(null, "Your plant is dead! \nWatering won't bring it back ):");
            case NightMode:
                    if (!night) {
                        view.getEastPanel().moonButton();
                        view.getEastPanel().nightColors();
                        view.getCenterPanel().centerNight();
                        view.getGardenPanel().nightGarden();
                        view.getMainPanel().nightMain();
                        view.getSouthPanel().nightSouth();

                        night = true;

                        for (Plant p : plantList) {
                            p.updateImage();
                            p.updateDeathImage();
                        }
                            currentPlant = plantList.get(currentPlantIndex);
                        ImageIcon updatedImage = currentPlant.getPlantPicture();
                        view.getCenterPanel().updatePlantImage(updatedImage);
                        view.getMainPanel().updateButtons(getPlantImagePaths());
                        view.getCenterPanel().updatePlantImage(currentPlant.getPlantPicture());
                        view.getGardenPanel().updateButtons(getPlantImagePaths());


                        currentPlant.updateImage();
                        currentPlant.updateDeathImage();
                        view.getCenterPanel().repaint();

                        night = true;

                    } else {
                        view.getEastPanel().sunButton();
                        view.getEastPanel().dayColors();
                        view.getCenterPanel().centerDay();
                        view.getGardenPanel().dayGarden();
                        view.getMainPanel().dayMain();
                        view.getSouthPanel().daySouth();

                        night = false;

                        for (Plant p : plantList) {
                            p.updateImage();
                            p.updateDeathImage();
                        }

                        currentPlant = plantList.get(currentPlantIndex);
                        ImageIcon updatedImage = currentPlant.getPlantPicture();
                        view.getCenterPanel().updatePlantImage(updatedImage);
                        view.getMainPanel().updateButtons(getPlantImagePaths());
                        view.getCenterPanel().updatePlantImage(currentPlant.getPlantPicture());
                        view.getGardenPanel().updateButtons(getPlantImagePaths());

                        currentPlant.updateImage();
                        currentPlant.updateDeathImage();
                        view.getCenterPanel().repaint();

                        night = false;
                    }
                    break;
        }
    }

    public LocalDateTime getTimeUntilDeath(){
        if(currentPlant != null){
            return currentPlant.getDeathTime();
        }else{
            return null;
        }
    }
    public void updateEastPanel() {

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

                Duration wateringInterval = Duration.ofSeconds(5);

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
                Duration wateringInterval = Duration.ofSeconds(5); // 10 seconds

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
     * @author Cyrus Shaerpour
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

     /*   public Timer getPlantTimer(Plant plant) {
            return plantTimers.get(plant);
        }*/

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
            if(isChosen){
                String newName = JOptionPane.showInputDialog("Please enter the new plant name: ");
                if (newName != null && !newName.trim().isEmpty()) {
                    currentPlant.setName(newName);
                    view.getCenterPanel().updatePlantName(currentPlant.getPlantName());
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid input. Name not changed.");
                }
            }else{
                JOptionPane.showMessageDialog(null, "You must choose a plant to change the name!");
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

                if(isChosen || currentPlant == null){
                    // Visa bekräftelsedialogrutan
                    int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this plant?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    boolean found = false;
                    // Loopa genom listan för att hitta rätt växt genom växtnamn
                    if (confirm == JOptionPane.YES_OPTION) {
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
                                setIsChosen(false);
                                break;
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "You have no plants to remove", ":(", JOptionPane.INFORMATION_MESSAGE);
                    }

                    if (!found) {
                        System.err.println("Det finns ingen växt med namnet \"" + plantName + "\" i listan.");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "You must choose a plant to remove it.");
                }
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
            GameRuleFrame gameRuleFrame = new GameRuleFrame(this);
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

