package Controller;

import Model.PlantArt;
import Model.Rose;
import View.ButtonType;
import View.CenterPanel;
import View.MainFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private MainFrame view;
    private List<Rose> plantList;
    private CenterPanel centerPanel;

    public Controller() {
        view = new MainFrame(this);
        plantList = new ArrayList<>();

        // Create a Rose object
        Rose rose = new Rose("Rose", PlantArt.Rose, 0, new ImageIcon("src/Images/rose 1.jpeg"), 0);
        plantList.add(rose);

        // Create and set up the CenterPanel
        centerPanel = new CenterPanel(400, 400);
        view.add(centerPanel);

        // No need to register CenterPanel as an observer anymore
    }

    public void buttonPressed(ButtonType button) {
        switch (button) {
            case Water:
                // For this example, we are using the first Rose object in the plantList
                Rose rose = plantList.get(0);
                rose.waterPlant();
                ImageIcon updatedImage = rose.getPlantPicture();
                centerPanel.updatePlantImage(updatedImage);
                break;
            // Handle other button types as needed
        }
    }

    // Other methods...
}
