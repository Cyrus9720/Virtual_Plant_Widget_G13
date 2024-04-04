package Model;

import javax.swing.*;

public class Cactus extends Plant {
    private ImageIcon plantPicture;

    /**
     *
     * @param name
     * @param timesWatered
     * @param plantLevel
     *
     * @author annagranberg
     */
    public Cactus(String name, int timesWatered, int plantLevel) {
        super(name, PlantArt.Cactus, timesWatered, null, plantLevel);
        this.plantPicture = new ImageIcon("src/Images/cactus.png");

    }

    @Override
    public ImageIcon getPlantPicture() {
        return plantPicture;
    }
}
