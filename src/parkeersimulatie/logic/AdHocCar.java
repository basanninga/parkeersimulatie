package parkeersimulatie.logic;

import java.util.Random;
import java.awt.*;

public class AdHocCar extends Car {
    private static final Color COLOR = Color.red;
    private int stayMinutes;

    public AdHocCar() {
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
