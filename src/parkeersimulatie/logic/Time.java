package parkeersimulatie.logic;


import java.util.Objects;

public class Time extends AbstractModel {

    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    public Time() {

    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public void advanceTime() {
        // Advance the time by one minute.
        minute++;
        while (minute > 59) {
            minute -= 60;
            hour++;
        }
        while (hour > 23) {
            hour -= 24;
            day++;
        }
        while (day > 6) {
            day -= 7;
        }

        System.out.println(day + " " + hour + ":" + minute);


    }

    public String getClock(){
        return hour + ":" + minute;
    }
}