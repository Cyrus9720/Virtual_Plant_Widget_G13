package View;
import Controller.Controller;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class CenterPanel extends JPanel
{
    Controller controller;
    private int width;
    private int height;
    public CenterPanel(Controller controller, int width, int height)
    {
        this.controller = controller;
        this.width = width;
        this.height = height;

        TitledBorder titledBorder = BorderFactory.createTitledBorder("Insert plant name here..");
        Font myFont = new Font("Bebas Neue", Font.BOLD, 12);
        titledBorder.setTitleFont(myFont);
        setBorder(titledBorder);

        setBackground(new Color(225, 240, 218));

        Border border = this.getBorder();
        Border margin = BorderFactory.createEmptyBorder(6, 6, 6, 6);
        setBorder(new CompoundBorder(border, margin));

        ImageIcon originalPlant = new ImageIcon("Images/plantIcon.png");
        Image originalPlantImage = originalPlant.getImage();
        Image scaledPlantImage = originalPlantImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledPlantImageIcon = new ImageIcon(scaledPlantImage);
        JLabel plantLabel = new JLabel(scaledPlantImageIcon);
        add(plantLabel, BorderLayout.CENTER);
    }
}
