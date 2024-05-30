package Model;

import Controller.Controller;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import javax.sound.sampled.Clip;

public abstract class Plant {
    private String name;
    private int nbrOfLives;
    private int timesWatered;
    private ImageIcon plantPicture;
    private int plantLevel;
    private PlantArt plantArt;
    private LocalDateTime lastWatered;
    private LocalDateTime deathTime;
    private Clip wateringSoundClip;
    private Controller controller;

    /**
     * Constructor for Plant
     *
     * @param controller   controller instansiaton
     * @param name         Name of the plant
     * @param plantArt     Art of the plant
     * @param plantPicture Picture of the plant
     * @param plantLevel   Level of the plant
     * @author Cyrus Shaerpour
     */
    public Plant(Controller controller, String name, PlantArt plantArt, int nbrOfLives, int timesWatered, ImageIcon plantPicture, int plantLevel, LocalDateTime lastWatered, LocalDateTime deathTime) {
        this.controller = controller;
        this.name = name;
        this.plantArt = plantArt;
        this.nbrOfLives = nbrOfLives;
        this.timesWatered = timesWatered;
        this.plantPicture = plantPicture;
        this.plantLevel = plantLevel;
        this.lastWatered = lastWatered;
        this.deathTime = deathTime;
    }

    /**
     * Method for watering the plant and increasing the plant level
     * If the plant is not fully grown, increase the plant level
     *
     * @return void
     * @author Cyrus Shaerpour
     */
    public void waterPlant() {
        if (nbrOfLives > 0) {
            setTimesWatered(getTimesWatered() + 1);
            if (plantLevel <= 3) {
                if (getTimesWatered() == plantLevel + 1) {
                    setPlantLevel(getPlantLevel() + 1);
                    setTimesWatered(0);
                    //System.out.println("Plant level " + plantLevel);
                    if (plantLevel == 3) {
                        System.out.println("Plant is fully grown");
                    }
                }
                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResourceAsStream("/sounds/watering.wav")));
                    wateringSoundClip = AudioSystem.getClip();
                    wateringSoundClip.open(audioInputStream);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if (wateringSoundClip != null) {
                    wateringSoundClip.setFramePosition(0);
                    wateringSoundClip.start();
                }
            }
        } else if (nbrOfLives == 0) {
            JOptionPane.showMessageDialog(null, "Your plant is dead! \nWatering won't bring it back ):");
        }
    }


    /**
     * Method for decreasing the number of lives of the plant
     *
     * @return void
     * @author Cyrus Shaerpour
     */
    public void decreaseLife() {
        if (nbrOfLives > 0) {
            nbrOfLives--; // Minska livräknaren med ett om den är större än noll
            setNbrOfLives(getNbrOfLives());
        }
    }

    public void setNewDeathTime() {
        LocalDateTime now = LocalDateTime.now();
        if (deathTime != null && now.isAfter(deathTime)) {
            decreaseLife();
            if (nbrOfLives > 0) {
                // Ställ in en ny dödstid om 1h som exempel
                deathTime = now.plusSeconds(10);
                setDeathTime(deathTime);
                System.out.println("New death time set: " + deathTime + " // plant");
            } else if (deathTime != null) {
                deathTime = now.plusSeconds(10);
                setDeathTime(deathTime);
                System.out.println("New death time is set to " + deathTime + " // plant");
            } else if (deathTime == null) {
                System.err.println("Death time is not set. // plant");
            }
        }
    }

    /**
     * Retrieves the name of the plant.
     *
     * @return The name of the plant.
     */
    public String getPlantName() {
        return name;
    }

    /**
     * Retrieves the number of lives of the plant.
     *
     * @return The number of lives of the plant.
     */

    public int getNbrOfLives() {
        return nbrOfLives;
    }

    public void setNbrOfLives(int nbrOfLives) {
        this.nbrOfLives = nbrOfLives;
    }

    /**
     * Method for getting the number of times the plant has been watered
     *
     * @return int Number of times the plant has been watered
     * @author Cyrus Shaerpour
     */
    public int getTimesWatered() {
        return timesWatered;
    }

    /**
     * Method for setting the number of times the plant has been watered
     *
     * @param timesWatered Number of times the plant has been watered
     * @return void
     * @author Cyrus Shaerpour
     */
    public void setTimesWatered(int timesWatered) {
        this.timesWatered = timesWatered;
    }

    /**
     * Method for getting the picture of the plant
     *
     * @return ImageIcon Picture of the plant
     * @author Cyrus Shaerpour
     */
    public ImageIcon getPlantPicture() {
        return plantPicture;
    }

    /**
     * Method for setting the picture of the plant
     *
     * @param plantPicture Picture of the plant
     * @return void
     * @author Cyrus Shaerpour
     */
    public void setPlantPicture(ImageIcon plantPicture) {
        this.plantPicture = plantPicture;
    }

    /**
     * Method for getting the level of the plant
     *
     * @return int Level of the plant
     * @author Cyrus Shaerpour
     */
    public int getPlantLevel() {
        System.out.println(name + " Plant level " + (plantLevel));
        return plantLevel;
    }

    /**
     * Method for setting the level of the plant
     *
     * @param plantLevel Level of the plant
     * @return void
     * @author Cyrus Shaerpour
     */
    public void setPlantLevel(int plantLevel) {
        this.plantLevel = plantLevel;
    }

    /**
     * Method for getting the plantArt of the plant
     *
     * @return plantArt
     * @author Anna Granberg
     */
    public PlantArt getPlantArt() {
        return plantArt;
    }

    /**
     * Sets the last time the plant was watered.
     *
     * @param lastWatered The LocalDateTime object representing the last time the plant was watered.
     */
    public void setLastWatered(LocalDateTime lastWatered) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String formattedDateTime = lastWatered.format(formatter);
        System.out.println(formattedDateTime); // For debugging purposes
        this.lastWatered = lastWatered;
    }

    /**
     * Retrieves the last time the plant was watered.
     *
     * @return The LocalDateTime object representing the last time the plant was watered.
     */
    public LocalDateTime getLastWatered() {
        return lastWatered;
    }

    /**
     * Sets the name of the plant.
     *
     * @param name The name to set for the plant.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the name of the plant.
     *
     * @return The name of the plant.
     */
    public String getName() {
        return name;
    }

    public void setDeathTime(LocalDateTime deathTime) {
        this.deathTime = deathTime;
    }

    public LocalDateTime getDeathTime() {
        return deathTime;
    }

    public abstract void updateImage();{
    }

    public abstract void updateDeathImage(); {
}

    /**
     * toString method
     *
     * @return textOut
     * @author Anna Granberg
     */
    public String toString() {
        String formattedLastWatered = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            formattedLastWatered = lastWatered.format(formatter);
        } catch (Exception e) {
            // System.err.println("Could not format date");
        }
        return String.format("Plant art; %s | Plant name; %s | Plant level; %d | Times watered; %d | Number of lives; %d | Plant picture; %s | Last time watered; %s", plantArt, name, plantLevel, timesWatered, nbrOfLives, plantPicture, formattedLastWatered);
    }
    }

