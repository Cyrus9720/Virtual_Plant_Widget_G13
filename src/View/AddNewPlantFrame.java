package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddNewPlantFrame extends JFrame {
    /**
     * Constructs an AddNewPlantFrame with the specified controller.
     *
     * @param controller The controller object to be associated with the frame.
     */
    public AddNewPlantFrame(Controller controller) {
        setSize(new Dimension(300, 300)); // Ställer in storleken på fönstret
        setVisible(true); // Gör fönstret synligt när det skapas

        NewPlantPanel newPlantPanel = new NewPlantPanel(controller); // Skapar en ny panel för att lägga till växter
        add(newPlantPanel); // Lägger till den nya panelen i fönstret
    }

    /**
     * Panel for adding new plants to the garden.
     */
    private class NewPlantPanel extends JPanel {
        /**
         * Constructs a NewPlantPanel with controller.
         *
         * @param controller The controller object associated with the panel.
         */

        private int height = 120;
        private int widht = 50;
        private NewPlantPanel(Controller controller) {
            if (controller.night) {
                setBackground(new Color(47, 49, 73)); // bakgrundsfärgen för panelen
            } else {
                setBackground(new Color(225, 240, 218)); // bakgrundsfärgen för panelen
            }
            //setBackground(new Color(225, 240, 218)); // bakgrundsfärgen för panelen
            setLayout(new FlowLayout()); // Ställer in layouten för panelen

            JLabel infoLabel = new JLabel("Choose a plant to add to the garden!"); // Skapar en label för att visa instruktioner
            Font customFont = new Font("Bebas Neue", Font.BOLD, 12); // anpassat typsnitt
            infoLabel.setFont(customFont); // lägger typsnittet på label

            // Knapp för solros
            JButton sunflowerButton = new JButton("Sunflower");
            sunflowerButton.setFont(customFont); // anpassat typsnittet på knappen
            sunflowerButton.setPreferredSize(new Dimension(height, widht));
            sunflowerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.addNewSunflower(); // controller-metod för att lägga till en ny solros
                    AddNewPlantFrame.this.dispose(); // Stänger det aktuella fönstret när knappen trycks
                }
            });
            // Knapp för ros
            JButton roseButton = new JButton("Rose");
            roseButton.setPreferredSize(new Dimension(height, widht));
            roseButton.setFont(customFont); // Tillämpar det anpassade typsnittet på knappen
            roseButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.addNewRose(); // controller-metod för att lägga till en ny ros
                    AddNewPlantFrame.this.dispose(); // Stänger det aktuella fönstret när knappen trycks
                }
            });

            // Knapp för tomatplanta
            JButton tomatoButton = new JButton("Tomato Plant");
            tomatoButton.setPreferredSize(new Dimension(height, widht));
            tomatoButton.setFont(customFont); // anpassat typsnittet på knappen
            tomatoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.addNewTomatoPlant(); // controller-metod för att lägga till en ny tomatplanta
                    AddNewPlantFrame.this.dispose(); // Stänger det aktuella fönstret när knappen trycks
                }
            });

            JButton blackberryButton = new JButton("Blackberry");
            blackberryButton.setFont(customFont);
            blackberryButton.setPreferredSize(new Dimension(height, widht));
            blackberryButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.addNewBlackberry();
                    AddNewPlantFrame.this.dispose();
                }
            });

            JButton miniTreeButton = new JButton("Mini Tree");
            miniTreeButton.setFont(customFont);
            miniTreeButton.setPreferredSize(new Dimension(height, widht));
            miniTreeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.addNewMiniTree();
                    AddNewPlantFrame.this.dispose();
                }
            });

            JButton cactusButton = new JButton("Cactus");
            cactusButton.setFont(customFont);
            cactusButton.setPreferredSize(new Dimension(height, widht));
            cactusButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.addNewCactus();
                    AddNewPlantFrame.this.dispose();
                }
            });


            // Lägger till knapparna på panelen
            add(infoLabel);
            add(sunflowerButton);
            add(roseButton);
            add(tomatoButton);
            add(blackberryButton);
            add(miniTreeButton);
            add(cactusButton);
        }
    }
}
