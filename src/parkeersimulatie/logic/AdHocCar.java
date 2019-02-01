package parkeersimulatie.logic;

import java.util.Random;
import java.awt.*;

/**
 * Class AbstractView
   In deze klasse wordt een normale auto gemaakt.
 * hij geeft de auto een bepaalde tijd mee om in de parkeergarage te parkeren.
 * De klasse geeft de auto de kleur rood mee
 * @author Bas Anninga, Victor Vrancianu, Jens Slauerhoff
 * @version 1.0
 */

public class AdHocCar extends Car {
    private static final Color COLOR = Color.red;
    private int stayMinutes;

    /**
     * Constructor van AsHocCar
     */

    public AdHocCar() {
        Random random = new Random();
        stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
    }

    /**
     * de methode returnt de aantal minuten
     *
     * @return int
     */

    public int getStayMinutes(){
        return stayMinutes;
    }

    /**
     * Geeft de kleur mee
     *
     * @return Color
     */

    public Color getColor() {
        return COLOR;
    }
}
