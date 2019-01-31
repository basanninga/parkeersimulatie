package parkeersimulatie.logic;


import javax.swing.*;
import java.awt.*;
import java.util.Random;

public final class CarPark extends AbstractModel {



    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private int numberOfOpenSpots;
    private Car[][][] cars;

    private Time time;

    private static final String AD_HOC = "1";
    private static final String PASS = "2";

    private static int totalMinutes;
    private static int stayMinutes;

    private CarQueue entranceCarQueue;
    private CarQueue entrancePassQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;



    int weekDayArrivals = 0; // average number of arriving cars per hour
    int weekendArrivals = 0; // average number of arriving cars per hour
    int weekDayPassArrivals = 0; // average number of arriving cars per hour
    int weekendPassArrivals = 0; // average number of arriving cars per hour

    int enterSpeed = 6; // number of cars that can enter per minute
    int paymentSpeed = 7; // number of cars that can pay per minute
    int exitSpeed = 5; // number of cars that can leave per minute

    public int adhocCarsPass;
    public int passCar;

    public CarPark (int numberOfFloors, int numberOfRows, int numberOfPlaces, Time time){



        this.time = time;


        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;
        this.numberOfOpenSpots = numberOfFloors * numberOfRows * numberOfPlaces;
        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];

        entranceCarQueue = new CarQueue();
        entrancePassQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();

    }

    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return cars[location.getFloor()][location.getRow()][location.getPlace()];
    }

    public boolean setCarAt(Location location, Car car) {
        if (!locationIsValid(location)) {
            return false;
        }
        Car oldCar = getCarAt(location);
        if (oldCar == null) {
            cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
            car.setLocation(location);
            numberOfOpenSpots--;
            return true;
        }
        return false;
    }

    public Car removeCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        Car car = getCarAt(location);
        if (car == null) {
            return null;
        }
        cars[location.getFloor()][location.getRow()][location.getPlace()] = null;
        car.setLocation(null);
        numberOfOpenSpots++;
        return car;
    }

    public Location getFirstFreeLocation() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    if (getCarAt(location) == null) {
                        return location;
                    }
                }
            }
        }
        return null;
    }



    public Car getFirstLeavingCar() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying()) {
                        return car;
                    }
                }
            }
        }
        return null;
    }

    public void tick() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null) {
                        car.tick();
                    }
                }
            }
        }
    }

    private boolean locationIsValid(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        if (floor < 0 || floor >= numberOfFloors || row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces) {
            return false;
        }
        return true;
    }

    public void setArrivals(){
        //Het wordt druk
        if (time.getHour() > 12 && time.getHour() < 18) {
            switch (time.getDay()) {
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
            switch (time.getDay()) {
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
        tick();
        // Update the car park view.
        super.notifyViews();
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
        while (queue.carsInQueue() > 0 && getNumberOfOpenSpots() > 0 && i < enterSpeed) {
            Car car = queue.removeCar();
            Location freeLocation = getFirstFreeLocation();
            setCarAt(freeLocation, car);
            i++;
            stayMinutes = (stayMinutes + car.getStayMinutes());

        }
    }

    private void carsReadyToLeave() {
        // Add leaving cars to the payment queue.
        Car car = getFirstLeavingCar();
        while (car != null) {
            if (car.getHasToPay()) {
                car.setIsPaying(true);
                paymentCarQueue.addCar(car);
            } else {
                carLeavesSpot(car);
            }
            car = getFirstLeavingCar();
        }
    }

    private void carsPaying() {
        // Let cars pay.
        int i = 0;
        while (paymentCarQueue.carsInQueue() > 0 && i < paymentSpeed) {
            Car car = paymentCarQueue.removeCar();
            totalMinutes = (totalMinutes + car.getStayMinutes());
            // TODO Handle payment.
            carLeavesSpot(car);
            i++;
        }
    }

    private void carsLeaving() {
        // Let cars leave.
        int i = 0;
        while (exitCarQueue.carsInQueue() > 0 && i < exitSpeed) {
            Car car = exitCarQueue.removeCar();
            totalMinutes = (totalMinutes + car.getStayMinutes());
            if(car.getColor() == Color.red){
                adhocCarsPass--;
            }
            if(car.getColor() == Color.blue){
                passCar--;
            }
            i++;
        }
    }

    private int getNumberOfCars(int weekDay, int weekend) {
        Random random = new Random();

        // Get the average number of cars that arrive per hour.
        int averageNumberOfCarsPerHour = time.getDay() < 5
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
                    adhocCarsPass++;
                }
                break;
            case PASS:
                for (int i = 0; i < numberOfCars; i++) {
                    entrancePassQueue.addCar(new ParkingPassCar());
                    passCar++;
                }
                break;
        }
    }

    private void carLeavesSpot(Car car) {
        removeCarAt(car.getLocation());
        exitCarQueue.addCar(car);
    }


    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public int getNumberOfOpenSpots() {
        return numberOfOpenSpots;
    }

    public Time getTime() {
        return time;
    }

    public static void setTotalMinutes(int totalMinutes) {
        CarPark.totalMinutes = totalMinutes;
    }

    public int getTotalMinutes() {
        return totalMinutes;
    }

    public static void setStayMinutes(int stayMinutes) {
        CarPark.stayMinutes = stayMinutes;
    }

    public int getStayMinutes() {
        return stayMinutes;
    }

    public int getAdhocCarsPass() {
        return adhocCarsPass;
    }

    public int getPassCar() {
        return passCar;
    }












}