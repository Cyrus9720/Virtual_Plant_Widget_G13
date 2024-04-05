package Model;

import javax.swing.*;
import java.awt.*;

public class Rose extends Plant{

    public Rose(String name, int nbrOfLives, int timesWatered, ImageIcon plantPicture, int plantLevel) {
        super(name, nbrOfLives, timesWatered, plantPicture, plantLevel);
    }

    /*public void waterPlant() {
        setTimesWatered(getTimesWatered() + 1);
        if (getTimesWatered() == 3) {
            setPlantLevel(getPlantLevel() + 1);
            setTimesWatered(0);
        }
    }*/
}
