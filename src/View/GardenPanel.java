package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GardenPanel extends JPanel {
    private List<String> plantPaths; // Lista med växtvägar
    private Font customFont = new Font("Bebas Neue", Font.BOLD, 12); // Anpassat typsnitt
    private Controller controller; // Referens till Controller


    /**
     * Constructor for GardenPanel.
     *
     * @param plantPaths List of plant paths
     */
    public GardenPanel(List<String> plantPaths, Controller controller) {
        this.plantPaths = plantPaths;
        this.controller = controller;

        setPreferredSize(new Dimension(150,500));
        setBackground(new Color(225, 240, 218)); // Ställ in bakgrundsfärg
        setLayout(new GridLayout(5,2));
        generateButtons(); // Generera knappar baserat på tillgängliga växter
        addAddPlantButton(); // Lägg till knappen "Lägg till växt"
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
                    //GardenView.this.dispose(); // Stäng aktuellt fönster
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
    public void updateButtons(List<String> newPlantPaths) {
        removeAll(); // Ta bort befintliga knappar
        plantPaths = newPlantPaths; // Uppdatera listan med nya växter
        generateButtons(); // Generera nya knappar baserat på de nya växterna
        addAddPlantButton();
        revalidate(); // Uppdatera layout
        repaint(); // Repaint om panelen
    }

    /**
     * Method to add the "Add Plant" button.
     */
    private void addAddPlantButton() {
        JButton addPlantButton = new JButton("Add new plant");
        addPlantButton.setSize(new Dimension(25, 25));
        addPlantButton.setFont(customFont);
        addPlantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller.getPlantList().size() < 6) { // kontroll så att man inte har fler än 6 växter
                    AddNewPlantFrame addNewPlantFrame = new AddNewPlantFrame(controller);
                    //GardenView.this.dispose();
                }else {
                    JOptionPane.showMessageDialog(GardenPanel.this,
                            "You can only have 6 plants in your garden. Please remove a plant to continue",
                            "Too many plants :(", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        add(addPlantButton);
    }

    private ImageIcon scaleImageIcon(ImageIcon imageIcon, int width, int height) {
        Image image = imageIcon.getImage(); // ImageIcon till Image
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH); // Skalar bilden
        return new ImageIcon(scaledImage); // Omvandlar bilden tillbaka till ImageIcon
    }
}