package View;

import Controller.Controller;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;

/**
 * The MainPanel class represents the main panel of the user interface.
 * This panel contains other sub-panels such as the center panel, east panel,
 * and south panel, which together form the layout of the application's user interface.
 * This class extends {@link javax.swing.JPanel}.
 * @author annagranberg
 */
public class MainPanel extends JPanel
{
    private BorderLayout layout; // Layout manager for the panel
    private Controller controller; // Reference to controller
    private CenterPanel centerPanel;
    private EastPanel eastPanel;

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

        centerPanel = new CenterPanel(300, 450, this, controller);
        add(centerPanel, BorderLayout.CENTER);

        SouthPanel southPanel = new SouthPanel(controller, width, height);
        add(southPanel, BorderLayout.SOUTH);
        setVisible(true);

        JLabel plantInfoLabel = new JLabel("Plant information");
    }

    //TODO: assistent added this
    public void refreshBar() {
        eastPanel.refreshBar();
    }

    public CenterPanel getCenterPanel() {
        return centerPanel;
    }

}
