package View;

import Model.Plant;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class CenterPanel extends JPanel {
    private ImageIcon plantPicture;
    private JLabel plantLabel;
    private Plant currentPlant;


    private static final int IMAGE_WIDTH = 300; // Desired width for scaled images
    private static final int IMAGE_HEIGHT = 450; // Desired height for scaled images


    public CenterPanel(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setBackground(new Color(225, 240, 218));

        plantPicture = new ImageIcon("src/Images/PotArt1.JPG"); // Default image

        plantLabel = new JLabel();
        plantLabel.setIcon(scaleImageIcon(plantPicture, IMAGE_WIDTH, IMAGE_HEIGHT)); // Scale image
        Image plantPictureImage = plantPicture.getImage();
        Image scaledPlantPictureImage = plantPictureImage.getScaledInstance(200,200, Image.SCALE_SMOOTH);
        ImageIcon scaledPlantPictureIcon = new ImageIcon(scaledPlantPictureImage); //Oklart om detta behövs //Cyrus
        add(plantLabel);

    }

    /**
     * Updates the image of the plant in the center panel.
     * @param newImage The new image to display.
     * @author Cyrus Shaerpour
     */
    public void updatePlantImage(ImageIcon newImage, Plant plant) {
        setCurrentPlant(plant);  // Update the current plant first
        plantPicture = newImage;
        plantLabel.setIcon(scaleImageIcon(plantPicture, IMAGE_WIDTH, IMAGE_HEIGHT));
        repaint();
    }


    private ImageIcon scaleImageIcon(ImageIcon imageIcon, int width, int height) {
        Image image = imageIcon.getImage(); // Transform ImageIcon to Image
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH); // Scale image
        return new ImageIcon(scaledImage); // Transform Image back to ImageIcon
    }

    public void setCurrentPlant(Plant plant) {
        this.currentPlant = plant;
        System.out.println("Current Plant Name: " + currentPlant.getName());
        updateTitle();
    }

    private void updateTitle() {
        if (currentPlant != null) {
            TitledBorder titledBorder = BorderFactory.createTitledBorder(currentPlant.getName());
            Font myFont = new Font("Bebas Neue", Font.BOLD, 12);
            titledBorder.setTitleFont(myFont);
            setBorder(titledBorder);
        }
    }
}
