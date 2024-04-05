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
        switch (plantLevel) {
            case 0:
                setTimesWatered(getTimesWatered() + 1);
                System.out.println("Times watered: " + getTimesWatered());
                System.out.println("Plant level: " + getPlantLevel());
                if (getTimesWatered() == 1) {
                    plantLevel++;
                    setTimesWatered(0);
                }
                break;

            case 1:
                setTimesWatered(getTimesWatered() + 1);
                System.out.println("Times watered: " + getTimesWatered());
                System.out.println("Plant level: " + getPlantLevel());
                if (getTimesWatered() == 2) {
                    plantLevel++;
                    setTimesWatered(0);
                }
                break;

            case 2:
                setTimesWatered(getTimesWatered() + 1);
                System.out.println("Times watered: " + getTimesWatered());
                System.out.println("Plant level: " + getPlantLevel());
                if (getTimesWatered() == 3) {
                    plantLevel++;
                    setTimesWatered(0);
                }
                break;

            case 3:
                System.out.println("Plant level: " + getPlantLevel());
                setTimesWatered(3);
                break;

            default:
                System.out.println("Invalid plant level");
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

