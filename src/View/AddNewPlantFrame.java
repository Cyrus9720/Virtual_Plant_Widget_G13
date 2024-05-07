package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddNewPlantFrame extends JFrame {
    public AddNewPlantFrame(Controller controller) {
        setSize(new Dimension(300, 150)); // Ställer in storleken på fönstret
        setVisible(true); // Gör fönstret synligt när det skapas

        NewPlantPanel newPlantPanel = new NewPlantPanel(controller); // Skapar en ny panel för att lägga till växter
        add(newPlantPanel); // Lägger till den nya panelen i fönstret
    }

    private class NewPlantPanel extends JPanel {
        private NewPlantPanel(Controller controller) {
            setBackground(new Color(225, 240, 218)); // bakgrundsfärgen för panelen
            setLayout(new FlowLayout()); // Ställer in layouten för panelen

            JLabel infoLabel = new JLabel("Choose a plant to add to the garden!"); // Skapar en label för att visa instruktioner
            Font customFont = new Font("Bebas Neue", Font.BOLD, 12); // anpassat typsnitt
            infoLabel.setFont(customFont); // lägger typsnittet på label

            // Knapp för solros
            JButton sunflowerButton = new JButton("Sunflower");
            sunflowerButton.setFont(customFont); // anpassat typsnittet på knappen
            sunflowerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.addNewSunflower(); // controller-metod för att lägga till en ny solros
                    AddNewPlantFrame.this.dispose(); // Stänger det aktuella fönstret när knappen trycks
                }
            });
            // Knapp för ros
            JButton roseButton = new JButton("Rose");
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
            blackberryButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.addNewBlackberry();
                    AddNewPlantFrame.this.dispose();
                }
            });

            // Lägger till knapparna på panelen
            add(infoLabel);
            add(sunflowerButton);
            add(roseButton);
            add(tomatoButton);
            add(blackberryButton);
        }
    }
}
