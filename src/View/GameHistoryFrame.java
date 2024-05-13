package View;

import Controller.GameHistoryReader;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a JFrame that displays the game history including information about old plants.
 */
public class GameHistoryFrame extends JFrame {
    private static final int IMAGE_WIDTH = 75;
    private static final int IMAGE_HEIGHT = 100;
    private static final int BORDER_PADDING = 20;

    private Font customFont = new Font("Bebas Neue", Font.BOLD, 12);
    private JPanel panel;

    /**
     * Constructs a new GameHistoryFrame.
     * @author Anna Granberg
     */
    public GameHistoryFrame() {
        setTitle("Game History");
        setPreferredSize(new Dimension(200,600));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window on exit
        setResizable(false);
        setBackground(new Color(225, 240, 218));
        setFont(customFont);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(225, 240, 218));
        panel.setBorder(BorderFactory.createEmptyBorder(BORDER_PADDING, BORDER_PADDING, BORDER_PADDING, BORDER_PADDING));

        ArrayList<String> gameHistory = getGameHistory(); // Populate game history

        for (String entry : gameHistory) {
            String[] parts = entry.split("\\|");

            // panel
            JPanel entryPanel = new JPanel();
            entryPanel.setLayout(new BoxLayout(entryPanel, BoxLayout.Y_AXIS));
            entryPanel.setBackground(new Color(225, 240, 218));

            if (parts.length >= 4) {
                String imagePath = parts[3].trim().split(":")[1].trim();

                try {
                    ImageIcon originalIcon = new ImageIcon(ImageIO.read(new File(imagePath)));
                    ImageIcon scaledIcon = scaleImageIcon(originalIcon, IMAGE_WIDTH, IMAGE_HEIGHT); // skala bild
                    JLabel imageLabel = new JLabel(scaledIcon);
                    entryPanel.add(imageLabel);
                } catch (IOException e) {
                    System.err.println("Error loading image: " + e.getMessage());
                }
            } else {
                System.err.println("Invalid entry format: " + entry);
            }

            for (int i = 0; i < parts.length-1; i++) {
                JLabel label = new JLabel(parts[i].trim());
                label.setFont(customFont);
                label.setBackground(new Color(225, 240, 218));
                entryPanel.add(label);
            }

            panel.add(entryPanel);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane);

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(border, "Old plants", TitledBorder.CENTER, TitledBorder.TOP, customFont, Color.BLACK);
        panel.setBorder(titledBorder);

        pack(); // Adjust frame size to fit contents
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    /**
     * Retrieves the game history data from GameHistoryReader class.
     *
     * @return an ArrayList containing the game history data
     * @author Anna Granberg
     */
    public static ArrayList<String> getGameHistory() {
        return GameHistoryReader.getGameHistory();
    }

    /**
     * Scales the given ImageIcon to the specified width and height.
     *
     * @param imageIcon the ImageIcon to scale
     * @param width     the desired width of the scaled image
     * @param height    the desired height of the scaled image
     * @return a new ImageIcon containing the scaled image
     * @author Cyrus
     */
    private ImageIcon scaleImageIcon(ImageIcon imageIcon, int width, int height) {
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}
