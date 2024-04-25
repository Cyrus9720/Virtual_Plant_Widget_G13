package View;
import Controller.Controller;
import Model.Plant;
import Model.PlantArt;
import Model.Sunflower;
import Model.TomatoPlant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GardenView extends JDialog {

    private Controller controller;
    private int width = 300; // The width of the dialog
    private int height = 450; // The height of the dialog

    //TODO: assistent added this
    CenterPanel centerPanel;

    public GardenView(JFrame parentFrame, CenterPanel centerPanel, Controller controller) {
        super(parentFrame, "Your garden!", true); // modal dialog
        setSize(width, height);
        setResizable(false);
        this.controller = controller;

        // Calculate the location relative to the CenterPanel
        int xCoordinate = parentFrame.getX() - width;
        //int xCoordinate = parentFrame.getX() - centerPanel.getX() - 280; // Move to the left side by subtracting the width
        int yCoordinate = parentFrame.getY() + centerPanel.getY() - 12; // Adjust as needed



        //TODO: assistent added this
        this.centerPanel = centerPanel;




        // Set the location of GardenView relative to the CenterPanel
        setLocation(xCoordinate, yCoordinate);

        GardenPanel gardenPanel = new GardenPanel();
        add(gardenPanel);

        setVisible(true);
    }

    private class GardenPanel extends JPanel {
        public GardenPanel() {
            setBackground(new Color(225, 240, 218));
            setLayout(new GridLayout(4, 3));

            // Array of image paths for the buttons
            String[] imagePaths = {
                    "src/Images/RoseArt3.JPG",
                    "src/Images/Sunflower3.JPG",
                    "src/Images/Tomatoe3.JPG",
                    "src/Images/RoseArt3.JPG",
                    "src/Images/RoseArt3.JPG",
                    "src/Images/RoseArt3.JPG"
                    // Add more paths for additional buttons
            };

            generateButtons(imagePaths); // Call the method to generate buttons
        }

        // Add plant buttons with images
        public void generateButtons(String[] imagePaths) {
            for (int i = 0; i < imagePaths.length; i++) {
                ImageIcon icon = new ImageIcon(imagePaths[i]);
                Image iconImage = icon.getImage();
                Image scaledIconImage = iconImage.getScaledInstance(50, 75, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledIconImage);
                JButton plantButton = new JButton(String.valueOf(i));
                plantButton.setIcon(scaledIcon);
                plantButton.setFocusPainted(false);
                plantButton.setBorderPainted(false);

                plantButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("action listener says" + e.getActionCommand());
                        controller.switchPlant(e.getActionCommand());
                        controller.getPlantList();
                        //centerPanel.getMainPanel().refreshBar();
                        //controller.addPlant(e.getSource());
                        //controller.addPlant(new TomatoPlant("Empty", PlantArt.POT, 0, new ImageIcon("src/Images/PotArt1.JPG"), 0));
                    }
                });

                add(plantButton);
            }
        }
    }
}