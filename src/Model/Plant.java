package Model;

import java.util.ArrayList;
import java.util.List;

public class Plant {
    private String name;
    private int nbrOfLives;

    public Plant(String name) {
        this.name = name;
        nbrOfLives = 3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNbrOfLives() {
        return nbrOfLives;
    }

    public void setNbrOfLives(int nbrOfLives) {
        this.nbrOfLives = nbrOfLives;
    }
}

