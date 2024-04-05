package Model;

import javax.swing.*;
import java.awt.*;

public class Plant {
    private String name;
    private int nbrOfLives;
    private int timesWatered;
    private ImageIcon plantPicture;
    private int plantLevel;

    public Plant(String name, int nbrOfLives, int timesWatered, ImageIcon plantPicture, int plantLevel) {
        this.name = name;
        this.nbrOfLives = 3;
        this.timesWatered = 0;
        this.plantPicture = plantPicture;
        this.plantLevel = 0;
    }

    public void waterPlant() {

        int i = 0;
        switch (plantLevel) {
            case 0:
                setTimesWatered(i+1);
                System.out.println("Times watered: " + getTimesWatered());

                plantLevel++;
                System.out.println("Plantlevel: " + getPlantLevel());
                break;

            case 1:
                setTimesWatered(i+1);
                System.out.println("Times watered: " + getTimesWatered());
                System.out.println("Plantlevel: " + getPlantLevel());
                if(timesWatered == 2)
                    plantLevel++;
                break;
            case 2:
                setTimesWatered(i+1);
                System.out.println(getTimesWatered());
                if(timesWatered == 3)
                    plantLevel++;
                break;
            case 3:
                setTimesWatered(3);
                break;

            default:

                break;
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
        return plantLevel;
    }

    public void setPlantLevel(int plantLevel) {
        this.plantLevel = plantLevel;
    }
}

