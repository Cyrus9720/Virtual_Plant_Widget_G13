package View;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class CenterPanel extends JPanel {

    private ImageIcon plantPicture;
    private JLabel plantLabel;
    private MainPanel mainPanel;
    private static final int IMAGE_WIDTH = 300; // Desired width for scaled images
    private static final int IMAGE_HEIGHT = 450; // Desired height for scaled images


    public CenterPanel(int width, int height, MainPanel mainPanel) {
        setPreferredSize(new Dimension(320, 485));
        setBackground(new Color(225, 240, 218));

        //TODO: assistent added this
        this.mainPanel = mainPanel;

        plantPicture = new ImageIcon("src/Images/PotArt1.JPG"); // Default bild

        TitledBorder titledBorder = BorderFactory.createTitledBorder("Plant name here"); // skapar en border runt panelen
        Font myFont = new Font("Bebas Neue", Font.BOLD, 12); // font för hela spelet
        titledBorder.setTitleFont(myFont);
        setBorder(titledBorder);

        plantLabel = new JLabel();
        plantLabel.setIcon(scaleImageIcon(plantPicture, IMAGE_WIDTH, IMAGE_HEIGHT)); // skalar bild
        Image plantPictureImage = plantPicture.getImage();
        Image scaledPlantPictureImage = plantPictureImage.getScaledInstance(200,200, Image.SCALE_SMOOTH);

        add(plantLabel);
    }

    //TODO: assistent added this
    public MainPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * Updates the image of the plant in the center panel.
     * @param newImage The new image to display.
     * @author Cyrus Shaerpour
     */
    public void updatePlantImage(ImageIcon newImage) {
        plantPicture = newImage;
        //System.out.println(plantPicture.toString());
        //plantLabel.setIcon(plantPicture);
        plantLabel.setIcon(scaleImageIcon(plantPicture, IMAGE_WIDTH, IMAGE_HEIGHT)); // Skalar och uppdaterar bilden
        this.revalidate();
        this.repaint();  // Repaint panelen för att uppdatera bild
    }

    /**
     * Scales the image to the size of the gui frame
     * @param imageIcon
     * @param width
     * @param height
     * @return ImageIcon(scaledImage)
     * Author Cyrus Shaerpour
     */
    private ImageIcon scaleImageIcon(ImageIcon imageIcon, int width, int height) {
        Image image = imageIcon.getImage(); // Transform ImageIcon to Image
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH); // Scale image
        return new ImageIcon(scaledImage); // Transform Image back to ImageIcon
    }

}