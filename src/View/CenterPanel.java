package View;

import Controller.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * This class represents the center panel which shows the currently selected plant in the GUI with a title of the plant's name.
 * @author Anna Granberg & Cyrus Shaerpour
 */
public class CenterPanel extends JPanel {

    private ImageIcon plantPicture; // Bilden på växten
    private String name; // Namnet på växten
    private JLabel plantLabel; // Label som visar växtbilden
    private MainPanel mainPanel; // Referens till huvudpanelen
    private Border border;
    private TitledBorder titledBorder; // En border med text runt panelen för att visa växtnamnet
    private static final int IMAGE_WIDTH = 300; // bredd för skalade bilder
    private static final int IMAGE_HEIGHT = 450; // höjd för skalade bilder

    /**
     * Constructor for the center panel.
     * @param mainPanel  Reference to the main panel.
     * @param controller Reference to the controller
     * @author Anna Granberg
     */
    public CenterPanel(MainPanel mainPanel, Controller controller) {
        setPreferredSize(new Dimension(450, 500)); // Ställer in önskad storlek för panelen
        setBackground(new Color(225, 240, 218)); // Ställer bakgrundsfärgen för panelen

        this.mainPanel = mainPanel; // Sätter huvudpanelen

        plantPicture = new ImageIcon("src/Images/deafult.png"); // Laddar standardbilden för växten
        Font myFont = new Font("Bebas Neue", Font.BOLD, 12); // typsnitt för titeltexten
        border = BorderFactory.createLineBorder(Color.BLACK);
        titledBorder = BorderFactory.createTitledBorder(border,"You must choose a plant", TitledBorder.CENTER, TitledBorder.TOP, myFont, Color.BLACK); // Skapar en border runt panelen med ett standardmeddelande
        setBorder(titledBorder); // lägger till titelborder på panelen

        plantLabel = new JLabel(); // Skapar en label för växtbilden
        plantLabel.setIcon(scaleImageIcon(plantPicture, IMAGE_WIDTH, IMAGE_HEIGHT)); // Skalar och lägger till växtbilden på label

        add(plantLabel); // Lägger till växt-label på panelen
    }


    /**
     * Retrieves the main panel.
     * @return En referens till huvudpanelen.
     * @author Cyrus Shaerpour
     */
    public MainPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * Method to update the image of the plant in the center panel.
     * @param newImage The new image to be shown.
     * @author Cyrus Shaerpour
     */
    public void updatePlantImage(ImageIcon newImage) {
        plantLabel.setText("");
        plantPicture = newImage; // Uppdaterar bilden på växten
        plantLabel.setIcon(scaleImageIcon(plantPicture, IMAGE_WIDTH, IMAGE_HEIGHT)); // Skalar och uppdaterar växtbilden
        this.revalidate(); //  för att uppdatera bilden
        this.repaint(); // Repaint panelen för att visa den uppdaterade bilden
    }

    /**
     * Method to update the plant's name.
     * @param plantName Name to be changed to.
     * @author Anna Granberg
     */
    public void updatePlantName(String plantName){
        name = plantName; // Uppdatera växtnamnet

        titledBorder.setTitle(name); // Uppdatera titeln
        this.revalidate(); // för att uppdatera titeln
        this.repaint(); // Repaint panelen för att visa den uppdaterade titeln
    }

    /**
     * Scales the image to fit the size of the window
     * @param imageIcon Icon to be used for the image.
     * @param width Width of the image.
     * @param height Height of the image.
     * @return ImageIcon(scaledImage)
     * @author Cyrus Shaerpour
     */
    private ImageIcon scaleImageIcon(ImageIcon imageIcon, int width, int height) {
        Image image = imageIcon.getImage(); // ImageIcon till Image
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH); // Skalar bilden
        return new ImageIcon(scaledImage); // Omvandlar bilden tillbaka till ImageIcon
    }

    /**
     * Clears the center panel by resetting the plant name, plant picture, and titled border.
     * @author Anna Granberg
     */
    public void clearCenterPanel() {
        name = "You must choose a plant";
        plantPicture = null;
        plantLabel.setIcon(null);
        titledBorder.setTitle(name);
        revalidate();
        repaint();
    }

    /**
     * Sets the center panel to night mode.
     * Changes the background color of the panel to a darker color and the title text color to white.
     * @author Cyrus Shaerpour
     */
    public void centerNight() {
        setBackground(new Color(47, 49, 73));
        titledBorder.setTitleColor(Color.WHITE);
    }

    /**
     * Sets the center panel to day mode.
     * Changes the background color of the panel to a lighter color and the title text color to black.
     * @author Cyrus Shaerpour
     */
    public void centerDay() {
        setBackground(new Color(225, 240, 218));
        titledBorder.setTitleColor(Color.BLACK);
    }
}
