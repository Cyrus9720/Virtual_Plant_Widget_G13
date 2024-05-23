package Model;

import Controller.Controller;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Timer;
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
    private Duration wateringInterval;

    /**
     * Constructor for Plant
     * @param controller controller instansiaton
     * @param name Name of the plant
     * @param plantArt Art of the plant
     * @param plantPicture Picture of the plant
     * @param plantLevel Level of the plant
     * @author Cyrus Shaerpour
     */
    public Plant(Controller controller, String name, PlantArt plantArt, int nbrOfLives, int timesWatered, ImageIcon plantPicture, int plantLevel, LocalDateTime lastWatered) {
        this.controller = controller;
        this.name = name;
        this.plantArt = plantArt;
        this.nbrOfLives = nbrOfLives;
        this.timesWatered = timesWatered;
        this.plantPicture = plantPicture;
        this.plantLevel = plantLevel;
        this.lastWatered = lastWatered;
        this.deathTime = calculateDeathTime(lastWatered); // Initialize deathTime based on lastWatered
        wateringInterval = Duration.ofHours(1); // en timme som test
        System.out.println(wateringInterval.toMillis() + "från plant konstruktor");
    }

    /**
     * Calculates the death time for a plant based on the last time it was watered.
     * If the last watered time is not null, the death time is set to one minute after the last watered time.
     * If the last watered time is null, the death time is set to one minute after the current time.
     *
     * @param lastWatered The last time the plant was watered.
     * @return The calculated death time for the plant.
     */
    public LocalDateTime calculateDeathTime(LocalDateTime lastWatered) {
        return lastWatered != null ? lastWatered : LocalDateTime.now().plusMinutes(10);
    }

    public void setWateringInterval(Duration interval) {
        this.wateringInterval = interval;
    }

    public Duration getWateringInterval() {
        return wateringInterval;
    }

    public void setDeathTime(LocalDateTime deathTime) {
        this.deathTime = deathTime;
    }

    public void setNextDeathTime() {
        // Anta att du vill att nästa död ska inträffa om 48 timmar
        int hoursUntilDeath = 48; // 48 timmar
        long millisecondsUntilDeath = hoursUntilDeath * 60 * 60 * 1000; // Konvertera till millisekunder

        // Anropa set-metoden för att ställa in den återstående tiden tills nästa död
        controller.setRemainingDeathTimerMilliseconds(millisecondsUntilDeath);
    }
    public LocalDateTime getDeathTime() {
        return deathTime;
    }

    /**
     * Method for watering the plant and increasing the plant level
     * If the plant is not fully grown, increase the plant level
     * @return void
     * @author Cyrus Shaerpour
     */
    public void waterPlant() {
        if (nbrOfLives > 0) {
            setTimesWatered(getTimesWatered() + 1);
            if (plantLevel <=3) {
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
        } else if(nbrOfLives == 0){
            JOptionPane.showMessageDialog(null, "Your plant is dead! \nWatering won't bring it back ):");
        }
    }


    public void decreaseLife() {
        if (nbrOfLives > 0) {
            nbrOfLives--; // Minska livräknaren med ett om den är större än noll
            setNbrOfLives(getNbrOfLives());
        }
    }

    public void activateDeathEvent() {
        this.decreaseLife();
        controller.checkLife();
        System.out.println("Plant life " + this.getNbrOfLives() + " " + this.getPlantName());

        // Check if the plant's number of lives is zero and stop the timer
        if (this.getNbrOfLives() == 0) {
            Timer timer = controller.getLossLifeTimer();
            if (timer != null) {
                timer.cancel(); // Stop the timer
                System.out.println("Timer stopped for plant: " + this.getPlantName());
            }
        }
    }

    public void startNewTimer() {
        LocalDateTime now = LocalDateTime.now();
        if (deathTime != null && now.isAfter(deathTime)) {
            decreaseLife();
            if (nbrOfLives > 0) {
                // Ställ in en ny dödstid om 30 minuter som exempel
                deathTime = now.plusMinutes(30);
                setDeathTime(deathTime);
                System.out.println("New death time set: " + deathTime);
            } else {
                System.out.println("Plant has no more lives.");
            }
        } else if (deathTime == null) {
            System.out.println("Death time is not set.");
        }
    }

    public void deathMethod() {
        LocalDateTime timeRightNow = LocalDateTime.now();
        LocalDateTime deathTime = calculateDeathTime(lastWatered); // Calculate the death time based on the last watering time
        lastWatered = getLastWatered(); // Update the last watered time

        // Calculate the remaining duration until death
        Duration remainingDuration = Duration.between(timeRightNow, deathTime);

        // Calculate remaining days, hours, minutes, and seconds
        long remainingDays = remainingDuration.toDays();
        remainingDuration = remainingDuration.minusDays(remainingDays);
        long remainingHours = remainingDuration.toHours();
        remainingDuration = remainingDuration.minusHours(remainingHours);
        long remainingMinutes = remainingDuration.toMinutes();
        remainingDuration = remainingDuration.minusMinutes(remainingMinutes);
        long remainingSeconds = remainingDuration.getSeconds();

        // Construct a new LocalDateTime with the remaining time
        LocalDateTime remainingTime = timeRightNow.plusDays(remainingDays)
                .plusHours(remainingHours)
                .plusMinutes(remainingMinutes)
                .plusSeconds(remainingSeconds);

        setDeathTime(remainingTime);
    }

    /**
     * Retrieves the name of the plant.
     *
     * @return The name of the plant.
     */
    public String getPlantName() {
        return name;
    }


    public void setPlantName(String name) {
        this.name = name;
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
            // System.err.println("Could not format date");
        }
        return String.format("Plant art; %s | Plant name; %s | Plant level; %d | Times watered; %d | Number of lives; %d | Plant picture; %s | Last time watered; %s", plantArt, name, plantLevel, timesWatered, nbrOfLives, plantPicture, formattedLastWatered);
    }
}
