package Model;

import javax.swing.*;
import java.awt.*;

public class Rose extends Plant{

    public Rose(String name, PlantArt plantArt, int timesWatered, ImageIcon plantPicture, int plantLevel) {
        super(name, PlantArt.Rose, timesWatered, plantPicture, plantLevel);
    }

    //hej

    public void waterPlant(int plantLevel) {
        setTimesWatered(getTimesWatered() + 1);
        if (getTimesWatered() == 1) {
            setPlantLevel(getPlantLevel() + 1);
            setTimesWatered(0);
        }
    }
}
