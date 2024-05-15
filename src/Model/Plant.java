package Model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private String plantinfo;
    private PlantArt plantArt;
    private LocalDateTime lastWatered;
    private Timer timer;
    private LocalDateTime lastUpdatedTimestamp;
    private Clip wateringSoundClip;



    /**
     * Constructor for Plant
     * @param name Name of the plant
     * @param plantArt Art of the plant
     * @param plantPicture Picture of the plant
     * @param plantLevel Level of the plant
     * @author Cyrus Shaerpour
     */
    public Plant(String name, PlantArt plantArt, int nbrOfLives, int timesWatered, ImageIcon plantPicture, int plantLevel, LocalDateTime lastWatered) {
        this.name = name;
        this.plantArt = plantArt;
        this.nbrOfLives = nbrOfLives;
        this.timesWatered = timesWatered;
        this.plantPicture = plantPicture;
        this.plantLevel = plantLevel;
        this.lastWatered = lastWatered;
        plantinfo = null;
    }

    /**
     * Method for watering the plant and increasing the plant level
     * If the plant is not fully grown, increase the plant level
     * @return void
     * @author Cyrus Shaerpour
     */
    public void waterPlant() {
        if (nbrOfLives >0) {
            setTimesWatered(getTimesWatered() + 1);
            if (plantLevel <= 3) {
                if (getTimesWatered() == plantLevel + 1) {
                    setPlantLevel(getPlantLevel() + 1);
                    setTimesWatered(0);
                    //System.out.println("Plant level " + plantLevel);
                    if (plantLevel == 3) {
                        System.out.println("Plant is fully grown");
                    }
                }try {
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
        } else {
            JOptionPane.showMessageDialog(null, "Your plant is dead! \nWatering won't bring it back ):");
        }
    }


    public void decreaseLife() {
        if (nbrOfLives > 0) {
            nbrOfLives--; // Minska livräknaren med ett om den är större än noll
            setNbrOfLives(getNbrOfLives());
        }
    }

    //@TODO: Lägg till javadocs efterhand när metoderna börjar användas.
    public String getPlantName() {
        return name;
    }

    public void setPlantName(String name) {
        this.name = name;
    }

    public int getNbrOfLives() {
        return nbrOfLives;
    }

    public void setNbrOfLives(int nbrOfLives) {
        this.nbrOfLives = nbrOfLives;
    }

    /**
     * Method for getting the number of times the plant has been watered
     * @return int Number of times the plant has been watered
     * @author Cyrus Shaerpour
     */
    public int getTimesWatered() {
        return timesWatered;
    }

    /**
     * Method for setting the number of times the plant has been watered
     * @param timesWatered Number of times the plant has been watered
     * @return void
     * @author Cyrus Shaerpour
     */
    public void setTimesWatered(int timesWatered) {
        this.timesWatered = timesWatered;
    }

    /**
     * Method for getting the picture of the plant
     * @return ImageIcon Picture of the plant
     * @author Cyrus Shaerpour
     */
    public ImageIcon getPlantPicture() {
        return plantPicture;
    }

    /**
     * Method for setting the picture of the plant
     * @param plantPicture Picture of the plant
     * @return void
     * @author Cyrus Shaerpour
     */
    public void setPlantPicture(ImageIcon plantPicture) {
        this.plantPicture = plantPicture;
    }

    /**
     * Method for getting the level of the plant
     * @return int Level of the plant
     * @author Cyrus Shaerpour
     */
    public int getPlantLevel() {
        System.out.println(name + " Plant level " + (plantLevel));
        return plantLevel;
    }

    /**
     * Method for setting the level of the plant
     * @param plantLevel Level of the plant
     * @return void
     * @author Cyrus Shaerpour
     */
    public void setPlantLevel(int plantLevel) {
        this.plantLevel = plantLevel;
    }

    /**
     * Method for getting the plantArt of the plant
     * @return plantArt
     * @author Anna Granberg
     */
    public PlantArt getPlantArt() {
        return plantArt;
    }

    public void setLastWatered(LocalDateTime lastWatered) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String formattedDateTime = lastWatered.format(formatter);
        System.out.println(formattedDateTime); // För att kontrollera utskriften
        this.lastWatered = lastWatered;
    }
    public LocalDateTime getLastWatered() {
        return lastWatered;
    }

    public String getPlantinfo() {
        return plantinfo;
    }

    public void setPlantArt(PlantArt plantArt) {
        this.plantArt = plantArt;
    }

    public void updateTimestamp(LocalDateTime timestamp) {
        this.lastUpdatedTimestamp = timestamp;
    }

    /**
     * toString method
     * @return textOut
     * @author Anna Granberg
     */
    public String toString() {
        String formattedLastWatered = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            formattedLastWatered = lastWatered.format(formatter);
        }catch (Exception e){
            System.err.println("Could not format date");
        }
        return String.format("Plant art; %s | Plant name; %s | Plant level; %d | Times watered; %d | Number of lives; %d | Plant picture; %s | Last time watered; %s", plantArt, name, plantLevel, timesWatered, nbrOfLives, plantPicture, formattedLastWatered);
    }
}
