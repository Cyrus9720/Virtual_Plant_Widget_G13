package View;

import Controller.Controller;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class SouthPanel extends JPanel {
    private Controller controller;
    private JLabel plantInformation;
    private TitledBorder titledBorder;
    private Border border;
    
    /**
     * Constructs a SouthPanel with the specified controller.
     *
     * @param controller The controller object associated with the panel.
     */
    public SouthPanel(Controller controller) {
        this.controller = controller;
        setPreferredSize(new Dimension(320,130));

        Font myFont = new Font("Bebas Neue", Font.BOLD, 12);  // font för hela spelet
        border = BorderFactory.createLineBorder(Color.BLACK);
        titledBorder = BorderFactory.createTitledBorder(border, "Plant information", TitledBorder.CENTER, TitledBorder.TOP, myFont, Color.BLACK);
        setBorder(titledBorder);

        setBackground(new Color(225, 240, 218));
        setLayout(new BorderLayout());

        // Initialize the plant information label with default text
        plantInformation = new JLabel("Choose a plant to see plant information");

        add(plantInformation, BorderLayout.NORTH);
    }

    /**
     * A method to update plantInformation label whenever a plant is switched.
     * @author Anna Granberg
     */
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
                        "The central florets look like the center of a normal flower and the outer florets look like yellow petals. " +
                        "All together they make up a 'false flower'.</html>");
                break;
            case TOMATO_PLANT:
                plantInformation.setText("<html>The tomato is the edible berry of the plant Solanum lycopersicum,<br>" +
                        "commonly known as a tomato plant. The species originated in western South America and Central America.<br>" +
                        "The Nahuatl word tomatl gave rise to the Spanish word tomate, from which the English word tomato derived.</html>");
                break;
            case BLACKBERRY:
                plantInformation.setText("<html> Blackberries have the highest antioxidant content per serving of any food. " +
                        "Blackberries are not true berries. Each little bump in the “berry” is a fruit, or “drupelet” containing its own seed. <br>" +
                        "Blackberries grow wild all over the world, on every continent except Australia and Antarctica. </html>");
                break;
            case MINI_TREE:
                plantInformation.setText("<html> Minitree is a compact, innovative solution for urban greenery enthusiasts. Perfectly designed for small spaces like apartments or offices, " +
                        "it brings the beauty of nature indoors. With its slender trunk and carefully cultivated foliage, it adds a touch of freshness and tranquility to any environment." +
                        "A mini tree requires minimal maintenance, making it ideal for busy urban dwellers seeking a slice of nature in their hectic lives. Whether placed on a desk, shelf, or windowsill, " +
                        "the mini tree effortlessly elevates the ambiance, offering a refreshing escape from the concrete jungle. </html>");
                break;
            case CACTUS:
                plantInformation.setText("<html> One of the most unique things about cacti is their ability to store large amounts of water in their stems. " +
                        "This adaptation helps them to survive in desert conditions where water is scarce. " +
                        "When it rains, cacti absorb water through their roots and store it in their stems. </html>");
                break;
            default:
                // Handle the case if the plant type is unknown or unsupported
                plantInformation.setText("Unknown plant type.");
                break;
        }
    }

    /**
     * A method to clear the southPanel of text
     * @author Anna Granberg
     */
    public void clearSouthPanel(){
        plantInformation.setText("Choose a plant to see plant information");
        revalidate();
        repaint();
    }

    /**
     * Sets the south panel to night mode.
     * Changes the background color and the text color of the plant information label.
     * @author Cyrus Shaerpour
     */
    public void nightSouth() {
        setBackground(new Color(47, 49, 73));
        plantInformation.setForeground(Color.WHITE);
        titledBorder.setTitleColor(Color.WHITE);
    }

    /**
     * Sets the south panel to day mode.
     * Changes the background color and the text color of the plant information label.
     * @author Cyrus Shaerpour
     */
    public void daySouth() {
        setBackground(new Color(225, 240, 218));
        plantInformation.setForeground(Color.BLACK);
        titledBorder.setTitleColor(Color.BLACK);
    }
}
