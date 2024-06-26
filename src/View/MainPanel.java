package View;

import Controller.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * The MainPanel class represents the main panel of the user interface.
 * This panel contains other sub-panels such as the center panel, east panel,
 * and south panel, which together form the layout of the application's user interface.
 * This class extends {@link javax.swing.JPanel}.
 * @author annagranberg
 */
public class MainPanel extends JPanel {
    private BorderLayout layout; // Layout manager for the panel
    private Controller controller; // Reference to controller
    private CenterPanel centerPanel;
    private EastPanel eastPanel;
    private SouthPanel southPanel;
    private GardenPanel gardenPanel;
    private GameRuleFrame gameRuleFrame;

    /**
     * Constructs a new MainPanel with the specified controller, width, and height.
     *
     * @param controller The controller object responsible for handling user actions.
     * @param width The width of the main panel.
     * @param height The height of the main panel.
     * @author annagranberg
     */
    public MainPanel(Controller controller, int width, int height) {
        this.controller = controller;
        layout = new BorderLayout();
        setLayout(layout);
        setBackground(new Color(153, 188, 133));

        Border border = this.getBorder();
        Border margin = BorderFactory.createEmptyBorder(12, 12, 12, 12);
        setBorder(new CompoundBorder(border, margin));

        eastPanel = new EastPanel(controller, width/2-60, height-130);
        add(eastPanel, BorderLayout.EAST);

        centerPanel = new CenterPanel( this, controller);
        add(centerPanel, BorderLayout.CENTER);

        southPanel = new SouthPanel(controller);
        add(southPanel, BorderLayout.SOUTH);

        gardenPanel = new GardenPanel(controller.getPlantImagePaths(), controller);
        add(gardenPanel, BorderLayout.WEST);
        setVisible(true);

        //gameRuleFrame = new GameRuleFrame();

        JLabel plantInfoLabel = new JLabel("Plant information");
    }

    /**
     * Updates the buttons in the garden panel with new plant paths.
     *
     * @param newPlantPaths The list of paths for new plant images.
     */
    public void updateButtons(ArrayList<String> newPlantPaths) {
        gardenPanel.updateButtons(newPlantPaths);
    }

    /**
     * Refreshes the progress bar in the east panel.
     */
    public void refreshBar() {
        eastPanel.refreshBar();
        eastPanel.updateAmountOfLife(controller.getNbrOfLives());
    }

    /**
     * Retrieves the center panel.
     * @author Cyrus Shaerpour
     * @return The center panel.
     */
    public CenterPanel getCenterPanel() {
        return centerPanel;
    }

    /**
     * Retrieves the east panel.
     * @author Cyrus Shaerpour
     * @return The east panel.
     */
    public EastPanel getEastPanel() {
        return eastPanel;
    }

    /**
     * Retrieves the south panel.
     * @author Cyrus Shaerpour
     * @return The south panel.
     */
    public SouthPanel getSouthPanel() {
        return southPanel;
    }

    /**
     * Retrieves the garden panel.
     * @return The garden panel.
     * @author Cyrus Shaerpour
     */

    public GardenPanel getGardenPanel() {
        return gardenPanel;
    }

    /**
     * Retrieves the game rule frame.
     * @return The game rule frame.
     * @author Cyrus Shaerpour
     */

    public GameRuleFrame getGameRuleFrame() {
        return gameRuleFrame;
    }

    /**
     * Sets the background color of the main panel to a night theme.
     * @author Cyrus Shaerpour
     */
    public void nightMain() {
        setBackground(new Color(13, 12, 29));
    }

    /**
     * Sets the background color of the main panel to a day theme.
     * @author Cyrus Shaerpour
     */
    public void dayMain() {
        setBackground(new Color(153, 188, 133));
    }
}
