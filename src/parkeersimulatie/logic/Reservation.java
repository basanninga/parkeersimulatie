package parkeersimulatie.logic;

import java.util.Random;
import java.awt.*;

public class Reservation extends Car {
    private static final Color COLOR = Color.green;

    private int stayMinutes;

    public Reservation() {
        Random random = new Random();
        stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
    }

    public int getStayMinutes(){
        return stayMinutes;
    }

    public Color getColor() {
        return COLOR;
    }
}
