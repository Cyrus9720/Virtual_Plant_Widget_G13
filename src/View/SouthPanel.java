package View;

import Controller.Controller;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 *  The SouthPanel class represents the panel that displays information
 *  related to the plant in the user interface.
 *
 *  This panel includes visuals such as plant images and progress bars
 *  to indicate the status of the plant.
 *
 *  This class extends {@link javax.swing.JPanel}.
 *
 *  @author annagranberg
 */
public class SouthPanel extends JPanel
{
    private Controller controller;
    private JLabel plantInformation;

    /**
     * Constructs a new SouthPanel with the specified controller, width, and height.
     *
     * @param controller The controller object responsible for handling user actions.
     * @param width The width of the panel.
     * @param height The height of the panel.
     */
    public SouthPanel(Controller controller, int width, int height) {
        this.controller = controller;

        TitledBorder titledBorder = BorderFactory.createTitledBorder("Plant information");
        Font myFont = new Font("Bebas Neue", Font.BOLD, 12);
        titledBorder.setTitleFont(myFont);
        setBorder(titledBorder);


        setBackground(new Color(225, 240, 218));
        setLayout(new BorderLayout());
    }

    private JLabel scalePlantInfo(String plantInfo, int maxWidth) {
        plantInformation = new JLabel("<html>" + plantInfo + "</html>"); // Enable HTML rendering for text wrapping
        plantInformation.setFont(new Font("Bebas Neue", Font.BOLD, 10));
        plantInformation.setPreferredSize(new Dimension(maxWidth, 0)); // Set initial preferred size with max width
        plantInformation.setVerticalAlignment(SwingConstants.TOP); // Align text to the top

        // Calculate preferred size based on the text
        Dimension preferredSize = plantInformation.getPreferredSize();

        // Update the preferred size with the calculated width and height
        plantInformation.setPreferredSize(new Dimension(preferredSize.width, preferredSize.height));
        return plantInformation;
    }


    /**
     * Updates the plant information displayed in the panel.
     * @param plantInfo The new plant information to display.
     * @author Cyrus Shaerpour
     */
    public void updatePlantInfo(String plantInfo) {
        // Remove the existing plant information label
        // remove(plantInformation);

        // Create a new plant information label with dynamically adjusted size
        plantInformation = scalePlantInfo(plantInfo, getWidth()- 15); // Adjust width as needed
        add(plantInformation, BorderLayout.EAST);
        revalidate();
        repaint();
    }


}

