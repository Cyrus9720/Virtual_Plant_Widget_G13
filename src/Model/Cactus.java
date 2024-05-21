package Model;

import Controller.Controller;

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
    public Cactus(Controller controller, String name, PlantArt plantArt, int nbrOfLives, int timesWatered, ImageIcon plantPicture, int plantLevel, LocalDateTime lastWatered) {
        super(controller, name, plantArt, nbrOfLives, timesWatered, plantPicture, plantLevel, lastWatered);
    }

    @Override
    public void setPlantLevel(int plantLevel) {
        super.setPlantLevel(plantLevel);
        updateImage();
        System.out.println(getPlantPicture());
    }

    @Override
    public void setNbrOfLives(int nbrOfLives) {
        super.setNbrOfLives(nbrOfLives);
        updateDeathImage();
        System.out.println(getPlantPicture());
        System.out.println("We here!");
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

    private void updateDeathImage() {
        if (getNbrOfLives() == 0 && getPlantLevel() == 1) {
            setPlantPicture(new ImageIcon("src/Images/CactusDead1.JPG"));
        } else if (getNbrOfLives() == 0 && getPlantLevel() == 2) {
            setPlantPicture(new ImageIcon("src/Images/CactusDead2.JPG"));
        } else if (getNbrOfLives() == 0 && getPlantLevel() == 3) {
            setPlantPicture(new ImageIcon("src/Images/CactusDead3.JPG"));
        }
    }
}
