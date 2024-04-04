package Model;

import javax.swing.*;

public class Rose extends Plant{
    private ImageIcon plantPicture;

    /**
     *
     * @param name
     * @param timesWatered
     * @param plantLevel
     *
     * @author annagranberg
     */
    public Rose(String name, int timesWatered, int plantLevel) {
        super(name, PlantArt.Cactus, timesWatered, null, plantLevel);
        this.plantPicture = new ImageIcon("src/Images/rose.png");

    }

}
