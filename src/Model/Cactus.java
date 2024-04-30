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

    public Cactus(String name, PlantArt plantArt,int nbrOfLives, int timesWatered, ImageIcon plantPicture, int plantLevel, String plantInfo) {
        super(name, plantArt,nbrOfLives, timesWatered, plantPicture, plantLevel, plantInfo);
    }

    private void updateImage() {
        switch (getPlantLevel()) {
            case 0:
                setPlantPicture(new ImageIcon("src/Images/PotArt1.JPG"));
                break;
            case 1:
                setPlantPicture(new ImageIcon("src/Images/RoseArt1.JPG"));
                break;
            case 2:
                setPlantPicture(new ImageIcon("src/Images/RoseArt2.JPG"));
                break;
            case 3:
                setPlantPicture(new ImageIcon("src/Images/RoseArt3.JPG"));
                break;
            default:
                // Handle any other cases or provide a default image
                break;
        }
    }

    @Override
    public ImageIcon getPlantPicture() {
        return plantPicture;
    }
}
