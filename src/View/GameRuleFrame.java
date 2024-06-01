package View;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * GameRuleFrame represents a window displaying the game rules for the game.
 * It contains a JLabel to display the game rules and an image at the bottom as part of the menu.
 * @author Anna Granberg
 */

public class GameRuleFrame extends JFrame {

    private JLabel gameRulesLabel; // JLabel för att visa spelreglerna

    // size of the frame
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 350;
    private static final Font customFont = new Font("Bebas Neue", Font.BOLD, 12); // Custom font

    /**
     * Constructs a GameRuleFrame to display the game rules.
     */
    public GameRuleFrame() {
        gameRulesLabel = new JLabel();
        String welcome = "<html> Welcome to Virtual Plant Widgets!\n" +
                "You've just gained access to your very own virtual garden where you can plant and nurture your own plants. <br/>"
                + "<br/>";
        // Text for game rules
        String rules = "<html><h2 style='margin-bottom: 5px;'>How to Play:</h2>" +
                "<ul>" +
                "<li>Add plants to your garden through the 'Garden' on your left.</li>" +
                "<li>Observe your plants as they progress from pot to a big plant.</li>" +
                "<li>Monitor your plant's health using the hearts on the right side. " +
                "<li>Missing watering reduces a life.</li>" +
                "<li>Track water progress displayed above the hearts.</li>" +
                "<li>To delete a plant, simply press remove plant in the menu.</li>" +
                "<li>You can see your removed plants in Game History!  </li>" +
                "</ul>" +
                "<p>Have fun and enjoy your virtual garden!</p>";


        gameRulesLabel.setText(welcome + rules); // add game rules to the label
        gameRulesLabel.setFont(customFont); // custom font
        gameRulesLabel.setSize(new Dimension(450, 200)); // sets size for the label
        gameRulesLabel.setBackground(new Color(153, 188, 133));

        // Create different borders for the label
        Border innerBorder = BorderFactory.createLineBorder(Color.BLACK); // Creates an inner border
        Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10); // creates an empty border around label
        Border compoundBorder = BorderFactory.createCompoundBorder(innerBorder, emptyBorder); // creates a compound border.

        gameRulesLabel.setBorder(compoundBorder); // Sets the border

        add(gameRulesLabel, BorderLayout.CENTER); // add label to center of frame

        setTitle("Game Rules"); // title
        getContentPane().setBackground(new Color(225, 240, 218)); // background
        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT)); // size
        setResizable(false); // non-resizable
        setVisible(true); // makes the frame visible.
        centerAndToFront();

    }

    /**
     * Centers the frame on the screen and brings it to the front.
     */
    private void centerAndToFront() {
        // Get the screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // Calculate the center position of the frame
        int frameX = (screenWidth - getWidth()) / 2;
        int frameY = (screenHeight - getHeight()) / 2;

        // Set the frame's position
        setLocation(frameX, frameY);

        // Bring the frame to the front
        toFront();
    }
}