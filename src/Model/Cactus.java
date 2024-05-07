package Model;

import javax.swing.*;
import java.time.LocalDateTime;

public class Cactus extends Plant {
    private ImageIcon plantPicture;

    /**
     * @param name
     * @param timesWatered
     * @param plantLevel
     * @author annagranberg
     */

    public Cactus(String name, PlantArt plantArt, int nbrOfLives, int timesWatered, ImageIcon plantPicture, int plantLevel, LocalDateTime lastWatered) {
        super(name, plantArt, nbrOfLives, timesWatered, plantPicture, plantLevel, lastWatered);
    }

    @Override
    public void setPlantLevel(int plantLevel) {
        super.setPlantLevel(plantLevel);
        updateImage();
    }

    private void updateImage() {
        switch (getPlantLevel()) {
            case 0:
                setPlantPicture(new ImageIcon("src/Images/PotArt1.JPG"));
                break;
            case 1:
                setPlantPicture(new ImageIcon("src/Images/Cactus1.JPG"));
                break;
            case 2:
                setPlantPicture(new ImageIcon("src/Images/Cactus2.JPG"));
                break;
            case 3:
                setPlantPicture(new ImageIcon("src/Images/Cactus3.JPG"));
                break;
            default:
                // Handle any other cases or provide a default image
                break;
        }
    }

    @Override
    public String getPlantInfo() {
        return
                "Cacti are members of the plant family Cactaceae, in the order Caryophyllales. \n"
                + "The word 'cactus' derives, through Latin, from the Ancient Greek κάκτος, kaktos, a name originally used by Theophrastus for a spiny plant whose identity is now not certain. \n"
                + "Cacti are native to the Americas, ranging from Patagonia in the south to parts of western Canada in the north—except for Rhipsalis baccifera, which also grows in Africa and Sri Lanka.";
    }
}
