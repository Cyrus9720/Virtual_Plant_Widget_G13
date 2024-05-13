package Model;

import javax.swing.*;
import java.time.LocalDateTime;

public class Sunflower extends Plant{
    public Sunflower(String name, PlantArt plantArt, int nbrOfLives, int timesWatered, ImageIcon plantPicture, int plantLevel, LocalDateTime lastWatered, Controller.Controller controller) {
        super(name, plantArt, nbrOfLives,timesWatered, plantPicture, plantLevel, lastWatered, controller);
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


    private void updateImage() {
        switch (getPlantLevel()) {
            case 0:
                setPlantPicture(new ImageIcon("src/Images/PotArt1.JPG"));
                break;
            case 1:
                deathTimer();
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

    private void updateDeathImage() {
        if (getNbrOfLives() == 0 && getPlantLevel() == 1) {
            setPlantPicture(new ImageIcon("src/Images/SunflowerDead1.JPG"));
        }
        else if (getNbrOfLives() == 0 && getPlantLevel() == 2) {
            setPlantPicture(new ImageIcon("src/Images/SunflowerDead2.JPG"));
        }
        else if (getNbrOfLives() == 0 && getPlantLevel() == 3) {
            setPlantPicture(new ImageIcon("src/Images/SunflowerDead3.JPG"));
        }
    }
}
