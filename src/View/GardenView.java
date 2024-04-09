package View;

import javax.swing.*;
import java.awt.*;

public class GardenView extends JFrame {

    private int width = 300;
    private int height = 450;
    public GardenView(){
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

            setLayout(new GridLayout(4, 3)); // Set the layout to one row with 10 columns

            for (int i = 0; i < 12; i++) {
                JButton plantButton = new JButton();
                add(plantButton); // Add the button directly to the panel
            }
        }
    }

    private class NorthPanel extends JPanel{
        public NorthPanel(){
            setBackground(new Color(225, 240, 218));

            JLabel plantInfo = new JLabel("<html> Change plant! </html>");
            plantInfo.setPreferredSize(new Dimension(100, 60));
            plantInfo.setFont(new Font("Bebas Neue", Font.BOLD, 10));
            add(plantInfo);
        }
    }
}
