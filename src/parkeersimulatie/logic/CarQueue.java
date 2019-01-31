package parkeersimulatie.logic;



import java.util.LinkedList;
import java.util.Queue;

public class CarQueue extends AbstractModel {
    private Queue<Car> queue = new LinkedList<>();

    /**
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
    public int carsInQueue() {
        return queue.size();
    }
}
