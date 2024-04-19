package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GardenView extends JFrame {

    private int width = 300; // The width of the JFrame
    private int height = 450; // The height of the JFrame

    public GardenView() {
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

            // Array of image paths for the buttons
            String[] imagePaths = {
                    "src/Images/RoseArt3.JPG",
                    "src/Images/RoseArt2.JPG",
                    "src/Images/RoseArt3.JPG",
                    "src/Images/RoseArt3.JPG",
                    "src/Images/RoseArt3.JPG",
                    "src/Images/RoseArt3.JPG"

                    // Add more paths for additional buttons
            };

            // Add plant buttons with images
            for (String path : imagePaths) {
                ImageIcon icon = new ImageIcon(path);
                Image iconImage = icon.getImage();
                Image scaledIconImage = iconImage.getScaledInstance(50, 75, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledIconImage);
                JButton plantButton = new JButton(scaledIcon);
                plantButton.setFocusPainted(false);
                plantButton.setBorderPainted(false);
                plantButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Handle swapping of plants here
                        ImageIcon currentIcon = (ImageIcon) plantButton.getIcon();
                    }
                });
                add(plantButton, new GridLayout(3, 2));
            }
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
