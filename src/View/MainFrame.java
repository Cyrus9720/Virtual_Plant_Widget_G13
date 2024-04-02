package View;

import Controller.Controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private Controller controller;
    private int width = 1200;
    private int height = 1000;

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

        MainPanel mainPanel = new MainPanel(controller, width, height);
        setContentPane(mainPanel);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(225, 240, 218));

        JMenu alternatives = new JMenu("Alternatives");
        JMenuItem gameRules = new JMenuItem("Game Rules");
        gameRules.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showRulesDialog();
            }
        });

        alternatives.add(gameRules);
        menuBar.add(alternatives);

        setJMenuBar(menuBar);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Displays a dialog box showing the game rules.
     * @author annagranberg
     */
    public void showRulesDialog()
    {
        String rules = "Game rules:\nComing soon... ";
        Font customFont = new Font("Bebas Neue", Font.BOLD, 12);
        UIManager.put("OptionPane.messageFont", customFont);
        UIManager.put("OptionPane.background", new Color(225, 240, 218));
        UIManager.put("Panel.background", new Color(225, 240, 218));
        ImageIcon customIcon = new ImageIcon("src/Images/img.png");
        Image originalCustomImage = customIcon.getImage();
        Image scaledIconImage = originalCustomImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledPlantImageIcon = new ImageIcon(scaledIconImage);
        JOptionPane.showOptionDialog(null, rules, "Virtual Plant Widget", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, scaledPlantImageIcon, null, null);
    }
}