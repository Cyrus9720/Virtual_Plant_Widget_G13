package View;

import Controller.Controller;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * GameRuleFrame represents a window displaying the game rules for the game.
 * It contains a JLabel to display the game rules and an image at the bottom as part of the menu.
 */
public class GameRuleFrame extends JFrame {
    private JLabel gameRulesLabel; // JLabel för att visa spelreglerna
    private Controller controller;

    // Size for the frame
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 350;
    private static final Font customFont = new Font("Bebas Neue", Font.BOLD, 12); // Anpassat typsnitt för hela spelet

    /**
     * Constructs a GameRuleFrame to display the game rules.
     * @auhtor Anna Granberg
     */
    public GameRuleFrame(Controller controller) {
        this.controller = controller;
        gameRulesLabel = new JLabel();
        String welcome = "<html> Welcome to Virtual Plant Widgets!\n" +
                "You've just gained access to your very own virtual garden where you can plant and nurture your own plants. <br/>"
                + "<br/>";
        // Game Rules String
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

        gameRulesLabel.setText(welcome + rules); // Adds the game rules to the JLabel
        gameRulesLabel.setFont(customFont); // Sets custom font for the JLabel
        gameRulesLabel.setSize(new Dimension(450, 200)); // Sets size for the JLabel
        if (controller.night) {
            gameRulesLabel.setForeground(new Color(225, 240, 218)); // Sets text color for JLabel
        } else {
            gameRulesLabel.setForeground(new Color(47, 49, 73)); // Sets text color for JLabel
        }
        gameRulesLabel.setBackground(new Color(153, 188, 133)); // Sets background color for JLabel

        // Creates different borders for JLabel
        Border innerBorder = BorderFactory.createLineBorder(Color.BLACK); // Creates an inner border
        Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10); // Creates an empty border around JLabel
        Border compoundBorder = BorderFactory.createCompoundBorder(innerBorder, emptyBorder); // Creates a compound border

        gameRulesLabel.setBorder(compoundBorder); // Applies the compound border to JLabel

        add(gameRulesLabel, BorderLayout.CENTER); // Adds JLabel for game rules in the center of the window

        setTitle("Game Rules"); // Sets the title of the window
        if (controller.night) {
            getContentPane().setBackground(new Color(47, 49, 73)); // Sets background color for the window
        } else {
            getContentPane().setBackground(new Color(225, 240, 218)); // Sets background color for the window
        }
        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT)); // Sets size of the window
        setResizable(false); // Makes the window non-resizable
        setVisible(true); // Makes the window visible when created
        centerAndToFront();
    }

    /**
     * Centers the frame on the screen and brings it to the front.
     * @author Anna Granberg
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