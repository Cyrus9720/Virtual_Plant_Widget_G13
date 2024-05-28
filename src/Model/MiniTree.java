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

    private Controller controller;
    public MiniTree(Controller controller, String name, PlantArt plantArt, int nbrOfLives, int timesWatered, ImageIcon plantPicture, int plantLevel, LocalDateTime lastWatered) {
        super(controller, name, plantArt, nbrOfLives, timesWatered, plantPicture, plantLevel, lastWatered);
        this.controller = controller;
    }

    @Override
    public void setPlantLevel(int plantLevel) {
        super.setPlantLevel(plantLevel);
        updateImage();
    }

    @Override
    public void setNbrOfLives(int nbrOfLives) {
        super.setNbrOfLives(nbrOfLives);
        updateDeathImage();
    }

    @Override
    public void updateImage() {
        if (controller.night) {
            switch (getPlantLevel()) {
                case 0:
                    setPlantPicture(new ImageIcon("src/Images/NightEmpty.JPG"));
                    break;
                case 1:
                    setPlantPicture(new ImageIcon("src/Images/Night_Tree1.JPG"));
                    break;
                case 2:
                    setPlantPicture(new ImageIcon("src/Images/Night_Tree2.JPG"));
                    break;
                case 3:
                    setPlantPicture(new ImageIcon("src/Images/Night_Tree3.JPG"));
                    System.out.println("Night");
                    break;
                default:
                    break;
            }
        } else {
            switch (getPlantLevel()) {
                case 0:
                    setPlantPicture(new ImageIcon("src/Images/PotArt1.JPG"));
                    System.out.println("Day1");
                    break;
                case 1:
                    setPlantPicture(new ImageIcon("src/Images/MiniTree1.JPG"));
                    break;
                case 2:
                    setPlantPicture(new ImageIcon("src/Images/MiniTree2.JPG"));
                    break;
                case 3:
                    setPlantPicture(new ImageIcon("src/Images/MiniTree3.JPG"));
                    System.out.println("Day");
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void updateDeathImage() {
        if (controller.night) {
            if (getNbrOfLives() == 0 && getPlantLevel() == 1) {
                setPlantPicture(new ImageIcon("src/Images/Night_Tree_Dead1.JPG"));
            } else if (getNbrOfLives() == 0 && getPlantLevel() == 2) {
                setPlantPicture(new ImageIcon("src/Images/Night_Tree_Dead2.JPG"));
            } else if (getNbrOfLives() == 0 && getPlantLevel() == 3) {
                setPlantPicture(new ImageIcon("src/Images/Night_Tree_Dead3.JPG"));
            } else {
                if (getNbrOfLives() == 0 && getPlantLevel() == 1) {
                    setPlantPicture(new ImageIcon("src/Images/MiniTreeDead1.JPG"));
                } else if (getNbrOfLives() == 0 && getPlantLevel() == 2) {
                    setPlantPicture(new ImageIcon("src/Images/MiniTreeDead2.JPG"));
                } else if (getNbrOfLives() == 0 && getPlantLevel() == 3) {
                    setPlantPicture(new ImageIcon("src/Images/MiniTreeDead3.JPG"));
                }
            }
        }
    }
}
