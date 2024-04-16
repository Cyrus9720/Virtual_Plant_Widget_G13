package View;

import javax.swing.*;
import java.awt.*;

public class GameRuleFrame extends JFrame {

    private JLabel gameRulesLabel;
    private JLabel imageLabel;
    private ImageIcon imageIcon;

    // Constants for frame size
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 250;

    // Custom font
    private static final Font CUSTOM_FONT = new Font("Bebas Neue", Font.BOLD, 12);
    public GameRuleFrame(){
        gameRulesLabel = new JLabel();

        String rules = "<html>Game rules:<br/>" +
                "1. Plant different types of plants and take care of them.<br/>" +
                "  <br/>"+
                "2. Water your plants regularly to keep them healthy.<br/>" +
                "  <br/>"+
                "3. Beware of deadlines for watering, or your plants may die.<br/>" +
                "  <br/>"+
                "4. Harvest seeds from mature plants to grow new ones.<br/>" +
                "  <br/>"+
                "5. Enjoy watching your garden flourish!</html>";

        gameRulesLabel.setText(rules);
        gameRulesLabel.setFont(CUSTOM_FONT);
        add(gameRulesLabel, BorderLayout.CENTER);
        
        setTitle("Game Rules");
        getContentPane().setBackground(new Color(225, 240, 218)); // Set background color
        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setResizable(false);
        setVisible(true);
    }

}
