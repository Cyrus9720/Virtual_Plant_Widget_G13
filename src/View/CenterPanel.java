package View;

import Controller.Controller;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * CenterPanel representerar den centrala panelen i användargränssnittet som visar en bild på en växt.
 * Den innehåller också en titel som visar växtens namn.
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
     * Skapar en ny CenterPanel.
     *
     * @param mainPanel  Referens till huvudpanelen
     * @param controller Referens till Controller för att hämta växtinformation
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
     * Hämtar huvudpanelen som denna CenterPanel är en del av.
     *
     * @return En referens till huvudpanelen.
     */
    public MainPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * Uppdaterar bilden på växten i centerpanelen.
     * @param newImage Den nya bilden som ska visas.
     * Author Cyrus Shaerpour
     */
    public void updatePlantImage(ImageIcon newImage) {
        plantLabel.setText("");
        plantPicture = newImage; // Uppdaterar bilden på växten
        plantLabel.setIcon(scaleImageIcon(plantPicture, IMAGE_WIDTH, IMAGE_HEIGHT)); // Skalar och uppdaterar växtbilden
        this.revalidate(); //  för att uppdatera bilden
        this.repaint(); // Repaint panelen för att visa den uppdaterade bilden
    }

    // Metod för att uppdatera växtnamnet
    public void updatePlantName(String plantName){
        name = plantName; // Uppdatera växtnamnet
        titledBorder.setTitle(name); // Uppdatera titeln
        this.revalidate(); // för att uppdatera titeln
        this.repaint(); // Repaint panelen för att visa den uppdaterade titeln
    }

    /**
     * Skalar bilden till storleken på GUI-fönstret
     * @param imageIcon
     * @param width
     * @param height
     * @return ImageIcon(scaledImage)
     * Author Cyrus Shaerpour
     */
    private ImageIcon scaleImageIcon(ImageIcon imageIcon, int width, int height) {
        Image image = imageIcon.getImage(); // ImageIcon till Image
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH); // Skalar bilden
        return new ImageIcon(scaledImage); // Omvandlar bilden tillbaka till ImageIcon
    }

    /**
     * Clears the center panel by resetting the plant name, plant picture, and titled border.
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
     * Author Cyrus Shaerpour
     */
    public void centerNight() {
        setBackground(new Color(47, 49, 73));
        titledBorder.setTitleColor(Color.WHITE);
    }

    /**
     * Sets the center panel to day mode.
     * Changes the background color of the panel to a lighter color and the title text color to black.
     * Author Cyrus Shaerpour
     */
    public void centerDay() {
        setBackground(new Color(225, 240, 218));
        titledBorder.setTitleColor(Color.BLACK);
    }
}
