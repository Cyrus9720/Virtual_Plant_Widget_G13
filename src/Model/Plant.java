package Model;

import javax.swing.ImageIcon;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Plant {
    private String name;
    private int nbrOfLives;
    private int timesWatered;
    private ImageIcon plantPicture;
    private int plantLevel;
    private PlantArt plantArt;
    private Timestamp lastWatered;

    /**
     * Constructor for Plant
     * @param name Name of the plant
     * @param plantArt Art of the plant
     * @param plantPicture Picture of the plant
     * @param plantLevel Level of the plant
     * @author Cyrus Shaerpour
     */
    public Plant(String name, PlantArt plantArt, int nbrOfLives, int timesWatered, ImageIcon plantPicture, int plantLevel) {
        this.name = name;
        this.plantArt = plantArt;
        this.nbrOfLives = nbrOfLives;
        this.timesWatered = timesWatered;
        this.plantPicture = plantPicture;
        this.plantLevel = plantLevel;
        // System.out.println("Plant created " + name);
    }

    /**
     * Method for watering the plant and increasing the plant level
     * If the plant is not fully grown, increase the plant level
     * @return void
     * @author Cyrus Shaerpour
     */
    public void waterPlant() {
        setTimesWatered(getTimesWatered() + 1);
        if (plantLevel < 3) {
            if (getTimesWatered() == plantLevel + 1) {
                setPlantLevel(getPlantLevel() + 1);
                setTimesWatered(0);
                //System.out.println("Plant level " + plantLevel);
                if (plantLevel == 3) {
                    System.out.println("Plant is fully grown");
                }
            }
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

    public void setLastWatered(Timestamp lastWatered) {
        lastWatered = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.lastWatered = lastWatered;
    }

    public Timestamp getLastWatered() {
        return lastWatered;
    }

    /**
     * toString method
     * @return textOut
     * @author Anna Granberg
     */
    public String toString() {
        return String.format("Plant art: %s | Plant name: %s | Plant level: %d | Times watered: %d | Number of lives: %d | Plant picture: %s | Last time watered: %s", plantArt, name, plantLevel, timesWatered, nbrOfLives, plantPicture, lastWatered);
    }
}
