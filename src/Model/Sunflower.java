package Model;

import javax.swing.*;
import java.time.LocalDateTime;

public class Sunflower extends Plant{
    public Sunflower(String name, PlantArt plantArt, int nbrOfLives, int timesWatered, ImageIcon plantPicture, int plantLevel, LocalDateTime lastWatered ) {
        super(name, plantArt, nbrOfLives,timesWatered, plantPicture, plantLevel, lastWatered);
    }

    @Override
    public void setPlantLevel(int plantLevel) {
        super.setPlantLevel(plantLevel);
        updateImage();
    }


    private void updateImage() {
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

    public String getPlantInfo() {
        // You can customize this method to provide specific information about the rose plant
        return "Sunflower: " + getPlantName() + "\n"
                + "Number of lives: " + getNbrOfLives() + "\n"
                + "Times watered: " + getTimesWatered() + "\n"
                + "Plant level: " + getPlantLevel() + "\n"
                + "Last watered: " + getLastWatered();
    }
}
