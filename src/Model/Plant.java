package Model;

import javax.swing.*;
import java.awt.*;

public class Plant {
    private String name;
    private int nbrOfLives;
    private int timesWatered;
    private ImageIcon plantPicture;
    private int plantLevel;
    private PlantArt plantArt;

    public Plant(String name, PlantArt plantArt) {
        this.name = name;
        this.plantArt = plantArt;
        nbrOfLives = 3;
    }



    public Plant(String name, int nbrOfLives, int timesWatered, ImageIcon plantPicture, int plantLevel) {
        this.name = name;
        this.nbrOfLives = 3;
        this.timesWatered = 0;
        this.plantPicture = plantPicture;
        this.plantLevel = 0;
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

    public ImageIcon getPlantPicture() {
        return plantPicture;
    }

    public void setPlantPicture(ImageIcon plantPicture) {
        this.plantPicture = plantPicture;
    }

    public int getPlantLevel() {
        return plantLevel;
    }

    public void setPlantLevel(int plantLevel) {
        this.plantLevel = plantLevel;
    }
}

