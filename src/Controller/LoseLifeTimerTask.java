package Controller;

import Model.Plant;


import java.time.Duration;
import java.time.LocalDateTime;

import java.util.Timer;
import java.util.TimerTask;

public class LoseLifeTimerTask extends TimerTask {
    private Controller controller;
    private Timer timer;
    private Duration timeSinceLastWatered;
    public LoseLifeTimerTask(Controller controller, Timer timer) {
        this.controller = controller;
        this.timer = timer;
    }

    @Override
    public void run() {
        Plant currentPlant = controller.getCurrentPlant();

        if (currentPlant.getLastWatered() != null) { // Kontrollera om lastWatered är null
            // Kontrollera om växten behöver vattnas
            timeSinceLastWatered = Duration.between(currentPlant.getLastWatered(), LocalDateTime.now());
            System.out.println(timeSinceLastWatered.toHours());
            setTimeSinceLastWatered(timeSinceLastWatered);
            if (timeSinceLastWatered.compareTo(currentPlant.getWateringInterval()) >= 0) {
                currentPlant.decreaseLife(); // Minska växten med ett liv
            }
        } else {
            // Hantera fallet när lastWatered är null
            System.out.println("Last watered time is null");
        }

        // Avbryt den tidigare schemalagda uppgiften
        timer.cancel();
        timer.purge();

        // Schemalägg nästa kontroll
        timer.schedule(new LoseLifeTimerTask(controller, timer), currentPlant.getWateringInterval().toMillis());
        controller.getView().getEastPanel().updateDeathTimer(timeSinceLastWatered);
    }

    public Duration getTimeSinceLastWatered() {
        return timeSinceLastWatered;
    }

    public void setTimeSinceLastWatered(Duration timeSinceLastWatered) {
        this.timeSinceLastWatered = timeSinceLastWatered;
    }
}
