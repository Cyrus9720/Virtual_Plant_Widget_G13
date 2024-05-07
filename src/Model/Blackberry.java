package Model;

import javax.swing.*;
import java.time.LocalDateTime;

public class Blackberry extends Plant{

    private String plantInfo;

    public Blackberry(String name, PlantArt plantArt, int nbrOfLives, int timesWatered, ImageIcon plantPicture, int plantLevel, LocalDateTime lastWatered) {
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
    @Override
    public String getPlantInfo() {
        return
            "The blackberry is an edible fruit produced by many species in the genus Rubus in the family Rosaceae, hybrids among these species within the subgenus Rubus, and hybrids between the subgenera Rubus and Idaeobatus. \n"
            + "The taxonomy of the blackberries has historically been confused because of hybridization and apomixis, so that species have often been grouped together and called species aggregates. ";
        }
    }
