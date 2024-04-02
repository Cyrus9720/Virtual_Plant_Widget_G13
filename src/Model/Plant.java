package Model;

public class Plant {
    private String name;
    private int nbrOfLives;
    private PlantArt plantArt;

    public Plant(String name, PlantArt plantArt) {
        this.name = name;
        this.plantArt = plantArt;
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

