package Model;

import Controller.Controller;

import javax.swing.*;
import java.time.LocalDateTime;

public class TomatoPlant extends Plant{

    private Controller controller;

    /**
     * Constructor for TomatoPlant
     * @param controller controller instantiation
     * @param name Name of the plant
     * @param plantArt Art of the plant
     * @param nbrOfLives Amount of Starting Lives for the plant
     * @param timesWatered Amount of Times the plant has been watered
     * @param plantPicture Picture of the plant
     * @param plantLevel Level of the plant
     * @param lastWatered Last Time and Date the plant was watered
     * @param deathTime Time and Date of the plant when to reduce/kill the plant
     * @author Cyrus Shaerpour
     */
    public TomatoPlant(Controller controller, String name, PlantArt plantArt, int nbrOfLives, int timesWatered, ImageIcon plantPicture, int plantLevel, LocalDateTime lastWatered, LocalDateTime deathTime) {
        super(controller, name, plantArt, nbrOfLives,timesWatered, plantPicture, plantLevel, lastWatered, deathTime);
        this.controller = controller;
    }

    /**
     * Setting the plant level
     * @param plantLevel Level of the plant
     * @author Cyrus Shaerpour
     */
    @Override
    public void setPlantLevel(int plantLevel) {
        super.setPlantLevel(plantLevel);
        updateImage();
    }

    /**
     * Setting the number of lives
     * @param nbrOfLives Number of lives
     * @author Cyrus Shaerpour
     */
    @Override
    public void setNbrOfLives(int nbrOfLives) {
        super.setNbrOfLives(nbrOfLives);
        updateImage();
    }

    /**
     * Update the image of the plant
     * @author Cyrus Shaerpour & Malek Borovnjak
     */
    @Override
    public void updateImage() {
        switch (getPlantLevel()) {
            case 0:
                if(controller.night){
                    setPlantPicture(new ImageIcon("src/Images/Night_Empty.JPG"));
                    break;
                } else{
                    setPlantPicture(new ImageIcon("src/Images/PotArt1.JPG"));
                    break;
                }
            case 1:
                if(controller.night){
                    if(getNbrOfLives() == 0){
                        setPlantPicture(new ImageIcon("src/Images/Night_Tomato_Dead1.JPG"));
                        break;
                    } else{
                        setPlantPicture(new ImageIcon("src/Images/Night_Tomato1.JPG"));
                        break;
                    }
                } else{
                    if(getNbrOfLives() == 0){
                        setPlantPicture(new ImageIcon("src/Images/TomatoDead1.JPG"));
                        break;
                    } else{
                        setPlantPicture(new ImageIcon("src/Images/Tomatoe1.JPG"));
                        break;
                    }
                }
            case 2:
                if(controller.night){
                    if(getNbrOfLives() == 0){
                        setPlantPicture(new ImageIcon("src/Images/Night_Tomato_Dead2.JPG"));
                        break;
                    } else{
                        setPlantPicture(new ImageIcon("src/Images/Night_Tomato2.JPG"));
                        break;
                    }
                } else{
                    if(getNbrOfLives() == 0){
                        setPlantPicture(new ImageIcon("src/Images/TomatoDead2.JPG"));
                        break;
                    } else{
                        setPlantPicture(new ImageIcon("src/Images/Tomatoe2.JPG"));
                        break;
                    }
                }
            case 3:
                if(controller.night){
                    if(getNbrOfLives() == 0){
                        setPlantPicture(new ImageIcon("src/Images/Night_Tomato_Dead3.JPG"));
                        break;
                    } else{
                        setPlantPicture(new ImageIcon("src/Images/Night_Tomato3.JPG"));
                        break;
                    }
                } else{
                    if(getNbrOfLives() == 0){
                        setPlantPicture(new ImageIcon("src/Images/TomatoDead3.JPG"));
                        break;
                    } else{
                        setPlantPicture(new ImageIcon("src/Images/Tomatoe3.JPG"));
                        break;
                    }
                }
            default:
                // Handle any other cases or provide a default image
                break;
        }
    }
}
