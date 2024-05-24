package View;

import Controller.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;

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

        TitledBorder titledBorder = BorderFactory.createTitledBorder("Plant care");  // skapa en border runt panel
        Font myFont = new Font("Bebas Neue", Font.BOLD, 12);  // font för hela spelet
        titledBorder.setTitleFont(myFont);
        setBorder(titledBorder);

        JPanel pnlButtons = new JPanel(); // skapa knappar
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

        Border border = this.getBorder();
        Border margin = BorderFactory.createEmptyBorder(6, 6, 6, 6);
        setBorder(new CompoundBorder(border, margin));

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

        timeUntilDeathLabel = new JLabel("tid här ;(");
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

        nightMode = new JButton("Night mode");
        nightMode.setFont(new Font("Bebas Neue", Font.BOLD, 12));
        add(nightMode, BorderLayout.SOUTH);

        nightMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // Create a timer to update the time until next watering every second
        waterTimer = new Timer(1000, new ActionListener() { // delay 1000 för att timern ska uppdateras varje sekund
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller.getTimeUntilNextWatering() == 0) {
                    Water.setEnabled(true);
                    if(controller.getCurrentPlant() != null){
                        controller.startLoseLifeTimer();
                    }
                }
                updateTimeUntilLabelWatering();
                repaint();
                revalidate();
            }
        });
        waterTimer.start();

        deathTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Skapa en ny timern för att räkna ner från den beräknade initialDelay
                Timer deathTimer = new Timer(1000, new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        LocalDateTime timeUntil = controller.getTimeSinceLastWatered();
                        LocalDateTime timeNow = LocalDateTime.now();

                        Duration duration = Duration.between(timeNow, timeUntil);

                        // Update the time every second
                        updateDeathTimer(controller.getTimeUntilLoseLife());
                    }
                });

                // Starta den nya timern med den beräknade initialDelay
                // deathTimer.setInitialDelay((int) initialDelay);
                deathTimer.start();
            }
        });

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
                //System.out.println("No lives left");
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
            timeUntilWatering.setText("plantlist is null // eastpanel");
        } else {
            long timeUntilNextWatering = controller.getTimeUntilNextWatering();
            // Kontrollera om tiden är negativ
            if (timeUntilNextWatering < 0) {
                // todo: lägg till mainFrame.timeToWater()
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

    /**
     * Updates the time until the plant loses a life.
     * This method is called every second by the timer to keep the display up-to-date.
     *
     * @param timeUntilLoseLife The duration until the plant loses a life.
     * @author Anna Granberg
     */
    public void updateDeathTimer(Duration timeUntilLoseLife) {
        if (timeUntilLoseLife == null || timeUntilLoseLife.isZero() || timeUntilLoseLife.isNegative()) {
            timeUntilDeathLabel.setText("<html><div style='text-align: center; font-size: 9px;'>Time until life loss:<br>" + "00:00:00" + "</div></html>");
            controller.getCurrentPlant().decreaseLife();
            System.err.println("TimeUntilLoseLife is null // EastPanel");
            // Stop the timer if the duration has reached zero
            deathTimer.stop();
        } else {
            // Format the time as you want to display in JLabel
            long hours = timeUntilLoseLife.toHours();
            long minutes = timeUntilLoseLife.toMinutesPart();
            long seconds = timeUntilLoseLife.toSecondsPart();

            String formattedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);

            // Update JLabel with the formatted time
            timeUntilDeathLabel.setText("<html><div style='text-align: center; font-size: 9px;'>Time until life loss:<br>" + formattedTime + "</div></html>");
            repaint();
            revalidate();
        }
    }

    // Metod för att uppdatera och nollställa deathtimer när plantan vattnas
    public void updateAndResetDeathTimer() {
        // Stoppa den befintliga deathtimer, om den är aktiv
        if (deathTimer != null) {
            deathTimer.stop();
        }

        // Uppdatera deathtimer med den nya tiden
        long timeUntilNextDeath = controller.getRemainingDeathTimerMilliseconds();

        // Starta om deathtimer med den nya tiden
        deathTimer = new Timer((int) timeUntilNextDeath, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.err.println("Death timer aktiverad från eastpanel");
                updateDeathTimer(controller.getRemainingTime());
            }
        });
        deathTimer.start();
    }

}
