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


}

