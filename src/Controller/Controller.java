package Controller;

import Model.*;
import View.ButtonType;
import View.CenterPanel;
import View.MainFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Controller {

    private MainFrame view;
    private List<Plant> plantList = new ArrayList<>();
    private CenterPanel centerPanel;
    private Plant plant;

    public Controller() {
        // Återställ tillståndet för plantorna från när programmet senast stängde
      //  restorePlantsState();

        // Lägg till en testväxt om plantList är tom

        plantList.add(new Rose("Rose", PlantArt.ROSE, 0, new ImageIcon("src/Images/rose 1.jpeg"), 0));
        plant = new Plant("Rose", PlantArt.ROSE, 0, new ImageIcon("src/Images/rose 1.jpeg"), 0);

        // Skapa ditt MainFrame-objekt efter att plantorna har skapats
        view = new MainFrame(this);

        // Skapa och konfigurera CenterPanel
        centerPanel = new CenterPanel(400, 400);
        view.add(centerPanel);


        // Uppdatera vyn med den aktuella växten
        updateView();
    }

    // Metod för att uppdatera vyn med den aktuella växten
    private void updateView() {
        if (!plantList.isEmpty()) {
            Plant currentPlant = plantList.get(0);
            centerPanel.updatePlantImage(currentPlant.getPlantPicture(), plant);
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
            centerPanel.updatePlantImage(currentPlant.getPlantPicture(), plant);
        }
    }

    // Metod för att återställa tillståndet för varje växt
   /* private void restorePlantsState() {
        List<String> savedData = GameLoad.loadGame();
        for (String data : savedData) {
            System.out.println("Data: " + data);
            String[] parts = data.split("\\,");
            String plantData = "";
            String imagiconData = "";
            for (String part : parts) {
                System.out.println("Part: " + part);
                if (part.startsWith("Plantor:") && part.contains("[")) {
                    int startIndex = part.indexOf("[") + 1;
                    int endIndex = part.indexOf("]");
                    if (startIndex >= 0 && endIndex > startIndex) {
                        plantData = part.substring(startIndex, endIndex);
                        System.out.println("Plant data: " + plantData);
                    }
                } else if (part.startsWith("Plant picture:") && part.contains("[")) {
                    int startIndex = part.indexOf("[") + 1;
                    int endIndex = part.indexOf("]");
                    if (startIndex >= 0 && endIndex > startIndex) {
                        imagiconData = part.substring(startIndex, endIndex);
                        System.out.println("Imagicon data: " + imagiconData);
                    }
                }
            }
            Plant restoredPlant = processPlantData(plantData);
            if (restoredPlant != null) {
                ImageIcon imageIcon = dataToImageIcon(imagiconData);
                if (imageIcon != null) {
                    restoredPlant.setPlantPicture(imageIcon);
                } else {
                    System.out.println("Failed to load plant picture: " + imagiconData);
                }
                plantList.add(restoredPlant);
            } else {
                System.out.println("Failed to restore plant from data: " + data);
            }
        }
    }





    // Metod för att konvertera plantdata till Plant-objekt
    /*private Plant processPlantData(String plantData) {
        if (plantData.isEmpty()) {
            return null;
        }

        // Dela upp plantData-strängen för att extrahera individuella delar
        String[] parts = plantData.split("\\|");

        String name = "";
        PlantArt art = null;
        int level = 0;
        int timesWatered = 0;
        ImageIcon plantPicture = null;

        // Processa varje del av plantData-strängen
        for (String part : parts) {
            if (part.startsWith("Plant name:")) {
                name = part.substring(part.indexOf(":") + 1).trim();
            } else if (part.startsWith("Plant art:")) {
                String artString = part.substring(part.indexOf(":") + 1).trim();
                art = PlantArt.valueOf(artString.toUpperCase());
            } else if (part.startsWith("Plant level:")) {
                level = Integer.parseInt(part.substring(part.indexOf(":") + 1).trim());
            } else if (part.startsWith("Times watered:")) {
                timesWatered = Integer.parseInt(part.substring(part.indexOf(":") + 1).trim());
            }
            // Här kan du lägga till mer logik för att bearbeta andra egenskaper hos plantan om det behövs
        }

        // Skapa och returnera ett Plant-objekt med extraherad information
        return new Plant(name, art, level, plantPicture, timesWatered);
    }

    // Metod för att konvertera Imagicon-data till ImageIcon-objekt
    private ImageIcon dataToImageIcon(String data) {
        if (data.isEmpty()) {
            return null;
        }

        // Avkoda datasträngen och skapa ett ImageIcon-objekt
        byte[] imageData = Base64.getDecoder().decode(data);
        return new ImageIcon(imageData);
    }*/

    // Metod för att spara spelstatusen
    public void saveGame() {
        System.out.println("Status sparas");
        SaveGame saveGame = new SaveGame(plantList);
    }

    // Metod för att hämta plantlistan
    public List<Plant> getPlantList() {
        return plantList;
    }

    public int getNbrOfLives(){
        return plant.getNbrOfLives();
    }

    public int getTimesWatered(){
        return plant.getTimesWatered();
    }

    public int getPlantLevel(){
        return plant.getPlantLevel();
    }

    public String getPlantName(){
        return plant.getName();
    }
}
