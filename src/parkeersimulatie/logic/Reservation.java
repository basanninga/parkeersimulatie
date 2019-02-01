package parkeersimulatie.logic;

import java.util.Random;
import java.awt.*;

public class Reservation extends Car {
    private static final Color COLOR = Color.green;

    private int stayMinutes;

    /**
     Constructor voor reservation.
     */
    public Reservation() {
        Random random = new Random();
        stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
    }

    /**
     Geeft de minuten terug hoeveel minuten de reservatie blijft
     @return int
     */
    public int getStayMinutes(){
        return stayMinutes;
    }

    /**
     Geeft de kleur terug
     @return COLOR
     */
    public Color getColor() {
        return COLOR;
    }
}
