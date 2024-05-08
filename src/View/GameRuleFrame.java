package View;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

/**
 * GameRuleFrame represents a window displaying the game rules for the game.
 * It contains a JLabel to display the game rules and an image at the bottom as part of the menu.
 * @author Anna Granberg
 */

public class GameRuleFrame extends JFrame {

    private JLabel gameRulesLabel; // JLabel för att visa spelreglerna

    // Storlek för frame size
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 350;
    private static final Font CUSTOM_FONT = new Font("Bebas Neue", Font.BOLD, 12); // Anpassat typsnitt för hela spelet

    // Constructor för GameRuleFrame
    public GameRuleFrame() {
        gameRulesLabel = new JLabel();


        String welcome = "<html> Welcome to Virtual Plant Widgets!\n" +
                "You've just gained access to your very own virtual garden where you can plant and nurture your own plants. <br/>"
                + "<br/>";
        // Text för spelreglerna
        String rules = "<html><h2 style='margin-bottom: 5px;'>How to Play:</h2>" +
                "<ul>" +
                "<li>Add plants to your garden through the 'Garden' menu.</li>" +
                "<li>Enter your garden to water your plants regularly.</li>" +
                "<li>Observe your plants as they progress from seeds to maturity.</li>" +
                "<li>Monitor your plant's health using the hearts on the right side. Missing watering reduces a life.</li>" +
                "<li>Track water progress displayed above the hearts.</li>" +
                "<li>To remove a plant, simply delete it from your garden.</li>" +
                "</ul>" +
                "<p>Have fun and enjoy your virtual garden!</p>";


        gameRulesLabel.setText(welcome + rules); // lägger till spelreglerna på JLabel
        gameRulesLabel.setFont(CUSTOM_FONT); // anpassat typsnitt på JLabel
        gameRulesLabel.setSize(new Dimension(450, 200)); // Ställer storlek på JLabel
        gameRulesLabel.setBackground(new Color(153, 188, 133)); // Ställer bakgrundsfärg för JLabel

        // Skapar olika borders för JLabel
        Border innerBorder = BorderFactory.createLineBorder(Color.BLACK); // Skapar en inre border
        Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10); // Skapar en tom border runt JLabel
        Border compoundBorder = BorderFactory.createCompoundBorder(innerBorder, emptyBorder); // Skapar en sammansatt border

        gameRulesLabel.setBorder(compoundBorder); // Tillämpar den sammansatta border på JLabel

        add(gameRulesLabel, BorderLayout.CENTER); // Lägger till JLabel för spelreglerna i mitten av fönstret

        setTitle("Game Rules"); // Sätter titeln på fönstret
        getContentPane().setBackground(new Color(225, 240, 218)); // Ställer bakgrundsfärg för fönstret
        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT)); // Sätter storlek på fönstret
        setResizable(false); // Gör fönstret icke-omstoringsbart
        setVisible(true); // Gör fönstret synligt när det skapas
        centerAndToFront();

    }
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