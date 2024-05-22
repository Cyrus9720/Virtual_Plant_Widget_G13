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
    public MiniTree(String name, PlantArt plantArt, int nbrOfLives, int timesWatered, ImageIcon plantPicture, int plantLevel, LocalDateTime lastWatered, boolean night) {
        super(name, plantArt, nbrOfLives, timesWatered, plantPicture, plantLevel, lastWatered, night);
    }

    @Override
    public void setPlantLevel(int plantLevel) {
        super.setPlantLevel(plantLevel);
        updateImage();
        System.out.println(getPlantPicture());
    }

    @Override
    public void setNbrOfLives(int nbrOfLives, boolean night) {
        super.setNbrOfLives(nbrOfLives, night);
        updateDeathImage();
        System.out.println(getPlantPicture());
        System.out.println("We here!");
    }

    private void updateImage() {
        if (getNight()) {
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
