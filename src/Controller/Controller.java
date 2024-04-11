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
    private List<Plant> plantList = new ArrayList<>();
    private CenterPanel centerPanel;

    public Controller() {
        // Återställ tillståndet för plantorna från när programmet senast stängde
        restorePlantsState();

        // Skapa ditt MainFrame-objekt efter att plantorna har skapats
        view = new MainFrame(this);

        // Skapa och konfigurera CenterPanel
        centerPanel = new CenterPanel(400, 400);
        view.add(centerPanel);

        // Lägg till en testväxt om plantList är tom
        if (plantList.isEmpty()) {
            plantList.add(new Rose("Rose", PlantArt.ROSE, 0, new ImageIcon("src/Images/rose 1.jpeg"), 0));
        }

        // Uppdatera vyn med den aktuella växten
        updateView();
    }

    // Metod för att uppdatera vyn med den aktuella växten
    private void updateView() {
        if (!plantList.isEmpty()) {
            Plant currentPlant = plantList.get(0);
            centerPanel.updatePlantImage(currentPlant.getPlantPicture());
        }
    }

    // Metod för att hantera knapptryckningar
    public void buttonPressed(ButtonType button) {
        switch (button) {
            case Water:
                waterPlant();
                break;
            // Lägg till hantering för andra knapptryckningar här
        }
    }

    // Metod för att bevattna den aktuella växten
    private void waterPlant() {
        if (!plantList.isEmpty()) {
            Plant currentPlant = plantList.get(0);
            currentPlant.waterPlant();
            centerPanel.updatePlantImage(currentPlant.getPlantPicture());
        }
    }

    // Metod för att återställa tillståndet för varje växt
    private void restorePlantsState() {
        List<String> savedData = GameLoad.loadGame();
        for (String data : savedData) {
            String[] parts = data.split("\\|");
            String plantData = "";
            String imagiconData = "";
            for (String part : parts) {
                if (part.startsWith("Plantor:")) {
                    plantData = part.substring(part.indexOf("[") + 1, part.indexOf("]"));
                } else if (part.startsWith("Plant picture:")) {
                    imagiconData = part.substring(part.indexOf("[") + 1, part.indexOf("]"));
                }
            }
            Plant restoredPlant = processPlantData(plantData);
            ImageIcon imageIcon = dataToImageIcon(imagiconData);
            restoredPlant.setPlantPicture(imageIcon);
            plantList.add(restoredPlant);
        }
    }

    // Metod för att konvertera plantdata till Plant-objekt
    private Plant processPlantData(String plantData) {
        // Implementera hur plantdata ska bearbetas och ett Plant-objekt ska skapas
    }

    // Metod för att konvertera Imagicon-data till ImageIcon-objekt
    private ImageIcon dataToImageIcon(String data) {
        // Implementera hur Imagicon-data ska konverteras till ett ImageIcon-objekt
    }

    // Metod för att spara spelstatus
    public void saveGame() {
        SaveGame saveGame = new SaveGame(plantList);
    }

    // Metod för att hämta plantlistan
    public List<Plant> getPlantList() {
        return plantList;
    }
}
