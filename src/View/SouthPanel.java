package View;

import Controller.Controller;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class SouthPanel extends JPanel {
    private Controller controller;
    private JLabel plantInformation;

    public SouthPanel(Controller controller, int width, int height) {
        setPreferredSize(new Dimension(320, 140));
        this.controller = controller;

        TitledBorder titledBorder = BorderFactory.createTitledBorder("Plant Information");
        Font myFont = new Font("Bebas Neue", Font.BOLD, 12);
        titledBorder.setTitleFont(myFont);
        setBorder(titledBorder);

        setBackground(new Color(225, 240, 218));
        setLayout(new BorderLayout());

        // Initialize plant information label with default text
        plantInformation = new JLabel("Choose a plant to see plant information");
        add(plantInformation, BorderLayout.NORTH);
    }

    public void updatePlantInfo() {
        // Update plant information based on the selected plant
        switch (controller.getPlantArt()) {
            case ROSE:
                plantInformation.setText("<html>The rose is a type of flowering shrub. Its name comes from the Latin word Rosa. " +
                        "The flowers of the rose grow in many different colors from the well known red rose or yellow roses and sometimes white or purple roses. " +
                        "Roses belong to the family of plants called Rosaceae");
                break;
            case SUNFLOWER:
                plantInformation.setText("<html>The sunflower is a large inflorescence, this means that the flower head is actually made of many tiny flowers called florets. " +
                        "The central florets look like the center of a normal flower and the outer florets look like yellow petals. " +
                        "All together they make up a 'false flower'.</html>");
                break;
            case TOMATO_PLANT:
                plantInformation.setText("<html>The tomato is the edible berry of the plant Solanum lycopersicum commonly known as a tomato plant." +
                        " The species originated in western South America and Central America." +
                        " The Nahuatl word tomatl gave rise to the Spanish word tomate, from which the English word tomato derived.");
                break;
            case CACTUS:
                plantInformation.setText("<html>Cacti are members of the plant family Cactaceae, in the order Caryophyllales." +
                        "The word 'cactus' derives, through Latin, from the Ancient Greek κάκτος, kaktos, a name originally used by Theophrastus for a spiny plant whose identity is now not certain." +
                        "Cacti are native to the Americas, ranging from Patagonia in the south to parts of western Canada in the north—except for Rhipsalis baccifera, which also grows in Africa and Sri Lanka.");
                break;
            case BLACKBERRY:
                plantInformation.setText("<html>The blackberry is an edible fruit produced by many species in the genus Rubus in the family Rosaceae. " +
                        "Hybrids among these species within the subgenus Rubus, and hybrids between the subgenera Rubus and Idaeobatus." +
                        "The taxonomy of the blackberries has historically been confused because of hybridization and apomixis, so that species have often been grouped together and called species aggregates.");
                break;
            case MINI_TREE:
                plantInformation.setText("<html>A tree is a tall plant with a trunk and branches made of wood. Trees can live for many years." +
                        "The oldest tree ever discovered is approximately 5,000 years old. The four main parts of a tree are the roots, the trunk, the branches, and the leaves." +
                        "The roots of a tree are usually under the ground. Most trees have a single trunk. ");
                break;
            default:
                // Handle the case if the plant type is unknown or unsupported
                plantInformation.setText("Unknown plant type.");
                break;
        }
    }
}
