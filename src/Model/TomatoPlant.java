package Model;

import javax.swing.*;
import java.time.LocalDateTime;

public class TomatoPlant extends Plant{

    public TomatoPlant(String name, PlantArt plantArt, int nbrOfLives, int timesWatered, ImageIcon plantPicture, int plantLevel, LocalDateTime lastWatered ) {
        super(name, plantArt, nbrOfLives,timesWatered, plantPicture, plantLevel, lastWatered, false);
    }

    @Override
    public void setPlantLevel(int plantLevel) {
        super.setPlantLevel(plantLevel);
        updateImage();
    }

    @Override
    public void setNbrOfLives(int nbrOfLives, boolean night) {
        super.setNbrOfLives(nbrOfLives, night);
        updateDeathImage();
    }

    private void updateImage() {
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

    private void updateDeathImage() {
        if (getNbrOfLives() == 0 && getPlantLevel() == 1) {
            setPlantPicture(new ImageIcon("src/Images/TomatoDead1.JPG"));
        }
        else if (getNbrOfLives() == 0 && getPlantLevel() == 2) {
            setPlantPicture(new ImageIcon("src/Images/TomatoDead2.JPG"));
        }
        else if (getNbrOfLives() == 0 && getPlantLevel() == 3) {
            setPlantPicture(new ImageIcon("src/Images/TomatoDead3.JPG"));
        }
    }
}
