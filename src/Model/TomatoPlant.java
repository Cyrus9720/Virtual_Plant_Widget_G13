package Model;

import Controller.Controller;

import javax.swing.*;
import java.time.LocalDateTime;

public class TomatoPlant extends Plant{

    private Controller controller;

    public TomatoPlant(Controller controller, String name, PlantArt plantArt, int nbrOfLives, int timesWatered, ImageIcon plantPicture, int plantLevel, LocalDateTime lastWatered, deathTime) {
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
                    setPlantPicture(new ImageIcon("src/Images/Night_Tomato1.JPG"));
                    break;
                case 2:
                    setPlantPicture(new ImageIcon("src/Images/Night_Tomato2.JPG"));
                    break;
                case 3:
                    setPlantPicture(new ImageIcon("src/Images/Night_Tomato3.JPG"));
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
                    setPlantPicture(new ImageIcon("src/Images/Tomatoe1.JPG"));
                    break;
                case 2:
                    setPlantPicture(new ImageIcon("src/Images/Tomatoe2.JPG"));
                    break;
                case 3:
                    setPlantPicture(new ImageIcon("src/Images/Tomatoe3.JPG"));
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
                setPlantPicture(new ImageIcon("src/Images/Night_Tomato_Dead1.JPG"));
            } else if (getNbrOfLives() == 0 && getPlantLevel() == 2) {
                setPlantPicture(new ImageIcon("src/Images/Night_Tomato_Dead2.JPG"));
            } else if (getNbrOfLives() == 0 && getPlantLevel() == 3) {
                setPlantPicture(new ImageIcon("src/Images/Night_Tomato_Dead3.JPG"));
            }
        } else {
            if (getNbrOfLives() == 0 && getPlantLevel() == 1) {
                setPlantPicture(new ImageIcon("src/Images/TomatoDead1.JPG"));
            } else if (getNbrOfLives() == 0 && getPlantLevel() == 2) {
                setPlantPicture(new ImageIcon("src/Images/TomatoDead2.JPG"));
            } else if (getNbrOfLives() == 0 && getPlantLevel() == 3) {
                setPlantPicture(new ImageIcon("src/Images/TomatoDead3.JPG"));
            }
        }
    }
}
