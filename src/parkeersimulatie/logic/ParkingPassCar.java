package parkeersimulatie.logic;


import java.util.Random;
import java.awt.*;

public class ParkingPassCar extends Car {
    private static final Color COLOR = Color.blue;

    /**
     Constructor voor de ParkingPassCar.
     */
    public ParkingPassCar() {
        Random random = new Random();
        int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }

    /**
     * Return kleur van Object Color
     *
     * @return Kleur
     */
    public Color getColor() {
        return COLOR;
    }
}
