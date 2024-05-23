package Model;

import Controller.Controller;

import javax.swing.*;
import java.time.LocalDateTime;

public class Blackberry extends Plant{
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
    public Blackberry(Controller controller, String name, PlantArt plantArt, int nbrOfLives, int timesWatered, ImageIcon plantPicture, int plantLevel, LocalDateTime lastWatered) {
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
    }

    private void updateImage() {
        switch (getPlantLevel()) {
            case 0:
                setPlantPicture(new ImageIcon("src/Images/PotArt1.JPG"));
                break;
            case 1:
                setPlantPicture(new ImageIcon("src/Images/Blackberry1.JPG"));
                break;
            case 2:
                setPlantPicture(new ImageIcon("src/Images/Blackberry2.JPG"));
                break;
            case 3:
                setPlantPicture(new ImageIcon("src/Images/Blackberry3.JPG"));
                break;
            default:
                // Handle any other cases or provide a default image
                break;
        }
    }

    private void updateDeathImage() {
        if (getNbrOfLives() == 0 && getPlantLevel() == 1) {
            setPlantPicture(new ImageIcon("src/Images/BlackberryDead1.JPG"));
        } else if (getNbrOfLives() == 0 && getPlantLevel() == 2) {
            setPlantPicture(new ImageIcon("src/Images/BlackberryDead2.JPG"));
        } else if (getNbrOfLives() == 0 && getPlantLevel() == 3) {
            setPlantPicture(new ImageIcon("src/Images/BlackberryDead3.JPG"));
        }
    }
}
