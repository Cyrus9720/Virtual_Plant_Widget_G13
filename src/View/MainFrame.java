package View;

import Controller.Controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private Controller controller;
    private int width = 1200;
    private int height = 1000;

    public MainFrame(Controller controller) {
        this.controller = controller;
        setUpFrame();
    }

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

    public void showRulesDialog()
    {
        String rules = "Game rules:\nComing soon... ";
        Font customFont = new Font("Bebas Neue", Font.BOLD, 12);
        UIManager.put("OptionPane.messageFont", customFont);
        UIManager.put("OptionPane.background", new Color(225, 240, 218));
        UIManager.put("Panel.background", new Color(225, 240, 218));
        ImageIcon customIcon = new ImageIcon("/Users/annagranberg/Documents/GitHub/Virtual_Plant_Widged_G13/src/Images/img.png");
        Image originalCustomImage = customIcon.getImage();
        Image scaledIconImage = originalCustomImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledPlantImageIcon = new ImageIcon(scaledIconImage);
        JOptionPane.showOptionDialog(null, rules, "Virtual Plant Widget", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, scaledPlantImageIcon, null, null);
    }
}