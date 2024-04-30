package View;
import Model.Plant;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import javax.swing.JLabel;

public class CenterPanel extends JPanel {

    private ImageIcon plantPicture;
    private JLabel plantLabel;
    private MainPanel mainPanel;

    private JProgressBar healthBar; //A progress bar to display plant health

    //private JLabel agingLabel; // A label to display aging information

    private JLabel plantLevelLabel;

    private static final int IMAGE_WIDTH = 300; // Desired width for scaled images
    private static final int IMAGE_HEIGHT = 450; // Desired height for scaled images

   // private static final long YOUNG_THRESHOLD = 3000; // Threshold for young age (e.g., 5 minutes)
    // private static final long MATURE_THRESHOLD = 6000; //10m



    public CenterPanel(int width, int height) {
        setPreferredSize(new Dimension(3200, 450));}

    public CenterPanel(int width, int height, MainPanel mainPanel) {
        setPreferredSize(new Dimension(320, 485));

        setBackground(new Color(225, 240, 218));

        //TODO: assistent added this
        this.mainPanel = mainPanel;

        plantPicture = new ImageIcon("src/Images/PotArt1.JPG"); // Default image

        TitledBorder titledBorder = BorderFactory.createTitledBorder("Plant name here");
        Font myFont = new Font("Bebas Neue", Font.BOLD, 12);
        titledBorder.setTitleFont(myFont);
        setBorder(titledBorder);

        plantLabel = new JLabel();
        plantLabel.setIcon(scaleImageIcon(plantPicture, IMAGE_WIDTH, IMAGE_HEIGHT)); // Scale imagee
        Image plantPictureImage = plantPicture.getImage();
        Image scaledPlantPictureImage = plantPictureImage.getScaledInstance(200,200, Image.SCALE_SMOOTH);
        // ImageIcon scaledPlantPictureIcon = new ImageIcon(scaledPlantPictureImage); //Oklart om detta behövs //Cyrus
        add(plantLabel);

        healthBar = new JProgressBar(0, 300); // progress bar for health
        healthBar.setStringPainted(true); // display the health value as text
        add(healthBar);

        /*agingLabel = new JLabel("Aging : Young"); //Label for aging info
        add(agingLabel);*/

        plantLevelLabel = new JLabel("Plant Level: ");
        add(plantLevelLabel);
    }


    public void updatePlantLevelLabel(int plantLevel){
        plantLevelLabel.setText("Plant Level: " + plantLevel);
    }

    /*public void updateAgingLabel(Plant plant){
        long age = plant.getAge();

        if (age < Plant.YOUNG_THRESHOLD){
            agingLabel.setText("Aging: Young");
        }else if ( age < Plant.MATURE_THRESHOLD){
            agingLabel.setText("Aging: Middle-aged");
        } else {
            agingLabel.setText("Aging: Mature");
        }}*/



    //TODO: assistent added this
    public MainPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * Updates the image of the plant in the center panel.
     * @param newImage The new image to display.
     * @author Cyrus Shaerpour
     */
    public void updatePlantImage(ImageIcon newImage) {
        plantPicture = newImage;
        //System.out.println(plantPicture.toString());
        //plantLabel.setIcon(plantPicture);
        plantLabel.setIcon(scaleImageIcon(plantPicture, IMAGE_WIDTH, IMAGE_HEIGHT)); // Scale and update the image
        this.revalidate();
        this.repaint();  // Repaint the panel to update the image
    }

    /**
     * Scales the image to the size of the gui frame
     * @param imageIcon
     * @param width
     * @param height
     * @return ImageIcon(scaledImage)
     * Author Cyrus Shaerpour
     */
    private ImageIcon scaleImageIcon(ImageIcon imageIcon, int width, int height) {
        Image image = imageIcon.getImage(); // Transform ImageIcon to Image
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH); // Scale image
        return new ImageIcon(scaledImage); // Transform Image back to ImageIcon
    }

    public void updatePanel(ImageIcon plantPicture) {
        //plantLabel.setIcon(scaleImageIcon(plantPicture, IMAGE_WIDTH, IMAGE_HEIGHT)); // Scale and update the image
        //Sunflower sunflower = new Sunflower("Sunflower", PlantArt.SUNFLOWER, 0, new ImageIcon("src/Images/Sunflower3.JPG"), 0);
    }
}