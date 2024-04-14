package Controller;

import java.util.ArrayList;
import java.util.List;
import Model.Plant;
import Model.PlantArt;

import javax.swing.*;
import java.util.List;

public class GameProcessor {
    /*public void processSavedData(List<String> savedData) {
        for (String data : savedData) {
            // Dela upp datasträngen vid kommatecken för att extrahera individuella delar
            String[] parts = data.split(",");

            String plantData = ""; // Variabel för att lagra information om plantor
            long savedTime = 0;    // Variabel för att lagra sparad tidpunkt

            // Processa varje del av datasträngen
            for (String part : parts) {
                if (part.startsWith("Plantor:")) {
                    // Extrahera information om plantor
                    plantData = part.substring(part.indexOf("[") + 1, part.indexOf("]"));
                } else if (part.startsWith("tid:")) {
                    // Extrahera sparad tidpunkt
                    savedTime = Long.parseLong(part.substring(part.indexOf(":") + 1).trim());
                }
            }

            // Hantera sparad information om plantor och tidpunkt
            processPlantsData(plantData);
            processSavedTime(savedTime);
        }
    }

    // Metod för att bearbeta sparad information om plantor
    public List<Plant> processPlantsData(String plantData) {
        List<Plant> plants = new ArrayList<>();

        // Dela upp plantdatasträngen vid ";" för att få varje enskild planta
        String[] plantsInfo = plantData.split(";");

        // Processa varje planta
        for (String plantInfo : plantsInfo) {
            Plant plant = parsePlantInfo(plantInfo);
            // Lägg till plantan i listan av växter
            plants.add(plant);
        }

        // Returnera listan med alla växter
        return plants;
    }

    // Metod för att hantera sparad tidpunkt
    public void processSavedTime(long savedTime) {
        // Exempel 3: Visa sparad tidpunkt för användaren
        System.out.println("Spelet sparades " + calculateElapsedTime(savedTime) + " sedan.");
    }

    public String calculateElapsedTime(long savedTime) {
        long elapsedTime = System.currentTimeMillis() - savedTime;
        long seconds = elapsedTime / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        if (days > 0) {
            return days + " dagar";
        } else if (hours > 0) {
            return hours + " timmar";
        } else if (minutes > 0) {
            return minutes + " minuter";
        } else {
            return seconds + " sekunder";
        }
    }

    // Metod för att parsa information om en enskild planta
    private Plant parsePlantInfo(String plantInfo) {
        // Dela upp plantinfosträngen vid "|" för att få olika egenskaper för plantan
        String[] properties = plantInfo.split("\\|");

        // Extrahera egenskaperna och skapa en ny Plant-instans
        String plantArtString = properties[0].trim().substring(properties[0].indexOf(":") + 1).trim();
        PlantArt plantArt = PlantArt.valueOf(plantArtString.toUpperCase());
        String name = properties[1].trim().substring(properties[1].indexOf(":") + 1).trim();
        int level = Integer.parseInt(properties[2].trim().substring(properties[2].indexOf(":") + 1).trim());
        int timesWatered = Integer.parseInt(properties[3].trim().substring(properties[3].indexOf(":") + 1).trim());
        String plantPictureString = properties[4].trim().substring(properties[4].indexOf(":") + 1).trim();
        ImageIcon plantPicture = new ImageIcon(plantPictureString);
        // Skapa och returnera en ny Plant-instans
        return new Plant(name, plantArt, timesWatered, plantPicture,level);
    }*/
}
