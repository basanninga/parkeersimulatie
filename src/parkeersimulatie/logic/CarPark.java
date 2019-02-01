package parkeersimulatie.logic;


import java.awt.*;
import java.util.Random;

public final class CarPark extends AbstractModel {

    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private int numberOfOpenSpots;
    private int passNumberOfOpenSpots;
    private int reservationNumberOfOpenSpots;
    private Car[][][] cars;

    private Time time;

    private static final String AD_HOC = "1";
    private static final String PASS = "2";
    private static final String RESERVATION = "3";

    private static int totalMinutes;
    private static int stayMinutes;

    private CarQueue entranceCarQueue;
    private CarQueue entrancePassQueue;
    private CarQueue entranceReservationQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;

    int weekDayArrivals = 0; // average number of arriving cars per hour
    int weekendArrivals = 0; // average number of arriving cars per hour
    int weekDayPassArrivals = 0; // average number of arriving cars per hour
    int weekendPassArrivals = 0; // average number of arriving cars per hour
    int weekDayReservationArrivals = 0; // average number of arriving cars per hour
    int weekendReservationArrivals = 0;

    private static int enterSpeed = 10; // number of cars that can enter per minute
    private static int paymentSpeed = 7; // number of cars that can pay per minute
    private static int exitSpeed = 15; // number of cars that can leave per minute

    private int adhocCarsPass;
    private int passCar;
    private int reservation;


    /**
     * @param numberOfFloors, numberOfRows, numberOfPlaces en time
     *
     Constructor van CarPark
     @param numberOfFloors, numberOfRows, numberOfPlaces en time
     */
    public CarPark (int numberOfFloors, int numberOfRows, int numberOfPlaces, Time time){

        this.time = time;


        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;
        this.reservationNumberOfOpenSpots = numberOfPlaces*2;
        this.passNumberOfOpenSpots = numberOfPlaces*4;
        this.numberOfOpenSpots = 2 * 6 * numberOfPlaces;

        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];

