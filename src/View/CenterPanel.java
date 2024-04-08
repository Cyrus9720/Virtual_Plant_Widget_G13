package View;

import javax.swing.*;
import java.awt.*;

public class CenterPanel extends JPanel {

    private ImageIcon plantPicture;
    private JLabel plantLabel;

    private static final int IMAGE_WIDTH = 400; // Desired width for scaled images
    private static final int IMAGE_HEIGHT = 600; // Desired height for scaled images


    public CenterPanel(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.WHITE);

        plantPicture = new ImageIcon("src/Images/PotArt1.JPG"); // Default image
        plantLabel = new JLabel();
        plantLabel.setIcon(scaleImageIcon(plantPicture, IMAGE_WIDTH, IMAGE_HEIGHT)); // Scale image
        add(plantLabel);
    }

    public void updatePlantImage(ImageIcon newImage) {
        plantPicture = newImage;
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
