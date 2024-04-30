package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddNewPlantFrame extends JFrame {
    private Controller controller;
    public AddNewPlantFrame(Controller controller) {
        setSize(new Dimension(300, 150));
        setVisible(true);

        NewPlantPanel newPlantPanel = new NewPlantPanel(controller);
        add(newPlantPanel);
    }

    private class NewPlantPanel extends JPanel {
        private NewPlantPanel(Controller controller) {
            setBackground(new Color(225, 240, 218));
            setLayout(new FlowLayout());

            JLabel infoLabel = new JLabel("Choose a plant to add to the garden!");
            Font customFont = new Font("Bebas Neue", Font.BOLD, 12);
            infoLabel.setFont(customFont);

            // Create buttons for sunflower, rose, and tomato plant
            JButton sunflowerButton = new JButton("Sunflower");
            sunflowerButton.setFont(customFont);
            sunflowerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // todo: Handle the click event for sunflowerButton
                }
            });

            JButton roseButton = new JButton("Rose");
            roseButton.setFont(customFont);
            roseButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.addNewRose();
                }
            });

            JButton tomatoButton = new JButton("Tomato Plant");
            tomatoButton.setFont(customFont);
            tomatoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // todo: Handle the click event for tomatoButton
                }
            });

            // Add buttons to the panel
            add(infoLabel);
            add(sunflowerButton);
            add(roseButton);
            add(tomatoButton);
        }
    }
}
