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
 * @author Anna Granberg
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
     * @author Anna Granberg
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


        JMenuItem gameRules = new JMenuItem("Game Rules"); // JMenuItem to show Game Rules
        gameRules.setFont(customFont);
        gameRules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRulesDialog();
            }
        });


        JMenuItem gameHistory = new JMenuItem("Game History"); // JMenuItem to show Game History
        gameHistory.setFont(customFont);
        gameHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameHistoryFrame gameHistoryFrame = new GameHistoryFrame();
            }
        });

        JMenuItem changeName = new JMenuItem("Change Plant Name"); // JMenuItem to change the name of a plant.
        changeName.setFont(customFont);
        changeName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.changePlantName();
            }
        });

        JMenuItem removePlant = new JMenuItem("Remove plant"); // JMenuItem to remove a plant
        removePlant.setFont(customFont);
        removePlant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.removePlant(controller.getPlantName());
            }
        });

        JMenuItem newGame = new JMenuItem("Start Over"); // JMenuItem to remove all the plants.
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

        // add the JMenuItem to the MenuBar
        menuBar.add(gameRules);
        menuBar.add(changeName);
        menuBar.add(gameHistory);
        menuBar.add(removePlant);
        menuBar.add(newGame);
        setJMenuBar(menuBar);

        addWindowListener(new WindowAdapter() { // listener för att spara spelet när man trycker på exit
            @Override
            public void windowClosing(WindowEvent e) {
                controller.saveGame();
            }
        }); // when closing the window, save the game.

        pack();
        setLocationRelativeTo(null); // location centered.
        setVisible(true); // make visible
    }
    
    /**
     * Displays gameRulesFrame showing the game rules.
     *
     * @author annagranberg
     */
    public void showRulesDialog() {
        GameRuleFrame gameRuleFrame = new GameRuleFrame();
    }

    /**
     * Retrieves the CenterPanel.
     * @return CenterPanel
     */
    public CenterPanel getCenterPanel() {
        return mainPanel.getCenterPanel();
    }

    /**
     * Retrieves the EastPanel.
     * @return EastPanel
     */
    public EastPanel getEastPanel() {
        return mainPanel.getEastPanel();
    }

    /**
     * Retrieves the SouthPanel.
     * @return SouthPanel
     */
    public SouthPanel getSouthPanel(){
        return mainPanel.getSouthPanel();
    }

    /**
     * Retrieves the MainPanel.
     * @return MainPanel
     */
    public MainPanel getMainPanel(){
        return mainPanel;
    }

    /**
     * Retrieves the GardenPanel.
     * @return GardenPanel
     */
    public GardenPanel getGardenPanel(){
        return mainPanel.getGardenPanel();
    }
}