package Model;

import javax.swing.*;

public class TomatoPlant extends Plant{

    public TomatoPlant(String name, PlantArt plantArt, int timesWatered, ImageIcon plantPicture, int plantLevel) {
        super(name, plantArt, timesWatered, plantPicture, plantLevel);
    }

    @Override
    public void setPlantLevel(int plantLevel) {
        super.setPlantLevel(plantLevel);
        updateImage();
    }

    private void updateImage() {
        switch (getPlantLevel()) {
            case 0:
                setPlantPicture(new ImageIcon("src/Images/rose 1.jpeg"));
                break;
            case 1:
                setPlantPicture(new ImageIcon("src/Images/rose 2.jpeg"));
                break;
            case 2:
                setPlantPicture(new ImageIcon("src/Images/rose 3.jpeg"));
                break;
            case 3:
                setPlantPicture(new ImageIcon("src/Images/rose 4.jpeg"));
                break;
            default:
                // Handle any other cases or provide a default image
                break;
        }
    }
}
