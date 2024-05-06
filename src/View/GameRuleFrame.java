package View;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

/**
 *
 * @author annagranberg
 */

public class GameRuleFrame extends JFrame {

    private JLabel gameRulesLabel;

    // Konstanter för frame storlek
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 350;
    private static final Font CUSTOM_FONT = new Font("Bebas Neue", Font.BOLD, 12); // Font för hela spelet

    public GameRuleFrame() {
        gameRulesLabel = new JLabel();

        String rules = "<html>Game rules:<br/>" + // String för spelregler
                "<br/" +
                "coming soon"; // todo: lägga till spelregler som stämmer överrens med spelet, kanske med beskrivande bild?

        gameRulesLabel.setText(rules);
        gameRulesLabel.setFont(CUSTOM_FONT);
        gameRulesLabel.setSize(new Dimension(450, 200));

        ImageIcon menuBar = new ImageIcon("src/Images/menuBar.png");
        Image menuBarImage = menuBar.getImage();
        Image scaledMenuBar = menuBarImage.getScaledInstance(400, 25, Image.SCALE_SMOOTH);
        ImageIcon finalMenuBar = new ImageIcon(scaledMenuBar);

        JLabel imageLabel = new JLabel(finalMenuBar);
        add(imageLabel, BorderLayout.SOUTH);


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
