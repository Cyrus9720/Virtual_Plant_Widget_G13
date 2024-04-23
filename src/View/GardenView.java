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

            // Add plant buttons with images from the plantImages list
            for (ImageIcon icon : plantImages) {
                JButton plantButton = new JButton(icon);
                plantButton.setFocusPainted(false);
                plantButton.setBorderPainted(false);
                plantButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Handle button click action here
                        // You can use the ImageIcon associated with the button
                        // to determine which plant button was clicked
                    }
                });
                add(plantButton);
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
