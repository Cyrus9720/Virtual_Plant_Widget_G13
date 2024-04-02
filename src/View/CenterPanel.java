package View;
import Controller.Controller;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * The CenterPanel class represents the central panel of the user interface.
 * This panel typically displays the main content of the application, such as the plant image.
 *
 * This class extends {@link javax.swing.JPanel}.
 *
 * @author annagranberg
 */
public class CenterPanel extends JPanel
{
    Controller controller;
    private int width;
    private int height;

    /**
     * Constructs a new CenterPanel with the specified controller, width, and height.
     *
     * @param controller The controller object responsible for handling user actions.
     * @param width The width of the panel.
     * @param height The height of the panel.
     */
    public CenterPanel(Controller controller, int width, int height)
    {
        this.controller = controller;
        this.width = width;
        this.height = height;

        TitledBorder titledBorder = BorderFactory.createTitledBorder("Insert plant name here..");
        Font myFont = new Font("Bebas Neue", Font.BOLD, 12);
        titledBorder.setTitleFont(myFont);
        setBorder(titledBorder);

        setBackground(new Color(225, 240, 218));

        Border border = this.getBorder();
        Border margin = BorderFactory.createEmptyBorder(6, 6, 6, 6);
        setBorder(new CompoundBorder(border, margin));

        ImageIcon originalPlant = new ImageIcon("Images/plantIcon.png");
        Image originalPlantImage = originalPlant.getImage();
        Image scaledPlantImage = originalPlantImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledPlantImageIcon = new ImageIcon(scaledPlantImage);
        JLabel plantLabel = new JLabel(scaledPlantImageIcon);
        add(plantLabel, BorderLayout.CENTER);
    }
}
