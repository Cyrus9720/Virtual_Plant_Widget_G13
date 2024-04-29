package View;
import Controller.Controller;
import javax.swing.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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
    private Clip wateringSoundClip;
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
        setPreferredSize(new Dimension(125, 300));

        TitledBorder titledBorder = BorderFactory.createTitledBorder("Plant care");
        Font myFont = new Font("Bebas Neue", Font.BOLD, 12);
        titledBorder.setTitleFont(myFont);
        setBorder(titledBorder);

        JPanel pnlButtons = new JPanel();
        pnlButtons.setBackground(new Color(225, 240, 218));

        ImageIcon waterButton = new ImageIcon("src/Images/Watercan.png");
        Image originalWaterButtonImage = waterButton.getImage();
        Image scaledWaterButtonImage = originalWaterButtonImage.getScaledInstance(60,50, Image.SCALE_SMOOTH);
        ImageIcon scaledWaterIcon = new ImageIcon(scaledWaterButtonImage);

        Water = new JButton(scaledWaterIcon);
        Water.setBorderPainted(true);
        Water.setContentAreaFilled(true);
        Water.setBackground(new Color(225, 240, 218));
        pnlButtons.add(Water, BorderLayout.NORTH);

        Border border = this.getBorder();
        Border margin = BorderFactory.createEmptyBorder(6, 6, 6, 6);
        setBorder(new CompoundBorder(border, margin));

        add(pnlButtons);
        progressbarLabel = new JLabel();
        ImageIcon emptyProgressBarIcon = new ImageIcon("src/Images/emptyProgressBar.png");
        Image originalImage = emptyProgressBarIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(100, 75, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        progressbarLabel.setIcon(scaledIcon);
        add(progressbarLabel, BorderLayout.SOUTH);

        // Adding ActionListener to the water button
        Water.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == Water){
                    controller.buttonPressed(ButtonType.Water);
                    progressbarLabel.setIcon(updateWaterProgress());
                    System.out.println("Water button clicked");

                    //Play the watering sound
                        //wateringSoundClip.start(); //to start playing the sound
                    }
                }

        });

    }

    public void refreshBar(){
        progressbarLabel.setIcon(updateWaterProgress());
    }

    /**
     * Updates the water progress indicator.
     * This method updates the progress bar to indicate the water level of the plant.
     * The water level depends on the plant's level and the number of times it has been watered.
     *
     * @return ImageIcon representing the water progress bar.
     * @author Anna Granberg
     */
    public ImageIcon updateWaterProgress() {
        int timesWatered = controller.getTimesWatered();
        int plantLevel = controller.getPlantLevel();

        String imagePath = "src/Images/emptyProgressBar.png"; // Default value

        switch (plantLevel) {
            case 0:
                if(timesWatered == 0){
                    imagePath = "src/Images/emptyProgressBar.png";
                }
                else if (timesWatered == 1) {
                    imagePath = "src/Images/fullProgressBar.png";
                }
                break;
            case 1:
                if(timesWatered == 0){
                    imagePath = "src/Images/emptyProgressBar.png";
                } else if (timesWatered == 1) {
                    imagePath = "src/Images/halfProgressBar.png";
                } else {
                    imagePath = "src/Images/fullProgressBar.png";
                }
                break;
            case 2:
                if(timesWatered == 0){
                    imagePath = "src/Images/emptyProgressBar.png";
                } else if (timesWatered == 1) {
                    imagePath = "src/Images/thirdProgressBar.png";
                } else if (timesWatered == 2){
                    imagePath = "src/Images/twoThirdsProgressBar.png";
                } else if (timesWatered == 3){
                    imagePath = "src/Images/fullProgressBar.png";
                }
                break;
            case 3:
                if(timesWatered == 0){
                    imagePath = "src/Images/emptyProgressBar.png";
                } else if  (timesWatered >= 1) {
                    imagePath = "src/Images/fullProgressBar.png";
                }
                break;
        }

        ImageIcon progressBarIcon = new ImageIcon(imagePath);

        // Skala bilden för att passa panelen
        Image originalImage = progressBarIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(100, 75, Image.SCALE_SMOOTH);

        return new ImageIcon(scaledImage);
    }


}
