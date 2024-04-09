package Model;

import javax.swing.ImageIcon;

public class Rose extends Plant {

    public Rose(String name, PlantArt plantArt, int timesWatered, ImageIcon plantPicture, int plantLevel) {
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

    public void setName(String name) {
        super.setName(name);
    }
}
