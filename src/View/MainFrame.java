package View;

import Controller.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The MainFrame class represents the main frame of the application.
 * This frame contains the main user interface components, including the main panel
 * and menu bar, and is responsible for initializing and setting up the application window.
 *
 * This class extends {@link javax.swing.JFrame}.
 *
 * @author annagranberg
 */
public class MainFrame extends JFrame {
    private Controller controller; // reference to controller
    private int width = 675; // dimensions for frame size
    private int height = 700; // dimensions for frame size
    private Font customFont = new Font("Bebas Neue", Font.BOLD, 12);
    private MainPanel mainPanel; // reference to mainPanel

    /**
     * Constructs a new MainFrame with the specified controller.
     *
     * @param controller The controller object responsible for handling user actions.
     */
    public MainFrame(Controller controller) {
        this.controller = controller;
        setUpFrame();
    }

    /**
     * Sets up the main frame by configuring its properties, adding components, and making it visible.
     * @author annagranberg
     */
    public void setUpFrame() {
        setTitle("Virtual Plants");
        setPreferredSize(new Dimension(width, height));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        mainPanel = new MainPanel(controller, width, height);
        setContentPane(mainPanel);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(225, 240, 218));
        menuBar.setPreferredSize(new Dimension(200,25));
        menuBar.setFont(customFont);

        JMenuItem gameRules = new JMenuItem("Game Rules"); // button to display game rules
        gameRules.setFont(customFont);
        gameRules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRulesDialog();
            }
        });

        JMenuItem gameHistory = new JMenuItem("Game History"); // button to display game history
        gameHistory.setFont(customFont);
        gameHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // showGameHistory();
                GameHistoryFrame gameHistoryFrame = new GameHistoryFrame(controller);
            }
        });

        JMenuItem changeName = new JMenuItem("Change Plant Name");
        changeName.setFont(customFont);
        changeName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.changePlantName();
            }
        });

        JMenuItem removePlant = new JMenuItem("Remove plant");
        removePlant.setFont(customFont);
        removePlant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.removePlant(controller.getPlantName());
            }
        });

        JMenuItem newGame = new JMenuItem("Start Over"); // button to start the game over
        newGame.setFont(customFont);
        newGame.setBackground(new Color(225, 78, 78));
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setGameToNull();
            }
        });

        Border menuItemBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(4, 8, 4, 8));

        // Set the custom border for each JMenuItem
        gameRules.setBorder(menuItemBorder);
        gameHistory.setBorder(menuItemBorder);
        newGame.setBorder(menuItemBorder);
        removePlant.setBorder(menuItemBorder);
        changeName.setBorder(menuItemBorder);
        menuBar.add(gameRules);
        menuBar.add(changeName);
        menuBar.add(gameHistory);
        menuBar.add(removePlant);
        menuBar.add(newGame);
        setJMenuBar(menuBar);
        addWindowListener(new WindowAdapter() { // listener to save the game when exit button is pressed
            @Override
            public void windowClosing(WindowEvent e) {
                controller.saveGame();
            }
        });
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Displays the game rule frame showing the game rules.
     * @author annagranberg
     */
    public void showRulesDialog() {
        GameRuleFrame gameRuleFrame = new GameRuleFrame(controller);
    }

    /**
     * Retrieves the center panel.
     * @return The center panel.
     * @author Cyrus Shaerpour
     */
    public CenterPanel getCenterPanel() {
        return mainPanel.getCenterPanel();
    }

    /**
     * Retrieves the east panel.
     * @return The east panel.
     * @author Cyrus Shaerpour
     */
    public EastPanel getEastPanel() {
        return mainPanel.getEastPanel();
    }

    /**
     * Retrieves the south panel.
     * @return The south panel.
     * @author Cyrus Shaerpour
     */
    public SouthPanel getSouthPanel() {
        return mainPanel.getSouthPanel();
    }

    /**
     * Retrieves the main panel.
     * @return The main panel.
     * @author Cyrus Shaerpour
     */
    public MainPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * Retrieves the garden panel.
     * @return The garden panel.
     * @author Cyrus Shaerpour
     */
    public GardenPanel getGardenPanel() {
        return mainPanel.getGardenPanel();
    }

    /**
     * Retrieves the game rule frame.
     * @return The game rule frame.
     * @return Cyrus Shaerpour
     */
    public GameRuleFrame getGameRuleFrame() {
        return mainPanel.getGameRuleFrame();
    }
}
