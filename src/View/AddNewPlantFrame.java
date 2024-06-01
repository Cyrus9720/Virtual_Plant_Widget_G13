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
     * @author Anna Granberg
     */
    public AddNewPlantFrame(Controller controller) {
        setSize(new Dimension(300, 300)); // Sets the size of the frame
        setVisible(true); // Makes the frame visible

        NewPlantPanel newPlantPanel = new NewPlantPanel(controller); // Create a new panel to store plants.
        add(newPlantPanel); // Adds the panel to the frame.
    }

    /**
     * Panel for adding new plants to the garden.
     * @author Anna Granberg
     */
    private class NewPlantPanel extends JPanel {
        /**
         * Constructs a NewPlantPanel with controller.
         *
         * @param controller The controller object associated with the panel.
         */

        private int height = 120;
        private int widht = 50;
        private NewPlantPanel(Controller controller) {
            setBackground(new Color(225, 240, 218)); // background color
            setLayout(new FlowLayout()); // set layout for the panel

            JLabel infoLabel = new JLabel("Choose a plant to add to the garden!"); // Create a new panel to show instructions
            Font customFont = new Font("Bebas Neue", Font.BOLD, 12); // custom font
            infoLabel.setFont(customFont); // set customfont

            // Button for sunflower
            JButton sunflowerButton = new JButton("Sunflower");
            sunflowerButton.setFont(customFont); // custom font
            sunflowerButton.setPreferredSize(new Dimension(height, widht));
            sunflowerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.addNewSunflower();// controller-method to add a new plant
                    AddNewPlantFrame.this.dispose(); // Closes the window when the button is pressed.
                }
            });
            // Button for rose
            JButton roseButton = new JButton("Rose");
            roseButton.setPreferredSize(new Dimension(height, widht));
            roseButton.setFont(customFont); // custom font
            roseButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.addNewRose(); // controller-method to add a new plant
                    AddNewPlantFrame.this.dispose(); // Closes the window when the button is pressed.
                }
            });

            // Button for tomato plant
            JButton tomatoButton = new JButton("Tomato Plant");
            tomatoButton.setPreferredSize(new Dimension(height, widht));
            tomatoButton.setFont(customFont); // custom font for the text on the button
            tomatoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.addNewTomatoPlant(); // controller-method to add a new plant
                    AddNewPlantFrame.this.dispose(); // Closes the window when the button is pressed.
                }
            });

            // button for blackberry plant
            JButton blackberryButton = new JButton("Blackberry");
            blackberryButton.setFont(customFont); // custom font
            blackberryButton.setPreferredSize(new Dimension(height, widht));
            blackberryButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.addNewBlackberry();  // controller-method to add a new plant
                    AddNewPlantFrame.this.dispose(); // Closes the window when the button is pressed.

                }
            });

            // button for mini tree
            JButton miniTreeButton = new JButton("Mini Tree");
            miniTreeButton.setFont(customFont); // custom font
            miniTreeButton.setPreferredSize(new Dimension(height, widht));
            miniTreeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.addNewMiniTree(); // controller-method to add a new plant
                    AddNewPlantFrame.this.dispose(); // Closes the window when the button is pressed.
                }
            });

            // button for cactus
            JButton cactusButton = new JButton("Cactus");
            cactusButton.setFont(customFont); // custom font
            cactusButton.setPreferredSize(new Dimension(height, widht));
            cactusButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.addNewCactus(); // controller-method to add a new plant
                    AddNewPlantFrame.this.dispose(); // Closes the window when the button is pressed.
                }
            });


            // Add the buttons to the panel.
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
