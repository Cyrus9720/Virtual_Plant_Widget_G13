package Model;

import javax.swing.*;
import java.time.LocalDateTime;

public class MiniTree extends Plant{
    /**
     * Constructor for Plant
     *
     * @param name         Name of the plant
     * @param plantArt     Art of the plant
     * @param nbrOfLives
     * @param timesWatered
     * @param plantPicture Picture of the plant
     * @param plantLevel   Level of the plant
     * @param lastWatered
     * @author Cyrus Shaerpour
     */
    public MiniTree(String name, PlantArt plantArt, int nbrOfLives, int timesWatered, ImageIcon plantPicture, int plantLevel, LocalDateTime lastWatered, Controller.Controller controller) {
        super(name, plantArt, nbrOfLives, timesWatered, plantPicture, plantLevel, lastWatered, controller);
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
                setPlantPicture(new ImageIcon("src/Images/MiniTree1.JPG"));
                break;
            case 2:
                setPlantPicture(new ImageIcon("src/Images/MiniTree2.JPG"));
                break;
            case 3:
                setPlantPicture(new ImageIcon("src/Images/MiniTree3.JPG"));
                break;
            default:
                break;
        }
    }
}
