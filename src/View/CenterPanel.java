package View;

import javax.swing.*;
import java.awt.*;

public class CenterPanel extends JPanel {

    private ImageIcon plantPicture;
    private JLabel plantLabel;

    public CenterPanel(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.WHITE);

        plantPicture = new ImageIcon("src/Images/PotArt1.JPG"); // Default image
        Image plantPictureImage = plantPicture.getImage();
        Image scaledPlantPictureImage = plantPictureImage.getScaledInstance(200,200, Image.SCALE_SMOOTH);
        ImageIcon scaledPlantPictureIcon = new ImageIcon(scaledPlantPictureImage);
        plantLabel = new JLabel(scaledPlantPictureIcon);
        add(plantLabel);
    }

    public void updatePlantImage(ImageIcon newImage) {
        plantPicture = newImage;
        plantLabel.setIcon(plantPicture);
        repaint();  // Repaint the panel to update the image
    }
}
