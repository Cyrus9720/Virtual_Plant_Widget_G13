package Controller;
import Model.Plant;
import Model.PlantArt;
import Model.Rose;
import View.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
/**
 * The Controller class acts as the controller in the Model-View-Controller (MVC) architecture.
 * It handles user inputs from the view and updates the model accordingly.
 */
public class Controller
{
    private MainFrame view;// Reference to the main view
    private List<Plant> plantList; // List to store plant objects
    private SouthPanel southPanel = new SouthPanel(this, 1200, 1000); // Reference to the south panel
    private Plant plant;

    /**
     * Constructs a new Controller object.
     * Initializes the main view and plant list.
     * @author annagranberg
     */
    public Controller()
    {
        createPlant(PlantArt.Rose);
        view = new MainFrame(this);
        plantList = new ArrayList<>();
    }

    /**
     * Handles button press events from the view.
     * @param button The type of button pressed.
     * @author annagranberg
     */

    public void buttonPressed(ButtonType button)
    {
        switch (button)
        {
            case Water:


        }
    }

    /**
     * Creates plants
     * @param plantArt The type of plant art
     *
     * @author annagranberg
     */

    public void createPlant(PlantArt plantArt)
    {
       /* if(plantArt == PlantArt.Tomato_Plant)
       {
            Plant tomatoPlant = new Plant("Tomato Plant", plantArt);
            plantList.add(tomatoPlant);
        }else if( plantArt == PlantArt.Cactus)
        {
            Plant cactus = new Plant("Cactus", PlantArt.Cactus);
            plantList.add(cactus);
        } else if (plantArt == PlantArt.Rose)
         {
            Plant redRose = new Plant("Red rose", PlantArt.Rose);
            plantList.add(redRose);
        } else if (plantArt == PlantArt.Orchid)
        {
            Plant orchid = new Plant("Orchid", PlantArt.Orchid);
            plantList.add(orchid);
        }else if(plantArt == PlantArt.Sunflower)
        {
            Plant sunflower = new Plant("Sunflower", PlantArt.Sunflower);
        }*/

        Plant rose = new Rose("Rose", 0, 0);
        rose.setPlantPicture(new ImageIcon("src/Images/rose.png"));
        plantList.add(rose);
        plant = rose; // Initialize the 'plant' variable with the created rose object
    }

    public ImageIcon getImageIcon()
    {
        return plant.getPlantPicture();
    }
}
