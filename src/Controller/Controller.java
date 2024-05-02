package Controller;

import Model.*;
import View.AddNewPlantFrame;
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

public class Controller {
    private MainFrame view;
    private ArrayList<Plant> plantList = new ArrayList<>();
    private Plant[] plants;
    private Plant currentPlant;
    private Clip wateringSoundClip;
    private int currentPlantIndex;

    public Controller() {

        try {
            LoadGame.loadGame(plantList, this); // ifall spelet spelats tidigare kommer plantList hämtas här
        } catch (Exception e) {
            System.err.println("Error loading game data: " + e.getMessage());
        }

        garden();
        view = new MainFrame(this);
    }

    private void garden() {
        if (plantList.isEmpty()) {
            plants = new Plant[]{
                    new Rose("Rose", PlantArt.ROSE, 3, 0, new ImageIcon("src/Images/PotArt1.JPG"), 0, null),
                    new Sunflower("Sunflower", PlantArt.SUNFLOWER, 3, 0, new ImageIcon("src/Images/PotArt1.JPG"), 0, null),
                    new TomatoPlant("TomatoPlant", PlantArt.TOMATO_PLANT, 3, 0, new ImageIcon("src/Images/PotArt1.JPG"), 0, null),
            };
        } else if (plantList.size() < 3) {
            Plant plant = plantList.get(0);
            PlantArt plantArt = plant.getPlantArt();
            switch (plantArt) {
                case TOMATO_PLANT:
                    plants = new Plant[]{
                            new Rose("Rose", PlantArt.ROSE, 3, 0, new ImageIcon("src/Images/PotArt1.JPG"), 0, null),
                            new Sunflower("Sunflower", PlantArt.SUNFLOWER, 3, 0, new ImageIcon("src/Images/PotArt1.JPG"), 0, null)
                    };
                    break;
                case ROSE:
                    plants = new Plant[]{
                            new TomatoPlant("TomatoPlant", PlantArt.TOMATO_PLANT, 3, 0, new ImageIcon("src/Images/PotArt1.JPG"), 0, null),
                            new Sunflower("Sunflower", PlantArt.SUNFLOWER, 3, 0, new ImageIcon("src/Images/PotArt1.JPG"), 0, null),
                    };
                    break;
                case SUNFLOWER:
                    plants = new Plant[]{
                            new Rose("Rose", PlantArt.ROSE, 3, 0, new ImageIcon("src/Images/PotArt1.JPG"), 0, null),
                            new TomatoPlant("TomatoPlant", PlantArt.TOMATO_PLANT, 3, 0, new ImageIcon("src/Images/PotArt1.JPG"), 0, null)
                    };
                    break;
            }

            for (Plant potentialPlant : plants) {
                boolean exists = false;
                for (Plant existingPlant : plantList) {
                    if (potentialPlant.getPlantName().equals(existingPlant.getPlantName())) {
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    plantList.add(potentialPlant);
                }
            }
            plants = plantList.toArray(new Plant[0]);
        } else {
            System.out.println("Load game har fyllt plantlist");
        }
    }

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

            // Uppdatera currentPlantIndex till det nya växtindexet
            currentPlantIndex = plantIndex;

            // Uppdatera gränssnittet för att visa förändringar, t.ex. en progressbar
            view.getCenterPanel().getMainPanel().refreshBar();
        } else {
            // Om plantIndex är ogiltigt (utanför intervallet), skriv ut ett felmeddelande
            System.err.println("Invalid plant index: " + id);
        }
    }

    public void addNewRose() {
        Random random = new Random();
        int randomNumber = random.nextInt(11); // Generera en slumpmässig siffra mellan 0 och 10Random random = new Random();
        String newRoseName = "Rose" + randomNumber;
        Rose newRose = new Rose(newRoseName, PlantArt.ROSE, 3, 0, new ImageIcon("src/Images/PotArt1.JPG"), 0, null);
        plantList.add(newRose);
    }

    public void addNewSunflower(){
        Random random = new Random();
        int randomNumber = random.nextInt(11); // Generera en slumpmässig siffra mellan 0 och 10
        String newSunflowerName = "Sunflower" + randomNumber;
        Sunflower newSunflower = new Sunflower(newSunflowerName, PlantArt.SUNFLOWER, 3, 0, new ImageIcon("src/Images/PotArt1.JPG"), 0, null);
        plantList.add(newSunflower);
    }

    public void addNewTomatoplant(){
        Random random = new Random();
        int randomNumber = random.nextInt(11); // Generera en slumpmässig siffra mellan 0 och 10
        String newTomatoName = "TomatoPlant" + randomNumber;
        TomatoPlant newSunflower = new TomatoPlant(newTomatoName, PlantArt.TOMATO_PLANT, 3, 0, new ImageIcon("src/Images/PotArt1.JPG"), 0, null);
        plantList.add(newSunflower);
    }
    public void buttonPressed(ButtonType button) {
        switch (button) {
            case Water:
                // Kontrollera om plantList är tom
                if (plantList.isEmpty()) {
                    // Visa felmeddelande
                    JOptionPane.showMessageDialog(null, "The pot is empty. Choose a plant to water first.", "Empty Pot", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                // Hämta den aktuella växten från plantList
                Plant currentPlant = plantList.get(currentPlantIndex);
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

                // Kontrollera om växterna behöver vattnas baserat på en viss tidsstämpel (24h)
                checkWateringStatus();
                break;
        }
    }

    /**
     * Checks if the plants need to be watered based on a certain timestamp (24h).
     */
    private void checkWateringStatus() { // todo: fixa denna roa
        LocalDateTime currentDateTime = LocalDateTime.now();

        for (Plant plant : plantList) {
            LocalDateTime lastWatered = plant.getLastWatered(); // Retrieve the LocalDateTime object

            if (lastWatered == null) {
                System.err.println("Plant last watered timestamp is null");
                continue; // Skip this plant and move on to the next one
            }

            Duration timeSinceLastWatered = Duration.between(lastWatered, currentDateTime);
            Duration wateringInterval = Duration.ofDays(1); // 24 hours

            if (timeSinceLastWatered.compareTo(wateringInterval) >= 0) {
                // Plant needs to be watered
                view.timeToWater();
            }
        }
    }
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

    public List<String> getPlantImagePaths() {
        List<String> imagePaths = new ArrayList<>();
        for (Plant plant : plantList) {
            imagePaths.add(plant.getPlantPicture().toString());
        }
        return imagePaths;
    }

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

}
