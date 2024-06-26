package View;

import Controller.Controller;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the garden panel shown on the left, which shows all the different plants in the user's garden, and populates it with the approriate buttons for selecting the active plant.
 * @author Anna Granberg & Cyrus Shaerpour & Roa Jamhour
 */
public class GardenPanel extends JPanel {
    private List<String> plantPaths;
    private Font customFont = new Font("Bebas Neue", Font.BOLD, 12);
    private Controller controller;
    private JButton addPlantButton;
    private Border border;
    private List<JButton> plantButtons; // Store plant buttons in a list
    private TitledBorder titledBorder;

    /**
     * Constructor for GardenPanel
     * @param plantPaths List of the plantPath
     * @param controller controller
     * @author Cyrus Shaerpour & Anna Granberg
     */
    public GardenPanel(List<String> plantPaths, Controller controller) {
        this.plantPaths = plantPaths;
        this.controller = controller;
        plantButtons = new ArrayList<>(); // Initialize the list

        setPreferredSize(new Dimension(175, 500));
        setBackground(new Color(225, 240, 218));
        setLayout(new GridLayout(4, 2));

        addAddPlantButton();
        generateButtons();

        border = BorderFactory.createLineBorder(Color.BLACK);
        titledBorder = BorderFactory.createTitledBorder(border, "Garden", TitledBorder.CENTER, TitledBorder.TOP, customFont, Color.BLACK);
        setBorder(titledBorder);
    }

    /**
     * Creates and fills the buttons to populate the panel.
     * @author Anna Granberg & Cyrus Shaerpour
     */
    public void generateButtons() {
        for (int i = 0; i < plantPaths.size(); i++) {
            ImageIcon icon = new ImageIcon(plantPaths.get(i));
            Image iconImage = icon.getImage();
            Image scaledIconImage = iconImage.getScaledInstance(50, 75, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledIconImage);

            JButton plantButton = new JButton(String.valueOf(i));
            plantButton.setFont(customFont);
            plantButton.setIcon(scaledIcon);
            plantButton.setFocusPainted(false);
            plantButton.setBorderPainted(false);
            plantButton.setContentAreaFilled(false);

            plantButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.switchPlant(e.getActionCommand());
                }
            });

            plantButtons.add(plantButton); // Add each button to the list
            add(plantButton);
        }
    }

    /**
     * Removes and then populates the buttons on the panel.
     * @param newPlantPaths Updates the plantPath
     * @author Anna Granberg & Cyrus Shaerpour
     */
    public void updateButtons(List<String> newPlantPaths) {
        removeAll();
        plantPaths = newPlantPaths;
        addAddPlantButton();
        generateButtons();
        revalidate();
        repaint();
    }

    /**
     * Sets the default look and size of the addPlant button, depending on the day/night mode.
     * @author Anna Granberg & Cyrus Shaerpour & Roa Jamhour
     */
    private void addAddPlantButton() {
        addPlantButton = new JButton("Add new plant");
        addPlantButton.setBackground(new Color(153, 188, 133));
        addPlantButton.setSize(150, 30);
        addPlantButton.setFont(customFont);
        addPlantButton.setFocusPainted(false);
        addPlantButton.setBorderPainted(true);

        // Set initial foreground color based on the current mode
        if (controller.night) {
            addPlantButton.setForeground(Color.WHITE);
            addPlantButton.setBackground(new Color(13, 12, 29));
        } else {
            addPlantButton.setForeground(Color.BLACK);
            addPlantButton.setBackground(new Color(153, 188, 133));
        }

        addPlantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller.getPlantList().size() <= 6) {
                    AddNewPlantFrame addNewPlantFrame = new AddNewPlantFrame(controller);
                } else {
                    JOptionPane.showMessageDialog(GardenPanel.this,
                            "You can only have 7 plants in your garden. Please remove a plant to continue",
                            "You have too many plants :(", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        add(addPlantButton, BorderLayout.SOUTH);
    }

    /**
     * Sets the garden panel to night mode.
     * Changes the background color and the add plant button color.
     * @author Cyrus Shaerpour
     */
    public void nightGarden() {
        setBackground(new Color(47, 49, 73));
        addPlantButton.setBackground(new Color(156, 166, 0));
        border = BorderFactory.createLineBorder(Color.WHITE);
        titledBorder.setTitleColor(Color.WHITE);
        // Iterate through the list and update each button's color
        System.out.println(plantButtons.size());
        for (JButton button : plantButtons) {
            button.setForeground(Color.WHITE);
        }
    }

    /**
     * Sets the garden panel to day mode.
     * Changes the background color and the add plant button color.
     * @author Cyrus Shaerpour
     */
    public void dayGarden() {
        setBackground(new Color(225, 240, 218));
        addPlantButton.setBackground(new Color(153, 188, 133));
        border = BorderFactory.createLineBorder(Color.BLACK);
        titledBorder.setTitleColor(Color.BLACK);
        // Iterate through the list and update each button's color

        for (JButton button : plantButtons) {
            button.setForeground(Color.BLACK);
        }
    }
}
