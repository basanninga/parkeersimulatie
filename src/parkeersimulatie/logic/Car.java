package parkeersimulatie.logic;


import java.awt.*;

public abstract class Car extends AbstractModel {

    private Location location;
    private int minutesLeft;
    private int stayMinutes;
    private boolean isPaying;
    private boolean hasToPay;

    /**
     Constructor die de stayminutes vult met minutes left wanneer de constructor aangeroepen wordt
     */
    public Car() {
        this.stayMinutes = minutesLeft;

    }

    /**
     Geeft de locatie terug
     @return location
     */
    public Location getLocation() {
        return location;
    }

    /**
     Geeft de stayMinutes terug
     @return int
     */
    public int getStayMinutes() {
        return stayMinutes;
    }

    /**
     * @param location
     Zet de locatie
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     Geeft de minutesLeft terug
     @return int
     */
    public int getMinutesLeft() {
        return minutesLeft;
    }

    /**
     * @param minutesLeft
     Zet de minutesleft
     */
    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }

    /**
     Geeft een boolean terug om te kijken of iemand aan het betalen is
     @return boolean
     */
    public boolean getIsPaying() {
        return isPaying;
    }

    /**
     * @param isPaying
     Zet of iemand aan het betalen is
     */
    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    /**
     Geeft een boolean terug om te kijken of die nog moet betalen
     @return boolean
     */
    public boolean getHasToPay() {
        return hasToPay;
    }

    /**
     * @param hasToPay
     * Zet dat iemand moet betalen
     */
    public void setHasToPay(boolean hasToPay) {
        this.hasToPay = hasToPay;
    }

    /**
     Haalt 1 minuut van minutesleft af
     */
    public void tick() {
        minutesLeft--;
    }

    /**
     Geeft de kleur terug
     @return Color
     */
    public abstract Color getColor();

}