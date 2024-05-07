package Model;

import javax.swing.*;
import java.time.LocalDateTime;

public class Sunflower extends Plant{
<<<<<<< HEAD
    public Sunflower(String name, PlantArt plantArt, int nbrOfLives, int timesWatered, ImageIcon plantPicture, int plantLevel, LocalDateTime lastWatered ) {
        super(name, plantArt, nbrOfLives,timesWatered, plantPicture, plantLevel, lastWatered);
=======
    public Sunflower(String name, PlantArt plantArt,int nbrOfLives, int timesWatered, ImageIcon plantPicture, int plantLevel, String plantInfo) {
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
    @Override
    public String getPlantInfo() {
        return "The sunflower is a large inflorescence, \n" +
                " this means that the flower head is actually made of many tiny flowers called florets. \n" +
                " The central florets look like the center of a normal flower and the outer florets look like yellow petals. \n" +
                " All together they make up a 'false flower'.";
    }
}
