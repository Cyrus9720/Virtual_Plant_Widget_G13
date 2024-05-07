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

        // Text för spelreglerna
        String rules = "<html>Game rules:<br/>" + // String för spelregler
                "<br/" +
                "coming soon"; // todo: lägga till spelregler som stämmer överrens med spelet, kanske med beskrivande bild?

        gameRulesLabel.setText(rules); // lägger till spelreglerna på JLabel
        gameRulesLabel.setFont(CUSTOM_FONT); // anpassat typsnitt på JLabel
        gameRulesLabel.setSize(new Dimension(450, 200)); // Ställer storlek på JLabel

        // Skapar en ImageIcon för menyraden
        ImageIcon menuBar = new ImageIcon("src/Images/menuBar.png");
        Image menuBarImage = menuBar.getImage();
        Image scaledMenuBar = menuBarImage.getScaledInstance(400, 25, Image.SCALE_SMOOTH);
        ImageIcon finalMenuBar = new ImageIcon(scaledMenuBar);

        JLabel imageLabel = new JLabel(finalMenuBar); // Skapar en JLabel för menyraden
        add(imageLabel, BorderLayout.SOUTH); // Lägger till menyraden längst ner i fönstret

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
    }
}