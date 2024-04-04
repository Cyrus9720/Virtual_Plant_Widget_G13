package Model;

import javax.swing.*;

public class Orchid extends Plant{
    private ImageIcon plantPicture;

    public Orchid(String name, int timesWatered, int plantLevel) {
        super(name, PlantArt.Cactus, timesWatered, null, plantLevel);
        this.plantPicture = new ImageIcon("src/Images/orchid.png");

    }
}
