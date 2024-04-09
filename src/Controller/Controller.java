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
        Rose rose = new Rose("Rose", PlantArt.Rose, 0, new ImageIcon("src/Images/rose 1.jpeg"), 0);
        plantList.add(rose);

        // create cactus object
        Cactus cactus = new Cactus("Cactus", PlantArt.Cactus, 0, new ImageIcon("src/Images/Cactus.png"),0);
        plantList.add(cactus);

        // Create and set up the CenterPanel
        centerPanel = new CenterPanel(400, 400);
        view.add(centerPanel);

    }

    public void buttonPressed(ButtonType button) {
        switch (button) {
            case Water:
                if(plantList instanceof Rose){
                    Plant rose = plantList.get(0);
                    rose.waterPlant();
                    ImageIcon updatedImage = rose.getPlantPicture();
                    centerPanel.updatePlantImage(updatedImage);
                }else if(plantList instanceof Cactus){
                    Plant cactus = plantList.get(0);
                    cactus.waterPlant();
                    ImageIcon updatedImage = cactus.getPlantPicture();
                    centerPanel.updatePlantImage(updatedImage);
                }else if(plantList instanceof Sunflower){
                    Plant sunflower = plantList.get(0);
                    sunflower.waterPlant();
                    ImageIcon updatedImage = sunflower.getPlantPicture();
                    centerPanel.updatePlantImage(updatedImage);
                }
                break;
            // Handle other button types as needed
        }
    }
}
