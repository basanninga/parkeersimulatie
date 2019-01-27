package parkeersimulatie;

import parkeersimulatie.controller.CarController;
import parkeersimulatie.controller.CarQueueController;
import parkeersimulatie.controller.TimeController;
import parkeersimulatie.view.TickView;

public class Simulator {

    private SimulatorFrame simulatorFrame;

    private CarQueueController simulatorController;
    private CarController carController;
    private TimeController timeController;
    private TickView tickView;


    public int tickPause = 50;

    public Simulator() {
        carController = new CarController(3, 6, 30);
        simulatorFrame = new SimulatorFrame(this);
        timeController = new TimeController();
        tickView = new TickView(this);



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
        while (true) {
            tick();


        }
    }

    private void tick() {
        timeController.advanceTime();
        simulatorController.setArrivals();
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
