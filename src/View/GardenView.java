package View;
import Controller.Controller;
import Model.Plant;
import Model.PlantArt;
import Model.Sunflower;
import Model.TomatoPlant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * The GardenView class represents a dialog window displaying the user's garden.
 * It provides a graphical interface for the user to interact with the plants in the garden.
 * The dialog includes a panel showing buttons with images of the plants in the garden.
 * Each button allows the user to select a plant for further interaction.
 * @author annagranberg
 */

public class GardenView extends JDialog {

    private Controller controller;
    private int width = 300; // The width of the dialog
    private int height = 450; // The height of the dialog

    /**
     * Constructs a new GardenView dialog.
     *
     * @param parentFrame   The parent frame for this dialog.
     * @param centerPanel   The center panel associated with the parent frame.
     * @param controller    The controller handling interactions with the model.
     */
    public GardenView(JFrame parentFrame, CenterPanel centerPanel, Controller controller) {
        super(parentFrame, "Your garden!", true); // modal dialog
        setSize(width, height);
        setResizable(false);
        this.controller = controller;

        int xCoordinate = parentFrame.getX() - width;
        int yCoordinate = parentFrame.getY() + centerPanel.getY() - 12;

        setLocation(xCoordinate, yCoordinate);

        GardenPanel gardenPanel = new GardenPanel();
        add(gardenPanel);

        setVisible(true);
    }

    /**
     * The GardenPanel class represents the panel displaying the garden in the dialog.
     */
    private class GardenPanel extends JPanel {
        private int width = 300; // The width of the JFrame
        private int height = 450; // The height of the JFrame
        private List<ImageIcon> plantImages;

        /**
         * Constructs a new GardenPanel.
         */
        public GardenPanel() {
            // Hämta växtbilderna från controller
            this.plantImages = controller.getPlantImages();

            if(plantImages == null){
                System.err.println("Plant icons are null");
            }

            setTitle("Your garden!");
            setSize(width, height);
            setResizable(false);

            setBackground(new Color(225, 240, 218));
            setLayout(new GridLayout(4, 3));

            // Knappar med bild på växter man har
            for (ImageIcon icon : plantImages) {
                JButton plantButton = new JButton(icon);
                plantButton.setFocusPainted(false);
                plantButton.setBorderPainted(false);
                plantButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // @todo: implementera kod för att kunna byta växt i centerPanel
                        // controller.switchPlant();
                    }
                });
                add(plantButton);
            }
        }
    }
}