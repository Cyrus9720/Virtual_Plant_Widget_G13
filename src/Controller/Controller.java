package Controller;

import Model.*;
import View.ButtonType;
import View.CenterPanel;
import View.MainFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private MainFrame view;
    private List<Plant> plantList;
    private CenterPanel centerPanel;

    public Controller() {
        view = new MainFrame(this);
        plantList = new ArrayList<>();

        // Create a Rose object
        Rose rose = new Rose("Rose", PlantArt.ROSE, 0, new ImageIcon("src/Images/rose 1.jpeg"), 0);
        plantList.add(rose);

        // create cactus object
        Cactus cactus = new Cactus("Cactus", PlantArt.CACTUS, 0, new ImageIcon("src/Images/Cactus.png"),0);
        plantList.add(cactus);

        // Create and set up the CenterPanel
        centerPanel = new CenterPanel(400, 400);
        view.add(centerPanel);

    }

    public void buttonPressed(ButtonType button) {
        switch (button) {
            case Water:
                Plant rose = plantList.get(0);
                rose.waterPlant();
                ImageIcon updatedImage = rose.getPlantPicture();
                centerPanel.updatePlantImage(updatedImage,rose);

                break;
            // Handle other button types as needed
        }
    }
}
