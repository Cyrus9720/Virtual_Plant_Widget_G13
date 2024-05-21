package Model;

import Controller.Controller;

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
    public MiniTree(Controller controller, String name, PlantArt plantArt, int nbrOfLives, int timesWatered, ImageIcon plantPicture, int plantLevel, LocalDateTime lastWatered) {
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
    private void updateDeathImage() {
        if (getNbrOfLives() == 0 && getPlantLevel() == 1) {
            setPlantPicture(new ImageIcon("src/Images/MiniTreeDead1.JPG"));
        } else if (getNbrOfLives() == 0 && getPlantLevel() == 2) {
            setPlantPicture(new ImageIcon("src/Images/MiniTreeDead2.JPG"));
        } else if (getNbrOfLives() == 0 && getPlantLevel() == 3) {
            setPlantPicture(new ImageIcon("src/Images/MiniTreeDead3.JPG"));
        }
    }
}
