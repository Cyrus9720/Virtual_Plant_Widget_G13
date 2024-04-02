package View;

import Controller.Controller;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class SouthPanel extends JPanel
{
    private Controller controller;
    private JLabel progressbarLabel;

    public SouthPanel(Controller controller, int width, int height) {
        this.controller = controller;

        setSize(width, height);

        TitledBorder titledBorder = BorderFactory.createTitledBorder("Plant information");
        Font myFont = new Font("Bebas Neue", Font.BOLD, 12);
        titledBorder.setTitleFont(myFont);
        setBorder(titledBorder);

        setBackground(new Color(225, 240, 218));
        setLayout(new FlowLayout(FlowLayout.LEADING));

        ImageIcon threeHearts = new ImageIcon("Images/ThreeHearts.png");
        Image originalThreeHearts = threeHearts.getImage();
        Image scaledHeartsLivesImage = originalThreeHearts.getScaledInstance(100, 75, Image.SCALE_SMOOTH);
        ImageIcon scaledThreeHearts = new ImageIcon(scaledHeartsLivesImage);
        JLabel threeHeartsLabel = new JLabel(scaledThreeHearts);
        add(threeHeartsLabel);

        ImageIcon progressbar = new ImageIcon("Images/almostEmptyProgressBar.png");
        Image progressbarImage = progressbar.getImage();
        Image scaledProgressbarImage = progressbarImage.getScaledInstance(100, 75, Image.SCALE_SMOOTH);
        ImageIcon scaledProgressbar = new ImageIcon(scaledProgressbarImage);
        progressbarLabel = new JLabel(scaledProgressbar);
        add(progressbarLabel);

        JLabel plantInfo = new JLabel("<html>More information about your plant, coming soon...</html>");
        plantInfo.setPreferredSize(new Dimension(100, 60));
        plantInfo.setFont(new Font("Bebas Neue", Font.BOLD, 10));
        add(plantInfo);
    }

    public void updateWaterProgress() {
        ImageIcon progressbar = new ImageIcon("Images/fullProgressBar.png");
        Image progressbarImage = progressbar.getImage();
        Image scaledProgressbarImage = progressbarImage.getScaledInstance(100, 75, Image.SCALE_SMOOTH);
        ImageIcon scaledProgressbar = new ImageIcon(scaledProgressbarImage);

        progressbarLabel = new JLabel(scaledProgressbar);
        add(progressbarLabel);
    }


}

