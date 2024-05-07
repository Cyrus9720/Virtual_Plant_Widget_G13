package Model;

import javax.swing.*;
import java.time.LocalDateTime;

public class Blackberry extends Plant{
    /**
     * Constructor for Plant
     *
     * @param name         Name of the plant
     * @param plantArt     Art of the plant
     * @param nbrOfLives
     * @param timesWatered
     * @param plantPicture Picture of the plant
     * @param plantLevel   Level of the plant
     * @param lastWatered
     * @author Cyrus Shaerpour
     */
    public Blackberry(String name, PlantArt plantArt, int nbrOfLives, int timesWatered, ImageIcon plantPicture, int plantLevel, LocalDateTime lastWatered) {
        super(name, plantArt, nbrOfLives, timesWatered, plantPicture, plantLevel, lastWatered);
    }
}
