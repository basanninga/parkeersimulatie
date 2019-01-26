package parkeersimulatie;

import parkeersimulatie.controller.CarController;
import parkeersimulatie.controller.CarQueueController;
import parkeersimulatie.controller.TimeController;

public class Simulator {

    private SimulatorFrame simulatorFrame;

    private CarQueueController simulatorController;
    private CarController carController;
    private TimeController timeController;


    private int tickPause = 100;

    public Simulator() {
        carController = new CarController(3, 6, 30);
        simulatorFrame = new SimulatorFrame(this);
        timeController = new TimeController();

        simulatorController = new CarQueueController(this);
    }

    public SimulatorFrame getSimulatorFrame() {
        return simulatorFrame;
    }

    public CarController getCarController() {
        return carController;
    }

    public TimeController getTimeController() {
        return timeController;
    }


    public void run() {
        for (int i = 0; i < 10000; i++) {
            tick();
        }
    }

    private void tick() {
        timeController.advanceTime();
        simulatorController.handleExit();
        simulatorController.updateViews();
        // Pause.
        try {
            Thread.sleep(tickPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        simulatorController.handleEntrance();
    }


}