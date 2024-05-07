package Model;

import javax.swing.*;
import java.time.LocalDateTime;

public class MiniTree extends Plant {

    public MiniTree(String name, PlantArt plantArt, int nbrOfLives, int timesWatered, ImageIcon plantPicture, int plantLevel, LocalDateTime lastWatered) {
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
                setPlantPicture(new ImageIcon("src/Images/MiniTree1.JPG"));
                break;
            case 2:
                setPlantPicture(new ImageIcon("src/Images/MiniTree2.JPG"));
                break;
            case 3:
                setPlantPicture(new ImageIcon("src/Images/MiniTree3.JPG"));
                break;
            default:
                // Handle any other cases or provide a default image
                break;
        }
    }

    @Override
    public String getPlantInfo() {
        return
                "A tree is a tall plant with a trunk and branches made of wood. Trees can live for many years. \n" +
                "The oldest tree ever discovered is approximately 5,000 years old. The four main parts of a tree are the roots, the trunk, the branches, and the leaves. \n" +
                "The roots of a tree are usually under the ground. Most trees have a single trunk. ";
    }
}
