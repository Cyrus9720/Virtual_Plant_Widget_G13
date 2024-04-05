package View;
import Controller.Controller;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The EastPanel class represents the panel containing plant care controls on the east side of the user interface.
 * This panel includes buttons for performing various plant care actions, such as watering.
 *
 * This class extends {@link javax.swing.JPanel}.
 *
 * @author annagranberg
 */
public class EastPanel extends JPanel
{
    private Controller controller; // Reference to controller
    private int width, height; // Dimensions of the panel
    private ButtonGroup buttonGroup; // Button group to manage buttons
    private JButton Water; // Button for watering action

    /**
     * Constructs a new EastPanel with the specified controller, width, and height.
     *
     * @param controller The controller object responsible for handling user actions.
     * @param width The width of the panel.
     * @param height The height of the panel.
     *
     * @author annagranberg
     */
    public EastPanel(Controller controller, int width, int height)
    {
        this.controller = controller;
        this.width = width;
        this.height = height;
        buttonGroup = new ButtonGroup();

        setBackground(new Color(225, 240, 218));

        TitledBorder titledBorder = BorderFactory.createTitledBorder("Plant care");
        Font myFont = new Font("Bebas Neue", Font.BOLD, 12);
        titledBorder.setTitleFont(myFont);
        setBorder(titledBorder);

        GridLayout layout = new GridLayout(2, 1, 2, 2);  //fungerar inte!!
        JPanel pnlButtons = new JPanel();

        ImageIcon waterButton = new ImageIcon("src/Images/Watercan.png");
        Image originalWaterButtonImage = waterButton.getImage();
        Image scaledWaterButtonImage = originalWaterButtonImage.getScaledInstance(60,50, Image.SCALE_SMOOTH);
        ImageIcon scaledWaterIcon = new ImageIcon(scaledWaterButtonImage);
        Water = new JButton(scaledWaterIcon);
        Water.setBorderPainted(false);
        Water.setContentAreaFilled(false);
        Water.setBackground(new Color(225, 240, 218));
        pnlButtons.add(Water);

        Border border = this.getBorder();
        Border margin = BorderFactory.createEmptyBorder(6, 6, 6, 6);
        setBorder(new CompoundBorder(border, margin));

        add(pnlButtons);

        // Adding ActionListener to the water button
        Water.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton clickedButton = (JButton) e.getSource();
                controller.buttonPressed(ButtonType.Water);
            }
        });

    }
}
