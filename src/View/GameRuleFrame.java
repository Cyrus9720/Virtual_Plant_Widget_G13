package View;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 *
 * @author annagranberg
 */

public class GameRuleFrame extends JFrame {

    private JLabel gameRulesLabel;

    // Konstanter för frame storlek
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 250;

    private static final Font CUSTOM_FONT = new Font("Bebas Neue", Font.BOLD, 12); // Font för hela spelet

    public GameRuleFrame() {
        gameRulesLabel = new JLabel();

        String rules = "<html>Game rules:<br/>" + // String för spelregler
                "1. Plant different types of plants and take care of them.<br/>" +
                "  <br/>" +
                "2. Water your plants regularly to keep them healthy.<br/>" +
                "  <br/>" +
                "3. Beware of deadlines for watering, or your plants may die.<br/>" +
                "  <br/>" +
                "4. Harvest seeds from mature plants to grow new ones.<br/>" +
                "  <br/>" +
                "5. Enjoy watching your garden flourish!</html>";

        gameRulesLabel.setText(rules);
        gameRulesLabel.setFont(CUSTOM_FONT);
        gameRulesLabel.setSize(new Dimension(450, 200));

        gameRulesLabel.setBackground(new Color(153, 188, 133)); // bakgrundsfärg

        Border innerBorder = BorderFactory.createLineBorder(Color.BLACK); // skapar en inre border
        Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border compoundBorder = BorderFactory.createCompoundBorder(innerBorder, emptyBorder);

        gameRulesLabel.setBorder(compoundBorder);

        add(gameRulesLabel, BorderLayout.CENTER);

        setTitle("Game Rules");
        getContentPane().setBackground(new Color(225, 240, 218)); // bakgrundsfärg
        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setResizable(false);
        setVisible(true);
    }
}
