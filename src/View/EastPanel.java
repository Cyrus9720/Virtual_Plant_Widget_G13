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
    private JButton Water; // Button for watering action

    private JLabel progressbarLabel;

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

        setBackground(new Color(225, 240, 218));
        setPreferredSize(new Dimension(width, 300));

        TitledBorder titledBorder = BorderFactory.createTitledBorder("Plant care");
        Font myFont = new Font("Bebas Neue", Font.BOLD, 12);
        titledBorder.setTitleFont(myFont);
        setBorder(titledBorder);

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
                if(e.getSource() == Water){
                    controller.buttonPressed(ButtonType.Water);
                    System.out.println("Water button clicked");
                }
            }
        });

        progressbarLabel = new JLabel(updateWaterProgress());
        add(progressbarLabel);

    }

    /**
     * Updates the water progress indicator.
     * This method updates the progress bar to indicate the water level of the plant.
     * The water level depends on the plant's level and the number of times it has been watered.
     *
     * @return ImageIcon representing the water progress bar.
     */
    public ImageIcon updateWaterProgress() {
        int timesWatered = controller.getTimesWatered();
        int plantLevel = controller.getPlantLevel();

        String imagePath;
        switch (plantLevel) {
            case 0:
                imagePath = (timesWatered >= 1) ? "src/Images/fullProgressBar.png" : "src/Images/emptyProgressBar.png";
                break;
            case 1:
                if (timesWatered == 2) {
                    imagePath = "src/Images/fullProgressBar.png";
                } else if (timesWatered == 1) {
                    imagePath = "src/Images/halfProgressBar.png";
                } else {
                    imagePath = "src/Images/emptyProgressBar.png";
                }
                break;
            case 2:
                if (timesWatered == 3) {
                    imagePath = "src/Images/fullProgressBar.png";
                } else if (timesWatered == 2) {
                    imagePath = "src/Images/twoThirdsProgressBar.PNG";
                } else if (timesWatered == 1) {
                    imagePath = "src/Images/thirdProgressBar.png";
                } else {
                    imagePath = "src/Images/emptyProgressBar.png";
                }
                break;
            default:
                // Handle other levels here if needed
                imagePath = "src/Images/emptyProgressBar.png";
                break;
        }

        ImageIcon progressBarIcon = new ImageIcon(imagePath);

        // Skala bilden för att passa panelen
        Image originalImage = progressBarIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(100, 75, Image.SCALE_SMOOTH);

        return new ImageIcon(scaledImage);
    }



}
