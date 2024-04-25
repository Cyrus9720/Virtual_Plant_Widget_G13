package View;

import Model.*;

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
        setPreferredSize(new Dimension(320, 485));
        setBackground(new Color(225, 240, 218));

        plantPicture = new ImageIcon("src/Images/PotArt1.JPG"); // Default image

        TitledBorder titledBorder = BorderFactory.createTitledBorder("Plant name here");
        Font myFont = new Font("Bebas Neue", Font.BOLD, 12);
        titledBorder.setTitleFont(myFont);
        setBorder(titledBorder);

        plantLabel = new JLabel();
        plantLabel.setIcon(scaleImageIcon(plantPicture, IMAGE_WIDTH, IMAGE_HEIGHT)); // Scale imagee
        add(plantLabel);
    }

    /**
     * Updates the image of the plant in the center panel.
     * @param newImage The new image to display.
     * @author Cyrus Shaerpour
     */
    public void updatePlantImage(ImageIcon newImage) {
        plantPicture = newImage;
        System.out.println(plantPicture);
        plantLabel.setIcon(plantPicture);
        plantLabel.setIcon(scaleImageIcon(plantPicture, IMAGE_WIDTH, IMAGE_HEIGHT)); // Scale and update the image
        repaint();  // Repaint the panel to update the image
    }
    private ImageIcon scaleImageIcon(ImageIcon imageIcon, int width, int height) {
        Image image = imageIcon.getImage(); // Transform ImageIcon to Image
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH); // Scale image
        return new ImageIcon(scaledImage); // Transform Image back to ImageIcon
    }

}