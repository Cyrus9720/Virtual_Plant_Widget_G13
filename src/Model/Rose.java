package Model;

import javax.swing.ImageIcon;
import java.time.LocalDateTime;

public class Rose extends Plant {

<<<<<<< HEAD
    private String plantInfo;

    public Rose(String name, PlantArt plantArt, int nbrOfLives, int timesWatered, ImageIcon plantPicture, int plantLevel, LocalDateTime lastWatered) {
        super(name, plantArt, nbrOfLives,timesWatered, plantPicture, plantLevel, lastWatered);
=======
    public Rose(String name, PlantArt plantArt, int nbrOfLives, int timesWatered, ImageIcon plantPicture, int plantLevel, String plantInfo) {
        super(name, plantArt, nbrOfLives,timesWatered, plantPicture, plantLevel, plantInfo);
>>>>>>> Cyrus_Branch_2
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

    @Override
    public String getPlantInfo() {
        // You can customize this method to provide specific information about the rose plant
        return "The rose is a type of flowering shrub. Its name comes from the Latin word Rosa. \n " +
                "The flowers of the rose grow in many different colors, \n " +
                "from the well-known red rose or yellow roses and sometimes white or purple roses. \n " +
                "Roses belong to the family of plants called Rosaceae.";
    }
}
