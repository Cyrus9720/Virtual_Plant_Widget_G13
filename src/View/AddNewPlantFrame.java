package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddNewPlantFrame extends JFrame {
    /**
     * Constructs an AddNewPlantFrame with the specified controller.
     *
     * @param controller The controller object to be associated with the frame.
     */
    public AddNewPlantFrame(Controller controller) {
        setSize(new Dimension(300, 300)); // Ställer in storleken på fönstret
        setVisible(true); // Gör fönstret synligt när det skapas
        setLocation(600, 400);
        NewPlantPanel newPlantPanel = new NewPlantPanel(controller); // Skapar en ny panel för att lägga till växter
        add(newPlantPanel); // Lägger till den nya panelen i fönstret
    }

    /**
     * Panel for adding new plants to the garden.
     */
    private class NewPlantPanel extends JPanel {
        /**
         * Constructs a NewPlantPanel with controller.
         *
         * @param controller The controller object associated with the panel.
         */

        private int height = 120;
        private int width = 50;

        private NewPlantPanel(Controller controller) {
            if (controller.night) {
                setBackground(new Color(47, 49, 73)); // Set background color for the panel
            } else {
                setBackground(new Color(225, 240, 218)); // Set background color for the panel
            }
            setLayout(new FlowLayout()); // Set layout for the panel

            JLabel infoLabel = new JLabel("Choose a plant to add to the garden!"); // Create a label to display instructions
            Font customFont = new Font("Bebas Neue", Font.BOLD, 12); // Custom font
            if (controller.night) {
                infoLabel.setForeground(Color.WHITE); // Set text color for JLabel
            } else {
                infoLabel.setForeground(Color.BLACK); // Set text color for JLabel
            }
            infoLabel.setFont(customFont); // Apply font to label

            // Button for sunflower
            JButton sunflowerButton = new JButton("Sunflower");
            sunflowerButton.setFont(customFont); // Apply custom font to button
            sunflowerButton.setPreferredSize(new Dimension(height, width)); // Set button size
            sunflowerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.addNewSunflower(); // Controller method to add a new sunflower
                    AddNewPlantFrame.this.dispose(); // Close the current window when the button is pressed
                }
            });

            // Button for rose
            JButton roseButton = new JButton("Rose");
            roseButton.setPreferredSize(new Dimension(height, width)); // Set button size
            roseButton.setFont(customFont); // Apply custom font to button
            roseButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.addNewRose(); // Controller method to add a new rose
                    AddNewPlantFrame.this.dispose(); // Close the current window when the button is pressed
                }
            });

            // Button for tomato plant
            JButton tomatoButton = new JButton("Tomato Plant");
            tomatoButton.setPreferredSize(new Dimension(height, width)); // Set button size
            tomatoButton.setFont(customFont); // Apply custom font to button
            tomatoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.addNewTomatoPlant(); // Controller method to add a new tomato plant
                    AddNewPlantFrame.this.dispose(); // Close the current window when the button is pressed
                }
            });

            // Button for blackberry
            JButton blackberryButton = new JButton("Blackberry");
            blackberryButton.setFont(customFont); // Apply custom font to button
            blackberryButton.setPreferredSize(new Dimension(height, width)); // Set button size
            blackberryButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.addNewBlackberry(); // Controller method to add a new blackberry
                    AddNewPlantFrame.this.dispose(); // Close the current window when the button is pressed
                }
            });

            // Button for mini tree
            JButton miniTreeButton = new JButton("Mini Tree");
            miniTreeButton.setFont(customFont); // Apply custom font to button
            miniTreeButton.setPreferredSize(new Dimension(height, width)); // Set button size
            miniTreeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.addNewMiniTree(); // Controller method to add a new mini tree
                    AddNewPlantFrame.this.dispose(); // Close the current window when the button is pressed
                }
            });

            // Button for cactus
            JButton cactusButton = new JButton("Cactus");
            cactusButton.setFont(customFont); // Apply custom font to button
            cactusButton.setPreferredSize(new Dimension(height, width)); // Set button size
            cactusButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.addNewCactus(); // Controller method to add a new cactus
                    AddNewPlantFrame.this.dispose(); // Close the current window when the button is pressed
                }
            });

            // Add the buttons to the panel
            add(infoLabel);
            add(sunflowerButton);
            add(roseButton);
            add(tomatoButton);
            add(blackberryButton);
            add(miniTreeButton);
            add(cactusButton);
        }

    }
}
