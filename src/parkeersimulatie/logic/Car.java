package parkeersimulatie.logic;


import java.awt.*;

public abstract class Car extends AbstractModel {

    private Location location;
    private int minutesLeft;
    private int stayMinutes;
    private boolean isPaying;
    private boolean hasToPay;

    /**
     * Constructor for objects of class Car
     */
    public Car() {
        this.stayMinutes = minutesLeft;

    }

    public Location getLocation() {
        return location;
    }

    public int getStayMinutes() {
        return stayMinutes;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getMinutesLeft() {
        return minutesLeft;
    }

    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }

    public boolean getIsPaying() {
        return isPaying;
    }

    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    public boolean getHasToPay() {
        return hasToPay;
    }

    public void setHasToPay(boolean hasToPay) {
        this.hasToPay = hasToPay;
    }

    public void tick() {
        minutesLeft--;
    }

    public abstract Color getColor();
}