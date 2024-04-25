package View;

import Controller.Controller;
import javax.swing.*;
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
    private int width = 600; // dimensions for frame size
    private int height = 800; // dimensions for frame size
    private MainPanel mainPanel; // reference to mainPanel
    private GardenView gardenView; // reference to gardenView

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

        JMenu alternatives = new JMenu("Alternatives");

        JMenuItem gameRules = new JMenuItem("Game Rules");
        gameRules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRulesDialog();
            }
        });

        JMenuItem differentPlants = new JMenuItem("Plants");
        differentPlants.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPlant();
            }
        });

        alternatives.add(gameRules);
        alternatives.add(differentPlants);
        menuBar.add(alternatives);

        setJMenuBar(menuBar);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Anropar SaveGame() för att spara spelet när fönstret stängs
                controller.saveGame();
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
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
        gardenView = new GardenView(this, mainPanel.getCenterPanel(), controller);
    }
}