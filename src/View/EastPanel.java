package View;

import Controller.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.TimerTask;

/**
 * The EastPanel class represents the panel containing plant care controls on the east side of the user interface.
 * This panel includes buttons for performing various plant care actions, such as watering.
 *
 * This class extends {@link javax.swing.JPanel}.
 *
 * @author annagranberg
 */
public class EastPanel extends JPanel {
    private Controller controller; // Referens till controller
    private int width, height; // Storlek på panelen
    private JButton Water; // Knapp för vattning
    private JLabel progressbarLabel; // JLabel för progressbar / timesWatered
    private JLabel threeHeartsLabel; // JLabel för nbrOfLives
    private JLabel timeUntilWatering; // JLabel för at visa tiden tills nästa vattning
    private JLabel timeUntilDeathLabel;
    private JButton nightMode;
    private Timer waterTimer; // Timer för uppdatering av tiden tills nästa vattning
    private Timer deathTimer;
    private TitledBorder titledBorder; // Border för panelen
    private JPanel pnlButtons; // Panel för knappar
    private Border border;

    /**
     * Constructs a new EastPanel with the specified controller, width, and height.
     *
     * @param controller The controller object responsible for handling user actions.
     * @param width The width of the panel.
     * @param height The height of the panel.
     *
     * @author Anna Granberg
     */
    public EastPanel(Controller controller, int width, int height) {
        this.controller = controller;
        this.width = width;
        this.height = height;

        setBackground(new Color(225, 240, 218));
        setPreferredSize(new Dimension(150, 300));

        titledBorder = BorderFactory.createTitledBorder("Plant care");  // skapa en border runt panel
        Font myFont = new Font("Bebas Neue", Font.BOLD, 12);  // font för hela spelet
        titledBorder.setTitleFont(myFont);
        setBorder(titledBorder);

        pnlButtons = new JPanel(); // skapa knappar
        pnlButtons.setBackground(new Color(225, 240, 218)); // bakgrundsfärg

        ImageIcon waterButton = new ImageIcon("src/Images/Watercan.png"); // Bild för vattenknapp
        Image originalWaterButtonImage = waterButton.getImage();
        Image scaledWaterButtonImage = originalWaterButtonImage.getScaledInstance(60, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledWaterIcon = new ImageIcon(scaledWaterButtonImage);

        Water = new JButton(scaledWaterIcon);
        Water.setBorderPainted(true);
        Water.setContentAreaFilled(true);
        Water.setBackground(new Color(225, 240, 218));
        pnlButtons.add(Water, BorderLayout.NORTH);

        border = BorderFactory.createLineBorder(Color.BLACK);
        titledBorder = BorderFactory.createTitledBorder(border, "Handle your plant", TitledBorder.CENTER, TitledBorder.TOP, myFont, Color.BLACK);
        setBorder(titledBorder);

        add(pnlButtons);
        progressbarLabel = new JLabel();
        ImageIcon emptyProgressBarIcon = new ImageIcon("src/Images/emptyProgressBar.png"); // bild för progressbar
        Image originalImage = emptyProgressBarIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(100, 75, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        timeUntilWatering = new JLabel();
        timeUntilWatering.setFont(new Font("Bebas Neue", Font.BOLD, 9));
        updateTimeUntilLabelWatering();
        add(timeUntilWatering, BorderLayout.NORTH);

        progressbarLabel.setIcon(scaledIcon);
        add(progressbarLabel, BorderLayout.SOUTH);

        threeHeartsLabel = new JLabel(updateAmountOfLife());
        add(threeHeartsLabel, BorderLayout.WEST);

        timeUntilDeathLabel = new JLabel();
        timeUntilDeathLabel.setFont(new Font("Bebas Neue", Font.BOLD, 12));
        add(timeUntilDeathLabel, BorderLayout.SOUTH);


        // ActionListener för vattenknappen
        Water.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == Water) {
                    controller.buttonPressed(ButtonType.Water);
                    progressbarLabel.setIcon(updateWaterProgress());
                }
            }
        });

        ImageIcon nightButton = new ImageIcon("src/Images/NightTime.PNG"); // Bild för vattenknapp
        Image originalNightButtonImage = nightButton.getImage();
        Image scaledNightButtonImage = originalNightButtonImage.getScaledInstance(60, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledNightIcon = new ImageIcon(scaledNightButtonImage);

        nightMode = new JButton(scaledNightIcon);
        nightMode.setBorderPainted(false);
        nightMode.setContentAreaFilled(true);
        nightMode.setBackground(new Color(225, 240, 218));
        add(nightMode, BorderLayout.SOUTH);

        nightMode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == nightMode) {
                    controller.buttonPressed(ButtonType.NightMode);
                }
            }
        });


    // Create a timer to update the time until next watering every second
        waterTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller.getTimeUntilNextWatering() == 0) {
                    Water.setEnabled(true);
                }
                updateTimeUntilLabelWatering();
                repaint();
                revalidate();
            }
        });
        waterTimer.start();


        // Create a timer to update the time until next watering and time until death every second
       Timer deathTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDateTime deathTime = controller.getTimeUntilDeath();
                updateTimeUntilDeath(deathTime);
            }
        });
        deathTimer.start();
    }

    /**
     * Refreshes the progress bar by updating its icon.
     */
    public void refreshBar() {
        progressbarLabel.setIcon(updateWaterProgress());
    }

    /**
     * Enables the water button.
     * @author anna granberg
     */
    public void enableWaterButton() {
        Water.setEnabled(true); // Enables the water button
        controller.getCurrentPlant().setNewDeathTime(); // sätter en ny tid för plantan o dö
        Water.repaint();
    }

    /**
     * Disables the water button.
     * @author anna granberg
     */
    public void disableWaterButton() {
        Water.setEnabled(false); // Disables the water button
        Water.repaint();
    }

    /**
     * Updates the water progress indicator.
     * This method updates the progress bar to indicate the water level of the plant.
     * The water level depends on the plant's level and the number of times it has been watered.
     *
     * @return ImageIcon representing the water progress bar.
     * @author Anna Granberg
     */
    public ImageIcon updateWaterProgress() {
        int timesWatered = controller.getTimesWatered();
        int plantLevel = controller.getPlantLevel();

        String imagePath = "src/Images/emptyProgressBar.png"; // Default värde

        switch (plantLevel) {
            case 0: // när plant level är 0
                if(timesWatered == 0) { // vattnad 0 gånger
                    imagePath = "src/Images/emptyProgressBar.png";
                } else if (timesWatered == 1) { // vattnad 1 gång
                    imagePath = "src/Images/fullProgressBar.png";
                }
                break;
            case 1: // när plantlevel är 1
                if(timesWatered == 0){ // vattnad 0 gånger
                    imagePath = "src/Images/emptyProgressBar.png";
                } else if (timesWatered == 1) { // vattnad 1 gång
                    imagePath = "src/Images/halfProgressBar.png";
                } else { // vattnad 2 gånger
                    imagePath = "src/Images/fullProgressBar.png";
                }
                break;
            case 2: // när plantlevel är 2
                if(timesWatered == 0){ // vattnad 0 gånger
                    imagePath = "src/Images/emptyProgressBar.png";
                } else if (timesWatered == 1) { // vattnad 1 gång
                    imagePath = "src/Images/thirdProgressBar.png";
                } else if (timesWatered == 2){ // vattnad 2 gånger
                    imagePath = "src/Images/twoThirdsProgressBar.png";
                } else if (timesWatered == 3){ // vattnad 3 gånger
                    imagePath = "src/Images/fullProgressBar.png";
                }
                break;
            case 3: // när plantlevel är 3
                if(timesWatered == 0){ // vattnad 0 gånger
                    imagePath = "src/Images/emptyProgressBar.png";
                } else if  (timesWatered >= 1) { // vattnad 1 gång
                    imagePath = "src/Images/fullProgressBar.png";
                }
                break;
        }

        ImageIcon progressBarIcon = new ImageIcon(imagePath);

        // Skala bilden för att passa panelen
        Image originalImage = progressBarIcon.getImage(); // hämtar rätt bild efter switch-satsen
        Image scaledImage = originalImage.getScaledInstance(100, 75, Image.SCALE_SMOOTH);

        return new ImageIcon(scaledImage);
    }

    /**
     * Updates the GUI of the number of lives.
     *
     * This method updates the icon of the threeHeartsLabel to show
     * the current number of lives and repaints the label.
     * @author Anna Granberg
     */
    public void updateLives() {
        ImageIcon newLivesIcon = updateAmountOfLife();
        threeHeartsLabel.setIcon(newLivesIcon);
        threeHeartsLabel.repaint();
        threeHeartsLabel.revalidate();
    }

    /**
     * Updates and returns an ImageIcon representing the number of remaining lives for the user.
     * Depending on the number of lives, different images of hearts are displayed.
     *
     * @return ImageIcon representing the number of remaining lives, or null if the plant list is null or the number of lives is negative.
     * @author Anna Granberg
     */
    public ImageIcon updateAmountOfLife() {
        ImageIcon heartsIcon = null;
        if (controller.getPlantList() == null || controller.getNbrOfLives() < 0) {
            // Handle the case when the plant list is null or the number of lives is negative
            return null;
        }

        int nbrOfLives = controller.getNbrOfLives();

        switch (nbrOfLives) {
            case 0:
                // If there are no lives left, display an empty heart icon
                heartsIcon = new ImageIcon("src/Images/tommaHjärtan.PNG");
                System.out.println("No lives left");
                break;
            case 1:
                // If there is one life left, display one heart
                heartsIcon = new ImageIcon("src/Images/ettHjärta.PNG");
                System.out.println("One life left");
                break;
            case 2:
                // If there are two lives left, display two hearts
                heartsIcon = new ImageIcon("src/Images/tvåHjärtan.PNG");
                System.out.println("Two lives left");
                break;
            case 3:
                // If there are three lives left, display three hearts
                heartsIcon = new ImageIcon("src/Images/treHjärtan.PNG");
                System.out.println("Three lives left");
                break;
            default:
                heartsIcon = null;
                break;
        }

        if (heartsIcon != null) {
            // Update the icon for the hearts label
            Image originalHearts = heartsIcon.getImage();
            Image scaledHearts = originalHearts.getScaledInstance(100, 30, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledHearts);
        } else {
            return null;
        }
    }


    /**
     * Updates the time until label with the time until the next watering period.
     *
     * @return void
     * @author Anna Granberg
     */
    private void updateTimeUntilLabelWatering() {
        if (controller.getPlantList() == null) {
            timeUntilWatering.setText(" ");
        } else {
            long timeUntilNextWatering = controller.getTimeUntilNextWatering();
            // Kontrollera om tiden är negativ
            if (timeUntilNextWatering < 0) {
                timeUntilNextWatering = 0; // Sätt tiden till 0 om den är negativ
            }
            long hours = timeUntilNextWatering / 3600; // Konvertera sekunder till timmar
            long minutes = (timeUntilNextWatering % 3600) / 60; // Få återstående minuter
            long seconds = timeUntilNextWatering % 60; // Få återstående sekunder

            String formattedTime = String.format("%02d h %02d m %02d s", hours, minutes, seconds);
            // Använd HTML för att bryta texten på tre rader och minska textstorleken
            timeUntilWatering.setText("<html><div style='text-align: center; font-size: 9px;'>Next watering period:<br>" + formattedTime + "</div></html>");
        }
    }

    public void updateTimeUntilDeath(LocalDateTime timeUntilDeath) {
        if (controller.getPlantList() == null || controller.getCurrentPlant() == null || timeUntilDeath == null) {
            timeUntilDeathLabel.setText(" ");
        } else {
            if (controller.getTimeUntilNextWatering() == 0) {
                // Calculate the difference between the current time and the given timeUntilDeath
                LocalDateTime now = LocalDateTime.now();
                long timeDifferenceMillis = ChronoUnit.MILLIS.between(now, timeUntilDeath);

                // Check if the time is negative and set it to 0 if it is
                if (timeDifferenceMillis < 0) {
                    timeDifferenceMillis = 0;
                }

                // Convert milliseconds to hours, minutes, and seconds
                long seconds = timeDifferenceMillis / 1000; // Convert milliseconds to seconds
                long hours = seconds / 3600;
                long minutes = (seconds % 3600) / 60;
                seconds = seconds % 60;

                String formattedTime = String.format("%02d h %02d m %02d s", hours, minutes, seconds);

                timeUntilDeathLabel.setText("<html><div style='text-align: center; font-size: 9px;'>Time until life lost:<br>" + formattedTime + "</div></html>");
            }
        }
    }


    /**
     * Changes the icon of the night mode button to a moon.
     * @author Cyrus Shaerpour
     */
    public void moonButton() {
        ImageIcon moonIcon = new ImageIcon("src/Images/NightTime_Moon.PNG");
        Image moonImage = moonIcon.getImage();
        Image scaledMoonImage = moonImage.getScaledInstance(60, 50, Image.SCALE_SMOOTH);
        nightMode.setIcon(new ImageIcon(scaledMoonImage));
    }

    /**
     * Changes the icon of the night mode button to a sun.
     * @author Cyrus Shaerpour
     */
         public void sunButton() {
            ImageIcon sunIcon = new ImageIcon("src/Images/NightTime_Sun.PNG");
            Image sunImage = sunIcon.getImage();
            Image scaledSunImage = sunImage.getScaledInstance(60, 50, Image.SCALE_SMOOTH);
            nightMode.setIcon(new ImageIcon(scaledSunImage));
        }

    /**
     * Changes the colors of the panel to night mode.
     * @author Cyrus Shaerpour
     */

    public void nightColors() {
            setBackground(new Color(47, 49, 73));
            progressbarLabel.setBackground(new Color(47, 49, 73));
            threeHeartsLabel.setBackground(new Color(47, 49, 73));
            timeUntilWatering.setBackground(new Color(47, 49, 73));
            timeUntilDeathLabel.setBackground(new Color(47, 49, 73));
            pnlButtons.setBackground(new Color(47, 49, 73)); // bakgrundsfärg
            nightMode.setBackground(new Color(47, 49, 73));
            titledBorder.setTitleColor(Color.WHITE);
            timeUntilWatering.setForeground(Color.WHITE);
            timeUntilDeathLabel.setForeground(Color.WHITE);
        }

    /**
     * Changes the colors of the panel to day mode.
     * @author Cyrus Shaerpour
     */
        public void dayColors() {
            setBackground(new Color(225, 240, 218));
            progressbarLabel.setBackground(new Color(225, 240, 218));
            threeHeartsLabel.setBackground(new Color(225, 240, 218));
            timeUntilWatering.setBackground(new Color(225, 240, 218));
            timeUntilDeathLabel.setBackground(new Color(225, 240, 218));
            pnlButtons.setBackground(new Color(225, 240, 218));
            nightMode.setBackground(new Color(225, 240, 218));
            titledBorder.setTitleColor(Color.BLACK);
            timeUntilWatering.setForeground(Color.BLACK);
            timeUntilDeathLabel.setForeground(Color.BLACK);
        }
    }


