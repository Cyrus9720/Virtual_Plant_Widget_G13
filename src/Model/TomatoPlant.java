package Model;

import javax.swing.*;
import java.time.LocalDateTime;

public class TomatoPlant extends Plant{

    public TomatoPlant(String name, PlantArt plantArt, int nbrOfLives, int timesWatered, ImageIcon plantPicture, int plantLevel, LocalDateTime lastWatered ) {
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

    public String getPlantInfo() {
        return "The tomato is the edible berry of the plant Solanum lycopersicum, \n" +
                " commonly known as a tomato plant. The species originated in western South America and Central America. \n" +
                " The Nahuatl word tomatl gave rise to the Spanish word tomate, from which the English word tomato derived.";
    }
}
