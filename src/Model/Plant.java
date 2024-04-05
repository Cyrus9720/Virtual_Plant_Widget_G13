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

    public Plant(String name, PlantArt plantArt, int timesWatered, ImageIcon plantPicture, int plantLevel) {
        this.name = name;
        this.plantArt = plantArt;
        nbrOfLives = 3;
        this.timesWatered = 0;
        this.plantPicture = plantPicture;
        this.plantLevel = 0;
        System.out.println("Plant created");
    }

    public void waterPlant() {
        setTimesWatered(getTimesWatered() + 1);
        if (getTimesWatered() == plantLevel + 1) {
            setPlantLevel(getPlantLevel() + 1);
            setTimesWatered(0);
            System.out.println("Plant level " + plantLevel);
        }
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
        System.out.println("Plant level " + plantLevel);
        return plantLevel;
    }

    public void setPlantLevel(int plantLevel) {
        this.plantLevel = plantLevel;
    }
}

