package parkeersimulatie.logic;


import java.util.Random;
import java.awt.*;


/**
 * Class ParkingPassCar
 *
 * @author Bas Anninga, Victor Vrancianu, Jens Slauerhoff
 * @version 1.0
 */
public class ParkingPassCar extends Car {
    private static final Color COLOR = Color.blue;
    private int stayMinutes;

    /**
     Constructor voor de ParkingPassCar.
     */
    public ParkingPassCar() {
        Random random = new Random();
        stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
    }

    /**
     Geeft de minuten terug hoeveel minuten de passcar blijft
     @return int
     */
    public int getStayMinutes(){
        return stayMinutes;
    }



    /**
     * Return kleur van Object Color
     *
     * @return Color
     */
    public Color getColor() {
        return COLOR;
    }
}
