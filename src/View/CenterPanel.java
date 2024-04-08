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
        plantLabel = new JLabel(plantPicture);
        add(plantLabel);
    }

    public void updatePlantImage(ImageIcon newImage) {
        plantPicture = newImage;
        plantLabel.setIcon(plantPicture);
        repaint();  // Repaint the panel to update the image
    }
}
