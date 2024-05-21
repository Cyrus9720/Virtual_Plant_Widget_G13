package Model;

import Controller.Controller;

import javax.swing.ImageIcon;
import java.time.LocalDateTime;

public class Rose extends Plant {

    public Rose(Controller controller, String name, PlantArt plantArt, int nbrOfLives, int timesWatered, ImageIcon plantPicture, int plantLevel, LocalDateTime lastWatered) {
        super(controller, name, plantArt, nbrOfLives,timesWatered, plantPicture, plantLevel, lastWatered);
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

    private void updateDeathImage() {
        if (getNbrOfLives() == 0 && getPlantLevel() == 1) {
            setPlantPicture(new ImageIcon("src/Images/RoseDead1.JPG"));
        }
        else if (getNbrOfLives() == 0 && getPlantLevel() == 2) {
            setPlantPicture(new ImageIcon("src/Images/RoseDead2.JPG"));
        }
        else if (getNbrOfLives() == 0 && getPlantLevel() == 3) {
            setPlantPicture(new ImageIcon("src/Images/RoseDead3.JPG"));
        }
    }
}
