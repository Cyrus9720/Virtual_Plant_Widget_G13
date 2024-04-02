package View;

import Controller.Controller;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public class MainPanel extends JPanel
{
    private BorderLayout layout;

    public MainPanel(Controller controller, int width, int height)
    {
        layout = new BorderLayout();
        setLayout(layout);
        setBackground(new Color(153, 188, 133));

        Border border = this.getBorder();
        Border margin = BorderFactory.createEmptyBorder(12, 12, 12, 12);
        setBorder(new CompoundBorder(border, margin));

        EastPanel eastPanel = new EastPanel(controller, width/2-60, height-130);
        add(eastPanel, BorderLayout.EAST);

        CenterPanel centerPanel = new CenterPanel(controller, width, height);
        add(centerPanel, BorderLayout.CENTER);

        SouthPanel northPanel = new SouthPanel(controller, width, 300);
        add(northPanel, BorderLayout.SOUTH);
        setVisible(true);


        JLabel plantInfoLabel = new JLabel("Plant information");

    }
}
