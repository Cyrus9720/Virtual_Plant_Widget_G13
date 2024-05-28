package Model;

import Controller.Controller;

import javax.swing.*;
import java.time.LocalDateTime;

public class Sunflower extends Plant {

    private Controller controller;
    public Sunflower(Controller controller, String name, PlantArt plantArt, int nbrOfLives, int timesWatered, ImageIcon plantPicture, int plantLevel, LocalDateTime lastWatered) {
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
                setPlantPicture(new ImageIcon("src/Images/Night_Empty.JPG"));
                break;
                case 1:
                    setPlantPicture(new ImageIcon("src/Images/Night_Sun1.JPG"));
                    break;
                case 2:
                    setPlantPicture(new ImageIcon("src/Images/Night_Sun2.JPG"));
                    break;
                case 3:
                    setPlantPicture(new ImageIcon("src/Images/Night_Sun3.JPG"));
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
                    setPlantPicture(new ImageIcon("src/Images/Sunflower1.JPG"));
                    break;
                case 2:
                    setPlantPicture(new ImageIcon("src/Images/Sunflower2.JPG"));
                    break;
                case 3:
                    setPlantPicture(new ImageIcon("src/Images/Sunflower3.JPG"));
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
                    setPlantPicture(new ImageIcon("src/Images/Night_Sun_Dead1.JPG"));
                } else if (getNbrOfLives() == 0 && getPlantLevel() == 2) {
                    setPlantPicture(new ImageIcon("src/Images/Night_Sun_Dead2.JPG"));
                } else if (getNbrOfLives() == 0 && getPlantLevel() == 3) {
                    setPlantPicture(new ImageIcon("src/Images/Night_Sun_Dead3.JPG"));
                }
            } else {
                if (getNbrOfLives() == 0 && getPlantLevel() == 1) {
                    setPlantPicture(new ImageIcon("src/Images/SunflowerDead1.JPG"));
                } else if (getNbrOfLives() == 0 && getPlantLevel() == 2) {
                    setPlantPicture(new ImageIcon("src/Images/SunflowerDead2.JPG"));
                } else if (getNbrOfLives() == 0 && getPlantLevel() == 3) {
                    setPlantPicture(new ImageIcon("src/Images/SunflowerDead3.JPG"));
                }
            }
        }
    }
