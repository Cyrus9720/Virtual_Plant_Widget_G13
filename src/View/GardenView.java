package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GardenView extends JFrame {

    private int width = 300; // The width of the JFrame
    private int height = 450; // The height of the JFrame
    private List<ImageIcon> plantImages;

    public GardenView(List<ImageIcon> plantImages) {
        this.plantImages = plantImages;

        setTitle("Your garden!");
        setSize(width, height);
        setResizable(false);

        GardenPanel gardenPanel = new GardenPanel();
        add(gardenPanel, BorderLayout.CENTER);

        NorthPanel northPanel = new NorthPanel();
        add(northPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    private class GardenPanel extends JPanel {
        public GardenPanel() {
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

                    }
                });
                add(plantButton);
            }

            // en plus-knapp för att lägga till ny växt
            ImageIcon plusIcon = new ImageIcon("src/Images/plusIcon.png"); // Replace "plus_image.png" with the path to your plus image
            JButton addPlantButton = new JButton(plusIcon);
            addPlantButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Show dialog or popup for adding a new plant
                    newPlant();
                }
            });
            add(addPlantButton);
        }

        /**
         * Method to add a new plant to the garden
         * @author Anna Granberg
         */
        public void newPlant(){

        }
    }

    private class NorthPanel extends JPanel {
        public NorthPanel() {
            setBackground(new Color(225, 240, 218));

            JLabel plantInfo = new JLabel("<html> Choose which plant you want to manage! </html>");
            plantInfo.setPreferredSize(new Dimension(100, 60));
            plantInfo.setFont(new Font("Bebas Neue", Font.BOLD, 12));
            add(plantInfo);
        }
    }
}
