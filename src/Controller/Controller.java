package Controller;
import Model.Plant;
import Model.PlantArt;
import View.*;

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

    /**
     * Constructs a new Controller object.
     * Initializes the main view and plant list.
     * @author annagranberg
     */
    public Controller()
    {
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
    
}
