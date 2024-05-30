package Model;

import Controller.Controller;

import javax.swing.ImageIcon;
import java.time.LocalDateTime;

public class Rose extends Plant {

    private Controller controller;

    public Rose(Controller controller, String name, PlantArt plantArt, int nbrOfLives, int timesWatered, ImageIcon plantPicture, int plantLevel, LocalDateTime lastWatered, deathTime) {
        super(controller, name, plantArt, nbrOfLives,timesWatered, plantPicture, plantLevel, lastWatered, deathTime);
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
                    setPlantPicture(new ImageIcon("src/Images/Night_Empty.JPG"));
                    break;
                case 1:
                    setPlantPicture(new ImageIcon("src/Images/Night_Rose1.JPG"));
                    break;
                case 2:
                    setPlantPicture(new ImageIcon("src/Images/Night_Rose2.JPG"));
                    break;
                case 3:
                    setPlantPicture(new ImageIcon("src/Images/Night_Rose3.JPG"));
                    break;
                default:
                    // Handle any other cases or provide a default image
                    break;
            }
        } else {
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
    }


    @Override
    public void updateDeathImage () {
        if (controller.night) {
            if (getNbrOfLives() == 0 && getPlantLevel() == 1) {
                setPlantPicture(new ImageIcon("src/Images/Night_Rose_Dead1.JPG"));
            } else if (getNbrOfLives() == 0 && getPlantLevel() == 2) {
                setPlantPicture(new ImageIcon("src/Images/Night_Rose_Dead2.JPG"));
            } else if (getNbrOfLives() == 0 && getPlantLevel() == 3) {
                setPlantPicture(new ImageIcon("src/Images/Night_Rose_Dead3.JPG"));
            }
        } else {
            if (getNbrOfLives() == 0 && getPlantLevel() == 1) {
                setPlantPicture(new ImageIcon("src/Images/RoseDead1.JPG"));
            } else if (getNbrOfLives() == 0 && getPlantLevel() == 2) {
                setPlantPicture(new ImageIcon("src/Images/RoseDead2.JPG"));
            } else if (getNbrOfLives() == 0 && getPlantLevel() == 3) {
                setPlantPicture(new ImageIcon("src/Images/RoseDead3.JPG"));
            }
        }
    }
}
