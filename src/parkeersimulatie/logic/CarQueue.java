package parkeersimulatie.logic;

import java.util.LinkedList;
import java.util.Queue;


/**
 * Class CarQueue
 *
 * @author Bas Anninga, Victor Vrancianu, Jens Slauerhoff
 * @version 1.0
 */
public class CarQueue extends AbstractModel {
    private Queue<Car> queue = new LinkedList<>();

    /**
     * @param car
     * Maakt een auto aan
     * @return een boolean
     */
    public boolean addCar(Car car) {
        return queue.add(car);
    }


    /**
     * Verwijderd een car
     * @return poll als object car
     */
    public Car removeCar() {
        return queue.poll();
    }


    /**
     * @return int grootte van de queue
     */
    public Car getCar() {
        return queue.peek();
    }

    public int carsInQueue() {
        return queue.size();
    }
}