        entranceCarQueue = new CarQueue();
        entrancePassQueue = new CarQueue();
        entranceReservationQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();

    }

    /**
     @param i
     *   Zet snelheid van inkomende auto's
     * @param i Integer
     */
    public static void setEnterSpeed(int i){
        enterSpeed = i;
    }

    /**
     * @param i
     *   Zet snelheid van betalende klanten
     * @param i Integer
     */
    public static void setPaymentSpeed(int i){
        paymentSpeed = i;
    }

    /**
     * @param i
     *   Zet snelheid van uitrijdende klanten
     */
    public static void setExitSpeed(int i){
        exitSpeed = i;
    }

    /**
     * @param location Object Location
     * Pakt de autos van een bepaalde locatie
     @return Car
     */
    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return cars[location.getFloor()][location.getRow()][location.getPlace()];
    }

    /**
     * @param location, car
     * Zet een car op een locatie
     * @return boolean
     */
    public boolean setCarAt(Location location, Car car) {
        if (!locationIsValid(location)) {
            return false;
        }
        Car oldCar = getCarAt(location);
        if (oldCar == null) {
            cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
            car.setLocation(location);
            if (car.getColor() == Color.red ) {
                numberOfOpenSpots--;
            }
            else if(car.getColor() == Color.blue){
                passNumberOfOpenSpots--;
            }
            else if (car.getColor() == Color.green) {
                reservationNumberOfOpenSpots--;
            }

            return true;
        }
        return false;
    }


    /**
     *   @return De lengte van de rij bij de ingang
     */
    public int getEntranceCarQueue(){

        return entranceCarQueue.carsInQueue();
    }

    /**
     *   @return De lengte van de rij bij de betaalautomaat
     */
    public int getPaymentCarQueue(){

        return paymentCarQueue.carsInQueue();
    }

    /**
     *   @return De lengte van de rij bij de uitgang
     */
    public int getExitCarQueue(){

        return exitCarQueue.carsInQueue();
    }

    /**
     *   @return De lengte van de rij bij de ingang
     */
    public int getEntrancePassCarQueue(){
        return entrancePassQueue.carsInQueue();
    }

    /**
     *   De lengte van de rij bij de ingang van
     *   @return int
     */
    public int getEntranceReservationQueue(){
        return entranceReservationQueue.carsInQueue();
    }


    /**
     *   @param location
     *   Verwijderd een auto bij een locatie
     *   @return car
     */
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
        if (car.getColor() == Color.red ) {
            numberOfOpenSpots++;
        }
        else if(car.getColor() == Color.blue){
            passNumberOfOpenSpots++;
        }
        else if (car.getColor() == Color.green) {
            reservationNumberOfOpenSpots++;
        }

        return car;
    }
    /**
     *   Pakt de eerste vrije plek voor abonnementhouders
     *   @return location
     */
    public Location getFirstReservationFreeLocation() {

        for (int floor = 2; floor < getNumberOfFloors(); floor++) {
            for (int row = 4; row < getNumberOfRows(); row++) {
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

    public Location getFirstPassFreeLocation() {

        for (int floor = 2; floor < getNumberOfFloors(); floor++) {
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

    /**
     *   Pakt de eerste vrije plek
     *   @return location
     */
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


    /**
     *   Pakt de eerste auto die weg kan die niet betaald heeft
     *   @return car
     */
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
        return floor >= 0 && floor < numberOfFloors && row >= 0 && row <= numberOfRows && place >= 0 && place <= numberOfPlaces;
    }

    public void setArrivals() {
        //Het wordt druk
        if (time.getHour() > 12 && time.getHour() < 18) {
            switch (time.getDay()) {
                case 0:
                    weekDayArrivals = 100;
                    weekDayPassArrivals = 40;
                    weekDayReservationArrivals = 25;
                    break;
                case 1:
                    weekDayArrivals = 100;
                    weekDayPassArrivals = 40;
                    weekDayReservationArrivals = 25;
                    break;
                case 2:
                    weekDayArrivals = 150;
                    weekDayPassArrivals = 40;
                    weekDayReservationArrivals = 25;
                    break;
                case 3:
                    weekDayArrivals = 175;
                    weekDayPassArrivals = 40;
                    weekDayReservationArrivals = 25;
                    break;
                case 4:
                    weekDayArrivals = 200;
                    weekDayPassArrivals = 50;
                    weekDayReservationArrivals = 30;
                    break;
                case 5:
                    weekendArrivals = 225;
                    weekendPassArrivals = 85;
                    weekendReservationArrivals = 30;
                    break;
                case 6:
                    weekendArrivals = 200;
                    weekendPassArrivals = 50;
                    weekendReservationArrivals = 30;
                    break;

            }
            //Het wordt rustig
        } else {
            switch (time.getDay()) {
                case 0:
                    weekDayArrivals = 60;
                    weekDayPassArrivals = 30;
                    weekDayReservationArrivals = 20;
                    break;
                case 1:
                    weekDayArrivals = 60;
                    weekDayPassArrivals = 30;
                    weekDayReservationArrivals = 20;
                    break;
                case 2:
                    weekDayArrivals = 90;
                    weekDayPassArrivals = 30;
                    weekDayReservationArrivals = 20;
                    break;
                case 3:
                    weekDayArrivals = 125;
                    weekDayPassArrivals = 30;
                    weekDayReservationArrivals = 20;
                    break;
                case 4:
                    weekDayArrivals = 150;
                    weekDayPassArrivals = 35;
                    weekDayReservationArrivals = 20;
                    break;
                case 5:
                    weekendArrivals = 175;
                    weekendPassArrivals = 40;
                    weekendReservationArrivals = 20;
                    break;
                case 6:
                    weekendArrivals = 60;
                    weekendPassArrivals = 15;
                    weekendReservationArrivals = 10;
                    break;

            }
        }
    }


    public void handleEntrance() {
        carsArriving();
        carsEntering(entrancePassQueue);
        carsEntering(entranceCarQueue);
        carsEntering(entranceReservationQueue);
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
        numberOfCars = getNumberOfCars(weekDayReservationArrivals, weekendReservationArrivals);
        addArrivingCars(numberOfCars, RESERVATION);

    }

    private void carsEntering(CarQueue queue) {
        int i = 0;
        // Remove car from the front of the queue and assign to a parking space.


        if(queue.carsInQueue() >0 ) {

            Car firstCar = queue.getCar();

            if (firstCar.getColor() == Color.green ) {
                while ((queue.carsInQueue() > 0 && getReservationNumberOfOpenSpots() > 0 && i < enterSpeed)) {

                    Car car = queue.removeCar();
                    Location freeLocation = getFirstReservationFreeLocation();
                    setCarAt(freeLocation, car);
                    i++;
                    stayMinutes = (stayMinutes + car.getStayMinutes());

                }
            }
            else if (firstCar.getColor() == Color.red ) {
                while ((queue.carsInQueue() > 0 && getNumberOfOpenSpots() > 0 && i < enterSpeed)) {

                    Car car = queue.removeCar();
                    Location freeLocation = getFirstFreeLocation();
                    setCarAt(freeLocation, car);
                    i++;
                    stayMinutes = (stayMinutes + car.getStayMinutes());

                }
            }
            else if (firstCar.getColor() == Color.blue) {
                while ((queue.carsInQueue() > 0 && getPassNumberOfOpenSpots() > 0 && i < enterSpeed)) {

                    Car car = queue.removeCar();
                    Location freeLocation = getFirstPassFreeLocation();
                    setCarAt(freeLocation, car);
                    i++;
                    stayMinutes = (stayMinutes + car.getStayMinutes());

                }
            }
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
            if (car.getColor() == Color.red) {
                adhocCarsPass--;
            }
            else if (car.getColor() == Color.blue) {
                passCar--;
            }
            else if(car.getColor() == Color.green){
                reservation--;
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
            case RESERVATION:
                for (int i = 0; i < numberOfCars; i++) {
                    entranceReservationQueue.addCar(new Reservation());
                    reservation++;
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

    public int getPassNumberOfOpenSpots(){
        return passNumberOfOpenSpots;
    }

    public int getReservationNumberOfOpenSpots() {
        return reservationNumberOfOpenSpots;
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

    public int getReservationCar() {
        return reservation;
    }

}