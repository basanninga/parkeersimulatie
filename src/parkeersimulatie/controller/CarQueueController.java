package parkeersimulatie.controller;

import mvc.Controller;
import mvc.View;
import parkeersimulatie.Simulator;
import parkeersimulatie.model.*;

import java.util.Random;

public class CarQueueController extends Controller {

    private static final String AD_HOC = "1";
    private static final String PASS = "2";

    private CarQueue entranceCarQueue;
    private CarQueue entrancePassQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;

    private Simulator simulator;

    int weekDayArrivals = 0; // average number of arriving cars per hour
    int weekendArrivals = 0; // average number of arriving cars per hour
    int weekDayPassArrivals = 0; // average number of arriving cars per hour
    int weekendPassArrivals = 0; // average number of arriving cars per hour

    int enterSpeed = 6; // number of cars that can enter per minute
    int paymentSpeed = 7; // number of cars that can pay per minute
    int exitSpeed = 5; // number of cars that can leave per minute

    public CarQueueController(Simulator simulator) {
        this.simulator = simulator;

        entranceCarQueue = new CarQueue();
        entrancePassQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();

    }

    public void setArrivals(){
        //Het wordt druk
        if (simulator.getTimeController().getHour() > 12 && simulator.getTimeController().getHour() < 18) {
            switch (simulator.getTimeController().getDay()) {
                case 0:
                    weekDayArrivals = 100;
                    weekDayPassArrivals = 20;
                    break;
                case 1:
                    weekDayArrivals = 100;
                    weekDayPassArrivals = 20;
                    break;
                case 2:
                    weekDayArrivals = 150;
                    weekDayPassArrivals = 35;
                    break;
                case 3:
                    weekDayArrivals = 175;
                    weekDayPassArrivals = 40;
                    break;
                case 4:
                    weekDayArrivals = 225;
                    weekDayPassArrivals = 50;
                    break;
                case 5: weekendArrivals = 350;
                        weekendPassArrivals = 100;
                        break;
                case 6: weekendArrivals = 275;
                        weekendPassArrivals = 75;
                        break;

            }
        //Het wordt rustig
        }else{
            switch (simulator.getTimeController().getDay()) {
                case 0:
                    weekDayArrivals = 60;
                    weekDayPassArrivals = 15;
                    break;
                case 1:
                    weekDayArrivals = 60;
                    weekDayPassArrivals = 15;
                    break;
                case 2:
                    weekDayArrivals = 90;
                    weekDayPassArrivals = 18;
                    break;
                case 3:
                    weekDayArrivals = 125;
                    weekDayPassArrivals = 20;
                    break;
                case 4:
                    weekDayArrivals = 150;
                    weekDayPassArrivals = 35;
                    break;
                case 5: weekendArrivals = 200;
                        weekendPassArrivals = 40;
                    break;
                case 6: weekendArrivals = 60;
                        weekendPassArrivals = 15;
                    break;

            }
        }
    }

    @Override
    protected boolean event(View view, int event_id) {
        return false;
    }

    public void handleEntrance() {
        carsArriving();
        carsEntering(entrancePassQueue);
        carsEntering(entranceCarQueue);
    }

    public void handleExit() {
        carsReadyToLeave();
        carsPaying();
        carsLeaving();
    }

    public void updateViews() {
        simulator.getCarController().tick();
        // Update the car park view.
        simulator.getSimulatorFrame().updateView();
    }

    private void carsArriving() {
        int numberOfCars = getNumberOfCars(weekDayArrivals, weekendArrivals);
        addArrivingCars(numberOfCars, AD_HOC);
        numberOfCars = getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
        addArrivingCars(numberOfCars, PASS);
    }

    private void carsEntering(CarQueue queue) {
        int i = 0;
        // Remove car from the front of the queue and assign to a parking space.
        while (queue.carsInQueue() > 0 && simulator.getCarController().getNumberOfOpenSpots() > 0 && i < enterSpeed) {
            Car car = queue.removeCar();
            Location freeLocation = simulator.getCarController().getFirstFreeLocation();
            simulator.getCarController().setCarAt(freeLocation, car);
            i++;
        }
    }

    private void carsReadyToLeave() {
        // Add leaving cars to the payment queue.
        Car car = simulator.getCarController().getFirstLeavingCar();
        while (car != null) {
            if (car.getHasToPay()) {
                car.setIsPaying(true);
                paymentCarQueue.addCar(car);
            } else {
                carLeavesSpot(car);
            }
            car = simulator.getCarController().getFirstLeavingCar();
        }
    }

    private void carsPaying() {
        // Let cars pay.
        int i = 0;
        while (paymentCarQueue.carsInQueue() > 0 && i < paymentSpeed) {
            Car car = paymentCarQueue.removeCar();
            // TODO Handle payment.
            carLeavesSpot(car);
            i++;
        }
    }

    private void carsLeaving() {
        // Let cars leave.
        int i = 0;
        while (exitCarQueue.carsInQueue() > 0 && i < exitSpeed) {
            exitCarQueue.removeCar();
            i++;
        }
    }

    private int getNumberOfCars(int weekDay, int weekend) {
        Random random = new Random();

        // Get the average number of cars that arrive per hour.
        int averageNumberOfCarsPerHour = simulator.getTimeController().getDay() < 5
                ? weekDay
                : weekend;

        // Calculate the number of cars that arrive this minute.
        double standardDeviation = averageNumberOfCarsPerHour * 0.3;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        return (int) Math.round(numberOfCarsPerHour / 60);
    }

    private void addArrivingCars(int numberOfCars, String type) {
        // Add the cars to the back of the queue.
        switch (type) {
            case AD_HOC:
                for (int i = 0; i < numberOfCars; i++) {
                    entranceCarQueue.addCar(new AdHocCar());
                }
                break;
            case PASS:
                for (int i = 0; i < numberOfCars; i++) {
                    entrancePassQueue.addCar(new ParkingPassCar());
                }
                break;
        }
    }

    private void carLeavesSpot(Car car) {
        simulator.getCarController().removeCarAt(car.getLocation());
        exitCarQueue.addCar(car);
    }
}
