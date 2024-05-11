package View;

import Controller.GameHistoryReader;
import Controller.GameHistoryWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameHistoryFrame extends JFrame {

    private int height = 300, width = 750;
    private Font customFont = new Font("Bebas Neue", Font.BOLD, 12); // Anpassat typsnitt
    private JPanel panel;
    private JLabel gameHistoryLabel;
    private ArrayList<String> gameHistory = new ArrayList<>();

    public GameHistoryFrame() {
        setTitle("Game History");
        setSize(new Dimension(width, height));
        setResizable(false);
        setBackground(new Color(225, 240, 218));
        setFont(customFont);

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(width, height));
        panel.setBackground(new Color(225, 240, 218));
        panel.setFont(customFont);

        gameHistory = getGameHistory(); // Populate game history

        for (String entry : gameHistory) {
            // Split each game history entry based on the delimiter "|"
            String[] parts = entry.split("\\|");

            // Create a panel for each entry
            JPanel entryPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
            entryPanel.setBackground(new Color(225, 240, 218));

            // Load and display the image
            String imagePath = parts[3].trim().split(";")[1].trim(); // Assuming image path is the sixth part of the entry
            try {
                ImageIcon originalIcon = new ImageIcon(ImageIO.read(new File(imagePath)));
                ImageIcon scaledIcon = scaleImageIcon(originalIcon, 50, 75); // Scale to desired width and height
                JLabel imageLabel = new JLabel(scaledIcon);
                entryPanel.add(imageLabel);
            } catch (IOException e) {
                System.err.println("Error loading image: " + e.getMessage());
            }

            // Add other information
            for (int i = 0; i < parts.length-1; i++) {
                JLabel label = new JLabel(parts[i].trim()); // Assuming each part contains "key; value"
                label.setFont(customFont);
                label.setBackground(new Color(225, 240, 218));
                entryPanel.add(label);
            }

            panel.add(entryPanel);
        }

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(border, "Game History", TitledBorder.CENTER, TitledBorder.TOP, customFont, Color.BLACK);
        panel.setBorder(titledBorder);

        add(panel);
        setVisible(true);
    }

    public static ArrayList<String> getGameHistory() {
        return GameHistoryReader.getGameHistory();
    }

    private ImageIcon scaleImageIcon(ImageIcon imageIcon, int width, int height) {
        Image image = imageIcon.getImage(); // ImageIcon till Image
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH); // Skalar bilden
        return new ImageIcon(scaledImage); // Omvandlar bilden tillbaka till ImageIcon
    }
}
