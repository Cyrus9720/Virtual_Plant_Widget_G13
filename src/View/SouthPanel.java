package View;

import Controller.Controller;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class SouthPanel extends JPanel {
    private Controller controller;
    private JLabel plantInformation;

    public SouthPanel(Controller controller, int width, int height) {
        this.controller = controller;

        TitledBorder titledBorder = BorderFactory.createTitledBorder("Plant information");
        Font myFont = new Font("Bebas Neue", Font.BOLD, 12);
        titledBorder.setTitleFont(myFont);
        setBorder(titledBorder);

        setBackground(new Color(225, 240, 218));
        setLayout(new BorderLayout());
    }

    @Override
    public void validate() {
        super.validate();
        updatePlantInfo();
    }

    private JLabel scalePlantInfo(String plantInfo, int maxWidth) {
        JLabel label = new JLabel("<html>" + plantInfo + "</html>"); // Enable HTML rendering for text wrapping
        label.setFont(new Font("Bebas Neue", Font.BOLD, 10));
        label.setPreferredSize(new Dimension(maxWidth, 0)); // Set initial preferred size with max width
        label.setVerticalAlignment(SwingConstants.TOP); // Align text to the top

        // Calculate preferred size based on the text
        Dimension preferredSize = label.getPreferredSize();

        // Update the preferred size with the calculated width and height
        label.setPreferredSize(new Dimension(preferredSize.width, preferredSize.height));
        return label;
    }

    public void updatePlantInfo() {
        String plantInfo = ""; // Initialize plant information string

        // Check the plant type and set the corresponding plant information
        switch (controller.getPlantArt()) {
            case ROSE:
                plantInfo = "The rose is a type of flowering shrub. Its name comes from the Latin word Rosa. \\n \" +\n" +
                        "\"The flowers of the rose grow in many different colors, \\n \" +\n" +
                        "\"from the well-known red rose or yellow roses and sometimes white or purple roses. \\n \" +\n" +
                        "\"Roses belong to the family of plants called Rosaceae.";
                break;
            case SUNFLOWER:
                plantInfo = "The sunflower is a large inflorescence, \n" +
                        " this means that the flower head is actually made of many tiny flowers called florets. \n" +
                        " The central florets look like the center of a normal flower and the outer florets look like yellow petals. \n" +
                        " All together they make up a 'false flower'.";
                break;
            case TOMATO_PLANT:
                plantInfo = "\"The tomato is the edible berry of the plant Solanum lycopersicum, \\n\" +\n" +
                        " \" commonly known as a tomato plant. The species originated in western South America and Central America. \\n\"" + " +\n" +
                        "\" The Nahuatl word tomatl gave rise to the Spanish word tomate, from which the English word tomato derived.\"";
                break;
            default:
                // Handle the case if the plant type is unknown or unsupported
                plantInfo = "Unknown plant type.";
                break;
        }

        // Create a new plant information label with dynamically adjusted size
        plantInformation = scalePlantInfo(plantInfo, getWidth() - 15); // Adjust width as needed
        removeAll(); // Remove existing components before adding the new one
        add(plantInformation, BorderLayout.EAST);
        revalidate();
        repaint();
    }
}
