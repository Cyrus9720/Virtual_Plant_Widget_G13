package Model;

import javax.swing.*;

public class TomatoPlant extends Plant{

    public TomatoPlant(String name, PlantArt plantArt,int nbrOfLives, int timesWatered, ImageIcon plantPicture, int plantLevel) {
        super(name, plantArt, nbrOfLives, plantPicture, plantLevel);
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
}
