package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GardenView extends JDialog {

    private int width = 300; // The width of the dialog
    private int height = 450; // The height of the dialog

    public GardenView(JFrame parentFrame, CenterPanel centerPanel) {
        super(parentFrame, "Your garden!", true); // modal dialog
        setSize(width, height);
        setResizable(false);

        // Calculate the location relative to the CenterPanel
        int xCoordinate = parentFrame.getX() - width + 10; // Move to the left side by subtracting the width
        int yCoordinate = parentFrame.getY() + centerPanel.getY() - 12; // Adjust as needed

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
                    "src/Images/RoseArt2.JPG",
                    "src/Images/RoseArt3.JPG",
                    "src/Images/RoseArt3.JPG",
                    "src/Images/RoseArt3.JPG",
                    "src/Images/RoseArt3.JPG"
                    // Add more paths for additional buttons
            };

            // Add plant buttons with images
            for (String path : imagePaths) {
                ImageIcon icon = new ImageIcon(path);
                Image iconImage = icon.getImage();
                Image scaledIconImage = iconImage.getScaledInstance(50, 75, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledIconImage);
                JButton plantButton = new JButton(scaledIcon);
                plantButton.setFocusPainted(false);
                plantButton.setBorderPainted(false);
                plantButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Handle swapping of plants here
                        // For example, change the icon of the button
                        ImageIcon currentIcon = (ImageIcon) plantButton.getIcon();
                        // Swap the icon with another plant's image
                        // You need to implement the logic for swapping plants
                    }
                });
                add(plantButton);
            }
        }
    }
}
