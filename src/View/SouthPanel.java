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
    private JLabel progressbarLabel;
    JLabel threeHeartsLabel;

    /**
     * Constructs a new SouthPanel with the specified controller, width, and height.
     *
     * @param controller The controller object responsible for handling user actions.
     * @param width The width of the panel.
     * @param height The height of the panel.
     */
    public SouthPanel(Controller controller, int width, int height) {
        this.controller = controller;

        setSize(width, height);

        TitledBorder titledBorder = BorderFactory.createTitledBorder("Plant information");
        Font myFont = new Font("Bebas Neue", Font.BOLD, 12);
        titledBorder.setTitleFont(myFont);
        setBorder(titledBorder);

        setBackground(new Color(225, 240, 218));
        setLayout(new BorderLayout());

        ImageIcon threeHearts = new ImageIcon("src/Images/treHjärtan.png");
        Image originalThreeHearts = threeHearts.getImage();
        Image scaledHeartsLivesImage = originalThreeHearts.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledThreeHearts = new ImageIcon(scaledHeartsLivesImage);
        threeHeartsLabel = new JLabel(updateAmountOfLife());
        add(threeHeartsLabel, BorderLayout.WEST);

        JLabel plantInfo = new JLabel("<html>More information about your plant, coming soon...</html>");
        plantInfo.setPreferredSize(new Dimension(100, 60));
        plantInfo.setFont(new Font("Bebas Neue", Font.BOLD, 10));
        add(plantInfo, BorderLayout.EAST);
    }

    public ImageIcon updateAmountOfLife() {
        int nbrOfLives = controller.getNbrOfLives();
        ImageIcon heartsIcon = null;

        switch (nbrOfLives) {
            case 0:
                // If there are no lives left, display an empty heart icon
                heartsIcon = new ImageIcon("src/Images/tommaHjärtan.png");
                break;
            case 1:
                // If there is one life left, display one heart
                heartsIcon = new ImageIcon("src/Images/ettHjärta.png");
                break;
            case 2:
                // If there are two lives left, display two hearts
                heartsIcon = new ImageIcon("src/Images/tvåHjärtan.png");
                break;
            case 3:
                // If there are three lives left, display three hearts
                heartsIcon = new ImageIcon("src/Images/treHjärtan.png");
                break;
            default:
                // Default case, do nothing or provide a default icon
                break;
        }

        if (heartsIcon != null) {
            // Update the icon for the hearts label
            Image originalHearts = heartsIcon.getImage();
            Image scaledHearts = originalHearts.getScaledInstance(100, 30, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledHearts);
        } else {
            return null;
        }
    }


}

