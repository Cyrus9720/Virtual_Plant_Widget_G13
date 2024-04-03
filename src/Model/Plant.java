package Model;

import java.awt.*;

public class Plant {
    private String name;
    private int nbrOfLives;
    private int timesWatered;
    private Image plantPicture;

    public Plant(String name, int nbrOfLives, int timesWatered, Image plantPicture) {
        this.name = name;
        this.nbrOfLives = 3;
        this.timesWatered = 0;
        this.plantPicture = plantPicture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNbrOfLives() {
        return nbrOfLives;
    }

    public void setNbrOfLives(int nbrOfLives) {
        this.nbrOfLives = nbrOfLives;
    }

    public int getTimesWatered() {
        return timesWatered;
    }

    public void setTimesWatered(int timesWatered) {
        this.timesWatered = timesWatered;
    }

    public Image getPlantPicture() {
        return plantPicture;
    }

    public void setPlantPicture(Image plantPicture) {
        this.plantPicture = plantPicture;
    }
}

