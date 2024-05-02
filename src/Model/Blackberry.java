package Model;

import javax.swing.*;

public class Blackberry extends Plant{

    public Blackberry(String name, PlantArt plantArt, int nbrOfLives, int timesWatered, ImageIcon plantPicture, int plantLevel, String plantInfo) {
        super(name, plantArt, nbrOfLives,timesWatered, plantPicture, plantLevel, plantInfo);
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
                setPlantPicture(new ImageIcon("src/Images/Blackberry1.JPG"));
                break;
            case 2:
                setPlantPicture(new ImageIcon("src/Images/Blackberry2.JPG"));
                break;
            case 3:
                setPlantPicture(new ImageIcon("src/Images/Blackberry3.JPG"));
                break;
            default:
                // Handle any other cases or provide a default image
                break;
        }
    }
}
