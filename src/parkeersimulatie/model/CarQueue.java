package parkeersimulatie.model;

import mvc.Model;

import java.util.LinkedList;
import java.util.Queue;

public class CarQueue extends Model {
    private Queue<Car> queue = new LinkedList<>();

    public boolean addCar(Car car) {
        return queue.add(car);
    }

    public Car removeCar() {
        return queue.poll();
    }

    public int carsInQueue() {
        return queue.size();
    }
}
