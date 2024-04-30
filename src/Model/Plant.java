package Model;

import javax.swing.ImageIcon;
import java.util.Timer;
import java.util.TimerTask;

public class Plant {
    private String name;
    private int nbrOfLives;
    private int timesWatered;
    private ImageIcon plantPicture;
    private int plantLevel;
    private PlantArt plantArt;
    private Timer timer;

    private long creationTime;

    private long timeSinceLastWatering; // Variable to track time since last watering
    private static final long WATERING_iNTERVAL = 60000; //Interval for watering in milliseconds (every 60 sec)

    private static final int THRESHOLD = 1000; // Threshold value for determining life stage in milliseconds

    public static final long YOUNG_THRESHOLD = 360; // Threshold for young age in seconds (e.g., 1 hour)
    public static final long MATURE_THRESHOLD = 864;



    /**
     * Constructor for Plant
     * @param name Name of the plant
     * @param plantArt Art of the plant
     * @param timesWatered Number of times the plant has been watered
     * @param plantPicture Picture of the plant
     * @param plantLevel Level of the plant
     * @author Cyrus Shaerpour
     */
    public Plant(String name, PlantArt plantArt, int timesWatered, ImageIcon plantPicture, int plantLevel) {
        this.name = name;
        this.plantArt = plantArt;
        nbrOfLives = 3;
        this.timesWatered = timesWatered;
        this.plantPicture = plantPicture;
        this.plantLevel = plantLevel;

        System.out.println("Plant created");

        timeSinceLastWatering = 0; //Time since last watering to 0

        startHealthDecrementTimer();
    }

    public long getAge(){
        return System.currentTimeMillis() - creationTime;
    }

    private void startHealthDecrementTimer(){
        timer= new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                decrementHealth();
            }
        }, 1000,1000);
    }

    public void stopHealthDecrementTimer(){
        if ( timer != null){
            timer.cancel();
            timer.purge();
        }
    }

    private void decrementHealth(){
        int healthDecrease = calculateHealthDecrease();

        if (plantLevel < 3){
            nbrOfLives -= healthDecrease;
            if (nbrOfLives <= 0){
                stopHealthDecrementTimer();
            }
        }
    }

    private int calculateHealthDecrease(){
        int healthDecrease = 1;
        return healthDecrease;

        System.out.println("Plant created" + name);

    }

    /**
     * Method for watering the plant and increasing the plant level
     * If the plant is not fully grown, increase the plant level
     * @return void
     * @author Cyrus Shaerpour
     */
    public void waterPlant() {
        setTimesWatered(getTimesWatered() + 1);
        if (plantLevel < 3) {
            if (getTimesWatered() == plantLevel + 1) {
                setPlantLevel(getPlantLevel() + 1);
                setTimesWatered(0);
                //System.out.println("Plant level " + plantLevel);
                if (plantLevel == 3) {
                    System.out.println("Plant is fully grown");
                }
            }
        }
    }


    //@TODO: Lägg till javadocs efterhand när metoderna börjar användas.

    public void update(long elapsedTime){
        timeSinceLastWatering += elapsedTime; //Update time since last watering

        if (timeSinceLastWatering >= WATERING_iNTERVAL){ //to check if enough time has passed since the last watering
            waterPlant();

            timeSinceLastWatering = 0; // reset the timer
        }
    }
    public String getName() {

    public String getPlantName() {

        return name;
    }

    public void setPlantName(String name) {
        this.name = name;
    }

    public int getNbrOfLives() {
        return nbrOfLives;
    }

    public void setNbrOfLives(int nbrOfLives) {
        this.nbrOfLives = nbrOfLives;
    }

    /**
     * Method for getting the number of times the plant has been watered
     * @return int Number of times the plant has been watered
     * @author Cyrus Shaerpour
     */
    public int getTimesWatered() {
        return timesWatered;
    }

    /**
     * Method for setting the number of times the plant has been watered
     * @param timesWatered Number of times the plant has been watered
     * @return void
     * @author Cyrus Shaerpour
     */
    public void setTimesWatered(int timesWatered) {
        this.timesWatered = timesWatered;
    }

    /**
     * Method for getting the picture of the plant
     * @return ImageIcon Picture of the plant
     * @author Cyrus Shaerpour
     */
    public ImageIcon getPlantPicture() {
        return plantPicture;
    }

    /**
     * Method for setting the picture of the plant
     * @param plantPicture Picture of the plant
     * @return void
     * @author Cyrus Shaerpour
     */
    public void setPlantPicture(ImageIcon plantPicture) {
        this.plantPicture = plantPicture;
    }

    /**
     * Method for getting the level of the plant
     * @return int Level of the plant
     * @author Cyrus Shaerpour
     */
    public int getPlantLevel() {
        System.out.println(name + " Plant level " + (plantLevel));
        return plantLevel;
    }


    /**
     * Method for setting the level of the plant
     * @param plantLevel Level of the plant
     * @return void
     * @author Cyrus Shaerpour
     */
    public void setPlantLevel(int plantLevel) {
        this.plantLevel = plantLevel;
    }

    public void updatePlantLevel(int newPLantLevel){
        setPlantLevel(newPLantLevel);
    }

    public String toString(){
        String textOut = String.format("Plant art: %s | Plant name: %s | Plant level: %s | Times watered: %s | Number of lives: %s | Plant picture: %s", plantArt, name, plantLevel, timesWatered, nbrOfLives, plantPicture);
        return textOut;
    }
}}
