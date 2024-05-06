package View;

import Controller.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import Controller.SaveGame;

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
    private int width = 800; // dimensions for frame size
    private int height = 1000; // dimensions for frame size
    private Font customFont = new Font("Bebas Neue", Font.BOLD, 12);
    private MainPanel mainPanel; // reference to mainPanel
    private GardenView gardenView; // reference to gardenView
    private SouthPanel southPanel;
    private EastPanel eastPanel;

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
        setTitle("Virtual Plant Widget");
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        mainPanel = new MainPanel(controller, width, height);
        setContentPane(mainPanel);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(225, 240, 218));
        menuBar.setPreferredSize(new Dimension(200,25));
        menuBar.setFont(customFont);


        JMenuItem gameRules = new JMenuItem("Game Rules");
        gameRules.setFont(customFont);
        gameRules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRulesDialog();
            }
        });

        JMenuItem differentPlants = new JMenuItem("Garden");
        differentPlants.setFont(customFont);
        differentPlants.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPlant();
            }
        });

        JMenuItem newGame = new JMenuItem("Start Over");
        newGame.setFont(customFont);
        newGame.setBackground(new Color(225, 78, 78));
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setGameToNull();
            }
        });

        JMenuItem gameHistory = new JMenuItem("Game History");
        gameHistory.setFont(customFont);
        gameHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // todo: implementera kod för att se historik över alla växter
            }
        });

        Border menuItemBorder = BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(Color.BLACK), // Add a line border
        BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Add some padding

        // Set the custom border for each JMenuItem
        differentPlants.setBorder(menuItemBorder);
        gameRules.setBorder(menuItemBorder);
        gameHistory.setBorder(menuItemBorder);
        newGame.setBorder(menuItemBorder);

        menuBar.add(differentPlants);
        menuBar.add(gameRules);
        menuBar.add(gameHistory);
        menuBar.add(newGame);
        setJMenuBar(menuBar);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.saveGame();
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public void timeToWater(){
        String message = "It's time to water the plant!\nDon't forget to give it some love and hydration.";
        Font customFont = new Font("Bebas Neue", Font.BOLD, 12);
        UIManager.put("OptionPane.messageFont", customFont);
        UIManager.put("OptionPane.background", new Color(225, 240, 218));
        UIManager.put("Panel.background", new Color(225, 240, 218));
        JOptionPane.showMessageDialog(null, message, "Plant Watering Reminder", JOptionPane.INFORMATION_MESSAGE);
    }

    public void welcomeBackMessage(){
        String message = "Welcome back! It's been " + controller.getTimeSinceLastPlayed() + " since you played"; // todo: insert some time method
        Font customFont = new Font("Bebas Neue", Font.BOLD, 12);
        UIManager.put("OptionPane.messageFont", customFont);
        UIManager.put("OptionPane.background", new Color(225, 240, 218));
        UIManager.put("Panel.background", new Color(225, 240, 218));
        JOptionPane.showMessageDialog(null, message, "Welcome back", JOptionPane.INFORMATION_MESSAGE);

    }

    /**
     * Displays gameRulesFrame showing the game rules.
     *
     * @author annagranberg
     */
    public void showRulesDialog() {
        GameRuleFrame gameRuleFrame = new GameRuleFrame();
    }

    public void switchPlant(){
        gardenView = new GardenView(this, controller);
    }

    public CenterPanel getCenterPanel() {
        return mainPanel.getCenterPanel();
    }

    public EastPanel getEastPanel() {
        return mainPanel.getEastPanel();
    }

    public SouthPanel getSouthPanel(){
        return mainPanel.getSouthPanel();
    }
}