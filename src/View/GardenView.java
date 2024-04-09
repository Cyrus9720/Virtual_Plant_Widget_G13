package View;

import javax.swing.*;
import java.awt.*;

/**
 * The GardenView class represents the main view of a garden management application.
 * It extends JFrame and provides a graphical interface for managing a garden.
 * @author annagranberg
 */
public class GardenView extends JFrame {

    private int width = 300; // The width of the JFrame
    private int height = 450; // The height of the JFrame

    /**
     * Constructs a GardenView object.
     * Initializes the frame, sets its title, size, and components.
     */
    public GardenView() {
        setTitle("Your garden!"); // Set the title of the JFrame
        setSize(width, height); // Set the size of the JFrame
        setResizable(false); // Disable frame resizing

        // Create and add the garden panel to the center of the frame
        GardenPanel gardenPanel = new GardenPanel();
        add(gardenPanel, BorderLayout.CENTER);

        // Create and add the north panel to the top of the frame
        NorthPanel northPanel = new NorthPanel();
        add(northPanel, BorderLayout.NORTH);

        setVisible(true); // Make the frame visible
    }

    /**
     * Represents the panel where the garden is displayed.
     */
    private class GardenPanel extends JPanel {

        /**
         * Constructs a GardenPanel object.
         * Sets the background color and layout of the panel,
         * and adds plant buttons to it.
         */
        public GardenPanel() {
            setBackground(new Color(225, 240, 218)); // Set the background color

            setLayout(new GridLayout(4, 3)); // Set the layout to a 4x3 grid

            // Add plant buttons to the panel
            for (int i = 0; i < 12; i++) {
                JButton plantButton = new JButton(); // Create a new button
                add(plantButton); // Add the button to the panel
            }
        }
    }

    /**
     * Represents the panel at the top of the frame,
     * which provides a JLabel with description of the frame .
     */
    private class NorthPanel extends JPanel {

        /**
         * Constructs a NorthPanel object.
         * Sets the background color and adds a label with information about managing plants.
         */
        public NorthPanel() {
            setBackground(new Color(225, 240, 218)); // Set the background color

            JLabel plantInfo = new JLabel("<html> Choose which plant you want to manage! </html>");
            // Create a label with HTML content to allow line breaks
            plantInfo.setPreferredSize(new Dimension(100, 60)); // Set preferred size
            plantInfo.setFont(new Font("Bebas Neue", Font.BOLD, 12)); // Set font
            add(plantInfo); // Add the label to the panel
        }
    }
}
