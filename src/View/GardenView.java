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
        int xCoordinate = parentFrame.getX() - width; // koordinater för att sätta frame på rätt plats
        int yCoordinate = parentFrame.getY();

        setLocation(xCoordinate, yCoordinate); // Ställ in positionen för frame

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
            setLayout(new GridLayout(0, 3)); // Set the layout to 3 columns

            addAddPlantButton(); // Lägg till knappen "Lägg till växt"
            generateButtons(); // Generera knappar baserat på tillgängliga växter

        }

        /**
         * Method to generate buttons with plant images.
         */
        public void generateButtons() {
            // Loopa igenom plantPaths-listan för att skapa knappar för varje växt
            for (int i = 0; i < plantPaths.size(); i++) {
                // Skapa en ImageIcon från bildsökvägen och skala den till önskad storlek
                ImageIcon icon = new ImageIcon(plantPaths.get(i));
                Image iconImage = icon.getImage();
                Image scaledIconImage = iconImage.getScaledInstance(50, 75, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledIconImage);

                // Skapa en knapp med växtens index som text och ikonen som ikon
                JButton plantButton = new JButton(String.valueOf(i));
                plantButton.setFont(customFont); // Använd anpassat typsnitt för knappen
                plantButton.setIcon(scaledIcon); // Sätt ikon på knappen
                plantButton.setFocusPainted(false); // Avaktivera fokusmålning på knappen
                plantButton.setBorderPainted(false); // Avaktivera kantmålning på knappen

                // Lägg till en action listener för knappen
                plantButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Vid knapptryck, växla till den valda växten och stäng trädgårdsfönstret
                        controller.switchPlant(e.getActionCommand());
                        GardenView.this.dispose(); // Stäng aktuellt fönster
                    }
                });

                add(plantButton, BorderLayout.SOUTH); // Lägg till knappen
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
                addPlantButton.setPreferredSize(new Dimension(25, 25));
                addPlantButton.setFont(customFont);
                addPlantButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (controller.getPlantList().size() < 6) { // kontroll så att man inte har fler än 6 växter
                            AddNewPlantFrame addNewPlantFrame = new AddNewPlantFrame(controller);
                            GardenView.this.dispose();
                        }else {
                            JOptionPane.showMessageDialog(GardenView.this,
                                    "You can only have 6 plants in your garden. Please remove a plant to continue",
                                    "Too many plants :(", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                });
                add(addPlantButton);
        }


    }
}
