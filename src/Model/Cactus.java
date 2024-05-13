package Model;

import javax.swing.*;
import java.time.LocalDateTime;

public class Cactus extends Plant{
    /**
     * Constructor for Cactus
     *
     * @param name         Name of the plant
     * @param plantArt     Art of the plant
     * @param nbrOfLives   Number of plant lives
     * @param timesWatered How many times plant is watered
     * @param plantPicture Picture of the plant
     * @param plantLevel   Level of the plant
     * @param lastWatered  When plant was watered last time
     * @author Cyrus Shaerpour
     */
    public Cactus(String name, PlantArt plantArt, int nbrOfLives, int timesWatered, ImageIcon plantPicture, int plantLevel, LocalDateTime lastWatered, Controller.Controller controller) {
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
}
