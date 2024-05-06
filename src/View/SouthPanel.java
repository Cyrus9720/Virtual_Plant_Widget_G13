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

        // Initialize plant information label with default text
        plantInformation = new JLabel("Choose a plant to see plant information");
        add(plantInformation, BorderLayout.CENTER);
    }

    public void updatePlantInfo() {
        // Update plant information based on the selected plant
        switch (controller.getPlantArt()) {
            case ROSE:
                plantInformation.setText("<html>The rose is a type of flowering shrub. Its name comes from the Latin word Rosa.<br>" +
                        "The flowers of the rose grow in many different colors,<br>" +
                        "from the well-known red rose or yellow roses and sometimes white or purple roses.<br>" +
                        "Roses belong to the family of plants called Rosaceae.</html>");
                break;
            case SUNFLOWER:
                plantInformation.setText("<html>The sunflower is a large inflorescence,<br>" +
                        "this means that the flower head is actually made of many tiny flowers called florets.<br>" +
                        "The central florets look like the center of a normal flower and the outer florets look like yellow petals.<br>" +
                        "All together they make up a 'false flower'.</html>");
                break;
            case TOMATO_PLANT:
                plantInformation.setText("<html>The tomato is the edible berry of the plant Solanum lycopersicum,<br>" +
                        "commonly known as a tomato plant. The species originated in western South America and Central America.<br>" +
                        "The Nahuatl word tomatl gave rise to the Spanish word tomate, from which the English word tomato derived.</html>");
                break;
            default:
                // Handle the case if the plant type is unknown or unsupported
                plantInformation.setText("Unknown plant type.");
                break;
        }
    }
}
