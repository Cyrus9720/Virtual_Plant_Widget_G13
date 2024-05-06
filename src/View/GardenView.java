package View;

import Controller.Controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GardenView extends JDialog {

    private Controller controller;
    private int width = 300; // The width of the dialog
    private int height = 450; // The height of the dialog
    private Font customFont = new Font("Bebas Neue", Font.BOLD, 12);
    private GardenPanel gardenPanel;

    public GardenView(JFrame parentFrame, Controller controller) {
        super(parentFrame, "Your garden!"); // modal dialog
        setSize(width, height);
        setResizable(false);
        this.controller = controller;

        // Calculate the location
        int xCoordinate = parentFrame.getX() - width;
        int yCoordinate = parentFrame.getY(); // You can adjust this as needed

<<<<<<< HEAD
        //TODO: assistent added this
        this.centerPanel = centerPanel;

        // Set the location of GardenView relative to the CenterPanel
=======
>>>>>>> backUpBranch
        setLocation(xCoordinate, yCoordinate);

        // Initialize the GardenPanel
        gardenPanel = new GardenPanel(controller.getPlantImagePaths());
        add(gardenPanel);

        setVisible(true);
    }

    // Method to update the buttons in the GardenPanel
    public void updateButtons(ArrayList<String> newPlantPaths) {
        gardenPanel.updateButtons(newPlantPaths);
    }

    private class GardenPanel extends JPanel {
        private List<String> plantPaths; // List of paths to plant images

        public GardenPanel(List<String> plantPaths) {
            this.plantPaths = plantPaths;

            setBackground(new Color(225, 240, 218));
            setLayout(new GridLayout(4, 3));

<<<<<<< HEAD
            // Array of image paths for the buttons
            String[] imagePaths = {
                    "src/Images/RoseArt3.JPG",
                    "src/Images/Sunflower3.JPG",
                    "src/Images/Tomatoe3.JPG",
                    "src/Images/Cactus3.JPG",
                    "src/Images/MiniTree3.JPG",
                    "src/Images/Blackberry3.JPG"
                    // Add more paths for additional buttons
            };

            generateButtons(imagePaths); // Call the method to generate buttons
=======
            generateButtons(); // Call the method to generate buttons based on available plants
            addAddPlantButton(); // Call the method to add the "Add Plant" button
>>>>>>> backUpBranch
        }

        // Add plant buttons with images
        public void generateButtons() {
            for (int i = 0; i < plantPaths.size(); i++) {
                ImageIcon icon = new ImageIcon(plantPaths.get(i));
                Image iconImage = icon.getImage();
                Image scaledIconImage = iconImage.getScaledInstance(50, 75, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledIconImage);
                JButton plantButton = new JButton(String.valueOf(i));
                plantButton.setFont(customFont);
                plantButton.setIcon(scaledIcon);
                plantButton.setFocusPainted(false);
                plantButton.setBorderPainted(false);

                plantButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.switchPlant(e.getActionCommand());
                        GardenView.this.dispose();

                    }
                });

                add(plantButton, BorderLayout.SOUTH);
            }
        }

        // Method to update buttons based on new plants
        public void updateButtons(ArrayList<String> newPlantPaths) {
            removeAll(); // Remove existing buttons
            plantPaths = newPlantPaths; // Update list with new plants
            generateButtons(); // Generate new buttons based on the new plants
            revalidate(); // Update layout
            repaint(); // Repaint the panel
        }

        // Method to add "Add Plant" button
        private void addAddPlantButton() {
            JButton addPlantButton = new JButton("Add new plant");
            addPlantButton.setPreferredSize(new Dimension(25,25));
            addPlantButton.setFont(customFont);
            addPlantButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    AddNewPlantFrame addNewPlantFrame = new AddNewPlantFrame(controller);
                    GardenView.this.dispose();
                }
            });
            // Håll fast vid GridLayout för att placera knappar i rader och kolumner
            setLayout(new GridLayout(plantPaths.size() + 1, 3));
            // Lägg till "Add new plant" knappen i norrläge
            add(addPlantButton);
        }

    }
}
