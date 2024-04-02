package Controller;
import Model.Plant;
import View.*;

import java.util.ArrayList;
import java.util.List;

public class Controller
{
    private MainFrame view;
    private List<Plant> plantList;
    private SouthPanel southPanel = new SouthPanel(this, 1200, 1000);
    public Controller()
    {
        view = new MainFrame(this);
        plantList = new ArrayList<>();
    }


    public void buttonPressed(ButtonType button)
    {
        switch (button)
        {
            case Water:


        }
    }
}
