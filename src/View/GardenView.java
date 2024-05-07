package View;

import Controller.Controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * GardenView represents the dialog that displays the user's garden.
 * It contains a panel (GardenPanel) to show the garden's plants and allows the user to add new plants.
 */
public class GardenView extends JDialog {

    private Controller controller; // Referens till Controller
    private int width = 300; // Dialogens bredd
    private int height = 450; // Dialogens höjd
    private Font customFont = new Font("Bebas Neue", Font.BOLD, 12); // Anpassat typsnitt
    private GardenPanel gardenPanel; // Trädgårdspanel för att visa växterna

    public GardenView(JFrame parentFrame, Controller controller) {
        super(parentFrame, "Your garden!");
        setSize(width, height); // Ställ in storlek
        setResizable(false); // Gör dialogrutan icke-omstoringsbar
        this.controller = controller; // Sätt Controller-referens

        // Beräkna positionen
        int xCoordinate = parentFrame.getX() - width;
        int yCoordinate = parentFrame.getY(); // Du kan justera detta efter behov

        setLocation(xCoordinate, yCoordinate); // Ställ in positionen för dialogrutan

        // Initialisera GardenPanel
        gardenPanel = new GardenPanel(controller.getPlantImagePaths());
        add(gardenPanel); // Lägg till trädgårdspanelen

        setVisible(true); // Gör dialogrutan synlig
    }

    /**
     * Method to update the buttons in GardenPanel with new plant paths.
     *
     * @param newPlantPaths List with new plant paths
     */
    public void updateButtons(ArrayList<String> newPlantPaths) {
        gardenPanel.updateButtons(newPlantPaths);
    }

    /**
     * GardenPanel represents the panel that displays the user's garden and allows them to add new plants.
     */
    private class GardenPanel extends JPanel {
        private List<String> plantPaths; // Lista med växtvägar

        /**
         * Constructor for GardenPanel.
         *
         * @param plantPaths List of plant paths
         */
        public GardenPanel(List<String> plantPaths) {
            this.plantPaths = plantPaths;

            setBackground(new Color(225, 240, 218)); // Ställ in bakgrundsfärg
            setLayout(new GridLayout(4, 3)); // Ställ in layout

            generateButtons(); // Generera knappar baserat på tillgängliga växter
            addAddPlantButton(); // Lägg till knappen "Lägg till växt"
        }

        /**
         * Method to generate buttons with plant images.
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

                plantButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.switchPlant(e.getActionCommand());

                    }
                });

                add(plantButton, BorderLayout.SOUTH);
            }
        }

        /**
         * Method to update buttons based on new plants.
         *
         * @param newPlantPaths List of new plant paths
         */
        public void updateButtons(ArrayList<String> newPlantPaths) {
            removeAll(); // Ta bort befintliga knappar
            plantPaths = newPlantPaths; // Uppdatera listan med nya växter
            generateButtons(); // Generera nya knappar baserat på de nya växterna
            revalidate(); // Uppdatera layout
            repaint(); // Repaint om panelen
        }

        /**
         * Method to add the "Add Plant" button.
         */
        private void addAddPlantButton() {
            JButton addPlantButton = new JButton("Add new plant");
            addPlantButton.setPreferredSize(new Dimension(25,25));
            addPlantButton.setFont(customFont);
            addPlantButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    AddNewPlantFrame addNewPlantFrame = new AddNewPlantFrame(controller);
                    GardenView.this.dispose();
                }
            });
            setLayout(new GridLayout(plantPaths.size() + 1, 3));
            add(addPlantButton);
        }

    }
}
